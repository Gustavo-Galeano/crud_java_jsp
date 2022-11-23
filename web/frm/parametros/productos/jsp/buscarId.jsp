<%@page import="Model.Cliente"%>
<%@page import="Controller.ClienteController"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>

<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

    Cliente cliente = new Cliente();
    cliente.setId_cliente(id_cliente);

    cliente = ClienteController.buscarId(cliente);
    
    if (cliente.getId_cliente()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cliente", cliente.getId_cliente());
    obj.put("nombre_cliente", cliente.getNombre());
    obj.put("apellido_cliente", cliente.getApellido());
    obj.put("ruc_cliente", cliente.getRuc());
    obj.put("telefono_cliente", cliente.getTelefono());
    
    out.print(obj);
    out.flush();
%> 