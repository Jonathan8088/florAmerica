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
    @NamedQuery(name = "traerEmpleados", query = "SELECT emp FROM Empleado emp"),
    @NamedQuery(name = "traerEmpleado", query = "SELECT emp FROM Empleado emp WHERE emp.cedula = :cedula")
})
public class Empleado implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    
    @Column
    private String nombre;
    
    @Column
    private String cedula;
    
    @Column
    private String contrasena;
    
    @Column
    private String area;
    
    @Column
    private float salario;
    
    @Column
    private boolean estado;

    public Empleado() {
    }

    public Empleado(String nombre, String cedula, String contrasena, String area, float salario, boolean estado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.area = area;
        this.salario = salario;
        this.estado = estado;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
