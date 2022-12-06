

<%@page import="controladores.VentasControlador"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="controladores.CategoriasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  
    String nombre_venta = request.getParameter("ven_bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "B?squeda exitosa.";
    String contenido = VentasControlador.buscarNombre(nombre_venta, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>