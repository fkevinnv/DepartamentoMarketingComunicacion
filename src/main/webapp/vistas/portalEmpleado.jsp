<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.Solicitud" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Portal Empleado</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <h1>Bienvenid@ al portal Empleado</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/vistas/index.jsp">Inicio</a> |
            <a href="${pageContext.request.contextPath}/vistas/nuevaSolicitud.jsp">Nueva Solicitud</a> |
            <a href="http://localhost:4999/logout">Cerrar Sesión</a>
        </nav>
    </header>
    <hr>

    <section>
        <h2>Mis Solicitudes</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Asunto</th>
                <th>Estado</th>
                <th>Fecha</th>
                <th>Acciones</th>
            </tr>
            <%
                ArrayList<Solicitud> lista = (ArrayList<Solicitud>) request.getAttribute("solicitudes");
                if (lista != null && !lista.isEmpty()) {
                    for (Solicitud s : lista) {
            %>
                <tr>
                    <td><%= s.getId() %></td>
                    <td><%= s.getAsunto() %></td>
                    <td><%= s.getEstado() %></td>
                    <td><%= s.getFechaSolicitud() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/MensajeControlador?opcion=ver&idSolicitud=<%= s.getId() %>">💬</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5">No tienes solicitudes por procesar</td>
                </tr>
            <%
                }
            %>
        </table>
    </section>
    <hr>

    <footer>
        <p> Departamento de Marketing y Comunicaci&oacute;n</p>
    </footer>
    <script src="${pageContext.request.contextPath}/js/alertas.js"></script>
</body>
</html>