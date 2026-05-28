<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.Publicacion"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion de Publicaciones</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

	<header>
		<h1>Panel de Administraci&oacute;n</h1>
		<nav>
			<a href="${pageContext.request.contextPath}/vistas/index.jsp">Volver
				a inicio</a> | <a
				href="${pageContext.request.contextPath}/SolicitudControlador?opcion=listar">Ver
				solicitudes</a>
		</nav>
	</header>
	<hr>
	<section>
		<h2>Gesti&oacute;n de Publicaciones</h2>
		<a
			href="${pageContext.request.contextPath}/vistas/nuevaPublicacion.jsp">Nueva
			Publicaci&oacute;n</a> <br> <br> <br>

		<!-- tabla de nuevas publicacione -->
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Titulo</th>
				<th>Tipo</th>
				<th>Estado</th>
				<th>Fecha</th>
				<th>Acciones</th>
			</tr>
			<%
			ArrayList<Publicacion> lista = (ArrayList<Publicacion>) request.getAttribute("publicaciones");
			// lista.empty comprueba que haya contenido antes de generar las filas	
			if (lista != null && !lista.isEmpty()) {
				for (Publicacion p : lista) {
			%>
			<tr>
				<td><%=p.getId()%></td>
				<td><%=p.getTitulo()%></td>
				<td><%=p.getTipo()%></td>
				<td><%=p.getEstado()%></td>
				<td><%=p.getFechaCreacion()%></td>
				<!-- accciones -->
				<td><a
					href="${pageContext.request.contextPath}/PublicacionControlador?opcion=editar&id=<%= p.getId() %>">Editar</a>
					<a
					href="${pageContext.request.contextPath}/PublicacionControlador?opcion=eliminar&id=<%= p.getId() %>">Eliminar</a>
				</td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td>NO HAY PUBLICACIONES</td>
			</tr>
			<%
			}
			%>
		</table>
	</section>
	<hr>
	<footer>
		<p>Departamento de Marketing y Comunicaci&oacute;n</p>
	</footer>
</body>
</html>