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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "sueldo_base", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SueldoBase.findAll", query = "SELECT s FROM SueldoBase s")
    , @NamedQuery(name = "SueldoBase.findBySueldoBaseId", query = "SELECT s FROM SueldoBase s WHERE s.sueldoBaseId = :sueldoBaseId")
    , @NamedQuery(name = "SueldoBase.findBySueldoBaseBaseNormal", query = "SELECT s FROM SueldoBase s WHERE s.sueldoBaseBaseNormal = :sueldoBaseBaseNormal")
    , @NamedQuery(name = "SueldoBase.findBySueldoBaseBasePartime", query = "SELECT s FROM SueldoBase s WHERE s.sueldoBaseBasePartime = :sueldoBaseBasePartime")
    , @NamedQuery(name = "SueldoBase.findBySueldoBaseDesde", query = "SELECT s FROM SueldoBase s WHERE s.sueldoBaseDesde = :sueldoBaseDesde")
    , @NamedQuery(name = "SueldoBase.findBySueldoBaseHasta", query = "SELECT s FROM SueldoBase s WHERE s.sueldoBaseHasta = :sueldoBaseHasta")
    , @NamedQuery(name = "SueldoBase.findBySueldoBaseFechaIngreso", query = "SELECT s FROM SueldoBase s WHERE s.sueldoBaseFechaIngreso = :sueldoBaseFechaIngreso")})
public class SueldoBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sueldo_base_id")
    private Integer sueldoBaseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sueldo_base_base_normal")
    private int sueldoBaseBaseNormal;
    @Size(max = 45)
    @Column(name = "sueldo_base_base_partime")
    private String sueldoBaseBasePartime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sueldo_base_desde")
    @Temporal(TemporalType.DATE)
    private Date sueldoBaseDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sueldo_base_hasta")
    @Temporal(TemporalType.DATE)
    private Date sueldoBaseHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sueldo_base_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sueldoBaseFechaIngreso;

    public SueldoBase() {
    }

    public SueldoBase(Integer sueldoBaseId) {
        this.sueldoBaseId = sueldoBaseId;
    }

    public SueldoBase(Integer sueldoBaseId, int sueldoBaseBaseNormal, Date sueldoBaseDesde, Date sueldoBaseHasta, Date sueldoBaseFechaIngreso) {
        this.sueldoBaseId = sueldoBaseId;
        this.sueldoBaseBaseNormal = sueldoBaseBaseNormal;
        this.sueldoBaseDesde = sueldoBaseDesde;
        this.sueldoBaseHasta = sueldoBaseHasta;
        this.sueldoBaseFechaIngreso = sueldoBaseFechaIngreso;
    }

    public Integer getSueldoBaseId() {
        return sueldoBaseId;
    }

    public void setSueldoBaseId(Integer sueldoBaseId) {
        this.sueldoBaseId = sueldoBaseId;
    }

    public int getSueldoBaseBaseNormal() {
        return sueldoBaseBaseNormal;
    }

    public void setSueldoBaseBaseNormal(int sueldoBaseBaseNormal) {
        this.sueldoBaseBaseNormal = sueldoBaseBaseNormal;
    }

    public String getSueldoBaseBasePartime() {
        return sueldoBaseBasePartime;
    }

    public void setSueldoBaseBasePartime(String sueldoBaseBasePartime) {
        this.sueldoBaseBasePartime = sueldoBaseBasePartime;
    }

    public Date getSueldoBaseDesde() {
        return sueldoBaseDesde;
    }

    public void setSueldoBaseDesde(Date sueldoBaseDesde) {
        this.sueldoBaseDesde = sueldoBaseDesde;
    }

    public Date getSueldoBaseHasta() {
        return sueldoBaseHasta;
    }

    public void setSueldoBaseHasta(Date sueldoBaseHasta) {
        this.sueldoBaseHasta = sueldoBaseHasta;
    }

    public Date getSueldoBaseFechaIngreso() {
        return sueldoBaseFechaIngreso;
    }

    public void setSueldoBaseFechaIngreso(Date sueldoBaseFechaIngreso) {
        this.sueldoBaseFechaIngreso = sueldoBaseFechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sueldoBaseId != null ? sueldoBaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SueldoBase)) {
            return false;
        }
        SueldoBase other = (SueldoBase) object;
        if ((this.sueldoBaseId == null && other.sueldoBaseId != null) || (this.sueldoBaseId != null && !this.sueldoBaseId.equals(other.sueldoBaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.SueldoBase[ sueldoBaseId=" + sueldoBaseId + " ]";
    }
    
}
