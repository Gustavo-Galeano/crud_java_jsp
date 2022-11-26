<%@page import="Controller.ProductoController"%>
<%@page import="Model.Producto"%>
<%@page import="Controller.ClienteController"%>
<%@page import="Model.Cliente"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    
    String tipo = "error";
    String mensaje = "Datos elimiados";
    
    Producto producto = new Producto();
    producto.setId_producto(id_producto);
    
    if (ProductoController.eliminar(producto)) {
        tipo = "success";        
        mensaje = "Datos eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    
    out.print(obj);
    out.flush();
%>