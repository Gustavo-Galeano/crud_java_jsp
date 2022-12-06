

<%@page import="controladores.Detalle_ventaControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Detalle_venta"%>
<%@page import="modelos.Existencias"%>
<%@page import="controladores.Detalle_compraControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Detalle_compra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
 
    int detvta_cantidad = Integer.parseInt(request.getParameter("detvta_cantidad"));
    int ven_idventa = Integer.parseInt(request.getParameter("ven_idventa"));
    int pro_idproducto = Integer.parseInt(request.getParameter("pro_idproducto"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Detalle_venta detalle_venta = new Detalle_venta();
   // detalle_venta.setDetvta_iddetalleventa(detvta_iddetalleventa);
    detalle_venta.setDetvta_cantidad(detvta_cantidad);

    Ventas venta = new Ventas();
    venta.setVen_idventa(ven_idventa);

    Productos producto = new Productos();
    producto.setPro_idproducto(pro_idproducto);

    detalle_venta.setVenta(venta);
    detalle_venta.setProducto(producto);
    Existencias existencia = new Existencias();
    existencia.setEx_cantidad(detvta_cantidad);
    existencia.setPro_idproducto(pro_idproducto);
    StocksControlador.sumar(stock);
     ExisteciaControlador.buscarCantidad(pro_idproducto);
    if (Detalle_ventaControlador.agregar(detalle_venta)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    %>