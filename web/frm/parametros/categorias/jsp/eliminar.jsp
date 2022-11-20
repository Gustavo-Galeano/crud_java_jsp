<%@page import="Controller.CategoriaController"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Model.Categoria"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    
    String tipo = "error";
    String mensaje = "Datos elimiados";
    
    Categoria categoria = new Categoria();
    categoria.setId_categoria(id_categoria);
    
    if (CategoriaController.eliminar(categoria)) {
        tipo = "success";        
        mensaje = "Datos eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    
    out.print(obj);
    out.flush();
%>