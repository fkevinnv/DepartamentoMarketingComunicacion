<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Publicacion" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle de Publicacion</title>
<link rel="stylesheet" href="../css/styles.css">
</head>
<body>
	<header>
		<h1>Departamento de Marketing y Comunicaci&oacute;n</h1>
		<nav>
			<a href="${pageContext.request.contextPath}/index.jsp">Volver al Inicio</a>
			<a href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">Volver a Publicaciones</a>
		</nav>
	</header>
	<hr>
	<section>
	<%
		Publicacion p = (Publicacion) request.getAttribute("publicacion");
		
		if (p != null) {
	
	%>	
		<h2><%= p.getTitulo() %></h2>
		<p> <strong>Tipo:</strong> <%= p.getTipo() %></p>
		<p><strong>Fecha:</strong> <%= p.getFechaCreacion() %></p>
		<p><strong>Estado:</strong> <%= p.getEstado() %></p>
		<p><%= p.getContenido() %></p>
		
	<%
		} else {
	%>
		<p>ERROR: No se ha podido cargar los detalles de la publicaci&oacute;n<p>
		<a href="${pageContext.request.contextPath}/index.jsp">Volver a inicio</a>
	<%
		}
	%>
</section>
	<hr>
	<footer>
		<p>Departameto de Marketing y Comunicacio&acute;n</p>
	</footer>
</body>
</html>
