/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.VentaCombustible;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IVentaCombustibleDao<T> extends IGenericDAO<T> {
    
    public List<VentaCombustible> findBySurtidorDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);
    
    public List<VentaCombustible> findByBusAndDate(Bus bus);    
    
    public List<VentaCombustible> findByDefaultBus();
    
    public VentaCombustible findByBus(Bus bus, Boleto boleto);
}
