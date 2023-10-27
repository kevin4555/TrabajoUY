function validateForm() {
    const videoLink = document.getElementById('textAreaVideo').value;
    
    if (videoLink.trim() !== '' && !isValidYouTubeLink(videoLink)) {
        alert('El enlace del video no es válido. Debe ser un enlace de YouTube.');
        return false; // Evita que el formulario se envíe si el enlace no es válido
    }
    
    return true; // Permite que el formulario se envíe si el campo de video está vacío o el enlace es válido
}

function isValidYouTubeLink(link) {
    // Expresión regular para validar enlaces de YouTube
    const youtubeRegex = /(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/watch\?v=|youtu\.be\/)([a-zA-Z0-9_-]+)/;
    
    return youtubeRegex.test(link);
}