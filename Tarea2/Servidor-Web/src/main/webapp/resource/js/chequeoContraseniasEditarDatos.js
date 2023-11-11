const inputContrasenia = document.getElementById('inputPassword');
const inputConfirmarContrasenia = document.getElementById('inputPasswordConfirm');
const chequeoContrasenia = document.getElementById('chequeoContrasenia');
const bloqueAvisoContrasenia = document.getElementById('bloqueAvisoContrasenia');

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
