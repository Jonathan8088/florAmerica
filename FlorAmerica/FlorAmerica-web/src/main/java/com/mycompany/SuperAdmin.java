/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import Entity.*;
import Interfaces.*;
import Modelo.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author DAVID
 */
@Named(value = "superAdmin")
@SessionScoped
public class SuperAdmin implements Serializable {

    @EJB
    AdministradorFacadeLocal administradorFacadeLocal;

    @EJB
    TurnoFacadeLocal turnoFacadeLocal;

    private Administrador administrador;

    private Turno turno;

    private Admin admin = new Admin();

    private Turnos turnos = new Turnos();

    private String cedulaAdministrador;

    private List<Turno> listaTurno;

    public SuperAdmin() {
    }

    public void crearAdministrador() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Administrador buscarCedula = administradorFacadeLocal.buscarCedula(admin.getCedula());
            context.addMessage("growl", new FacesMessage("Mensaje", "Cedula ya se Encuentra Registrada."));
        } catch (Exception ex) {
            administrador = new Administrador(admin.getNombre(), admin.getCargo(), admin.getCedula(), admin.getContrsena(), admin.getSalario());
            administradorFacadeLocal.create(administrador);
            context.addMessage("growl", new FacesMessage("Mensaje", "Administrador Guardado Correctamente."));
            vaciar();
        }

    }

    public void cargarAdministrador() {

        List<Administrador> lista = administradorFacadeLocal.findAll();
        for (Administrador lista1 : lista) {
            if (lista1.getCedula().equals(cedulaAdministrador)) {
                admin.setNombre(lista1.getNombre());
                admin.setCargo(lista1.getCargo());
                admin.setCedula(lista1.getCedula());
                admin.setContrsena(lista1.getContrasena());
                admin.setSalario(lista1.getSalario());
            }
        }

    }

    public void editarAdministrador() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            administrador = administradorFacadeLocal.buscarCedula(admin.getCedula());
            administrador.setNombre(admin.getNombre());
            administrador.setCargo(admin.getCargo());
            administrador.setCedula(admin.getCedula());
            administrador.setContrasena(admin.getContrsena());
            administrador.setSalario(admin.getSalario());
            administradorFacadeLocal.edit(administrador);
            context.addMessage("growlEdit", new FacesMessage("Mensaje", "Administrador Editado Correctamente."));
            vaciarEditar();
        } catch (Exception e) {
            context.addMessage("growlEdit", new FacesMessage("Mensaje", e.toString()));
        }
    }

    public void eliminarAdministrador() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            administrador = administradorFacadeLocal.buscarCedula(admin.getCedula());
            administrador.setNombre(admin.getNombre());
            administrador.setCargo(admin.getCargo());
            administrador.setCedula(admin.getCedula());
            administrador.setContrasena(admin.getContrsena());
            administrador.setSalario(admin.getSalario());
            administradorFacadeLocal.remove(administrador);
            context.addMessage("growlEdit", new FacesMessage("Mensaje", "Administrador Eliminado Correctamente."));
            vaciarEditar();
        } catch (Exception e) {
            context.addMessage("growlEdit", new FacesMessage("Mensaje", e.toString()));
        }
    }

    public void crearTurno() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            turno = new Turno(turnos.getDuracion(), turnos.getMeta(), turnos.getPromedioCorte(), turnos.getCamasCortadas());
            turnoFacadeLocal.create(turno);
            context.addMessage("growl", new FacesMessage("Mensaje", "Turno Creado Correctamente."));
            vaciarTurno();
        } catch (Exception e) {
            context.addMessage("growl", new FacesMessage("Mensaje", e.toString()));
        }
    }

    public List<Administrador> listaAdministradores() {
        return administradorFacadeLocal.findAll();
    }

    public List<Administrador> listaNombreAdministrador() {
        List<Administrador> lista = administradorFacadeLocal.traerCedulas();
        return lista;
    }

    @PostConstruct
    public void listaTurnos() {
        listaTurno = turnoFacadeLocal.turnoDesc();

    }

    public void editarTurno(Turno tur) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (tur.getCamas_cortadas() <= tur.getMeta()) {
                float promedio = (tur.getCamas_cortadas()*tur.getDuracion())/tur.getMeta();
                turno = turnoFacadeLocal.find(tur.getId());
                turno.setDuracion(tur.getDuracion());
                turno.setMeta(tur.getMeta());
                turno.setCamas_cortadas(tur.getCamas_cortadas());
                turno.setPromedio_corte(promedio);
                turnoFacadeLocal.edit(turno);
                context.addMessage("growl", new FacesMessage("Mensaje", "Actualizado Correctamente."));
                listaTurnos();
            }else{
                 context.addMessage("growl", new FacesMessage("Mensaje", "Las Camas Cortadas No Pueden Superar La Meta."));
            }
        } catch (Exception e) {

        }

    }

    public void cancelarEditar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage("Mensaje", "Actualizar Cancelado."));
    }

    public AdministradorFacadeLocal getAdministradorFacadeLocal() {
        return administradorFacadeLocal;
    }

    public void setAdministradorFacadeLocal(AdministradorFacadeLocal administradorFacadeLocal) {
        this.administradorFacadeLocal = administradorFacadeLocal;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getCedulaAdministrador() {
        return cedulaAdministrador;
    }

    public void setCedulaAdministrador(String cedulaAdministrador) {
        this.cedulaAdministrador = cedulaAdministrador;
    }

    public Turnos getTurnos() {
        return turnos;
    }

    public void setTurnos(Turnos turnos) {
        this.turnos = turnos;
    }

    public List<Turno> getListaTurno() {
        return listaTurno;
    }

    public void setListaTurno(List<Turno> listaTurno) {
        this.listaTurno = listaTurno;
    }

    public void vaciar() {
        admin = new Admin(0, null, null, null, null, (float) 0.0);
    }

    public void vaciarEditar() {
        admin = new Admin(0, null, null, null, null, (float) 0.0);
        cedulaAdministrador = null;
    }

    public void vaciarTurno() {
        turno = new Turno((float) 0.0, 0, (float) 0.0, (float) 0.0);
    }

}
