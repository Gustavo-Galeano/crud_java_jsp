<%@page import="Controller.ProductoController"%>
<%@page import="Model.Producto"%>
<%@page import="Model.Iva"%>
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
    String nombre = request.getParameter("nombre_producto");
    int precio = Integer.parseInt(request.getParameter("precio_producto"));
    
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    int id_iva = Integer.parseInt(request.getParameter("id_iva"));
        
    String tipo = "error";
    String mensaje = "Error al cargar los datos";

    Categoria categoria = new Categoria();
    categoria.setId_categoria(id_categoria);
    
    Iva iva = new Iva();
    iva.setId_iva(id_iva);
    
    Producto producto = new Producto();
//    producto.setId_producto(id_producto);
    producto.setNombre(nombre);
    producto.setPrecio(precio);
    producto.setCategoria(categoria);
    producto.setIva(iva);

    if (ProductoController.agregar(producto)) {
        tipo = "success";
        mensaje = "Datos insertados con exito";
    }

    JSONObject objeto = new JSONObject();
    objeto.put("tipo", tipo);
    objeto.put("mensaje", mensaje);
    out.print(objeto);
    out.flush();
%>