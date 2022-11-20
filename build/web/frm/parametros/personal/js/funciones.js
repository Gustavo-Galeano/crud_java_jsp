function agregarPersonal() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor..");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_personal").focus();
            $("#id_personal").select();
        },
        error: function (e) {
            $("#mensajes").html("Error al agregar los datos");
        },
        complete: function (objeto, exito, error) {
            $("#id_personal").focus();
        }
    });
}



function limpiarFormulario() {
    $("#id_personal").val("0");
    $("#nombre_categoria").val("");
    $("#apellido_categoria").val("");
    $("#ci_categoria").val("");
    $("#telefono_categoria").val("");
    $("#email_categoria").val("");
    $("#pass_categoria").val("");
}
