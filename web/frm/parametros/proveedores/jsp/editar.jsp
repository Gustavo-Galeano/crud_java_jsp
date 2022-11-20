
<%@page import="Controller.ProveedorController"%>
<%@page import="Model.Proveedor"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    String nombre = request.getParameter("nombre_proveedor");
    String email = request.getParameter("email_proveedor");
    String web = request.getParameter("web_proveedor");
    String direccion = request.getParameter("direccion_proveedor");

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Proveedor proveedor = new Proveedor();
    proveedor.setId_proveedor(id_proveedor);
    proveedor.setNombre(nombre);
    proveedor.setEmail(email);
    proveedor.setWeb(web);
    proveedor.setDireccion(direccion);

    if (ProveedorController.editar(proveedor)) {
        tipo = "success";
        mensaje = "Datos modificados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>