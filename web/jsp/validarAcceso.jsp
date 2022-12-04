
<%@page import="Controller.PersonalController"%>
<%@page import="Model.Personal"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    String login_personal = request.getParameter("login_personal");
    String password_personal = request.getParameter("password_personal");
    String acceso = "false";
    //Usuarios usuario = new Usuarios(0, "", password_usuario, login_usuario);]
    Personal personal = new Personal();
    personal.setLogin_personal(login_personal);
    personal.setPassword_personal(password_personal);

   if (PersonalController.validarAcceso(personal, request) != null) {
        acceso = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("acceso", acceso);
    out.print(obj);
    out.flush();
%>