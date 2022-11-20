
<%@page import="Controller.CategoriaController"%>
<%@page import="Model.Categoria"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    String descripcion = request.getParameter("descripcion_categoria");

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Categoria categoria = new Categoria();
    categoria.setId_categoria(id_categoria);
    categoria.setDescripcion(descripcion);

    if (CategoriaController.editar(categoria)) {
        tipo = "success";
        mensaje = "Datos modificados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>