<%@page import="Model.Personal"%>
<%@page import="org.json.simple.JSONObject"%>
<%--<%@page import="Modelo.Usuarios"%>--%>
<%@page import="java.sql.ResultSet"%>

<%
    HttpSession sesion = request.getSession();
    int id_personal = 0;
    String login_personal = "";
    String nombre = "";
    String activo = "false";
    String mensaje = "La sesion esta cerrada.";
    Personal usuarioLogueado = (Personal) sesion.getAttribute("usuarioLogueado");
    if (usuarioLogueado != null) {
        id_personal = usuarioLogueado.getId_personal();
        login_personal= usuarioLogueado.getLogin_personal();
        nombre = usuarioLogueado.getNombre();
        activo = "true";
        mensaje = "Sesion abierta.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("activo", activo);
    obj.put("id_personal", id_personal);
    obj.put("login_personal", login_personal);
    obj.put("nombre", nombre);
    out.print(obj);
    out.flush();
%>