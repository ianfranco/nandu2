/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "parametro_cesantia", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroCesantia.findAll", query = "SELECT p FROM ParametroCesantia p")
    , @NamedQuery(name = "ParametroCesantia.findByParametroCesantiaId", query = "SELECT p FROM ParametroCesantia p WHERE p.parametroCesantiaId = :parametroCesantiaId")
    , @NamedQuery(name = "ParametroCesantia.findByParametroCesantiaEmpleador", query = "SELECT p FROM ParametroCesantia p WHERE p.parametroCesantiaEmpleador = :parametroCesantiaEmpleador")
    , @NamedQuery(name = "ParametroCesantia.findByParametroCesantiaTrabajador", query = "SELECT p FROM ParametroCesantia p WHERE p.parametroCesantiaTrabajador = :parametroCesantiaTrabajador")
    , @NamedQuery(name = "ParametroCesantia.findByParametroCesantiaFechaIngreso", query = "SELECT p FROM ParametroCesantia p WHERE p.parametroCesantiaFechaIngreso = :parametroCesantiaFechaIngreso")
    , @NamedQuery(name = "ParametroCesantia.findByParametroCesantiaFechaActualizacion", query = "SELECT p FROM ParametroCesantia p WHERE p.parametroCesantiaFechaActualizacion = :parametroCesantiaFechaActualizacion")})
public class ParametroCesantia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "parametro_cesantia_id")
    private Integer parametroCesantiaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parametro_cesantia_empleador")
    private float parametroCesantiaEmpleador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parametro_cesantia_trabajador")
    private float parametroCesantiaTrabajador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parametro_cesantia_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date parametroCesantiaFechaIngreso;
    @Column(name = "parametro_cesantia_fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date parametroCesantiaFechaActualizacion;

    public ParametroCesantia() {
    }

    public ParametroCesantia(Integer parametroCesantiaId) {
        this.parametroCesantiaId = parametroCesantiaId;
    }

    public ParametroCesantia(Integer parametroCesantiaId, float parametroCesantiaEmpleador, float parametroCesantiaTrabajador, Date parametroCesantiaFechaIngreso) {
        this.parametroCesantiaId = parametroCesantiaId;
        this.parametroCesantiaEmpleador = parametroCesantiaEmpleador;
        this.parametroCesantiaTrabajador = parametroCesantiaTrabajador;
        this.parametroCesantiaFechaIngreso = parametroCesantiaFechaIngreso;
    }

    public Integer getParametroCesantiaId() {
        return parametroCesantiaId;
    }

    public void setParametroCesantiaId(Integer parametroCesantiaId) {
        this.parametroCesantiaId = parametroCesantiaId;
    }

    public float getParametroCesantiaEmpleador() {
        return parametroCesantiaEmpleador;
    }

    public void setParametroCesantiaEmpleador(float parametroCesantiaEmpleador) {
        this.parametroCesantiaEmpleador = parametroCesantiaEmpleador;
    }

    public float getParametroCesantiaTrabajador() {
        return parametroCesantiaTrabajador;
    }

    public void setParametroCesantiaTrabajador(float parametroCesantiaTrabajador) {
        this.parametroCesantiaTrabajador = parametroCesantiaTrabajador;
    }

    public Date getParametroCesantiaFechaIngreso() {
        return parametroCesantiaFechaIngreso;
    }

    public void setParametroCesantiaFechaIngreso(Date parametroCesantiaFechaIngreso) {
        this.parametroCesantiaFechaIngreso = parametroCesantiaFechaIngreso;
    }

    public Date getParametroCesantiaFechaActualizacion() {
        return parametroCesantiaFechaActualizacion;
    }

    public void setParametroCesantiaFechaActualizacion(Date parametroCesantiaFechaActualizacion) {
        this.parametroCesantiaFechaActualizacion = parametroCesantiaFechaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroCesantiaId != null ? parametroCesantiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroCesantia)) {
            return false;
        }
        ParametroCesantia other = (ParametroCesantia) object;
        if ((this.parametroCesantiaId == null && other.parametroCesantiaId != null) || (this.parametroCesantiaId != null && !this.parametroCesantiaId.equals(other.parametroCesantiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ParametroCesantia[ parametroCesantiaId=" + parametroCesantiaId + " ]";
    }
    
}
