<?php
include "configuracion.php";

$connection_string = "host=$servidor port=$puerto dbname=$basedatos user=$usuario password=$contrasena";
$conn = pg_connect($connection_string);

if (!$conn) {
    die("Error de conexión: " . pg_last_error());
}

echo '<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Estado del Envío</title>
    <link rel="stylesheet" href="http://localhost:8080/DepartamentoMarketingComunicacion/css/styles.css">
</head>
<body>
    <header>
        <h1>Departamento de Marketing y Comunicación</h1>
    </header>
    <div class="mensaje-box">';

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $asunto  = $_POST['asunto'];
    $email   = $_POST['email'];
    $mensaje = $_POST['mensaje'];

    $sql = "INSERT INTO formulario_php (asunto, email, mensaje) VALUES ($1, $2, $3)";
    $result = pg_query_params($conn, $sql, array($asunto, $email, $mensaje));

    if ($result) {
        echo "<h2>Mensaje guardado correctamente.</h2>";

        echo "<table>";
        echo "<tr><th>Asunto</th><td>" . htmlspecialchars($asunto) . "</td></tr>";
        echo "<tr><th>Correo</th><td>" . htmlspecialchars($email)  . "</td></tr>";
        echo "<tr><th>Fecha</th><td>"  . date("d-m-Y H:i:s")       . "</td></tr>";
        echo "</table>";

        echo "<br>";
        echo "<div style='text-align:center;'>";
        echo "<a href='http://localhost:8080/DepartamentoMarketingComunicacion/PublicacionControlador' class='btn'>Volver al Inicio</a>";
        echo "</div>";

    } else {
        echo "<h2>Error</h2>";
        echo "<p>No se pudo guardar: " . pg_last_error($conn) . "</p>";
        echo "<br>";
        echo "<div style='text-align:center;'>";
        echo "<a href='http://localhost:8080/DepartamentoMarketingComunicacion/index.jsp' class='btn'>Volver al Inicio</a>";
        echo "</div>";
    }
}

echo '    </div>
</body>
</html>';

pg_close($conn);
?>
