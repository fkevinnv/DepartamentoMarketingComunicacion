<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.Solicitud" %>
<%@ page import="modelo.Mensaje" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle Solicitud Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <h1>Panel de Administración</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a> |
            <a href="${pageContext.request.contextPath}/SolicitudControlador?opcion=listar">Volver a solicitudes</a>
        </nav>
    </header>
    <hr>

    <section>
        <%
            Solicitud s = (Solicitud) request.getAttribute("solicitud");
            if (s != null) {
        %>
        <h2>Detalle de la Solicitud</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Empleado</th>
                <th>Asunto</th>
                <th>Descripción</th>
                <th>Estado</th>
                <th>Fecha</th>
            </tr>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getIdEmpleado() %></td>
                <td><%= s.getAsunto() %></td>
                <td><%= s.getDescripcion() %></td>
                <td><%= s.getEstado() %></td>
                <td><%= s.getFechaSolicitud() %></td>
            </tr>
        </table>

        <br>
        <form action="${pageContext.request.contextPath}/SolicitudControlador" method="post">
            <input type="hidden" name="opcion" value="actualizarEstado">
            <input type="hidden" name="id" value="<%= s.getId() %>">
            <label>Cambiar estado:</label>
            <select name="estado">
                <option value="pendiente" <%= s.getEstado().equals("pendiente") ? "selected" : "" %>>Pendiente</option>
                <option value="respondida" <%= s.getEstado().equals("respondida") ? "selected" : "" %>>Respondida</option>
                <option value="cerrada" <%= s.getEstado().equals("cerrada") ? "selected" : "" %>>Cerrada</option>
            </select>
            <button type="submit">Actualizar</button>
        </form>
        <%
            }
        %>
    </section>
    <hr>

    <section>
        <h2>💬 Mensajes</h2>
        <%
            ArrayList<Mensaje> mensajes = (ArrayList<Mensaje>) request.getAttribute("mensajes");
            if (mensajes != null && !mensajes.isEmpty()) {
                for (Mensaje m : mensajes) {
        %>
            <div class="mensaje">
                <p><strong>Empleado <%= m.getIdEmpleado() %>:</strong> <%= m.getContenido() %></p>
                <small><%= m.getFechaEnvio() %></small>
                <form action="${pageContext.request.contextPath}/MensajeControlador" method="post">
                    <input type="hidden" name="opcion" value="eliminar">
                    <input type="hidden" name="id" value="<%= m.getId() %>">
                    <input type="hidden" name="idSolicitud" value="<%= m.getIdSolicitud() %>">
                    <button type="submit" onclick="return confirmarEliminar()">Eliminar</button>
                </form>
            </div>
            <hr>
        <%
                }
            } else {
        %>
            <p>No hay mensajes todavía.</p>
        <%
            }
        %>

        <h3>Escribir respuesta</h3>
        <%
            Solicitud sol = (Solicitud) request.getAttribute("solicitud");
            if (sol != null) {
        %>
        <form action="${pageContext.request.contextPath}/MensajeControlador" method="post">
            <input type="hidden" name="opcion" value="insertar">
            <input type="hidden" name="idSolicitud" value="<%= sol.getId() %>">
            <input type="hidden" name="idEmpleado" value="0">
            <label>Mensaje:</label><br>
            <textarea name="contenido" rows="4" cols="50" required></textarea><br><br>
            <button type="submit">Enviar</button>
        </form>
        <%
            }
        %>
    </section>
    <hr>

    <footer>
        <p>2026 Departamento de Marketing y Comunicación</p>
    </footer>
    <script src="${pageContext.request.contextPath}/js/alertas.js"></script>
</body>
</html>