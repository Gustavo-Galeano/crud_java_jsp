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
public class Ventas {

    private int id_venta;
    private int dep_iddeposito;
    private int id_personal;
    private int id_iva;
    private Cliente cliente;
    private Date ven_fecha;
    private Deposito deposito;

    public Ventas() {
    }

    public Ventas(int id_venta, int dep_iddeposito, int id_personal, int id_iva, Cliente cliente, Date ven_fecha, Deposito deposito) {
        this.id_venta = id_venta;
        this.dep_iddeposito = dep_iddeposito;
        this.id_personal = id_personal;
        this.id_iva = id_iva;
        this.cliente = cliente;
        this.ven_fecha = ven_fecha;
        this.deposito = deposito;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getDep_iddeposito() {
        return dep_iddeposito;
    }

    public void setDep_iddeposito(int dep_iddeposito) {
        this.dep_iddeposito = dep_iddeposito;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public int getId_iva() {
        return id_iva;
    }

    public void setId_iva(int id_iva) {
        this.id_iva = id_iva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getVen_fecha() {
        return ven_fecha;
    }

    public void setVen_fecha(Date ven_fecha) {
        this.ven_fecha = ven_fecha;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }
    
    
    
}
