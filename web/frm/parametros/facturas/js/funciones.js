function buscarIdPedido() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_facturacion").val(json.id_facturacion);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre").val(json.nombre);
            $("#fecha_factura").val(json.fecha_factura);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#id_tipopedido", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#id_tipopedido", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreFactura() {
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
                $("#id_facturacion").val(id);
                $("#bnombre").focus();
                buscarIdPedido();
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
function agregarPedido() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_pedido").val(json.id_pedido);
            buscarIdPedido();
            // $("#id_pedido").focus;
            //$("#id_pedido").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarPedido() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_pedido").focus;
            $("#id_pedido").select();
            buscarIdPedido();
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
function eliminarPedido() {
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
            eliminarPedidoDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_pedido").focus;
            $("#id_pedido").select();
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


function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/cliente/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor..");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
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

function buscarNombreCliente() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/cliente/buscarNombre.jsp',
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
                $("#id_cliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente();
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
    $("#nombre_proveedor").val("");
    $("#email_proveedor").val("");
    $("#web_proveedor").val("");
    $("#direccion_proveedor").val("");
}


function validarFormulario() {
    var valor = true;
    if ($("#nombre_pedido").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Pedido no puede estar vacio.");
        $("#nombre_pedido").focus();
    }

    if ($("#nombre_cliente").val().length < 2) {
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacio.");
        $("#id_cliente").focus();
    }

    if ($("#nombre_tipopedido").val().length < 2) {
        valor = false;
        $("#mensajes").html("Tipo Pedido no puede estar vacio.");
        $("#id_tipopedido").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_pedido").val("0");
    $("#nombre_pedido").val("");
    $("#nombre_tipopedido").val("");
    $("#nombre_cliente").val("");
    $("#id_cliente").val("0");
    $("#id_tipocpedido").val("0");

}
function agregarLinea() {
    $("#id_detallepedido").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#cantidad_articulopedido").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallepedido").val(id);
    $("#id_producto").val("0");
    $("#nombre_articulo").val("");
    $("#cantidad_articulopedido").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdPedidoDetalle();
    siguienteCampo("#cantidad_articulopedido", "#botonModificarLinea", true);
}
// pedidosarticulos
function buscarIdPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#cantidad_productofactura").val(json.cantidad_productofactura);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdPedidoPedidoDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPedidoPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoDetalle").html(json.contenido_detalle);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pedido = $("#id_pedido").val();
    datosFormulario += "&id_pedido=" + id_pedido;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPedido();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pedido = $("#id_pedido").val();
    datosFormulario += "&id_pedido=" + id_pedido;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPedido();
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
function eliminarPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pedido = $("#id_pedido").val();
    datosFormulario += "&id_pedido=" + id_pedido;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPedido();

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
//// articulos
function buscarIdProducto() {
    var datosFormulario = $("#formPrograma").serialize();
    // console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/producto/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor..");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#precio_producto").val(json.precio_producto);

            $("#id_categoria").val(json.id_categoria);
            $("#descripcion_categoria").val(json.descripcion_categoria);

            $("#id_iva").val(json.id_iva);
            $("#valor_iva").val(json.valor_iva);

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

function buscarNombreProducto() {
    alert("dsd");
    var datosFormulario = $("#formBuscar").serialize();
    // console.log("archivos pasando ");
    $.ajax({
        type: 'POST',
        url: 'jsp/producto/buscarNombre.jsp',
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
                $("#id_producto").val(id);
                $("#nombre_producto").focus();
                buscarIdProducto();
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