package Controller;

import Model.Cliente;
import Model.Factura;
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
public class FacturaController {

    public static Factura buscarId(Factura factura) {
        if (Conexion.conectar()) {
            String sql = "select * from ventas v "
                    + "left join clientes c on v.id_cliente=c.id_cliente "
                    + "where id_venta=" + factura.getId_facturacion();
            System.out.println("buscarId" + sql);
            try {

                ResultSet rs = Conexion.getSt().executeQuery(sql);
                Cliente cliente = new Cliente();
                if (rs.next()) {
                    factura.setId_facturacion(rs.getInt("id_venta"));
                    //  iva.setIva_idiva(rs.getInt("iva_idiva"));
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    factura.setFecha(rs.getDate("fecha"));
                    cliente.setNombre(rs.getString("nombre"));
                    factura.setCliente(cliente);
                }

            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return factura;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas v left join clientes c on v.id_cliente=v.id_cliente "
                        + "where upper(nombre) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre") + "</td>"
//                                + "<td>" + rs.getString("fecha") + "</td>"
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

    public static boolean agregar(Factura factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = factura.getCliente().getId_cliente();
            Date v2 = factura.getFecha();
            System.out.println("v2" + v2);
            String sql = "insert into ventas(id_cliente,fecha) "
                    + "values('" + v1 + "','" + v2 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int fac_idfactura = keyset.getInt(1);
                    factura.setId_facturacion(fac_idfactura);
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
}
