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
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 *
 * @author PC
 */
public class DetalleController {

    public static Detalle buscarId(int id) {
        Detalle detalle_venta = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalleVentas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta"
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_detventa=?";

//                System.out.println("detalle" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalle_venta = new Detalle();
                        detalle_venta.setId_detalleVenta(rs.getInt("id_detventa"));
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

    public static String buscarIdFactura(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
//                String sql = "select * from detalleVentas dv "
//                        + "left join ventas v on v.id_venta=dv.id_venta "
//                        + "left join productos p on p.id_producto=dv.id_producto "
//                        + "where dv.id_venta=" + id + " "
//                        + "order by id_detventa";

                String sql = "select * from detalleventas dv "
                        + "inner join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detventa";

                System.out.println("detalle--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###.00");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detventa") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detventa") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>" + df.format(total) + "</td></tr>";
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
;
}
