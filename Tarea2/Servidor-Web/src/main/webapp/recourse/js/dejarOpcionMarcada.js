document.addEventListener("DOMContentLoaded", function () {
    const dropdownButton = document.getElementById("dropdown-button");
    const dropdownLinks = document.querySelectorAll(".dropdown-content a");

    // Obtén la URL actual
    const currentPageURL = window.location.pathname.split("/").pop();
    let currentPageFound = false;

    // Recorre los enlaces del menú desplegable
    dropdownLinks.forEach(function (link) {
        const linkPathPart = (link.getAttribute("href").split("/"));
        const linkPathLength = linkPathPart.length;

        if (linkPathLength > 0) {
            const linkFileName = linkPathPart[linkPathLength - 1];


            // Si la URL actual coincide con la URL del enlace, actualiza el botón
            if (currentPageURL === linkFileName) {
                dropdownButton.textContent = link.textContent;
                currentPageFound = true;
            }
        }
    });

    // Si la página actual no se encontró en la lista, muestra "Seleccionar página"
    if (!currentPageFound) {
        dropdownButton.textContent = "Seleccionar página";
    }
});