/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.Ventas;
import Util.Conexion;
import Util.Utiles;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PC
 */
public class VentasControlador {

    public static Ventas buscarId(Ventas venta) {
        if (Conexion.conectar()) {
            String sql = "select * from ventas v "
                    + "left join clientes c on v.cli_id=c.cli_id "
                    + "where ven_idventa=" + venta.getId_venta();
            System.out.println("buscarId" + sql);
            try {

                ResultSet rs = Conexion.getSt().executeQuery(sql);
                Cliente cliente = new Cliente();
                if (rs.next()) {
                    venta.setId_venta(rs.getInt("ven_idventa"));
                    //  iva.setIva_idiva(rs.getInt("iva_idiva"));
                    cliente.setId_cliente(rs.getInt("cli_id"));
                    venta.setVen_fecha(rs.getDate("ven_fecha"));
//                    cliente.setCli_apellido(rs.getString("cli_apellido"));
                    cliente.setNombre(rs.getString("cli_nombre"));
//                    cliente.setCli_telefono(rs.getString("cli_telefono"));
//                    cliente.setCli_ruc(rs.getString("cli_ruc"));
//                    cliente.setCli_direccion(rs.getString("cli_direccion"));
                    venta.setCliente(cliente);
                }

            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return venta;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas v left join clientes c on v.cli_id=v.cli_id where upper(cli_nombre) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by ven_idventa "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("ven_idventa") + "</td>"
                                + "<td>" + rs.getString("cli_id") + "</td>"
                                + "<td>" + rs.getString("cli_nombre") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = venta.getCliente().getId_cliente();
            Date v2 = venta.getVen_fecha();
            System.out.println("v2" + v2);
            String sql = "insert into ventas(cli_id,ven_fecha) "
                    + "values('" + v1 + "','" + v2 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int ven_idventa = keyset.getInt(1);
                    venta.setId_venta(ven_idventa);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set ven_idventa=? "
                    + "where ven_idventa=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, venta.getCliente().getId_cliente());
                ps.setInt(2, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas where ven_idventa=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

}
