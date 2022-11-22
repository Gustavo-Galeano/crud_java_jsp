<%@page import="Controller.PersonalController"%>
<%@page import="Model.Personal"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_personal = Integer.parseInt(request.getParameter("id_personal"));
    
    String tipo = "error";
    String mensaje = "Datos elimiados";
    
    Personal personal = new Personal();
    personal.setId_personal(id_personal);
    
    if (PersonalController.eliminar(personal)) {
        tipo = "success";        
        mensaje = "Datos eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    
    out.print(obj);
    out.flush();
%>