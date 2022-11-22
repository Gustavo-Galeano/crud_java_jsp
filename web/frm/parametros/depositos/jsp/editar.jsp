
<%@page import="Controller.DepositoController"%>
<%@page import="Model.Deposito"%>
<%@page import="Controller.ClienteController"%>
<%@page import="Model.Cliente"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_deposito = Integer.parseInt(request.getParameter("id_deposito"));
    String nombre = request.getParameter("nombre_deposito");
    
    String tipo = "error";
    String mensaje = "Datos no modificados";

    Deposito deposito = new Deposito();
    deposito.setId_deposito(id_deposito);
    deposito.setNombre(nombre);

    if (DepositoController.editar(deposito)) {
        tipo = "success";
        mensaje = "Datos modificados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>