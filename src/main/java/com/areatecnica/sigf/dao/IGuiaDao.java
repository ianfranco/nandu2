/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IGuiaDao<T> extends IGenericDAO<T> {
    
    public Guia findByCuentaFolio(Cuenta cuenta, int folio);
    
    public Guia findLastGuiaByBusFecha(Bus bus, Date fecha);
    
    public List<Guia> findByCajaRecaudacionProcesoBus(CajaRecaudacion cajaRecaudacion, ProcesoRecaudacion procesoRecaudacion);
    
    public List<Guia> findByProcesoFechaRecaudacion(ProcesoRecaudacion procesoRecaudacion, Date fechaRecaudacion);
    
    public List<Guia> findByProcesoFechaGuia(ProcesoRecaudacion procesoRecaudacion, Date fechaGuia);
    
    public List<Guia> findByBusFecha(Bus bus, Date fecha);
    
    public List<Guia> findByCuentaFecha(Cuenta cuenta, Date fecha);
    
    public List<Guia> findByFechaGrupoServicio(GrupoServicio grupoServicio, Date fecha);
    
    public void delete(Guia guia);
    
}
