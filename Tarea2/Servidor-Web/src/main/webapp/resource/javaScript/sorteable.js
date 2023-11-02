$(document).ready(function() {
    const $sortable = $("#sortable");

    $sortable.sortable({
        update: function(event, ui) {
            const sortedData = $sortable.sortable('toArray');
            $("#sorted-data").val(sortedData.join(","));

            // También podemos actualizar el orden de los elementos en el formulario
            const $inputArray = $form.find("input[name='sorted-data']");
            $inputArray.val(sortedData.join(","));
        }
    });

    $sortable.disableSelection();
});