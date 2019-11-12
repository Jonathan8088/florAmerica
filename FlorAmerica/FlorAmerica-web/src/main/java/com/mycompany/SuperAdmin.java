/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import Entity.Administrador;
import Interfaces.AdministradorFacadeLocal;
import Modelo.Admin;
import Modelo.Admin;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author DAVID
 */
@Named(value = "superAdmin")
@SessionScoped
public class SuperAdmin implements Serializable {

    @EJB
    AdministradorFacadeLocal administradorFacadeLocal;

    private Administrador administrador;

    private Admin admin = new Admin();

    private String cedulaAdministrador;

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
            context.addMessage("growlEdit", new FacesMessage("Mensaje",e.toString()));
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
            context.addMessage("growlEdit", new FacesMessage("Mensaje",e.toString()));
        }
    }

    public List<Administrador> listaAdministradores() {
        return administradorFacadeLocal.findAll();
    }

    public List<Administrador> listaNombreAdministrador() {
        List<Administrador> lista = administradorFacadeLocal.traerCedulas();
        return lista;
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

    public void vaciar() {
        admin = new Admin(0, null, null, null, null, (float) 0.0);
    }

    public void vaciarEditar() {
        admin = new Admin(0, null, null, null, null, (float) 0.0);
        cedulaAdministrador = null;
    }

}
