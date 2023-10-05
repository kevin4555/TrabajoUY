function validarFormulario() {
    // Obtener el evento actual
    var evento = window.event || arguments.callee.caller.arguments[0];

    // Verificar si la tecla presionada es "Enter" (código 13)
    if (evento.keyCode === 13) {
        // Enviar el formulario
        document.forms[0].submit();
        return false; // Evitar el envío adicional del formulario
    }
    return true; // Continuar con el comportamiento normal del formulario
}