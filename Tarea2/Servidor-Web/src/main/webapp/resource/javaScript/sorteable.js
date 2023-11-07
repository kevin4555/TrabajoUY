$(document).ready(function() {
    $("#sortable").sortable();

    // Evento para capturar el orden cuando se haga clic en el botón
    $("#confirmarOrden").click(function(e) {
        e.preventDefault(); // Evita el envío por defecto del formulario

        // Obtiene el orden de los elementos
        const ordenElementos = $("#sortable").sortable("toArray");

        // Crea un objeto JavaScript que contiene el orden
        const data = {
            orden: ordenElementos
        };

        // Realiza una solicitud POST al servidor para enviar el orden
        $.ajax({
            url: "/seleccionarPostulacion", // Ajusta la URL a la ruta de tu servlet
            type: "POST",
            data: data,
            success: function(response) {
                // Maneja la respuesta del servidor aquí
                console.log("Orden enviado al servidor:", response);
            },
            error: function(xhr, status, error) {
                // Maneja los errores de la solicitud aquí
                console.error("Error en la solicitud:", status, error);
            }
        });
    });
});