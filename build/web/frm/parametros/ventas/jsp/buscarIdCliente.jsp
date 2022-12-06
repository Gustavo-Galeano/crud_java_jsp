
<%@page import="Model.Cliente"%>
<%@page import="Controller.ClienteController"%>

<%@page import="modelos.Clientes"%>
<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int cli_id = Integer.parseInt(request.getParameter("cli_id"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Cliente cliente = new Cliente();
    cliente.setId_cliente(cli_id);
    cliente = ClienteController.buscarId(cliente);
    if (cliente.getId_cliente()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("cli_id", cliente.getId_cliente());
 //   obj.put("usu_id_usuario", proveedor.getUsuarios().getUsu_id_usuario());
  //  obj.put("usu_nombre", proveedor.getUsuarios().getUsu_nombre());
  obj.put("cli_apellido", cliente.getApellido());  
  obj.put("cli_nombre", cliente.getNombre());
//  obj.put("cli_telefono", cliente.getCli_telefono());
    obj.put("cli_ruc", cliente.getRuc());
//    obj.put("cli_direccion", cliente.getCli_direccion());
    
    

    out.print(obj);
    out.flush();
%>