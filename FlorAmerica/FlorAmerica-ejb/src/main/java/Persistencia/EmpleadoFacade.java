/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Interfaces.EmpleadoFacadeLocal;
import Interfaces.AbstractFacade;
import Entity.Empleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jonathan
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> implements EmpleadoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_FlorAmerica-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    @Override
    public List<Empleado> findEmpleados() {
        TypedQuery<Empleado> consulta = em.createNamedQuery("traerEmpleados", Empleado.class);
        return consulta.getResultList();
    }

    @Override
    public Empleado findEmpleado(String cedula) {
        TypedQuery<Empleado> consulta = em.createNamedQuery("traerEmpleado", Empleado.class);
        consulta.setParameter("cedula", cedula);
        return consulta.getSingleResult();
    }
    
}
