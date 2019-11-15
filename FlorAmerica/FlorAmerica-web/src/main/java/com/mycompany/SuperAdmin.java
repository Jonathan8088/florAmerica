/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import Entity.*;
import Interfaces.*;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
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

    @EJB
    EmpleadoFacadeLocal empleadoLocal;

    private Empleado empleado;

    private Administrador administrador;

    private Turno turno;

    private Admin admin = new Admin();

    private Turnos turnos = new Turnos();

    private String cedulaAdministrador;

    private List<Turno> listaTurno;

    private String mensaje;

    int i = 0;

    Calendar calendario = Calendar.getInstance();

    int minutosTurno;

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
        minutosTurno = calendario.get((Calendar.MINUTE));
        List<Turno> listaTurno = turnoFacadeLocal.turnoDesc();
        float a = listaTurno.get(0).getDuracion();
        
        minutosTurno += (int)a;
    }

    public void editarTurno(Turno tur) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (tur.getCamas_cortadas() <= tur.getMeta()) {
                float promedio = (tur.getCamas_cortadas() * tur.getDuracion()) / tur.getMeta();
                turno = turnoFacadeLocal.find(tur.getId());
                turno.setDuracion(tur.getDuracion());
                turno.setMeta(tur.getMeta());
                turno.setCamas_cortadas(tur.getCamas_cortadas());
                turno.setPromedio_corte(promedio);
                turnoFacadeLocal.edit(turno);
                context.addMessage("growl", new FacesMessage("Mensaje", "Actualizado Correctamente."));
                listaTurnos();
            } else {
                context.addMessage("growl", new FacesMessage("Mensaje", "Las Camas Cortadas No Pueden Superar La Meta."));
            }
        } catch (Exception e) {

        }

    }
    


    public void calcularCambioTurno() {
        FacesContext context = FacesContext.getCurrentInstance();

        calendario = Calendar.getInstance();
        int minutos = calendario.get((Calendar.MINUTE));
        int minutosAdd;
        List<Empleado> lista = new ArrayList<>();
        lista = empleadoLocal.traerEstado();
        for (Empleado lista1 : lista) {
            lista1.setEstado(true);
        }
        
        try {

          if (minutos >= minutosTurno) {
                System.out.println(i);
                context.addMessage("growl", new FacesMessage("Mensaje", "Cambio de Turno"));
                if (lista.size() == 0) {
                    i = i + 1;
                    System.out.println(i);
                } else {
                    for (Empleado lista1 : lista) {
                        if (lista1.isEstado() == true && i > 0) {
                            minutosAdd = calendario.get((Calendar.MINUTE));
                            minutosAdd = minutosAdd + i;
                            int total = minutosAdd - minutos;
                            context.addMessage("growl", new FacesMessage("Mensaje", String.valueOf(total) + " Minutos Demoro el Cambio de Turno"));
                            i = 0;
                            System.out.println(i);

                        }
                    }
                }
            }
        } catch (Exception e) {
            /*lista = empleadoLocal.traerEstado();
             if (lista.size() == 0) {
             i = i + 1;
             System.out.println(i);
             }*/

        }
    }

    public void avisoTurno() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            /*List<Turno> lista = turnoFacadeLocal.turnoDesc();
             LocalTime horaf = hora.plusMinutes((long) lista.get(0).getDuracion());
             if (hora.isBefore(horaf)){
             mensaje = "Cambio de Turno.";
             }*/
        } catch (Exception e) {
            context.addMessage("growl", new FacesMessage("Mensaje", e.toString()));
        }
    }

    public void cancelarEditar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage("Mensaje", "Actualizar Cancelado."));
    }

    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporteTurno.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.listaTurno));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ReporteTurno.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void vaciar() {
        admin = new Admin(0, null, null, null, null, (float) 0.0);
    }

    public void vaciarEditar() {
        admin = new Admin(0, null, null, null, null, (float) 0.0);
        cedulaAdministrador = null;
    }

    
}
