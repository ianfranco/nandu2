/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.EstadoGuia;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IEstadoGuiaDao<T> extends IGenericDAO<T> {
    
    public List<EstadoGuia> findAllByCuenta(Cuenta cuenta);
    
    public List<EstadoGuia> findAllByCuentaInspector(Cuenta cuenta);
    
    public EstadoGuia findById(int id);
    
}
