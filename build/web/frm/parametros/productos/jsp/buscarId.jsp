<%@page import="java.sql.ResultSet" %>
<%@page import="Controller.ProductoController"%>
<%@page import="Model.Producto"%>
<%@page import="Model.Categoria"%>
<%@page import="org.json.simple.JSONObject"%>


<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

    Producto producto = new Producto();
    producto.setId_producto(id_producto);
    producto = ProductoController.buscarId(producto);

    if (producto.getId_producto() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_producto", producto.getId_producto());
    obj.put("nombre_producto", producto.getNombre());
    obj.put("precio_producto", producto.getPrecio());

    obj.put("id_categoria", producto.getCategoria().getId_categoria());
    obj.put("descripcion_categoria", producto.getCategoria().getDescripcion());

    obj.put("id_iva", producto.getIva().getId_iva());
    obj.put("valor_iva", producto.getIva().getValor());

    out.print(obj);
    out.flush();

%> 