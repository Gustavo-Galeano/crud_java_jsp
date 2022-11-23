
<%@page import="Controller.ClienteController"%>
<%@page import="Model.Cliente"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String nombre = request.getParameter("nombre_cliente");
    String apellido = request.getParameter("apellido_cliente");
    String ruc = request.getParameter("ruc_cliente");
    String telefono = request.getParameter("telefono_cliente");

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Cliente cliente = new Cliente();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre(nombre);
    cliente.setApellido(apellido);
    cliente.setRuc(ruc);
    cliente.setTelefono(telefono);

    if (ClienteController.editar(cliente)) {
        tipo = "success";
        mensaje = "Datos modificados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>