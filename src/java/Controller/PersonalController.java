/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categoria;
import Model.Personal;
import Util.Conexion;
import Util.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    + "values('" + personal.getNombre() + "', '" + personal.getApellido() + "', '" + personal.getCedula() + "', '" + personal.getTelefono() + "', '" + personal.getEmail() + "', '" + personal.getClave() + "' ) ";
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

}
