
<%@page import="Controller.IvaController"%>
<%@page import="Controller.ClienteController"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%

    String nombre = request.getParameter("bnombre_iva");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Busqueda exitosa";
    String contenido = IvaController.buscarNombre(nombre, pagina);
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>