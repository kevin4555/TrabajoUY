document.addEventListener("DOMContentLoaded", function () {
    const elements = document.querySelectorAll(".dropdownConsultaUsuario-content a");

    elements.forEach(function (element) {
        element.addEventListener("click", function (event) {
            event.preventDefault();
            const option = event.target.textContent;
            const dropdownConsulta = document.getElementById("dropdownConsultaUsuario-button");
            dropdownConsulta.textContent = option;
        });
    });
});