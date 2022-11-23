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
public class Producto {

    private int id_producto;
    private String nombre;
    private int precio;

    private int id_categoria;

    private int id_existencia;
    private int proveedor;
    private int id_iva;

    public Producto() {
    }

    public Producto(int id_producto, String nombre, int precio, int id_categoria, int id_existencia, int proveedor, int id_iva) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.id_existencia = id_existencia;
        this.proveedor = proveedor;
        this.id_iva = id_iva;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_existencia() {
        return id_existencia;
    }

    public void setId_existencia(int id_existencia) {
        this.id_existencia = id_existencia;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public int getId_iva() {
        return id_iva;
    }

    public void setId_iva(int id_iva) {
        this.id_iva = id_iva;
    }
}
