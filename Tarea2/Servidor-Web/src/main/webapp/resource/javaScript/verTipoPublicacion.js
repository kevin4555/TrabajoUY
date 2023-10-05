const tipoPublicacionSelect = document.getElementById("tipoPublicacion");
const botonRedireccionar = document.getElementById("botonRedireccionar");


tipoPublicacionSelect.addEventListener("change", function() 
{
	if (tipoPublicacionSelect.value !== "") {
		botonRedireccionar.style.display = "block";
    } 
    else 
    {
		botonRedireccionar.style.display = "none";
    }
});
    
botonRedireccionar.addEventListener("click", function() 
{
	const tipoPublicacionSeleccionado = tipoPublicacionSelect.value;
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	window.location.href = contextPath+"/tipoPublicacion?nombreTipoPublicacion=" + tipoPublicacionSeleccionado;
});

