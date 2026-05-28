from datetime import timedelta
from functools import wraps
from urllib.parse import urlparse
import os
import psycopg2
import requests
from dotenv import load_dotenv
from flask import Flask, render_template, request, redirect, url_for, session, jsonify

load_dotenv()

USUARIO_ADMIN = os.getenv("USUARIO_ADMIN")
PASSWD_ADMIN = os.getenv("PASSWD_ADMIN")
SECRET = os.getenv("SECRET")

app = Flask(__name__)
app.secret_key = SECRET
app.permanent_session_lifetime = timedelta(minutes=30)
app.config["SESSION_COOKIE_HTTPONLY"] = True
app.config["SESSION_COOKIE_SAMESITE"] = "Lax"

def get_conn():
    return psycopg2.connect(
        host=os.getenv("DB_HOST", "localhost"),
        port=os.getenv("DB_PORT", "5432"),
        dbname=os.getenv("POSTGRES_DB"),
        user=os.getenv("POSTGRES_USER"),
        password=os.getenv("POSTGRES_PASSWORD"),
    )

def login_empleado(user, passwd):
    try:
        with get_conn() as conn:
            with conn.cursor() as cur:
                cur.execute("SELECT login_empleado(%s, %s);", (user, passwd))
                res = cur.fetchone()
                if res and res[0] is True:
                    # Login correcto, obtenemos el ID real del empleado
                    cur.execute("SELECT id FROM empleados WHERE correo = %s;", (user,))
                    fila = cur.fetchone()
                    if fila:
                        return fila[0]
                return None
    except Exception as e:
        print(f"\n❌ ERROR CRÍTICO EN POSTGRESQL: {e}\n")
        return f"ERROR_DATABASE: {str(e)}"

def registrar_empleado(nombre, correo, passwd, id_sede):
    with get_conn() as conn:
        with conn.cursor() as cur:
            cur.execute(
                "SELECT registrar_empleado(%s, %s, %s, %s);",
                (nombre, correo, passwd, id_sede),
            )
            return cur.fetchone()

def login_requerido(f):
    @wraps(f)
    def wrapper(*args, **kwargs):
        if not session.get("login"):
            return redirect(url_for("login"))
        return f(*args, **kwargs)
    return wrapper

def next_url_segura(next_url):
    if not next_url:
        return False
    parsed = urlparse(next_url)
    return parsed.netloc == "" and parsed.scheme == ""

def obtener_empleado(empleado_id):
    with get_conn() as conn:
        with conn.cursor() as cur:
            cur.execute(
                "SELECT id, nombre, correo, id_sede FROM empleados WHERE id = %s;",
                (empleado_id,),
            )
            fila = cur.fetchone()
    if not fila:
        return jsonify({"error": "No existe el empleado"}), 404
    return jsonify({
        "id": fila[0],
        "nombre": fila[1],
        "correo": fila[2],
        "id_sede": fila[3],
    })

def obtener_sedes():
    try:
        respuesta = requests.get("http://info.empresa.dam.es:8055/items/sedes", timeout=4)
        respuesta.raise_for_status()
        return respuesta.json().get("data", [])
    except requests.RequestException:
        return [
            {"id": 1, "nombre": "Madrid"},
            {"id": 2, "nombre": "Toledo"},
            {"id": 3, "nombre": "Barcelona"},
        ]

@app.route("/")
@app.route("/index")
@login_requerido
def index():
    return render_template(
        "index.html",
        usuario=session.get("user"),
        roles=session.get("roles", []),
    )

@app.route("/empleados", methods=["GET"])
@login_requerido
def listar_empleados():
    if "admin" not in session.get("roles", []):
        return "Sin permisos", 403
    with get_conn() as conn:
        with conn.cursor() as cur:
            cur.execute("SELECT id, nombre, correo, id_sede FROM empleados ORDER BY id;")
            filas = cur.fetchall()
    empleados_lista = [
        {"id": f[0], "nombre": f[1], "correo": f[2], "id_sede": f[3]}
        for f in filas
    ]
    return jsonify(empleados_lista)

@app.route("/empleados/<int:empleado_id>", methods=["GET"])
@login_requerido
def empleados(empleado_id):
    if "admin" not in session.get("roles", []):
        return "Sin permisos", 403
    return obtener_empleado(empleado_id)

@app.route("/registro", methods=["GET", "POST"])
def registro():
    if request.method == "POST":
        nombre = request.form.get("nombre", "").strip()
        correo = request.form.get("email", "").strip()
        passwd = request.form.get("passwd", "")
        id_sede = request.form.get("id_sede")

        if id_sede:
            id_sede = int(id_sede)

        try:
            if registrar_empleado(nombre, correo, passwd, id_sede):
                return redirect(url_for("login"))
        except Exception:
            pass

        return render_template(
            "formulario.html",
            error="No se pudo registrar el empleado. Revisa si ya existe.",
            sedes=obtener_sedes(),
        ), 400

    return render_template(
        "formulario.html",
        sedes=obtener_sedes(),
        error="",
    )

@app.route("/login", methods=["GET", "POST"])
def login():
    error = ""
    next_url = request.args.get("next", "/")

    if request.method == "POST":
        user = request.form.get("user")
        passwd = request.form.get("passwd")

        # 1. Validación de Administrador Estático (.env)
        if user == USUARIO_ADMIN and passwd == PASSWD_ADMIN:
            session.permanent = True
            session["login"] = True
            session["user"] = user
            session["roles"] = ["admin"]
            return redirect("http://localhost:8080/DepartamentoMarketingComunicacion/vistas/portalAdmin.jsp")

        # 2. Validación de Empleado Regular
        resultado = login_empleado(user, passwd)

        if isinstance(resultado, str) and resultado.startswith("ERROR_DATABASE"):
            error = f"Fallo en Base de Datos: {resultado.replace('ERROR_DATABASE: ', '')}"
        elif resultado:
            session.permanent = True
            session["login"] = True
            session["user"] = user
            session["idEmpleado"] = resultado
            session["roles"] = ["empleado"]
            return redirect(
                "http://localhost:8080/DepartamentoMarketingComunicacion/IniciarSesionControlador?idEmpleado=" + str(resultado)
            )
        else:
            error = "Usuario o contraseña incorrectos"

    return render_template("login.html", error=error, next_url=next_url)

@app.route("/logout")
def logout():
    session.clear()
    return redirect(url_for("login"))

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=4999)