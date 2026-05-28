CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS empleados (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    correo VARCHAR(150) NOT NULL UNIQUE,
    passwd VARCHAR(255) NOT NULL,
    id_sede INT NOT NULL
);

CREATE OR REPLACE FUNCTION registrar_usuario(
    _nombre VARCHAR,
    _correo VARCHAR,
    _passwd VARCHAR,
    _id_sede INT
)
RETURNS BOOLEAN AS $$
BEGIN
    INSERT INTO empleados(nombre, correo, passwd, id_sede)
    VALUES (_nombre, _correo, crypt(_passwd, gen_salt('bf')), _id_sede);

    RETURN TRUE;
EXCEPTION
    WHEN unique_violation THEN
        RETURN FALSE;
    WHEN others THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION login_empleado(
    _login VARCHAR,
    _contrasena VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    v_contrasena_almacenada VARCHAR(255);
BEGIN
    SELECT passwd INTO v_contrasena_almacenada
    FROM empleados
    WHERE correo = _login OR nombre = _login;

    IF NOT FOUND THEN
        RETURN FALSE;
    END IF;

    IF crypt(_contrasena, v_contrasena_almacenada) = v_contrasena_almacenada THEN
        RETURN TRUE;
    END IF;

    RETURN FALSE;
END;
$$ LANGUAGE plpgsql;

INSERT INTO empleados(nombre, correo, passwd, id_sede)
VALUES ('empleado', 'empleado@dam.es', crypt('empleado123', gen_salt('bf')), 1)
ON CONFLICT DO NOTHING;
