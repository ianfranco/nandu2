/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEstadoGuiaDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.EstadoGuia;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IEstadoGuiaDaoImpl extends GenericDAOImpl<EstadoGuia> implements IEstadoGuiaDao<EstadoGuia> {

    @Override
    public List<EstadoGuia> findAllByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("EstadoGuia.findByEstadoGuiaIdCuenta").setParameter("estadoGuiaIdCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    @Override
    public List<EstadoGuia> findAllByCuentaInspector(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("EstadoGuia.findAllByCuentaInspector").setParameter("estadoGuiaIdCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public EstadoGuia findById(int id) {
        try {
            return (EstadoGuia) this.entityManager.createNamedQuery("EstadoGuia.findByEstadoGuiaId").setParameter("estadoGuiaId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
