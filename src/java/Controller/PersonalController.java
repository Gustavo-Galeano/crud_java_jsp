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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class PersonalController {

    public static Personal validarAcceso(Personal personal, HttpServletRequest request) {
        if (Conexion.conectar()) {
            try {
                String sql = "select * from personales  "
                        + "where login_personal='" + Utiles.quitarGuiones(personal.getLogin_personal())
                        + "' and password_personal='" + Utiles.md5(Utiles.quitarGuiones(personal.getPassword_personal())) + "'";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    System.out.println("---> " + sql);
                    if (rs.next()) {
                        HttpSession sesion = request.getSession(true);

                        personal = new Personal();
                        personal.setId_personal(rs.getInt("id_usuario"));
                        personal.setNombre(rs.getString("nombre"));
                        personal.setApellido(rs.getString("apellido"));
                        personal.setCedula(rs.getInt("cedula"));
                        personal.setTelefono(rs.getString("telefono"));
                        personal.setEmail(rs.getString("email"));
                        
                        personal.setPassword_personal(rs.getString("nombre_usuario"));
                        personal.setPassword_personal(rs.getString("password_usuario"));

                        sesion.setAttribute("usuarioLogueado", personal);
                    } else {
                        personal = null;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return personal;
    }

    public static boolean agregar(Personal personal) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into personales(nombre, apellido, cedula, telefono, email, password_personal, login_personal) "
                    + "values('" + personal.getNombre() + "', '" + personal.getApellido() + "', '"
                    + personal.getCedula() + "', '" + personal.getTelefono() + "', '" + personal.getEmail() + "', '"
                    + personal.getPassword_personal() + "', '" + personal.getLogin_personal() + "', ) ";
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
            String sql = " delete from personales where id_personal = " + personal.getId_personal() + " ";
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
            String sql = " update personales "
                    + "set nombre = '" + personal.getNombre() + "', "
                    + "apellido='" + personal.getApellido() + "', "
                    + "cedula='" + personal.getCedula() + "', "
                    + "telefono='" + personal.getTelefono() + "', "
                    + "email='" + personal.getEmail() + "', "
                    + "password_personal='" + personal.getPassword_personal() + "', "
                    + "login_personal='" + personal.getLogin_personal() + "' where id_personal='" + personal.getId_personal() + "' ";
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
                    personal.setPassword_personal(rs.getString("password_personal"));
                    personal.setLogin_personal(rs.getString("login_personal"));
                    // categoria.setId_categoria(rs.getInt("id_categoria"));
                    // categoria.setDescripcion(rs.getString("descripcion"));
                } else {
                    personal.setId_personal(0);
                    personal.setNombre("");
                    personal.setApellido("");
                    personal.setCedula(0);
                    personal.setTelefono("");
                    personal.setEmail("");
                    personal.setPassword_personal("");
                    personal.setLogin_personal("");
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
                                + "<td>" + rs.getString("password_personal") + "</td>"
                                + "<td>" + rs.getString("login_personal") + "</td>"
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
