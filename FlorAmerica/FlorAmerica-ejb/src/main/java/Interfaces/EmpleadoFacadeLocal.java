/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Empleado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jonathan
 */
@Local
public interface EmpleadoFacadeLocal {

    void create(Empleado empleado);

    void edit(Empleado empleado);

    void remove(Empleado empleado);

    Empleado find(Object id);

    List<Empleado> findAll();

    List<Empleado> findRange(int[] range);

    int count();
    
    List<Empleado> findEmpleados();
    
    Empleado findEmpleado(String cedula); 
    
    List<Empleado> traerEstado();
            
}
