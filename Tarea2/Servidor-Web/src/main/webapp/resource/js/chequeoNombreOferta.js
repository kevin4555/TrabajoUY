const chequeoNombreOferta = document.getElementById('chequeoNombreOferta');
const inputNombreOferta = document.getElementById('inputNombreOferta');
const bloqueAviso = document.getElementById('bloqueAviso');


inputNombreOferta.addEventListener('change', () => {
    const nombreOferta = inputNombreOferta.value;

    fetch('chequeoNombreOferta?nombreOferta=' + nombreOferta)
        .then(response => response.json())
        .then(data => {
            if (data.disponible) {
                chequeoNombreOferta.innerHTML =  `<strong>Error!</strong> El nombre ya esta en uso.`;
                bloqueAviso.classList.remove('alert-success');
                bloqueAviso.classList.add('alert-danger');
                
            } else {
                chequeoNombreOferta.innerHTML = `<strong>Éxito!</strong> El nombre esta disponible.`;
                bloqueAviso.classList.add('alert-success');
                bloqueAviso.classList.remove('alert-danger');
            }
        })
        .catch(error => {
            console.error('Error al verificar disponibilidad del nombre de la oferta:', error);
        });
});
