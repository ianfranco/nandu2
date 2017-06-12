/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.SerieBoletoGuia;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ISerieBoletoDao<T> extends IGenericDAO<T> {

    public List<SerieBoletoGuia> findByGuia(Guia guia);

    public SerieBoletoGuia findByGuiaBoleto(Guia guia, Boleto boleto);
}
