<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.Empleado"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Gestión de Empleados</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<header>
		<h1>Panel de Administración</h1>
		<nav>
			<a href="${pageContext.request.contextPath}/index.jsp">Inicio</a> | <a
				href="${pageContext.request.contextPath}/vistas/portalAdmin.jsp">Volver
				al panel</a> | <a href="http://localhost:4999/registro"> Registrar
				Empleado</a>
		</nav>
	</header>
	<hr>

	<section>
		<h2>Gestión de Empleados</h2>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Correo</th>
				<th>Sede</th>
				<th>Rol</th>
				<th>Acciones</th>
			</tr>
			<%
			ArrayList<Empleado> lista = (ArrayList<Empleado>) request.getAttribute("empleados");
			if (lista != null && !lista.isEmpty()) {
				for (Empleado e : lista) {
			%>
			<tr>
				<td><%=e.getId()%></td>
				<td><%=e.getNombre()%></td>
				<td><%=e.getApellidos()%></td>
				<td><%=e.getCorreo()%></td>
				<td><%=e.getIdSede()%></td>
				<td>
					<form
						action="${pageContext.request.contextPath}/EmpleadoControlador"
						method="post">
						<input type="hidden" name="opcion" value="actualizarRol">
						<input type="hidden" name="id" value="<%=e.getId()%>"> <select
							name="rol">
							<option value="empleado"
								<%=e.getRol().equals("empleado") ? "selected" : ""%>>Empleado</option>
							<option value="admin"
								<%=e.getRol().equals("admin") ? "selected" : ""%>>Admin</option>
						</select>
						<button type="submit">Cambiar</button>
					</form>
				</td>
				<td>
					<form
						action="${pageContext.request.contextPath}/EmpleadoControlador"
						method="post">
						<input type="hidden" name="opcion" value="eliminar"> <input
							type="hidden" name="id" value="<%=e.getId()%>">
						<button type="submit" onclick="return confirmarEliminar()">Eliminar</button>
					</form>
				</td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="7">No hay empleados registrados</td>
			</tr>
			<%
			}
			%>
		</table>
	</section>
	<hr>

	<footer>
		<p> Departamento de Marketing y Comunicación</p>
	</footer>
	<script src="${pageContext.request.contextPath}/js/alertas.js"></script>
</body>
</html>