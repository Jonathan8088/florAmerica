/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Interfaces.AdministradorFacadeLocal;
import Interfaces.AbstractFacade;
import Entity.Administrador;
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
public class AdministradorFacade extends AbstractFacade<Administrador> implements AdministradorFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_FlorAmerica-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }

    @Override
    public Administrador buscarCedula(String cedula) {
        TypedQuery<Administrador> consulta = em.createNamedQuery("buscarCedula", Administrador.class);
        consulta.setParameter("cedula", cedula);
        return consulta.getSingleResult();
    }

    @Override
    public List<Administrador> traerCedulas() {
        TypedQuery<Administrador> consulta = em.createNamedQuery("traerCedulaAdministrador", Administrador.class);
        return consulta.getResultList();
    }

}
