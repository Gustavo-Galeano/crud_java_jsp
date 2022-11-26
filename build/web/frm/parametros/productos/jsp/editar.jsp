
<%@page import="Controller.ProductoController"%>
<%@page import="Model.Producto"%>
<%@page import="Model.Categoria"%>
<%@page import="Model.Iva"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String nombre = request.getParameter("nombre_producto");
    int precio = Integer.parseInt(request.getParameter("precio_producto"));
    
    int id_iva = Integer.parseInt(request.getParameter("id_iva"));
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
 
    String tipo = "error";
    String mensaje = "Error al agregar los datos";
    
    Iva iva = new Iva();
    iva.setId_iva(id_iva);
    
    Categoria categoria = new Categoria();
    categoria.setId_categoria(id_categoria);
   
    Producto producto = new Producto();
    producto.setId_producto(id_producto);
    producto.setNombre(nombre);
    producto.setPrecio(precio);
    producto.setIva(iva);
    producto.setCategoria(categoria);

    if (ProductoController.editar(producto)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>