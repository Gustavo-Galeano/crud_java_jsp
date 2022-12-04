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
public class Personal {

    private int id_personal;
    private String nombre;
    private String apellido;
    private int cedula;
    private String telefono;
    private String email;
        private String login_personal;
    private String password_personal;

    public Personal() {

    }

    public Personal(int id_personal, String nombre, String apellido, int cedula, String telefono, String email, String login_personal, String password_personal) {
        this.id_personal = id_personal;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.email = email;
        this.login_personal = login_personal;
        this.password_personal = password_personal;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin_personal() {
        return login_personal;
    }

    public void setLogin_personal(String login_personal) {
        this.login_personal = login_personal;
    }

    public String getPassword_personal() {
        return password_personal;
    }

    public void setPassword_personal(String password_personal) {
        this.password_personal = password_personal;
    }


}
    