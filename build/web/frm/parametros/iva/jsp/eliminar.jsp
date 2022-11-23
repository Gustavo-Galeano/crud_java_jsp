<%@page import="Controller.IvaController"%>
<%@page import="Model.Iva"%>
<%@page import="Controller.ClienteController"%>
<%@page import="Model.Cliente"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_iva = Integer.parseInt(request.getParameter("id_iva"));
    
    String tipo = "error";
    String mensaje = "Datos elimiados";
    
    Iva iva = new Iva();
    iva.setId_iva(id_iva);
    
    if (IvaController.eliminar(iva)) {
        tipo = "success";        
        mensaje = "Datos eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    
    out.print(obj);
    out.flush();
%>