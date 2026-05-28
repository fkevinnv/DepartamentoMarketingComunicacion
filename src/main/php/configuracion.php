<?php
$servidor = getenv("DB_HOST") ?: "db";
$usuario = getenv("DB_USER") ?: "administrador";
$contrasena = getenv("DB_PASSWORD") ?: "123456";
$basedatos = getenv("DB_NAME") ?: "aplicacion_db";
$puerto = getenv("DB_PORT") ?: "5432";
?>