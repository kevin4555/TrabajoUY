const radioPorPaquete = document.getElementById("radioPorPaquete");
const radioGeneral = document.getElementById("radioGeneral");
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

radioGeneral.addEventListener("change", function() 
{
	if (radioGeneral.checked) 
	{
        divSeleccionPaquete.style.display = "none";
    } 
});