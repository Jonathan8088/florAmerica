/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jonathan
 */
public class Base {
    
    private int id;
    
    private int id_producto;
    
    private float ancho;
    
    private float largo;

    public Base() {
    }

    public Base(int id_producto, float ancho, float largo) {
        this.id_producto = id_producto;
        this.ancho = ancho;
        this.largo = largo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }
    
    
    
    
    
}
