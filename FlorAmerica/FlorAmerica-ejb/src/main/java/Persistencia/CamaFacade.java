/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Interfaces.CamaFacadeLocal;
import Interfaces.AbstractFacade;
import Entity.Cama;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jonathan
 */
@Stateless
public class CamaFacade extends AbstractFacade<Cama> implements CamaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_FlorAmerica-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CamaFacade() {
        super(Cama.class);
    }
    
}
