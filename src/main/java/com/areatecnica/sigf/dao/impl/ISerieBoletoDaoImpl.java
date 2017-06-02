/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ISerieBoletoDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.SerieBoletoGuia;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ISerieBoletoDaoImpl extends GenericDAOImpl<SerieBoletoGuia> implements ISerieBoletoDao<SerieBoletoGuia>{

    public ISerieBoletoDaoImpl() {
        super(SerieBoletoGuia.class);
    }

    
    
    @Override
    public List<SerieBoletoGuia> findByGuia(Guia guia) {
        try {
            return this.entityManager.createNamedQuery("SerieBoletoGuia.findByGuia").setParameter("serieBoletoGuiaIdGuia", guia).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public SerieBoletoGuia findByGuiaBoleto(Guia guia, Boleto boleto) {
        try {
            return (SerieBoletoGuia) this.entityManager.createNamedQuery("SerieBoletoGuia.findByGuiaBoleto").setParameter("serieBoletoGuiaIdGuia", guia).setParameter("serieBoletoGuiaIdBoleto", boleto).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
