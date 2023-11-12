const radioPorPaquete = document.getElementById("radioPorPaquete");
const radioGeneral = document.getElementById("radioGeneral");
const divSeleccionPaquete = document.getElementById("divSeleccionPaquete");
const listPaquetes = document.getElementById("listPaquetes");


document.addEventListener("DOMContentLoaded", function() {
	listPaquetes.value = "";	
	radioPorPaquete.addEventListener("change", function() {
		
		if (radioPorPaquete.checked) {
			if (listPaquetes.options.length > 0) {
        		listPaquetes.value = listPaquetes.options[0].value;
    		}
	        divSeleccionPaquete.style.display = "block";
	        return;
	    }
	});
	
	radioGeneral.addEventListener("change", function() {		
		if (radioGeneral.checked) {
	        divSeleccionPaquete.style.display = "none";
	        listPaquetes.value = "";
	        return;
	    }    
	});
	
});
 