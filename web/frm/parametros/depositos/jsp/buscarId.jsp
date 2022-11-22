<%@page import="Controller.DepositoController"%>
<%@page import="Model.Deposito"%>
<%@page import="Model.Cliente"%>
<%@page import="Controller.ClienteController"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>

<%
    int id_deposito = Integer.parseInt(request.getParameter("id_deposito"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

   Deposito deposito = new Deposito();
   deposito.setId_deposito(id_deposito);
    
   deposito = DepositoController.buscarId(deposito);
    
    if (deposito.getId_deposito()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_deposito", deposito.getId_deposito());
    obj.put("nombre_deposito", deposito.getNombre());
    out.print(obj);
    out.flush();
%> 