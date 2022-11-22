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
public class Deposito {
    private int id_deposito;
    private String nombre;

    public Deposito() {
    }

    public Deposito(int id_deposito, String nombre) {
        this.id_deposito = id_deposito;
        this.nombre = nombre;
    }

    public int getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(int id_deposito) {
        this.id_deposito = id_deposito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
