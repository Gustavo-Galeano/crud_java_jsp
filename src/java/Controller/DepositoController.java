/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Deposito;
import Util.Conexion;
import Util.Utiles;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DepositoController {
    public static boolean agregar(Deposito deposito) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into depositos(nombre)values('" + deposito.getNombre() + "')";
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

    public static boolean eliminar(Deposito deposito) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " delete from depositos where id_deposito = " + deposito.getId_deposito() + " ";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }

    public static boolean editar(Deposito deposito) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " update depositos set nombre = '" + deposito.getNombre()
                    + "' where id_deposito='" + deposito.getId_deposito() + "' ";
            System.out.println("-->" + "\n" + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }

    public static Deposito buscarId(Deposito deposito) {
        if (Conexion.conectar()) {
            String sql = " select * from depositos where id_deposito = '" + deposito.getId_deposito() + "' ";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    deposito.setId_deposito(rs.getInt("id_deposito"));
                    deposito.setNombre(rs.getString("nombre"));
                } else {
                    deposito.setId_deposito(0);
                    deposito.setNombre("");
                }
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return deposito;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from depositos where upper(nombre) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_deposito offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->" + "\n" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_deposito") + "</td>"
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