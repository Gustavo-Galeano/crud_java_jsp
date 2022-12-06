

<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int ven_idventa = Integer.parseInt(request.getParameter("ven_idventa"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
   Ventas venta = new Ventas();
   venta.setVen_idventa(ven_idventa);
    
    if (VentasControlador.eliminar(venta)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>