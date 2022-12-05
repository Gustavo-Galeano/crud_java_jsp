/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Detalle;
import Model.Factura;
import Model.Producto;
import Util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DetalleController {

    public static Detalle buscarId(int id) {
        Detalle detalle_venta = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalleVenta dv "
                        + "left join ventas v on v.id_venta=dv.id_venta"
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_detalleVenta=?";

                System.out.println("detalle" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalle_venta = new Detalle();
                        detalle_venta.setId_detalleVenta(rs.getInt("detvta_iddetalleventa"));
                        detalle_venta.setCantidad(rs.getInt("detvta_cantidad"));

                        Producto producto = new Producto();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre(rs.getString("nombre"));
                        detalle_venta.setProducto(producto);

                        Factura factura = new Factura();
                        factura.setId_facturacion(rs.getInt("id_venta"));
                        detalle_venta.setFactura(factura);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalle_venta;

    }
}
