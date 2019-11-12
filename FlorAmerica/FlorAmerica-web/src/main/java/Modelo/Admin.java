/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author DAVID
 */
public class Admin {

    private int id;

    private String nombre;

    private String cargo;

    private String cedula;

    private String contrsena;

    private float salario;

    public Admin() {
    }

    public Admin(int id, String nombre, String cargo, String cedula, String contrsena, float salario) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.cedula = cedula;
        this.contrsena = contrsena;
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

    public String getContrsena() {
        return contrsena;
    }

    public void setContrsena(String contrsena) {
        this.contrsena = contrsena;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

}
