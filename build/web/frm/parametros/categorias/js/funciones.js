function agregarCategoria() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensaje").html("Enviando datos al servidor..");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_categoria").focus();
            $("#id_categoria").select();
        },
        error: function (e) {
            $("#mensajes").html("Error al agregar los datos");
        },
        complete: function (objeto, exito, error) {
            $("#id_categoria").focus();
        }
    });
}


function eliminarCategoria() {
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
            $("#id_categoria").focus();
            $("#id_categoria").select();
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

function modificarCategoria() {
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
            $("#descripcion_categoria").focus();
        },
        error: function (e) {
            $("#mensajes").html("Error al modificar los datos");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function buscarIdCategoria() {
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
            $("#id_categoria").val(json.id_categoria);
            $("#descripcion_categoria").val(json.descripcion_categoria);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#descripcion_categoria", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#descripcion_categoria", "#botonModificar", true);
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

function buscarNombreCategoria() {
    var datosFormulario = $("#formBuscar").serialize();

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
                $("#id_categoria").val(id);
                $("#descripcion_categoria").focus();
                buscarIdCategoria();
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
    $("#id_categoria").val("0");
    $("#descripcion_categoria").val("");
}
