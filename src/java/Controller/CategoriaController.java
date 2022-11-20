/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categoria;
import Util.Conexion;
import Util.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class CategoriaController {

    public static boolean agregar(Categoria categoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into categorias(descripcion)values"
                    + "('" + categoria.getDescripcion() + "')";
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

    public static boolean eliminar(Categoria categoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " delete from categorias where id_categoria = " + categoria.getId_categoria() + " ";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }
    
    public static boolean editar(Categoria categoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
          String sql = " update categorias set descripcion = '"+categoria.getDescripcion()+"' where id_categoria='"+categoria.getId_categoria()+"' ";
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
    
    public static Categoria buscarId(Categoria categoria) {
        if (Conexion.conectar()) {
            String sql = " select * from categorias where id_categoria = '" + categoria.getId_categoria() + "' ";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setDescripcion(rs.getString("descripcion"));
                } else {
                    categoria.setId_categoria(0);
                    categoria.setDescripcion("");
                }
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return categoria;
    }
    
    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from categorias where upper(descripcion) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_categoria offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";

                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_categoria") + "</td>"
                                + "<td>" + rs.getString("descripcion") + "</td>"
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
