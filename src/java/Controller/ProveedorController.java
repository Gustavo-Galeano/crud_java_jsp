/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categoria;
import Model.Proveedor;
import Util.Conexion;
import Util.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class ProveedorController {

    public static boolean agregar(Proveedor proveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into proveedores(nombre, email, web, direccion)values"
                    + "('" + proveedor.getNombre() + "', '" + proveedor.getEmail() + "', "
                    + "'" + proveedor.getWeb() + "', '" + proveedor.getDireccion() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
            System.out.println("valor" + valor);
        }
        return valor;
    }

    public static Proveedor buscarId(Proveedor proveedor) {
        if (Conexion.conectar()) {
            String sql = " select * from proveedores where id_proveedor = '" + proveedor.getId_proveedor() + "' ";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setEmail(rs.getString("email"));
                    proveedor.setWeb(rs.getString("web"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    
                } else {
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre("");
                    proveedor.setEmail("");
                    proveedor.setWeb("");
                    proveedor.setDireccion("");
                }
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return proveedor;
    }
    
     public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from proveedores where upper(nombre) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_proveedor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre") + "</td>"
                                + "<td>" + rs.getString("email") + "</td>"
                                + "<td>" + rs.getString("web") + "</td>"
                                + "<td>" + rs.getString("direccion") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = " <tr> <td colpsan=2>No existen registros...</td> </tr> ";
                    }
                    ps.close();
                    valor = tabla;
                } catch (Exception ex) {
                    System.err.println("error" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }
}
