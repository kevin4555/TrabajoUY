var tipoPublicacionSelect = document.getElementById("tipoPublicacion");
var botonRedireccionar = document.getElementById("botonRedireccionar");

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
	var tipoPublicacionSeleccionado = tipoPublicacionSelect.value;
	window.location.href = "/servletTipoPublicacion?tipoPublicacion=" + tipoPublicacionSeleccionado;
});