/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Interfaces.TurnoFacadeLocal;
import Interfaces.AbstractFacade;
import Entity.Turno;
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
public class TurnoFacade extends AbstractFacade<Turno> implements TurnoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_FlorAmerica-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TurnoFacade() {
        super(Turno.class);
    }

    @Override
    public List<Turno> turnoDesc() {
               TypedQuery<Turno> consulta = em.createNamedQuery("listaTurnoDesc", Turno.class);
        return consulta.getResultList();
    }
    
}
