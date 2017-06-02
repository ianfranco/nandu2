/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICompraBoletoDao;
import com.areatecnica.sigf.entities.CompraBoleto;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ICompraBoletoDaoImpl extends GenericDAOImpl<CompraBoleto> implements ICompraBoletoDao<CompraBoleto> {

    public ICompraBoletoDaoImpl() {
        super(CompraBoleto.class);
    }

    @Override
    public List<CompraBoleto> findByFecha(Date fecha) {
        try {
            return this.entityManager.createNamedQuery("CompraBoleto.findByCompraBoletoFecha").setParameter("compraBoletoFecha", fecha).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public CompraBoleto findByFolioFactura(int folio) {
        try {
            return (CompraBoleto) this.entityManager.createNamedQuery("CompraBoleto.findByCompraBoletoFecha").setParameter("compraBoletoFecha", folio).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
