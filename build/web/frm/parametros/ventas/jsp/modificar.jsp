
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Clientes"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Proveedores"%>
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int ven_idventa = Integer.parseInt(request.getParameter("ven_idventa"));
    int cli_id = Integer.parseInt(request.getParameter("cli_id"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Clientes cliente = new Clientes();
    cliente.setCli_id(cli_id);

    Ventas venta = new Ventas();
    venta.setVen_idventa(ven_idventa);
    venta.setCliente(cliente);
   
    if (VentasControlador.modificar(venta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>