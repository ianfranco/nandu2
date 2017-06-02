/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IBusDaoImpl extends GenericDAOImpl<Bus> implements IBusDao<Bus> {

    @Override
    public int findMaxNumeroUnidad(UnidadNegocio unidadNegocio) {
        try {
            Bus bus = (Bus) this.entityManager.createNamedQuery("Bus.findMaxNumeroUnidad").setParameter("busIdUnidadNegocio", unidadNegocio).setMaxResults(1).getSingleResult();
            return bus.getBusNumero() + 1;
        } catch (NoResultException ne) {
            return 1;
        }
    }

    @Override
    public List<Bus> findByUnidad(UnidadNegocio unidadNegocio) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByBusIdUnidadNegocio").setParameter("busIdUnidadNegocio", unidadNegocio).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByProceso(ProcesoRecaudacion procesoRecaudacion) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByProcesoRecaudacion").setParameter("busIdProcesoRecaudacion", procesoRecaudacion).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
