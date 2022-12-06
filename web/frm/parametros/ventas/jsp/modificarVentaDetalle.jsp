
<%@page import="controladores.Detalle_ventaControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Detalle_venta"%>
<%@page import="controladores.Detalle_compraControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Detalle_compra"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Proveedores"%>
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int detvta_iddetalleventa = Integer.parseInt(request.getParameter("detvta_iddetalleventa"));
    int detvta_cantidad = Integer.parseInt(request.getParameter("detvta_cantidad"));
    int ven_idventa = Integer.parseInt(request.getParameter("ven_idventa"));
    int pro_idproducto = Integer.parseInt(request.getParameter("pro_idproducto")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Detalle_venta detalle_venta = new Detalle_venta();
    detalle_venta.setDetvta_iddetalleventa(detvta_iddetalleventa);
    detalle_venta.setDetvta_cantidad(detvta_cantidad);
    
    Ventas venta = new Ventas();
    venta.setVen_idventa(ven_idventa);
    
    Productos producto = new Productos();
    producto.setPro_idproducto(pro_idproducto);
    
    detalle_venta.setVenta(venta);
    detalle_venta.setProducto(producto);
      
    if (Detalle_ventaControlador.modificar(detalle_venta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>