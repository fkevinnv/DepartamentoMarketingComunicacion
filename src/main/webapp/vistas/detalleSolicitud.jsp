<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.Solicitud" %>
<%@ page import="modelo.Mensaje" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle Solicitud</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <h1>Portal del Empleado</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a> |
            <a href="${pageContext.request.contextPath}/SolicitudControlador?opcion=misolicitudes">Volver</a>
        </nav>
    </header>
    <hr>

    <section>
        <%
            Solicitud s = (Solicitud) request.getAttribute("solicitud");
            if (s != null) {
        %>
        <h2>Mi Solicitud</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Asunto</th>
                <th>Descripción</th>
                <th>Estado</th>
                <th>Fecha</th>
                <th>Respuesta del Admin</th>
            </tr>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getAsunto() %></td>
                <td><%= s.getDescripcion() %></td>
                <td><%= s.getEstado() %></td>
                <td><%= s.getFechaSolicitud() %></td>
                <td>
                    <%
                        ArrayList<Mensaje> mensajes = (ArrayList<Mensaje>) request.getAttribute("mensajes");
                        if (mensajes != null && !mensajes.isEmpty()) {
                            for (Mensaje m : mensajes) {
                    %>
                        <p><%= m.getContenido() %></p>
                        <small><%= m.getFechaEnvio() %></small><br>
                    <%
                            }
                        } else {
                    %>
                        <p>Sin respuesta todavía</p>
                    <%
                        }
                    %>
                </td>
            </tr>
        </table>
        <%
            } else {
        %>
            <p>No se ha podido cargar la solicitud</p>
        <%
            }
        %>
    </section>
    <hr>

    <footer>
        <p>2026 Departamento de Marketing y Comunicación</p>
    </footer>
</body>
</html>