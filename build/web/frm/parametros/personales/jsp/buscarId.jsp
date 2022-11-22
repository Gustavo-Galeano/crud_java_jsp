<%@page import="Controller.PersonalController"%>
<%@page import="Model.Personal"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>

<%
    int id_personal = Integer.parseInt(request.getParameter("id_personal"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

    Personal personal = new Personal();
    personal.setId_personal(id_personal);

    personal = PersonalController.buscarId(personal);
        
    if (personal.getId_personal()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_personal", personal.getId_personal());
    obj.put("nombre_personal", personal.getNombre());
    obj.put("apellido_personal", personal.getApellido());
    obj.put("ci_personal", personal.getCedula());
    obj.put("telefono_personal", personal.getTelefono());
    obj.put("email_personal", personal.getEmail());
    obj.put("pass_personal", personal.getClave());
    
    out.print(obj);
    out.flush();
%> 