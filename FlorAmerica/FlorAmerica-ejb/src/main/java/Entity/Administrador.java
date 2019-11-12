/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jonathan
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "buscarCedula", query = "SELECT ADMIN FROM Administrador admin WHERE ADMIN.cedula = :cedula"),
    @NamedQuery(name = "traerCedulaAdministrador", query = "SELECT ADMIN.cedula FROM Administrador admin")
})
public class Administrador implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    
    @Column
    private String nombre;
    
    @Column
    private String cargo;
    
    @Column
    private String cedula;
    
    @Column
    private String contrasena;
    
    @Column
    private float salario;

    public Administrador() {
    }

    public Administrador(String nombre, String cargo, String cedula, String contrasena, float salario) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    
    
    
}
