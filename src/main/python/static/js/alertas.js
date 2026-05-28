
// error en el login 
function mostrarError(mensaje){
    alert("ERROR: " + mensaje);
}

//Alerta de exito en registro
function mostrarExito(mensaje) {
    alert("EXITO: " + mensaje);
}

// funcion de mostrar la contrasena si pulsas 

function mostrarContrasena(){
	var campo = document.getElementById("passwd");
	if (campo.type === "password") {
		campo.type ="text";
	} else {
		campo.type="password";
	}
}
