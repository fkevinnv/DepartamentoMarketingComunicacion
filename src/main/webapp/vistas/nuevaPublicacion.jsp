<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<header>
		<h1>Nueva Publicaci&oacute;n</h1>
		<nav>
			<a href="${pageContext.request.contextPath}/vistas/index.jsp">Volver a inicio</a><br>
			<a href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">Volver al listado de publicaciones </a>
		</nav>
	</header>
	<hr>
	<section>
		<form action="${pageContext.request.contextPath}/PublicacionControlador" method="post">
			<input type="hidden" name="opcion" value="insertar">		
			
			<h3>T&iacute;tulo</h3>
			<input type="text" name="titulo" required><br>
			
			<h3>Tipo:</h3>
			<select name="tipo">
				<option value="campania">Campa&ntilde;a</option>
				<option value="noticia">Noticia</option>
				<option value="evento">Evento</option>
			</select><br>
			
			<h3>Estado:</h3>
			<select name="estado">
				<option value="borrador">Borrador</option>
				<option value="publicado">Publicado</option>
			</select><br>
			
			<h3>Descripci&oacute;n:</h3>
			<textarea name="descripcion" rows="7" cols="30" ></textarea><br>
						
			<!-- Si cancela se redirige al listado de publicaciones-->
			<button type="submit">GUARDAR</button>
			<a href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">Cancelar</a>
		</form>
	</section>
	<hr>
	<footer>
		<p>Departamento de Marketing y Comunicaci&oacute;n</p>
	</footer>
</body>
</html>