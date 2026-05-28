function confirmarEnvio() {
    // Lanza la ventana de confirmación del navegador
    var verificar = confirm("¿Deseas enviar el formulario?");
    
    if (verificar) {
        alert("Formulario enviado correctamente.");
        return true; 
    } else {
        alert("Envío cancelado.");
        return false; 
    }
}


// Chat
function toggleChat() {
    var chat = document.getElementById("chat-box");
    if (chat.style.display === "none" || chat.style.display === "") {
        chat.style.display = "flex";
    } else {
        chat.style.display = "none";
    }
}

function enviarMensaje() {
    var texto = document.getElementById("chat-texto").value;
    if (texto.trim() === "") return;

    var mensajes = document.getElementById("chat-mensajes");

    mensajes.innerHTML += "<div class='msg-usuario'>" + texto + "</div>";
    document.getElementById("chat-texto").value = "";

    fetch("ChatControlador", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "mensaje=" + encodeURIComponent(texto)
    })
    .then(function(response) { return response.json(); })
    .then(function(data) {
        mensajes.innerHTML += "<div class='msg-bot'>" + data.respuesta + "</div>";
        mensajes.scrollTop = mensajes.scrollHeight;
    })
    .catch(function(error) {
        mensajes.innerHTML += "<div class='msg-bot'>Error al conectar con el asistente</div>";
    });
}

document.addEventListener("DOMContentLoaded", function() {
    
    var boton = document.getElementById("chat-boton");
    if (boton) {
        boton.addEventListener("click", toggleChat);
    }

    var input = document.getElementById("chat-texto");
    if (input) {
        input.addEventListener("keypress", function(e) {
            if (e.key === "Enter") enviarMensaje();
        });
    }
}
)

function enviarIncidenciaN8N() {
    alert("Incidencia enviada correctamente");
    
   
    document.getElementById("form-n8n").submit();
};
