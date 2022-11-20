<%@page import="Controller.ProveedorController"%>
<%@page import="Model.Proveedor"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>

<%
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

    Proveedor proveedor = new Proveedor();
    proveedor.setId_proveedor(id_proveedor);

    proveedor = ProveedorController.buscarId(proveedor);
    
    if (proveedor.getId_proveedor()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_proveedor", proveedor.getId_proveedor());
    obj.put("nombre_proveedor", proveedor.getNombre());
    obj.put("email_proveedor", proveedor.getEmail());
    obj.put("web_proveedor", proveedor.getWeb());
    obj.put("direccion_proveedor", proveedor.getDireccion());
    
    out.print(obj);
    out.flush();
%> 