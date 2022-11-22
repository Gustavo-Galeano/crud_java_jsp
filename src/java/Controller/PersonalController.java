/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Personal;
import Util.Conexion;
import Util.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class PersonalController {

    public static boolean agregar(Personal personal) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into personales(nombre, apellido, cedula, telefono, email, clave) "
                    + "values('" + personal.getNombre() + "', '" + personal.getApellido() + "', '"
                    + personal.getCedula() + "', '" + personal.getTelefono() + "', '" + personal.getEmail() + "', '"
                    + personal.getClave() + "' ) ";
            System.out.println("Mostrando el error \t" + sql);
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

    public static boolean eliminar(Personal personal) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " delete from personales where id_personal = " + personal.getId_personal()+ " ";
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

    public static boolean editar(Personal personal) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " update personales set nombre = '" + personal.getNombre() + "', apellido='"
                    + personal.getApellido() + "', "
                    + " cedula='" + personal.getClave()+ "', telefono='" + personal.getTelefono() + "', "
                    + " email='" + personal.getEmail()+ "', clave='" + personal.getClave()+ "'  where id_personal='"
                    + personal.getId_personal() + "' ";
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

    public static Personal buscarId(Personal personal) {
        if (Conexion.conectar()) {
            String sql = " select * from personales where id_personal = '" + personal.getId_personal() + "' ";
            System.out.println("resultado" + "\n" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    personal.setId_personal(rs.getInt("id_personal"));
                    personal.setNombre(rs.getString("nombre"));
                    personal.setApellido(rs.getString("apellido"));
                    personal.setCedula(rs.getInt("cedula"));
                    personal.setTelefono(rs.getString("telefono"));
                    personal.setEmail(rs.getString("email"));
                    personal.setClave(rs.getString("clave"));
                    // categoria.setId_categoria(rs.getInt("id_categoria"));
                    // categoria.setDescripcion(rs.getString("descripcion"));
                } else {
                    personal.setId_personal(0);
                    personal.setNombre("");
                    personal.setApellido("");
                    personal.setCedula(0);
                    personal.setTelefono("");
                    personal.setEmail("");
                    personal.setClave("");
                    // categoria.setId_categoria(0);
                    // categoria.setDescripcion("");
                }
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return personal;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from personales where upper(nombre) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_personal offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->" + "\n" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_personal") + "</td>"
                                + "<td>" + rs.getString("nombre") + "</td>"
                                + "<td>" + rs.getString("apellido") + "</td>"
                                + "<td>" + rs.getString("cedula") + "</td>"
                                + "<td>" + rs.getString("telefono") + "</td>"
                                + "<td>" + rs.getString("email") + "</td>"
                                + "<td>" + rs.getString("clave") + "</td>"
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
