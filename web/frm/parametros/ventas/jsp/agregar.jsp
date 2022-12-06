

<%@page import="utiles.utiles"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Clientes"%>
<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Categorias"%>
<%@page import="controladores.CategoriasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int cli_id = Integer.parseInt(request.getParameter("cli_id"));
    String sven_fecha = request.getParameter("ven_fecha");
    java.sql.Date ven_fecha = utiles.stringToSqlDate(sven_fecha);


    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Clientes cliente = new Clientes();
    cliente.setCli_id(cli_id);

    Ventas venta = new Ventas();
    venta.setCliente(cliente);
    venta.setVen_fecha(ven_fecha);
    

    if (VentasControlador.agregar(venta)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("ven_idventa", String.valueOf(venta.getVen_idventa()));
    out.print(obj);
    out.flush();
%>