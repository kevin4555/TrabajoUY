function cambiarColorEstrella() {
  let icon = document.getElementById("estrellaIcon");

  if (icon) {
    if (icon.classList.contains("bi-star-fill")) {
      icon.classList.remove("bi-star-fill");
      icon.classList.add("bi-star");
    } else {
      icon.classList.remove("bi-star");
      icon.classList.add("bi-star-fill");
    }
  }
}

async function agregarFavorita(nickNamePostulante, nombreOferta) {
  try {
    const respuesta = await fetch(
      `ofertaFavorita?nicknamePostulante=${nickNamePostulante}&nombreOferta=${nombreOferta}`,
      {
        method: "GET",
      }
    );
    if (respuesta.ok) {
      cambiarColorEstrella();
    }
  } catch (error) {
    console.error("Error en la solicitud:", error);
    const errorAlert = document.getElementById("errorAlert");
    const errorMessage = document.getElementById("errorMessage");

    errorAlert.style.display = "block";
    errorMessage.textContent = error.message;
  }
  return;
}
