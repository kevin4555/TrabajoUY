var videoId = "<%= videoId %>"; // Asegúrate de que videoId contenga el ID del video

// Declarar una variable global para el reproductor de YouTube
var player = null;

// Función de inicialización de la API de YouTube
function onYouTubeIframeAPIReady() {
        var container = document.getElementById('video-container');
        var containerWidth = container.clientWidth; // Ancho del div contenedor
        var containerHeight = container.clientHeight; // Alto del div contenedor

        // Inicializa el reproductor de YouTube con el videoId y el tamaño del div contenedor
        player = new YT.Player('video-container', {
            width: containerWidth,
            height: containerHeight,
            videoId: videoId, // Utiliza el valor del videoId
            playerVars: {
                'rel': 0, // Evitar videos relacionados al final
            },
        });
    }