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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "precio_combustible", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrecioCombustible.findAll", query = "SELECT p FROM PrecioCombustible p"),
    @NamedQuery(name = "PrecioCombustible.findAllByCuenta", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleIdCuenta =:precioCombustibleIdCuenta"),
    @NamedQuery(name = "PrecioCombustible.findLastPrecio", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleIdCuenta =:precioCombustibleIdCuenta ORDER BY p.precioCombustibleFechaPrecioCombustible DESC"),
    @NamedQuery(name = "PrecioCombustible.findByPrecioCombustibleId", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleId = :precioCombustibleId"),
    @NamedQuery(name = "PrecioCombustible.findByPrecioCombustibleValor", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleValor = :precioCombustibleValor"),
    @NamedQuery(name = "PrecioCombustible.findByPrecioCombustibleFechaPrecioCombustible", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleFechaPrecioCombustible = :precioCombustibleFechaPrecioCombustible"),
    @NamedQuery(name = "PrecioCombustible.findByPrecioCombustibleFechaIngreso", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleFechaIngreso = :precioCombustibleFechaIngreso")})
public class PrecioCombustible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "precio_combustible_id")
    private Integer precioCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_combustible_valor")
    private int precioCombustibleValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_combustible_fecha_precio_combustible")
    @Temporal(TemporalType.DATE)
    private Date precioCombustibleFechaPrecioCombustible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_combustible_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date precioCombustibleFechaIngreso;
    @JoinColumn(name = "precio_combustible_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta precioCombustibleIdCuenta;
    @JoinColumn(name = "precio_combustible_id_tipo_combustible", referencedColumnName = "tipo_combustible_id")
    @ManyToOne(optional = false)
    private TipoCombustible precioCombustibleIdTipoCombustible;

    public PrecioCombustible() {
    }

    public PrecioCombustible(Integer precioCombustibleId) {
        this.precioCombustibleId = precioCombustibleId;
    }

    public PrecioCombustible(Integer precioCombustibleId, int precioCombustibleValor, Date precioCombustibleFechaPrecioCombustible, Date precioCombustibleFechaIngreso) {
        this.precioCombustibleId = precioCombustibleId;
        this.precioCombustibleValor = precioCombustibleValor;
        this.precioCombustibleFechaPrecioCombustible = precioCombustibleFechaPrecioCombustible;
        this.precioCombustibleFechaIngreso = precioCombustibleFechaIngreso;
    }

    public Integer getPrecioCombustibleId() {
        return precioCombustibleId;
    }

    public void setPrecioCombustibleId(Integer precioCombustibleId) {
        this.precioCombustibleId = precioCombustibleId;
    }

    public int getPrecioCombustibleValor() {
        return precioCombustibleValor;
    }

    public void setPrecioCombustibleValor(int precioCombustibleValor) {
        this.precioCombustibleValor = precioCombustibleValor;
    }

    public Date getPrecioCombustibleFechaPrecioCombustible() {
        return precioCombustibleFechaPrecioCombustible;
    }

    public void setPrecioCombustibleFechaPrecioCombustible(Date precioCombustibleFechaPrecioCombustible) {
        this.precioCombustibleFechaPrecioCombustible = precioCombustibleFechaPrecioCombustible;
    }

    public Date getPrecioCombustibleFechaIngreso() {
        return precioCombustibleFechaIngreso;
    }

    public void setPrecioCombustibleFechaIngreso(Date precioCombustibleFechaIngreso) {
        this.precioCombustibleFechaIngreso = precioCombustibleFechaIngreso;
    }

    public Cuenta getPrecioCombustibleIdCuenta() {
        return precioCombustibleIdCuenta;
    }

    public void setPrecioCombustibleIdCuenta(Cuenta precioCombustibleIdCuenta) {
        this.precioCombustibleIdCuenta = precioCombustibleIdCuenta;
    }

    public TipoCombustible getPrecioCombustibleIdTipoCombustible() {
        return precioCombustibleIdTipoCombustible;
    }

    public void setPrecioCombustibleIdTipoCombustible(TipoCombustible precioCombustibleIdTipoCombustible) {
        this.precioCombustibleIdTipoCombustible = precioCombustibleIdTipoCombustible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (precioCombustibleId != null ? precioCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioCombustible)) {
            return false;
        }
        PrecioCombustible other = (PrecioCombustible) object;
        if ((this.precioCombustibleId == null && other.precioCombustibleId != null) || (this.precioCombustibleId != null && !this.precioCombustibleId.equals(other.precioCombustibleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PrecioCombustible[ precioCombustibleId=" + precioCombustibleId + " ]";
    }
    
}
