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

    private Categoria categoria;

    private Iva iva;

    public Producto() {
    
    }

    public Producto(int id_producto, String nombre, int precio, Categoria categoria, Iva iva) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.iva = iva;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Iva getIva() {
        return iva;
    }

    public void setIva(Iva iva) {
        this.iva = iva;
    }

    
    
}
