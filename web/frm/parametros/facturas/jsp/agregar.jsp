
<%@page import="Controller.FacturaController"%>
<%@page import="Model.Factura"%>
<%@page import="Model.Cliente"%>
<%--<%@page import="javaweb2019.utiles.Utiles"%>--%>
<%@page import="Util.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_facturacion = Integer.parseInt(request.getParameter("id_facturacion"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String sfecha_factura = request.getParameter("fecha_factura");
    java.sql.Date fecha_factura = Utiles.stringToSqlDate(sfecha_factura);

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Cliente cliente = new Cliente();
    cliente.setId_cliente(id_cliente);

    Factura factura = new Factura();
    factura.setId_facturacion(id_facturacion);
    factura.setCliente(cliente);
    factura.setFecha(fecha_factura);

    if (FacturaController.agregar(factura)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_facturacion", String.valueOf(factura.getId_facturacion()));
    out.print(obj);
    out.flush();

%>