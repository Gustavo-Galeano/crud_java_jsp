<%@page import="Controller.ProveedorController"%>
<%@page import="Model.Proveedor"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controller.CategoriaController"%>
<%@page import="Model.Categoria"%>
<%@page import="java.sql.ResultSet"%>

<%
    //int id_proveedor = Integer.parseInt(request.getParameter("id_personal"));
    String nombre = request.getParameter("nombre_proveedor");
    String email = request.getParameter("email_proveedor");
    String web = request.getParameter("web_proveedor");
    String direccion = request.getParameter("direccion_proveedor");
    
    
    String tipo = "error";
    String mensaje = "Error al cargar los datos";

    Proveedor proveedor = new Proveedor();
    //proveedor.setId_proveedor(id_proveedor);
    proveedor.setNombre(nombre);
    proveedor.setEmail(email);
    proveedor.setWeb(web);
    proveedor.setDireccion(direccion);
    
    if (ProveedorController.agregar(proveedor)) {
        tipo = "success";
        mensaje = "Datos insertados con exito";
    }

    JSONObject objeto = new JSONObject();
    objeto.put("tipo", tipo);
    objeto.put("mensaje", mensaje);
    out.print(objeto);
    out.flush();
%>