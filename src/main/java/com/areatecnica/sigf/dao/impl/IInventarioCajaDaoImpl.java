/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IInventarioCajaDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.InventarioCaja;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IInventarioCajaDaoImpl extends GenericDAOImpl<InventarioCaja> implements IInventarioCajaDao<InventarioCaja> {

    @Override
    public List<InventarioCaja> findByBoletoEstado(Boleto boleto, Boolean estado) {
        try {
            return this.entityManager.createNamedQuery("InventarioCaja.findByInventarioCajaBoletoEstado").setParameter("inventarioInternoIdBoleto", boleto).setParameter("inventarioCajaEstado", estado).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<InventarioCaja> findByEstado(Boolean estado) {
        try {
            return this.entityManager.createNamedQuery("InventarioCaja.findByInventarioCajaEstado").setParameter("inventarioCajaEstado", estado).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
