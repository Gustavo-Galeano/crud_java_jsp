<%@page import="Controller.IvaController"%>
<%@page import="Model.Iva"%>
<%@page import="Model.Cliente"%>
<%@page import="Controller.ClienteController"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>

<%
    int id_iva = Integer.parseInt(request.getParameter("id_iva"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

    Iva iva = new Iva();
    iva.setId_iva(id_iva);
    
    iva = IvaController.buscarId(iva);
    
    if (iva.getId_iva()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_iva", iva.getId_iva());
    obj.put("valor_iva", iva.getValor());
    obj.put("nombre_iva", iva.getNombre());
    out.print(obj);
    out.flush();
%> 