

<%@page import="modelos.Ventas"%>
<%@page import="controladores.Detalle_ventaControlador"%>
<%@page import="modelos.Detalle_venta"%>
<%@page import="modelos.Compras"%>
<%@page import="controladores.Detalle_compraControlador"%>
<%@page import="modelos.Detalle_compra"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int detvta_iddetalleventa =Integer.parseInt(request.getParameter("detvta_iddetalleventa"));
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
     Detalle_venta detalle_venta = Detalle_ventaControlador.buscarId(detvta_iddetalleventa);
    if (detalle_venta != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalle_venta = new Detalle_venta();
        detalle_venta.setDetvta_iddetalleventa(0);
        
       Ventas venta = new Ventas();
       venta.setVen_idventa(0);
       detalle_venta.setVenta(venta);
        
        Productos producto = new Productos();
        producto.setPro_idproducto(0);
        producto.setPro_nombre("");
        detalle_venta.setProducto(producto);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("detvta_iddetalleventa", String.valueOf(detalle_venta.getDetvta_iddetalleventa()));
    obj.put("ven_idventa", String.valueOf(detalle_venta.getVenta().getVen_idventa()));
    obj.put("pro_idproducto", String.valueOf(detalle_venta.getProducto().getPro_idproducto()));
    obj.put("pro_nombre", detalle_venta.getProducto().getPro_nombre());
    obj.put("detvta_cantidad", String.valueOf(detalle_venta.getDetvta_cantidad()));
    
    out.print(obj);
    out.flush();
     
    
   
    %>
