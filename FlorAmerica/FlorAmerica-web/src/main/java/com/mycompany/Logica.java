/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import Entity.Administrador;
import Entity.Cama;
import Entity.Empleado;
import Entity.Producto;
import Entity.SuperAdministrador;
import Entity.Turno;
import Interfaces.AdministradorFacadeLocal;
import Interfaces.CamaFacadeLocal;
import Interfaces.EmpleadoFacadeLocal;
import Interfaces.ProductoFacadeLocal;
import Interfaces.SuperAdministradorFacadeLocal;
import Interfaces.TurnoFacadeLocal;
import Modelo.Base;
import Modelo.Trabajador;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
/*
 import org.primefaces.event.CellEditEvent;
 import org.primefaces.event.RowEditEvent;
 */

/**
 *
 * @author Jonathan
 */
@Named(value = "logica")
@SessionScoped
public class Logica implements Serializable {

    @EJB
    EmpleadoFacadeLocal empleadoLocal;

    @EJB
    CamaFacadeLocal camaLocal;

    @EJB
    TurnoFacadeLocal turnoLocal;

    @EJB
    AdministradorFacadeLocal adminisradorLocal;

    @EJB
    SuperAdministradorFacadeLocal superAdministradorLocal;
    
    @EJB
    ProductoFacadeLocal productoLocal;

    Empleado empleado;

    Administrador administrador;

    SuperAdministrador superAdministrador;

    Cama cama;

    Turno turno;
    
    Producto producto;

    Trabajador trabajador;

    Base base;

    List<Empleado> listaEmpleados;

    List<Cama> listaCama;
    
    List<Producto> listaProductos;

    @PostConstruct
    public void listaEmpleados() {
        listaEmpleados = empleadoLocal.findEmpleados();
        listaCama = camaLocal.findAll();
        listaProductos = productoLocal.findAll();
    }

    public Logica() {
        empleado = new Empleado();
        cama = new Cama();
        turno = new Turno();
        trabajador = new Trabajador();
        base = new Base();
        producto = new Producto();
    }

    public String validar() {

        List<Empleado> empl = empleadoLocal.findAll();
        for (Empleado emp1 : empl) {
            if (emp1.getCedula().equals(trabajador.getCedula()) && emp1.getContrasena().equals(trabajador.getContraseña())) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Successful", "Usuario identificado"));
                Empleado empleado1 = empleadoLocal.findEmpleado(trabajador.getCedula());
                if (empleado1.isEstado()) {
                    empleado1.setEstado(false);
                } else {
                    empleado1.setEstado(true);
                }
                empleadoLocal.edit(empleado1);
                return "index.xhtml";
            } else {
                List<Administrador> admin = adminisradorLocal.findAll();
                for (Administrador admin1 : admin) {
                    if (admin1.getCedula().equals(trabajador.getCedula()) && admin1.getContrasena().equals(trabajador.getContraseña())) {
                        trabajador = new Trabajador();
                        return "administrador.xhtml";
                    } else {
                        List<SuperAdministrador> superAdmin = superAdministradorLocal.findAll();
                        for (SuperAdministrador superAdmin1 : superAdmin) {
                            if (superAdmin1.getCedula().equals(trabajador.getCedula()) && superAdmin1.getContrasena().equals(trabajador.getContraseña())) {
                                trabajador = new Trabajador();
                                return "superAdministrador.xhtml";
                            }
                        }
                    }
                }
            }
        }
        FacesMessage message = new FacesMessage("Usuario o Contraseña Invalido");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }

    public void crearEmpleado(Trabajador trabajador1) {
        empleado = new Empleado(trabajador1.getNombre(), trabajador1.getCedula(), trabajador1.getContraseña(), trabajador1.getArea(), trabajador1.getSalario(), false);
        empleadoLocal.create(empleado);
        trabajador = new Trabajador();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Empleado Agregado"));
    }

    public void crearProducto(Producto producto1){
        producto.setNombre(producto1.getNombre());
        producto.setCodigo(producto1.getCodigo());
        productoLocal.create(producto);
    }
    
    public void crearCama(Base cama1) {
        cama.setAncho(cama1.getAncho());
        cama.setLargo(cama1.getLargo());
        cama.setId_producto(1);
        camaLocal.create(cama);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Cama Agregada"));
    }

    public List<Empleado> traerEmpleados() {
        listaEmpleados = empleadoLocal.findEmpleados();
        return listaEmpleados;
    }


    public void Editar(Empleado emp) {
        empleadoLocal.edit(emp);
    }

    public void Eliminar(Empleado emp) { 
        empleadoLocal.remove(emp);
    }
    
    public void EditarCama(Cama cam) {
        camaLocal.edit(cam);
    }

    public String EliminarCama(Cama cam) {
        camaLocal.remove(cam);
        return "EditarCama.xhtml";
    }
    
    public void EditarProducto(Producto prod) {
        productoLocal.edit(prod);
    }

    public void EliminarProducto(Producto prod) {
        productoLocal.remove(prod);
    }

    public void editarEmpleado(Trabajador traba) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            empleado = empleadoLocal.findEmpleado(traba.getCedula());
            empleado.setNombre(traba.getNombre());
            empleado.setArea(traba.getArea());
            empleado.setSalario(traba.getSalario());
            empleado.setContrasena(traba.getContraseña());
            empleadoLocal.edit(empleado);
            context.addMessage("growl", new FacesMessage("Mensaje", "Actualizado Correctamente."));
            //listaTurnos();
            context.addMessage("growl", new FacesMessage("Mensaje", "Las Camas Cortadas No Pueden Superar La Meta."));
        } catch (Exception e) {

        }

    }

    public void cancelarEditar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage("Mensaje", "Actualizar Cancelado."));
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

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public AdministradorFacadeLocal getAdminisradorLocal() {
        return adminisradorLocal;
    }

    public void setAdminisradorLocal(AdministradorFacadeLocal adminisradorLocal) {
        this.adminisradorLocal = adminisradorLocal;
    }

    public SuperAdministradorFacadeLocal getSuperAdministradorLocal() {
        return superAdministradorLocal;
    }

    public void setSuperAdministradorLocal(SuperAdministradorFacadeLocal superAdministradorLocal) {
        this.superAdministradorLocal = superAdministradorLocal;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public SuperAdministrador getSuperAdministrador() {
        return superAdministrador;
    }

    public void setSuperAdministrador(SuperAdministrador superAdministrador) {
        this.superAdministrador = superAdministrador;
    }

    public List<Cama> getListaCama() {
        return listaCama;
    }

    public void setListaCama(List<Cama> listaCama) {
        this.listaCama = listaCama;
    }

    public ProductoFacadeLocal getProductoLocal() {
        return productoLocal;
    }

    public void setProductoLocal(ProductoFacadeLocal productoLocal) {
        this.productoLocal = productoLocal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }


    
}
