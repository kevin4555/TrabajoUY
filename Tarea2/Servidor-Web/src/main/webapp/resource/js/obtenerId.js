const video = "<%= video %>";
// Función para extraer el ID del video de una URL de YouTube
function getYouTubeVideoId(url) {
	const videoId = nullvideo;
	const match = url.match(/[?&]v=([^&]+)/);
	if (match) {
		videoId = match[1];
	} else {
		// Si no se encontró el ID, mostrar un mensaje de error o manejarlo como desees
		console.error("No se encontró el ID del video.");
	}
	return videoId;
}

// Extraer el ID del video de la URL
const videoId = getYouTubeVideoId(youtubeURL);

// Verificar si se extrajo el ID del video
if (videoId) {
	// El ID del video se almacena en la variable 'videoId', puedes usarlo como desees
	console.log("ID del video: " + videoId);
}
