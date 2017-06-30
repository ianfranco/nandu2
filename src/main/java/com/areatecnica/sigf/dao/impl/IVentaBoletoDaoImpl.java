/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IVentaBoletoDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IVentaBoletoDaoImpl extends GenericDAOImpl<VentaBoleto> implements IVentaBoletoDao<VentaBoleto> {

    @Override
    public List<VentaBoleto> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta) {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoIdCajaDate").
                    setParameter("cajaProcesoIdCaja", cajaRecaudacion).
                    setParameter("ventaBoletoFecha", fechaVenta).getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }

    @Override
    public List<VentaBoleto> findByBus(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoIdGuia").setParameter("ventaBoletoIdGuia", bus).getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }
    
    @Override
    public List<VentaBoleto> findByBusEstado(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoIdGuiaEstado").setParameter("ventaBoletoIdGuia", bus).getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }

    @Override
    public List<VentaBoleto> findByDefaultBus() {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoDefaultIdGuia").getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }

    

}
