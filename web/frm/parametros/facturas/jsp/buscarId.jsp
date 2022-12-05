

<%@page import="Model.Cliente"%>
<%@page import="Controller.FacturaController"%>
<%@page import="Model.Factura"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_facturacion = Integer.parseInt(request.getParameter("id_facturacion"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Factura factura = new Factura();
    factura.setId_facturacion(id_facturacion);
    FacturaController.buscarId(factura);

    FacturaController.buscarId(factura);
    if (factura != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        factura = new Factura();
        factura.setId_facturacion(0);
        
        Cliente cliente = new Cliente();
        cliente.setId_cliente(0);
        cliente.setNombre("");
   
        java.sql.Date fecha_factura = new java.sql.Date(new java.util.Date().getTime());
        factura.setFecha(fecha_factura);

        factura.setCliente(cliente);
    }

    String contenido_detalle = Detalle_ventaControlador.buscarId(id_facturacion);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_facturacion", String.valueOf(factura.getId_facturacion()));
    obj.put("id_cliente", String.valueOf(factura.getCliente().getId_cliente()));

    obj.put("cli_nombre", factura.getCliente().getNombre());
  
    obj.put("ven_fecha", factura.getFecha().toString());
    //obj.put("fecha_pedido", String.valueOf(compras.getCom_fechacompra()));

    obj.put("contenido_detalle", contenido_detalle);
    //  System.out.println("fecha"+contenido_detalle);

    out.print(obj);
    out.flush();


%>

