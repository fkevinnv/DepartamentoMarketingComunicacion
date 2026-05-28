<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Portal Administrador</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<header>
		<h1>Panel de Administración</h1>
		<nav>
			<a href="${pageContext.request.contextPath}/vistas/index.jsp">Inicio</a>
			| <a
				href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">Publicaciones</a>
			| <a
				href="${pageContext.request.contextPath}/SolicitudControlador?opcion=listar">Solicitudes</a>
			| <a
				href="${pageContext.request.contextPath}/EmpleadoControlador?opcion=listar">Empleados</a>
			| <a href="http://localhost:4999/logout">Cerrar Sesión</a>
		</nav>
	</header>
	<hr>

	<section>
		<h2>Bienvenido al Panel de Administración</h2>
		<p>Selecciona una opción del menú para gestionar el departamento.</p>
	</section>

	<section>
		<h2>Acciones rápidas</h2>
		<table border="1">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/PublicacionControlador?opcion=nueva">➕
						Nueva Publicación</a></td>
				<td>Crear una nueva publicación o campaña</td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/PublicacionControlador?opcion=listar">📋
						Ver Publicaciones</a></td>
				<td>Gestionar todas las publicaciones</td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/SolicitudControlador?opcion=listar">📩
						Ver Solicitudes</a></td>
				<td>Ver y gestionar solicitudes recibidas</td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/EmpleadoControlador?opcion=listar">👥
						Gestionar Empleados</a></td>
				<td>Ver, modificar rol y eliminar empleados</td>
			</tr>
			<tr>
				<td><a href="http://localhost:4999/registro">Registrar
						Empleado</a></td>
				<td>Crear una nueva cuenta de empleado</td>
			</tr>
			<tr>
    <td>
        <form id="form-n8n" action="http://localhost:5678/webhook-test/mensaje" method="POST">
            <button type="submit" id="btn-correo-incidencia">Enviar Incidencia</button>
        </form>
    </td>
    <td>Notifica autom&aacute;ticamente una incidencia por correo mediante N8N</td>
</tr>

		</table>
		
	</section>
	<hr>

	<footer>
		<p>Departamento de Marketing y Comunicación</p>
	</footer>
</body>
</html>