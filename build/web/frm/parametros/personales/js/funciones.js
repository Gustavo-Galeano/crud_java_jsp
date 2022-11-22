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

function eliminarPersonal() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_personal").focus();
            $("#id_personal").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function modificarPersonal() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/editar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor..");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#nombre_personal").focus();
        },
        error: function (e) {
            $("#mensajes").html("Error al modificar los datos");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function buscarIdPersonal() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor..");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_personal").val(json.id_personal);
            $("#nombre_personal").val(json.nombre_personal);
            $("#apellido_personal").val(json.apellido_personal);
            $("#ci_personal").val(json.ci_personal);
            $("#telefono_personal").val(json.telefono_personal);
            $("#email_personal").val(json.email_personal);
            $("#pass_personal").val(json.pass_personal);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_personal", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_personal", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("Error al recuperar los datos");
        },
        complete: function (objeto, exito, error1) {
            if (exito === "success") {

            }
        }
    });
}

function buscarNombrePersonal() {
    var datosFormulario = $("#formBuscar").serialize();
    console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_personal").val(id);
                $("#nombre_personal").focus();
                buscarIdPersonal();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function limpiarFormulario() {
    $("#id_personal").val("0");
    $("#nombre_personal").val("");
    $("#apellido_personal").val("");
    $("#ci_personal").val("");
    $("#telefono_personal").val("");
    $("#email_personal").val("");
    $("#pass_personal").val("");
}
