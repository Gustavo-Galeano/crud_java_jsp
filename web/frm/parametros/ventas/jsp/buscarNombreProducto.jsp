

<%@page import="controladores.ProductosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String pro_nombre = request.getParameter("pro_nombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    System.out.println("");
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = ProductosControlador.buscarNombre(pro_nombre, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>