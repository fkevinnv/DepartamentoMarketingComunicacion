<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.Publicacion"%>

<%
// Si no vienen publicaciones cargadas, redirige al controlador
ArrayList<Publicacion> lista = (ArrayList<Publicacion>) request.getAttribute("publicaciones");
if (lista == null) {
    response.sendRedirect(request.getContextPath() + "/PublicacionControlador");
    return;
}
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Departamento de Marketing y comunicación</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <h1>Departamento de Marketing y Comunicación</h1>
        <nav>
            <a href="#presentacion">Inicio</a> | <a href="#publicaciones">Publicaciones</a>
            | <a href="#contacto">Contacto</a> |
            <button id="btn-login"
                onclick="window.location.href='${pageContext.request.contextPath}/LoginControlador'">LOGIN</button>
        </nav>
    </header>
    <hr>
    <section id="presentacion">
        <h2>Bienvenid@ al departamento de Marketing y comunicación</h2>
        <p>Este departamento se encarga de la difusión de varios
            servicios, campañas informativas y todas las comunicaciones de
            nuestra empresa</p>
    </section>
    <hr>
    <section id="publicaciones">
        <h2>Publicaciones</h2>
        <%
        if (!lista.isEmpty()) {
            for (Publicacion p : lista) {
        %>
        <div class="publicacion">
            <h3><%=p.getTitulo()%></h3>
            <p><%=p.getContenido()%></p>
           <small>FECHA: <%=p.getFechaCreacion().substring(0, 10)%></small>
        </div>
        <hr>
        <%
            }
        } else {
        %>
        <p>No hay publicaciones disponibles</p>
        <%
        }
        %>
    </section>
    <hr>
    <section id="contacto">
        <h3>SOLICITUDES DE CAMPAÑA</h3>
        <form id="form-contacto" action="http://localhost:6644/procesar.php"
            method="POST" onsubmit="return confirmarEnvio()">
            <label for="asunto">Asunto / Campaña:</label> <input type="text"
                id="asunto" name="asunto" required> <label for="email">Correo
                de contacto:</label> <input type="email" id="email" name="email" required>
            <label for="mensaje">Mensaje:</label>
            <textarea id="mensaje" name="mensaje" rows="4" required></textarea>
            <button type="submit">ENVIAR A MARKETING</button>
        </form>
        <%
        if (request.getParameter("status") != null && request.getParameter("status").equals("ok")) {
        %>
        <p style="color: green;">¡Mensaje enviado con éxito!</p>
        <%
        }
        %>
    </section>
    <hr>
    <footer>
        <p>Departamento de Marketing y Comunicación</p>
    </footer>
    <div id="chat-boton">💬 Asistente</div>
    <div id="chat-box" onclick="event.stopPropagation()">
        <div id="chat-header">
            <span>Asistente de Marketing</span>
            <button onclick="toggleChat()">✕</button>
        </div>
        <div id="chat-mensajes"></div>
        <div id="chat-input">
            <input type="text" id="chat-texto"
                placeholder="Escribe tu pregunta..."
                onclick="event.stopPropagation()">
            <button onclick="enviarMensaje()">Enviar</button>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/alertas.js"></script>
</body>
</html>