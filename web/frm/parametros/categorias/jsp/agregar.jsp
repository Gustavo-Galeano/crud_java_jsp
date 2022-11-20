<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controller.CategoriaController"%>
<%@page import="Model.Categoria"%>
<%@page import="java.sql.ResultSet"%>

<%
int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    String descripcion = request.getParameter("descripcion_categoria");

    String tipo = "error";
    String mensaje = "Error al cargar los datos";

    Categoria categoria = new Categoria();
    categoria.setId_categoria(id_categoria);
    categoria.setDescripcion(descripcion);

    if (CategoriaController.agregar(categoria)) {
        tipo = "success";
        mensaje = "Datos insertados con exito";
    }
    
    JSONObject objeto =  new JSONObject();
    objeto.put("tipo", tipo);
    objeto.put("mensaje", mensaje);
    out.print(objeto);
    out.flush();
%>