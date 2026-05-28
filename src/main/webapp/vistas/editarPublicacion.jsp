<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Publicacion" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Publicaci&oacute;n</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<header>
		<h1>Editar Publicaci&oacute;n</h1>
		<nav>
			<a href="${pageContext.request.contextPath}/vistas/index.jsp">Volver a inicio</a><br>
			<a href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">Volver al listado de publicaciones</a>
		</nav>
	</header>
	<hr>
	<section>
		<%
			Publicacion p = (Publicacion) request.getAttribute("publicacion");
			if (p !=null){
		%>
		<form action="${pageContext.request.contextPath}/PublicacionControlador" method="post">
			<input type="hidden" name="opcion" value="editar">
			<input type="hidden" name="id" value="<%= p.getId() %>">
			
			<h3>T&iacute;tulo</h3><br>
			<input type="text" name="titulo" value="<%= p.getTitulo() %>" required><br>
	
			<h3>Tipo:</h3><br>
			<select name="tipo">
				<option value="campana" <%= p.getTipo().equals("campana") ? "selected" : "" %>>Campa&ntilde;a</option>
				<option value="noticia" <%= p.getTipo().equals("noticia") ? "selected" : "" %> >Noticia</option>
				<option value="evento" <%= p.getTipo().equals("evento") ? "selected" : "" %>>Evento</option>
			</select><br>
			
			<h3>Descripci&oacute;n</h3>
			<textarea name= "descripcion" rows="5" cols="30"<%= p.getContenido()%>></textarea><br>
			
			<h3>Estado:</h3>
			<select name="estado">
				<option value="borrador" <%= p.getEstado().equals("borrador") ? "selected" : "" %>>Borrador</option>
				<option value="publicado" <%= p.getEstado().equals("publicado") ? "selected" : "" %>>Publicado</option>
			</select><br>
			<!-- Guardar y volver al listado de publicaciones -->
			<button type="submit">GUARDAR</button>
			<a href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">CANCELAR</a>
		</form>
		<%
			}
		%>	
	</section>
	<hr>
	<footer>
		<p>Departamento de Marketing y Comunicaci&oacute;n</p>
	</footer>
</body>
</html>