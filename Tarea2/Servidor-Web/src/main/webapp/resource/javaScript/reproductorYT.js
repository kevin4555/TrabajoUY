document.getElementById("botonProcesar").addEventListener("click", function() {
    procesarVideo();
    document.querySelector(".videoContainer").style.display = "block";
});

function getVideoIdFromUrl(youtubeUrl) {
    var match = youtubeUrl.match(/[\?&]v=([^&]+)/);
    return match && match[1];
}

function procesarVideo() {
    const video = "<%= videoUrl %>";
    const videoId = getVideoIdFromUrl(video);
    
}