
<%@page import="controladores.DepositoControlador"%>
<%@page import="modelos.Deposito"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int dep_iddeposito = Integer.parseInt(request.getParameter("dep_iddeposito"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Deposito deposito = new Deposito();
    deposito.setDep_iddeposito(dep_iddeposito);
    deposito = DepositoControlador.buscarId(deposito);
    if(deposito.getDep_iddeposito()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("dep_iddeposito", deposito.getDep_iddeposito());
    obj.put("dep_descripcion", deposito.getDep_descripcion());
       
    out.print(obj);
    out.flush();
%>