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
public class Factura {

    private int id_facturacion;
    private String fecha;
    private String estado;
    private String numero;
    private String condicion;
    
    private Cliente cliente;
    private Personal personal;

    public Factura() {
    }

    public Factura(int id_facturacion, String fecha, String estado, String numero, String condicion, Cliente cliente, Personal personal) {
        this.id_facturacion = id_facturacion;
        this.fecha = fecha;
        this.estado = estado;
        this.numero = numero;
        this.condicion = condicion;
        this.cliente = cliente;
        this.personal = personal;
    }

    public int getId_facturacion() {
        return id_facturacion;
    }

    public void setId_facturacion(int id_facturacion) {
        this.id_facturacion = id_facturacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    
    
}
