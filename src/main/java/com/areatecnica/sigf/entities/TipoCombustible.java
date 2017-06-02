/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tipo_combustible", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCombustible.findAll", query = "SELECT t FROM TipoCombustible t")
    , @NamedQuery(name = "TipoCombustible.findByTipoCombustibleId", query = "SELECT t FROM TipoCombustible t WHERE t.tipoCombustibleId = :tipoCombustibleId")
    , @NamedQuery(name = "TipoCombustible.findByTipoCombustibleNombre", query = "SELECT t FROM TipoCombustible t WHERE t.tipoCombustibleNombre = :tipoCombustibleNombre")
    , @NamedQuery(name = "TipoCombustible.findByTipoCombustibleFechaIngreso", query = "SELECT t FROM TipoCombustible t WHERE t.tipoCombustibleFechaIngreso = :tipoCombustibleFechaIngreso")})
public class TipoCombustible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_combustible_id")
    private Integer tipoCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_combustible_nombre")
    private String tipoCombustibleNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_combustible_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tipoCombustibleFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precioCombustibleIdTipoCombustible")
    private List<PrecioCombustible> precioCombustibleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraCombustibleIdTipo")
    private List<CompraCombustible> compraCombustibleList;

    public TipoCombustible() {
    }

    public TipoCombustible(Integer tipoCombustibleId) {
        this.tipoCombustibleId = tipoCombustibleId;
    }

    public TipoCombustible(Integer tipoCombustibleId, String tipoCombustibleNombre, Date tipoCombustibleFechaIngreso) {
        this.tipoCombustibleId = tipoCombustibleId;
        this.tipoCombustibleNombre = tipoCombustibleNombre;
        this.tipoCombustibleFechaIngreso = tipoCombustibleFechaIngreso;
    }

    public Integer getTipoCombustibleId() {
        return tipoCombustibleId;
    }

    public void setTipoCombustibleId(Integer tipoCombustibleId) {
        this.tipoCombustibleId = tipoCombustibleId;
    }

    public String getTipoCombustibleNombre() {
        return tipoCombustibleNombre;
    }

    public void setTipoCombustibleNombre(String tipoCombustibleNombre) {
        this.tipoCombustibleNombre = tipoCombustibleNombre;
    }

    public Date getTipoCombustibleFechaIngreso() {
        return tipoCombustibleFechaIngreso;
    }

    public void setTipoCombustibleFechaIngreso(Date tipoCombustibleFechaIngreso) {
        this.tipoCombustibleFechaIngreso = tipoCombustibleFechaIngreso;
    }

    @XmlTransient
    public List<PrecioCombustible> getPrecioCombustibleList() {
        return precioCombustibleList;
    }

    public void setPrecioCombustibleList(List<PrecioCombustible> precioCombustibleList) {
        this.precioCombustibleList = precioCombustibleList;
    }

    @XmlTransient
    public List<CompraCombustible> getCompraCombustibleList() {
        return compraCombustibleList;
    }

    public void setCompraCombustibleList(List<CompraCombustible> compraCombustibleList) {
        this.compraCombustibleList = compraCombustibleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCombustibleId != null ? tipoCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCombustible)) {
            return false;
        }
        TipoCombustible other = (TipoCombustible) object;
        if ((this.tipoCombustibleId == null && other.tipoCombustibleId != null) || (this.tipoCombustibleId != null && !this.tipoCombustibleId.equals(other.tipoCombustibleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoCombustible[ tipoCombustibleId=" + tipoCombustibleId + " ]";
    }
    
}
