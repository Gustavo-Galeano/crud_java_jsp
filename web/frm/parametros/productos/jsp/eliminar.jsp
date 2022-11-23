<%@page import="Controller.ClienteController"%>
<%@page import="Model.Cliente"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    
    String tipo = "error";
    String mensaje = "Datos elimiados";
    
    Cliente cliente = new Cliente();
    cliente.setId_cliente(id_cliente);
    
    if (ClienteController.eliminar(cliente)) {
        tipo = "success";        
        mensaje = "Datos eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    
    out.print(obj);
    out.flush();
%>