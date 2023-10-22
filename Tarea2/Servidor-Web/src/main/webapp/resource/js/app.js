// Obtengo elementos del DOM
const radioPostulante = document.getElementById('radioPostulante');
const radioEmpresa = document.getElementById('radioEmpresa');
const formularioAltaUsuario = document.getElementById('formAltaUsuario');
const radioTipoUsuario = document.getElementById('radioTipoUsuario');
const divNacionalidad = document.getElementById('divNacionalidad');
const divFechaNacimiento = document.getElementById('divFechaNacimiento');
const divSitioWeb = document.getElementById('divSitioWeb');
const divDescripcionEmpresa = document.getElementById('divDescripcionEmpresa');
const btnRegistrarUsuario = document.getElementById('btnRegistrarUsuario');
const inputNickname = document.getElementById('inputNickname');
const chequeoNickname = document.getElementById('chequeoNickname');
const bloqueAviso = document.getElementById('bloqueAviso');


let postulanteSeleccionado = false;
let empresaSeleccionado = false;
formularioAltaUsuario.removeChild(divNacionalidad);
formularioAltaUsuario.removeChild(divFechaNacimiento);
formularioAltaUsuario.removeChild(divSitioWeb);
formularioAltaUsuario.removeChild(divDescripcionEmpresa);

//Escucha de eventos
radioPostulante.addEventListener('click', () => {
    if (empresaSeleccionado) {
        formularioAltaUsuario.removeChild(divSitioWeb);
        formularioAltaUsuario.removeChild(divDescripcionEmpresa);
        empresaSeleccionado = false;
    }
    if (!postulanteSeleccionado) {
        formularioAltaUsuario.insertBefore(divNacionalidad, radioTipoUsuario);
        formularioAltaUsuario.insertBefore(divFechaNacimiento, divNacionalidad);
        postulanteSeleccionado = true;
    }
    
});
radioEmpresa.addEventListener('click', () => {
    if (postulanteSeleccionado) {
        formularioAltaUsuario.removeChild(divFechaNacimiento);
        formularioAltaUsuario.removeChild(divNacionalidad);
        postulanteSeleccionado = false;
    }
    if (!empresaSeleccionado) {
        formularioAltaUsuario.insertBefore(divSitioWeb, radioTipoUsuario);
        formularioAltaUsuario.insertBefore(divDescripcionEmpresa, divSitioWeb);
        empresaSeleccionado = true;
    }

});

inputNickname.addEventListener('change', () => {
    const nickname = inputNickname.value;

    fetch('chequeoNickname?nickname=' + nickname)
        .then(response => response.json())
        .then(data => {
            if (data.disponible) {
                chequeoNickname.innerHTML =  `<strong>Error!</strong> El nickname ya esta en uso.`;
                bloqueAviso.classList.remove('alert-success');
                bloqueAviso.classList.add('alert-danger');
                
            } else {
                chequeoNickname.innerHTML = `<strong>Éxtio!</strong> El nickname esta disponible.`;
                bloqueAviso.classList.add('alert-success');
                bloqueAviso.classList.remove('alert-danger');
            }
        })
        .catch(error => {
            console.error('Error al verificar disponibilidad del nickname:', error);
        });
});

