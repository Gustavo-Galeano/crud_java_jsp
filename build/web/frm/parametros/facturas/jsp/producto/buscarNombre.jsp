
<%@page import="Controller.ProductoController"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%

    String nombre = request.getParameter("bnombre_producto");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Busqueda exitosa";
    String contenido = ProductoController.buscarNombre(nombre, pagina);
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    System.out.println("contenido " + contenido);

    out.print(obj);
    out.flush();
%>