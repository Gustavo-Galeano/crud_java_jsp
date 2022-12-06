
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int pro_idproducto = Integer.parseInt(request.getParameter("pro_idproducto"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setPro_idproducto(pro_idproducto);
    producto = ProductosControlador.buscarId(producto);
    if (producto.getPro_idproducto()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("pro_idproducto", producto.getPro_idproducto());
    obj.put("cat_idcategoria", producto.getCategoria().getCat_idcat());
    obj.put("cat_nombre", producto.getCategoria().getCat_nombre());
    obj.put("iva_idiva", producto.getIva().getIva_idiva());
    obj.put("iva_iva_valor",producto.getIva().getIva_valor());
    obj.put("pro_nombre", producto.getPro_nombre());
    obj.put("pro_preciocompra", producto.getPro_preciocompra());
    obj.put("pro_precioventa", producto.getPro_precioventa());
  
    out.print(obj);
    out.flush();
%>