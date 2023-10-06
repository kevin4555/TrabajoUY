const radioPorPaquete = document.getElementById("radioPorPaquete");

const divSeleccionPaquete = document.getElementById("divSeleccionPaquete");
 
radioPorPaquete.addEventListener("change", function() 
{
	if (radioPorPaquete.checked) 
	{
        divSeleccionPaquete.style.display = "block";
    } 
    else 
    {
		divSeleccionPaquete.style.display = "none";
    }
});