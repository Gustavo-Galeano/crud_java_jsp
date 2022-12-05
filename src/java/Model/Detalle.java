/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PC
 */
public class Detalle {

    private int id_detalleVenta;
    private Factura factura;
    private Producto producto;
    private int iva_idiva;
    private int cantidad;

    public Detalle() {
    }

    public Detalle(int id_detalleVenta, Factura factura, Producto producto, int iva_idiva, int cantidad) {
        this.id_detalleVenta = id_detalleVenta;
        this.factura = factura;
        this.producto = producto;
        this.iva_idiva = iva_idiva;
        this.cantidad = cantidad;
    }

    public int getId_detalleVenta() {
        return id_detalleVenta;
    }

    public void setId_detalleVenta(int id_detalleVenta) {
        this.id_detalleVenta = id_detalleVenta;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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
