


<%@page import="utiles.conexion"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<%@page import="java.sql.Connection"%>

<%@page contentType="application/pdf"%>
<%
    
    String dirPath="/rpt";
    String realPath=this.getServletContext().getRealPath(dirPath);
    int factura=Integer.parseInt(request.getParameter("d"));
    String jasperReport="factura02.jasper";
    JasperPrint print=null;
    Connection conn=null;
   // HttpSession sesion=request.getSession();
   // Usuarios usuarioLogueado=(Usuarios) sesion.getAttribute("usuarioLogueado");
    
    try{
        conexion.conectar();
        conn=conexion.getConn();
        Map parameters=new HashMap();
        parameters.put("FacturaN",factura);

       // parameters.put("USUARIO",usuarioLogueado.getNombre_usuario());
        print =JasperFillManager.fillReport(realPath+"//"+jasperReport, parameters,conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }catch(Exception ex){
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
    finally{
        if(conn!=null){
            conn.close();
        }
    }
%>
