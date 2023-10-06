const seleccionTipoPublicacion = document.getElementById("seleccionTipoPublicacion");
const botonRedireccionar = document.getElementById("botonRedireccionar");
seleccionTipoPublicacion.addEventListener("change", function () 
{
	const tipoSeleccionado = seleccionTipoPublicacion.value;
    if (tipoSeleccionado) 
    {
    	botonRedireccionar.style.display = "block";
        botonRedireccionar.onclick = function () 
        {
        	window.location.href = `TipoPublicacion.html`;
        };
    } 
    else 
    {
    botonRedireccionar.style.display = "none";
    }
});