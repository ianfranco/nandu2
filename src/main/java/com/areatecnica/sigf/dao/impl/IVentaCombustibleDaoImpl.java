/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.VentaCombustible;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class IVentaCombustibleDaoImpl extends GenericDAOImpl<VentaCombustible> implements IVentaCombustibleDao<VentaCombustible> {

    @Override
    public List<VentaCombustible> findBySurtidorDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaCombustible> findByBusAndDate(Bus bus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaCombustible> findByDefaultBus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VentaCombustible findByBus(Bus bus, Boleto boleto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
