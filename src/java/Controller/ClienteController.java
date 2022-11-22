/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Util.Conexion;
import Util.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class ClienteController {

    public static boolean agregar(Cliente cliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into clientes(nombre, apellido, ruc, telefono)values"
                    + "('" + cliente.getNombre() + "', '" + cliente.getApellido() + "', "
                    + "'" + cliente.getRuc() + "', '" + cliente.getTelefono() + "')";
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

    public static boolean eliminar(Cliente cliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " delete from clientes where id_cliente = " + cliente.getId_cliente() + " ";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }

    public static boolean editar(Cliente cliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " update clientes set nombre = '" + cliente.getNombre() + "', apellido='"
                    + cliente.getApellido() + "', "
                    + " ruc='" + cliente.getRuc() + "', telefono='" + cliente.getTelefono() + "'  where id_cliente='"
                    + cliente.getId_cliente() + "' ";
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

    public static Cliente buscarId(Cliente cliente) {
        if (Conexion.conectar()) {
            String sql = " select * from clientes where id_cliente = '" + cliente.getId_cliente() + "' ";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setRuc(rs.getString("ruc"));
                    cliente.setTelefono(rs.getString("telefono"));
                } else {
                    cliente.setId_cliente(0);
                    cliente.setNombre("");
                    cliente.setApellido("");
                    cliente.setRuc("");
                    cliente.setTelefono("");
                }
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return cliente;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from clientes where upper(nombre) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_cliente offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->" + "\n" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";

                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre") + "</td>"
                                + "<td>" + rs.getString("apellido") + "</td>"
                                + "<td>" + rs.getString("ruc") + "</td>"
                                + "<td>" + rs.getString("telefono") + "</td>"
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
