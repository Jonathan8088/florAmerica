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
    @NamedQuery(name = "listaTurnoDesc", query = "SELECT TUR FROM Turno tur ORDER BY tur.id DESC")
})
public class Turno implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    
    @Column
    private float duracion;
    
    @Column
    private int meta;
    
    @Column
    private float promedio_corte;
    
    @Column
    private float camas_cortadas;

    public Turno() {
    }

    public Turno(float duracion, int meta, float promedio_corte, float camas_cortadas) {
        this.duracion = duracion;
        this.meta = meta;
        this.promedio_corte = promedio_corte;
        this.camas_cortadas = camas_cortadas;
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

    public float getPromedio_corte() {
        return promedio_corte;
    }

    public void setPromedio_corte(float promedio_corte) {
        this.promedio_corte = promedio_corte;
    }

    public float getCamas_cortadas() {
        return camas_cortadas;
    }

    public void setCamas_cortadas(float camas_cortadas) {
        this.camas_cortadas = camas_cortadas;
    }
    
    
    
}
