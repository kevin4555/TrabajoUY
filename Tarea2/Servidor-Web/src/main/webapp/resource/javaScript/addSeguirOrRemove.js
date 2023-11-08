function cambiarIcono() {
  let icon = document.getElementById("accionIcon");

  if (icon) {
    if (icon.classList.contains("bi-person-plus")) {
      icon.classList.remove("bi-person-plus");
      icon.classList.add("bi-person-dash-fill");
    } else {
      icon.classList.remove("bi-person-dash-fill");
      icon.classList.add("bi-person-plus");
    }
  }
}

async function seguirDejarSeguir(nickName){ 
	try{
		 const respuesta = await fetch(`seguirDejarSeguir?perfilUsuario=${nickName}`, {
      						method: "GET",});
		if(respuesta.ok){
			cambiarIcono();
		}
	} catch (error) {
        console.error('Error en la solicitud:', error);
        const errorAlert = document.getElementById('errorAlert');
	    const errorMessage = document.getElementById('errorMessage');
	
	    errorAlert.style.display = 'block';
	    errorMessage.textContent =  error.message;
	}
	return;
}
