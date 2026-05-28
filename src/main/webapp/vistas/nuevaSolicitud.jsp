<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva Solicitud</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <h1>Nueva Solicitud</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/vistas/index.jsp">Volver a Inicio</a>
            <a href="${pageContext.request.contextPath}/vistas/portalEmpleado.jsp">Volver atr&aacute;s</a>
        </nav>
    </header>
    <hr>
        <section>
            <form action="${pageContext.request.contextPath}/SolicitudControlador" method="post">
                <input type="hidden" name="opcion" value="insertar">
                
                <h3>Informaci&oacute;n de la Solicitud</h3>
                <input type="text" name="asunto" required><br>
                
                <h3>Descripci&oacute;n</h3>
                <textarea name="descripcion" rows="3" cols="30" required></textarea><br>

                <button type="submit">ENVIAR</button>
                <a href="${pageContext.request.contextPath}/SolicitudControlador?opcion=misolicitudes">Cancelar</a>
            </form>
        </section>
        <hr>
        <footer>
            <p>Departamento de Marketing y Comunicaci&oacute;n</p>
        </footer>
</body>
</html>