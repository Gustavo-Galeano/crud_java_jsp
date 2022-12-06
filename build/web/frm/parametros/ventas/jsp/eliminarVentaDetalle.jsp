

<%@page import="controladores.Detalle_ventaControlador"%>
<%@page import="modelos.Detalle_venta"%>
<%@page import="controladores.Detalle_compraControlador"%>
<%@page import="modelos.Detalle_compra"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
      int detvta_iddetalleventa = Integer.parseInt(request.getParameter("detvta_iddetalleventa"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Detalle_venta detalle_venta = new Detalle_venta();
    detalle_venta.setDetvta_iddetalleventa(detvta_iddetalleventa);

    if (Detalle_ventaControlador.eliminar(detalle_venta)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>