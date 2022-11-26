/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Producto;
import Model.Categoria;
import Model.Iva;
import Util.Conexion;
// import Util.Utiles;
import Util.Utiles;

import java.sql.SQLException;
import java.sql.PreparedStatement;
// import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class ProductoController {

    public static boolean agregar(Producto producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " insert into productos(nombre, precio, id_categoria, id_iva)"
                    + "values ('" + producto.getNombre() + "', '" + producto.getPrecio() + "', "
                    + " '" + producto.getCategoria().getId_categoria() + "', '" + producto.getIva().getId_iva() + "' )";
            // System.out.println("error" + "\n" + sql);
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

    public static Producto buscarId(Producto producto) {
        if (Conexion.conectar()) {
            String sql = " select * from productos p, categorias c, iva i "
                    + "where p.id_categoria=c.id_categoria and p.id_iva=i.id_iva and id_producto= '"
                    + producto.getId_producto()
                    + "'  ";
            // System.out.println("sql" + "\n" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                Categoria categoria = new Categoria();
                Iva iva = new Iva();
                if (rs.next()) {
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getInt("precio"));

                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setDescripcion(rs.getString("descripcion"));

                    iva.setId_iva(rs.getInt("id_iva"));
                    iva.setValor(rs.getInt("valor"));

                    producto.setCategoria(categoria);
                    producto.setIva(iva);
                } else {
                    producto.setId_producto(0);
                    producto.setNombre(rs.getString(""));
                    producto.setPrecio(rs.getInt(""));

                    categoria.setId_categoria(rs.getInt(0));
                    categoria.setDescripcion(rs.getString(""));

                    iva.setId_iva(rs.getInt(""));
                    iva.setValor(rs.getInt(""));

                    producto.setCategoria(categoria);
                    producto.setIva(iva);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                // and upper(nombre) like '%" + nombre.toUpperCase() + "%'
                String sql = "select * from productos p, categorias c, iva i where  "
                        + "p.id_categoria=c.id_categoria  and p.id_iva=i.id_iva "
                        + " order by id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                // System.out.println("Consulta buscar nombre" + "\n" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre") + "</td>"
                                + "<td>" + rs.getString("precio") + "</td>"
                                + "<td>" + rs.getString("valor") + "</td>"
                                + "<td>" + rs.getString("descripcion") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error:" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean editar(Producto producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " update productos set nombre='" + producto.getNombre() + "', "
                    + " precio='" + producto.getPrecio() + "', "
                    + " id_iva='" + producto.getIva().getId_iva() + "',"
                    + " id_categoria='" + producto.getCategoria().getId_categoria() + "'"
                    + "where id_producto = " + producto.getId_producto();
            // System.out.println("Modificando producto" + "\n" + sql);
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

    public static boolean eliminar(Producto producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = " delete from productos where id_producto = " + producto.getId_producto() + " ";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("error" + ex);
            }
        }
        return valor;
    }
}
