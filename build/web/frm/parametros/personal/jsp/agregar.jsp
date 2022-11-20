<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controller.PersonalController"%>
<%@page import="Model.Personal"%>
<%@page import="java.sql.ResultSet"%>

<%
//    int id_personal = Integer.parseInt(request.getParameter("id_personal"));
    String nombre = request.getParameter("nombre_personal");
    String apellido = request.getParameter("apellido_personal");
//    String cedula = request.getParameter("ci_personal");
    int cedula = Integer.parseInt(request.getParameter("ci_personal"));
//    String telefono = request.getParameter("telefono_personal");
    String telefono = request.getParameter("telefono_personal");
    String email = request.getParameter("email_personal");
    String pass = request.getParameter("pass_personal");

    String tipo = "error";
    String mensaje = "Error al cargar los datos";

    Personal personal = new Personal();
//    personal.setId_personal(id_personal);
    personal.setNombre(nombre);
    personal.setApellido(apellido);
    personal.setCedula(cedula);
    personal.setTelefono(telefono);
    personal.setEmail(email);
    personal.setClave(pass);

    if (PersonalController.agregar(personal)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }
    
    JSONObject datos = new JSONObject();
    datos.put("tipo", tipo);
    datos.put("mensaje", mensaje);
    out.print(datos);
    out.flush();
%>