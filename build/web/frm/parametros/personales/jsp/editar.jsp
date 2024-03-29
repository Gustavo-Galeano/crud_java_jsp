
<%@page import="Controller.PersonalController"%>
<%@page import="Model.Personal"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_personal = Integer.parseInt(request.getParameter("id_personal"));
    String nombre = request.getParameter("nombre_personal");
    String apellido = request.getParameter("apellido_personal");
    int ci = Integer.parseInt(request.getParameter("ci_personal"));
    String telefono = request.getParameter("telefono_personal");
    String email = request.getParameter("email_personal");
    String clave = request.getParameter("pass_personal");

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Personal personal = new Personal();
    personal.setId_personal(id_personal);
    personal.setNombre(nombre);
    personal.setApellido(apellido);
    personal.setCedula(ci);
    personal.setTelefono(telefono);
    personal.setEmail(email);
    personal.setClave(clave);
    
    if (PersonalController.editar(personal)) {
        tipo = "success";
        mensaje = "Datos modificados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>