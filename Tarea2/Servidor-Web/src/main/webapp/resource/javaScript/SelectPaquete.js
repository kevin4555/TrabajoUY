const radioPorPaquete = document.getElementById("radioPorPaquete");
const radioGeneral = document.getElementById("radioGeneral");
const divSeleccionPaquete = document.getElementById("divSeleccionPaquete");
const listPaquetes = document.getElementById("listPaquetes");
const p = document.getElementById("coso");
 
 document.addEventListener("click",function(){
	 p.innerHTML = `Select value: `;
 });
radioPorPaquete.addEventListener("change", function() 
{
	
	if (radioPorPaquete.checked) {
        divSeleccionPaquete.style.display = "block";
        return;
    }
});

radioGeneral.addEventListener("change", function() 
{
	if (radioGeneral.checked) {
        divSeleccionPaquete.style.display = "none";
        listPaquetes.value = "";
        return;
    }    
});