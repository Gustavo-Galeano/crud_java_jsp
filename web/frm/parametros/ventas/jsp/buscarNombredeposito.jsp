

<%@page import="controladores.DepositoControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    String dep_descripcion= request.getParameter("dep_bdescripcion");
    
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = DepositoControlador.buscarNombre(dep_descripcion, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>