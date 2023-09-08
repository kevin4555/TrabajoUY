document.addEventListener("DOMContentLoaded", function () {
    const elements = document.querySelectorAll(".dropdownConsulta-content a");

    elements.forEach(function (element) {
        element.addEventListener("click", function (event) {
            event.preventDefault();
            const option = event.target.textContent;
            const dropdownConsulta = document.getElementById("dropdownConsulta-button");
            dropdownConsulta.textContent = option;
        });
    });
});