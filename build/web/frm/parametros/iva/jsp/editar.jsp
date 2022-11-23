
<%@page import="Controller.IvaController"%>
<%@page import="Model.Iva"%>
<%@page import="Controller.ClienteController"%>
<%@page import="Model.Cliente"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_iva = Integer.parseInt(request.getParameter("id_iva"));
    int valor = Integer.parseInt(request.getParameter("valor_iva"));
    String nombre = request.getParameter("nombre_iva");

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Iva iva = new Iva();
    iva.setId_iva(id_iva);
    iva.setValor(valor);
    iva.setNombre(nombre);
    
    if (IvaController.editar(iva)) {
        tipo = "success";
        mensaje = "Datos modificados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>