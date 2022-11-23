<%@page import="Controller.IvaController"%>
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
    int valor = Integer.parseInt(request.getParameter("valor_iva"));
    String nombre = request.getParameter("nombre_iva");
    
    String tipo = "error";
    String mensaje = "Error al cargar los datos";

    Iva iva = new Iva();
    iva.setValor(valor);
    iva.setNombre(nombre);
    
    if (IvaController.agregar(iva)) {
        tipo = "success";
        mensaje = "Datos insertados con exito";
    }

    JSONObject objeto = new JSONObject();
    objeto.put("tipo", tipo);
    objeto.put("mensaje", mensaje);
    out.print(objeto);
    out.flush();
%>