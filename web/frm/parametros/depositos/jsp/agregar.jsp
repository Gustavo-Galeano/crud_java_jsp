<%@page import="Controller.DepositoController"%>
<%@page import="Model.Deposito"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    //int id_proveedor = Integer.parseInt(request.getParameter("id_personal"));
    String nombre = request.getParameter("nombre_deposito");   
    
    String tipo = "error";
    String mensaje = "Error al cargar los datos";

    Deposito deposito = new Deposito();
    deposito.setNombre(nombre);

    if (DepositoController.agregar(deposito)) {
        tipo = "success";
        mensaje = "Datos insertados con exito";
    }

    JSONObject objeto = new JSONObject();
    objeto.put("tipo", tipo);
    objeto.put("mensaje", mensaje);
    out.print(objeto);
    out.flush();
%>