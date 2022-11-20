<%@page import="Controller.ClienteController"%>
<%@page import="Model.Cliente"%>
<%@page import="Controller.ProveedorController"%>
<%@page import="Model.Proveedor"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controller.CategoriaController"%>
<%@page import="Model.Categoria"%>
<%@page import="java.sql.ResultSet"%>

<%
    //int id_proveedor = Integer.parseInt(request.getParameter("id_personal"));
    String nombre = request.getParameter("nombre_cliente");
    String apellido = request.getParameter("apellido_cliente");
    String ruc = request.getParameter("ruc_cliente");
    String telefono = request.getParameter("telefono_cliente");
    
    
    String tipo = "error";
    String mensaje = "Error al cargar los datos";

    Cliente cliente = new Cliente();
    cliente.setNombre(nombre);
    cliente.setApellido(apellido);
    cliente.setRuc(ruc);
    cliente.setTelefono(telefono);

    if (ClienteController.agregar(cliente)) {
        tipo = "success";
        mensaje = "Datos insertados con exito";
    }

    JSONObject objeto = new JSONObject();
    objeto.put("tipo", tipo);
    objeto.put("mensaje", mensaje);
    out.print(objeto);
    out.flush();
%>