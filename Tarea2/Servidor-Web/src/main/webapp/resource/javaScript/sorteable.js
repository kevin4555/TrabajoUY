$.noConflict();
jQuery(document).ready(function($){
  // Habilita el ordenamiento al cargar la página
  const $sortable = $("#sortable").sortable({
    update: function(event, ui) {
      const $data = $(this).sortable('toArray');
      $("#sorted-data").val(JSON.stringify($data));
    }
  });
  
  // Evita la selección de elementos mientras se ordenan
  $sortable.disableSelection();
  
  // Configura el botón "Guardar Orden" para almacenar el orden y enviar el formulario
  $("#guardarOrdenButton").click(function() {
    const $data = $("#sortable").sortable('toArray');
    $("#sorted-data").val(JSON.stringify($data));
    console.log("Form Submit, orden:", $("#sorted-data").val());
    
    // Envía el formulario
    $("#frmExample").submit();
  });
});

