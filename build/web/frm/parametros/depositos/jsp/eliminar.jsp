<%@page import="Controller.DepositoController"%>
<%@page import="Model.Deposito"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_deposito = Integer.parseInt(request.getParameter("id_deposito"));
    
    String tipo = "error";
    String mensaje = "Datos elimiados";
    
    Deposito deposito = new Deposito();
    deposito.setId_deposito(id_deposito);
    
    if (DepositoController.eliminar(deposito)) {
        tipo = "success";        
        mensaje = "Datos eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    
    out.print(obj);
    out.flush();
%>