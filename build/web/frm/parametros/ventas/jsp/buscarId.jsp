
<%@page import="controladores.Detalle_ventaControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="controladores.Detalle_compraControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int ven_idventa = Integer.parseInt(request.getParameter("ven_idventa"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Ventas ventas = new Ventas();
    ventas.setVen_idventa(ven_idventa);
    VentasControlador.buscarId(ventas);
    if (ventas != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        ventas = new Ventas();
        ventas.setVen_idventa(0);
        Clientes clientes = new Clientes();
        clientes.setCli_id(0);
        clientes.setCli_apellido("");
        clientes.setCli_nombre("");
        clientes.setCli_telefono("");
        clientes.setCli_ruc("");
        clientes.setCli_direccion("");

     
        java.sql.Date ven_fecha = new java.sql.Date(new java.util.Date().getTime());
        ventas.setVen_fecha(ven_fecha);
   
       ventas.setCliente(clientes);
    }

    String contenido_detalle = Detalle_ventaControlador.buscarIdVenta(ven_idventa);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("ven_idventa", String.valueOf(ventas.getVen_idventa()));
    obj.put("cli_id", String.valueOf(ventas.getCliente().getCli_id()));
     obj.put("cli_apellido", ventas.getCliente().getCli_apellido());
    obj.put("cli_nombre", ventas.getCliente().getCli_nombre());
    obj.put("cli_telefono", ventas.getCliente().getCli_telefono());
    obj.put("cli_ruc", ventas.getCliente().getCli_ruc());
    obj.put("cli_direccion", ventas.getCliente().getCli_direccion());
    obj.put("ven_fecha", ventas.getVen_fecha().toString());
    //obj.put("fecha_pedido", String.valueOf(compras.getCom_fechacompra()));
    
    obj.put("contenido_detalle", contenido_detalle);
  //  System.out.println("fecha"+contenido_detalle);

    out.print(obj);
    out.flush();


%>