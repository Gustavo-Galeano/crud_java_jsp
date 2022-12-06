/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.Conexion;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DetalleVentas {

    private int id_detventa;
    private Ventas venta;
    private Producto producto;
    private int iva_idiva;
    private int cantidad;

    public DetalleVentas() {
    }

    public DetalleVentas(int id_detventa, Ventas venta, Producto producto, int iva_idiva, int cantidad) {
        this.id_detventa = id_detventa;
        this.venta = venta;
        this.producto = producto;
        this.iva_idiva = iva_idiva;
        this.cantidad = cantidad;
    }

    public int getId_detventa() {
        return id_detventa;
    }

    public void setId_detventa(int id_detventa) {
        this.id_detventa = id_detventa;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getIva_idiva() {
        return iva_idiva;
    }

    public void setIva_idiva(int iva_idiva) {
        this.iva_idiva = iva_idiva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
