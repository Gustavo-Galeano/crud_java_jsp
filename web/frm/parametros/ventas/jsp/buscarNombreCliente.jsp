

<%@page import="controladores.ClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String cli_nombre = request.getParameter("bnombre_cliente");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    System.out.println("");
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = ClientesControlador.buscarNombre(cli_nombre, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>