
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.Solicitud" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Solicitudes</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <h1>Panel de Administración</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/vistas/index.jsp">Inicio</a> |
            <a href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">Ver Publicaciones</a>
        </nav>
    </header>
    <hr>
    <section>
        <h2>Solicitudes</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Empleado</th>
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
                    <td><%= s.getIdEmpleado() %></td>
                    <td><%= s.getAsunto() %></td>
                    <td><%= s.getEstado() %></td>
                    <td><%= s.getFechaSolicitud() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/SolicitudControlador?opcion=detalle&id=<%= s.getId() %>">Ver</a> |
                        <a href="${pageContext.request.contextPath}/SolicitudControlador?opcion=eliminar&id=<%= s.getId() %>">Eliminar</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td>No hay solicitudes</td>
                </tr>
            <%
                }
            %>
        </table>
    </section>
    <hr>
    <footer>
        <p>2026 Departamento de Marketing y Comunicación</p>
    </footer>
</body>
</html>