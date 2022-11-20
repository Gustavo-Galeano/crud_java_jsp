/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Proveedor;
import Util.Conexion;
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
                    + "('" + proveedor.getNombre() + "', '"+proveedor.getEmail()+"', "
                    + "'"+proveedor.getWeb()+"', '"+proveedor.getDireccion()+"')";
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
