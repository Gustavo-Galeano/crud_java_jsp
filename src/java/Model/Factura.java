/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class Factura {

    private int id_facturacion;
    private Date fecha;
    
    private Cliente cliente;
    private Personal personal;

    public Factura() {
    }

    public int getId_facturacion() {
        return id_facturacion;
    }

    public void setId_facturacion(int id_facturacion) {
        this.id_facturacion = id_facturacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Factura(int id_facturacion, Date fecha, Cliente cliente, Personal personal) {
        this.id_facturacion = id_facturacion;
        this.fecha = fecha;
        this.cliente = cliente;
        this.personal = personal;
    }

    
}
