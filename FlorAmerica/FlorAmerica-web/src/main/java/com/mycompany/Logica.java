/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import Entity.Cama;
import Entity.Empleado;
import Entity.Turno;
import Interfaces.CamaFacadeLocal;
import Interfaces.EmpleadoFacadeLocal;
import Interfaces.TurnoFacadeLocal;
import Modelo.Base;
import Modelo.Trabajador;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Jonathan
 */
@Named(value = "logica")
@SessionScoped
public class Logica implements Serializable{
    
    @EJB
    EmpleadoFacadeLocal empleadoLocal;
    
    @EJB
    CamaFacadeLocal camaLocal;
    
    @EJB
    TurnoFacadeLocal turnoLocal;
    
    Empleado empleado;
    
    Cama cama;
    
    Turno turno;
    
    Trabajador trabajador;
    
    Base base;

    public Logica() {
        empleado = new Empleado();
        cama = new Cama();
        turno = new Turno();
        trabajador = new Trabajador();
        base = new Base();
    }
    
    public void crearEmpleado(){
        empleado = new Empleado("prueba", "123", "prueba", "prueba", 1000, true);
        empleadoLocal.create(empleado);
    }

    public EmpleadoFacadeLocal getEmpleadoLocal() {
        return empleadoLocal;
    }

    public void setEmpleadoLocal(EmpleadoFacadeLocal empleadoLocal) {
        this.empleadoLocal = empleadoLocal;
    }

    public CamaFacadeLocal getCamaLocal() {
        return camaLocal;
    }

    public void setCamaLocal(CamaFacadeLocal camaLocal) {
        this.camaLocal = camaLocal;
    }

    public TurnoFacadeLocal getTurnoLocal() {
        return turnoLocal;
    }

    public void setTurnoLocal(TurnoFacadeLocal turnoLocal) {
        this.turnoLocal = turnoLocal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cama getCama() {
        return cama;
    }

    public void setCama(Cama cama) {
        this.cama = cama;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
    
    

    
}
