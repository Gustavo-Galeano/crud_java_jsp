/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.Iva;
import Util.Conexion;
import Util.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class IvaController {

    public static boolean agregar(Iva iva) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into iva(valor, nombre) values ('" + iva.getValor() + "', '" + iva.getNombre() + "')";
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

    public static boolean eliminar(Iva iva) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " delete from iva where id_iva = " + iva.getId_iva() + " ";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }

    public static boolean editar(Iva iva) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " update iva set valor = '" + iva.getValor() + "', nombre='" + iva.getNombre() + "' "
                    + "where id_iva='" + iva.getId_iva() + "' ";
            System.out.println("-->" + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }

    public static Iva buscarId(Iva iva) {
        if (Conexion.conectar()) {
            String sql = " select * from iva where id_iva = '" + iva.getId_iva() + "' ";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    iva.setId_iva(rs.getInt("id_iva"));
                    iva.setValor(rs.getInt("valor"));
                    iva.setNombre(rs.getString("nombre"));
                } else {
                    iva.setId_iva(0);
                    iva.setValor(0);
                    iva.setNombre("");
                }
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return iva;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from iva where upper(nombre) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_iva offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->" + "\n" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";

                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_iva") + "</td>"
                                + "<td>" + rs.getString("valor") + "</td>"
                                + "<td>" + rs.getString("nombre") + "</td>"
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
