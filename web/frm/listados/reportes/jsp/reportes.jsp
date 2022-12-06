

<%@page import="Util.Conexion"%>
<%@page import="Util.Utiles"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page contentType="application/pdf"%>
<%
    
    String dirPath="/rpt";
    String realPath=this.getServletContext().getRealPath(dirPath);
    String listado=request.getParameter("l");
    int desde=Integer.parseInt(request.getParameter("d"));
    int hasta=Integer.parseInt(request.getParameter("h"));
    String jasperReport=listado+".jasper";
    JasperPrint print=null;
    Connection conn=null;
   // HttpSession sesion=request.getSession();
   // Usuarios usuarioLogueado=(Usuarios) sesion.getAttribute("usuarioLogueado");
    
    try{
        Conexion.conectar();
        conn=Conexion.getConn();
        Map parameters=new HashMap();
        parameters.put("DESDE",desde);
        parameters.put("HASTA",hasta);
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
