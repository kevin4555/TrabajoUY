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
const bloqueAvisoEmail = document.getElementById('bloqueAvisoEmail');
const chequeoEmail = document.getElementById('chequeoEmail');
const inputEmail = document.getElementById('inputEmail');
const inputContrasenia = document.getElementById('inputPassword');
const inputConfirmarContrasenia = document.getElementById('inputPasswordConfirm');
const chequeoContrasenia = document.getElementById('chequeoContrasenia');
const bloqueAvisoContrasenia = document.getElementById('bloqueAvisoContrasenia');


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
                chequeoNickname.innerHTML = `<strong>Éxito!</strong> El nickname esta disponible.`;
                bloqueAviso.classList.add('alert-success');
                bloqueAviso.classList.remove('alert-danger');
            }
        })
        .catch(error => {
            console.error('Error al verificar disponibilidad del nickname:', error);
        });
});

inputEmail.addEventListener('change', () => {
    const email = inputEmail.value;

    fetch('chequeoEmail?email=' + email)
        .then(response => response.json())
        .then(data => {
            if (data.disponible) {
                chequeoEmail.innerHTML =  `<strong>Error!</strong> El email ya esta en uso.`;
                bloqueAvisoEmail.classList.remove('alert-success');
                bloqueAvisoEmail.classList.add('alert-danger');
                
            } else {
                chequeoEmail.innerHTML = `<strong>Éxito!</strong> El email esta disponible.`;
                bloqueAvisoEmail.classList.add('alert-success');
                bloqueAvisoEmail.classList.remove('alert-danger');
            }
        })
        .catch(error => {
            console.error('Error al verificar disponibilidad del email:', error);
        });
});

inputConfirmarContrasenia.addEventListener('change', () => {
    const contraseniaConfirmada = inputConfirmarContrasenia.value;
    const contrasenia = inputContrasenia.value;
    if (contraseniaConfirmada == contrasenia) {
		chequeoContrasenia.innerHTML = `<strong>Éxito!</strong> Las contraseñas coinciden.`;
		bloqueAvisoContrasenia.classList.add('alert-success');
		bloqueAvisoContrasenia.classList.remove('alert-danger');
	} else {
		chequeoContrasenia.innerHTML =  `<strong>Error!</strong> Las contraseñas no coinciden.`;
        bloqueAvisoContrasenia.classList.remove('alert-success');
        bloqueAvisoContrasenia.classList.add('alert-danger');
	}
});

inputContrasenia.addEventListener('change', () => {
    const contraseniaConfirmada = inputConfirmarContrasenia.value;
    const contrasenia = inputContrasenia.value;
    if (contraseniaConfirmada == contrasenia) {
		chequeoContrasenia.innerHTML = `<strong>Éxito!</strong> Las contraseñas coinciden.`;
		bloqueAvisoContrasenia.classList.add('alert-success');
		bloqueAvisoContrasenia.classList.remove('alert-danger');
	} else {
		chequeoContrasenia.innerHTML =  `<strong>Error!</strong> Las contraseñas no coinciden.`;
        bloqueAvisoContrasenia.classList.remove('alert-success');
        bloqueAvisoContrasenia.classList.add('alert-danger');
	}
});



