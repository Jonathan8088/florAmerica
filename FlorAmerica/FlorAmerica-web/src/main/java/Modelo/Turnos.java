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
public class Turnos {
    
    private int id;
    
    private float duracion;
    
    private int meta;
    
    private float promedioCorte;
    
    private float camasCortadas;

    public Turnos() {
    }

    public Turnos(int id, float duracion, int meta, float promedioCorte, float camasCortadas) {
        this.id = id;
        this.duracion = duracion;
        this.meta = meta;
        this.promedioCorte = promedioCorte;
        this.camasCortadas = camasCortadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public float getPromedioCorte() {
        return promedioCorte;
    }

    public void setPromedioCorte(float promedioCorte) {
        this.promedioCorte = promedioCorte;
    }

    public float getCamasCortadas() {
        return camasCortadas;
    }

    public void setCamasCortadas(float camasCortadas) {
        this.camasCortadas = camasCortadas;
    }
    
    
}
