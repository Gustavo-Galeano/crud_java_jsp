<%@page import="Controller.CategoriaController"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Model.Categoria"%>
<%@page import="java.sql.ResultSet" %>

<%
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

    Categoria categoria = new Categoria();
    categoria.setId_categoria(id_categoria);
    
    categoria = CategoriaController.buscarId(categoria);
    
    if (categoria.getId_categoria() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_categoria", categoria.getId_categoria());
    obj.put("descripcion_categoria", categoria.getDescripcion());

    out.print(obj);
    out.flush();
%> 