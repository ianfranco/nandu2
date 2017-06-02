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
@Table(name = "tipo_linea", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLinea.findAll", query = "SELECT t FROM TipoLinea t")
    , @NamedQuery(name = "TipoLinea.findByTipoLineaId", query = "SELECT t FROM TipoLinea t WHERE t.tipoLineaId = :tipoLineaId")
    , @NamedQuery(name = "TipoLinea.findByTipoLineaNombre", query = "SELECT t FROM TipoLinea t WHERE t.tipoLineaNombre = :tipoLineaNombre")
    , @NamedQuery(name = "TipoLinea.findByTipoLineaFechaIngreso", query = "SELECT t FROM TipoLinea t WHERE t.tipoLineaFechaIngreso = :tipoLineaFechaIngreso")})
public class TipoLinea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_linea_id")
    private Integer tipoLineaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_linea_nombre")
    private String tipoLineaNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_linea_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tipoLineaFechaIngreso;

    public TipoLinea() {
    }

    public TipoLinea(Integer tipoLineaId) {
        this.tipoLineaId = tipoLineaId;
    }

    public TipoLinea(Integer tipoLineaId, String tipoLineaNombre, Date tipoLineaFechaIngreso) {
        this.tipoLineaId = tipoLineaId;
        this.tipoLineaNombre = tipoLineaNombre;
        this.tipoLineaFechaIngreso = tipoLineaFechaIngreso;
    }

    public Integer getTipoLineaId() {
        return tipoLineaId;
    }

    public void setTipoLineaId(Integer tipoLineaId) {
        this.tipoLineaId = tipoLineaId;
    }

    public String getTipoLineaNombre() {
        return tipoLineaNombre;
    }

    public void setTipoLineaNombre(String tipoLineaNombre) {
        this.tipoLineaNombre = tipoLineaNombre;
    }

    public Date getTipoLineaFechaIngreso() {
        return tipoLineaFechaIngreso;
    }

    public void setTipoLineaFechaIngreso(Date tipoLineaFechaIngreso) {
        this.tipoLineaFechaIngreso = tipoLineaFechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoLineaId != null ? tipoLineaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLinea)) {
            return false;
        }
        TipoLinea other = (TipoLinea) object;
        if ((this.tipoLineaId == null && other.tipoLineaId != null) || (this.tipoLineaId != null && !this.tipoLineaId.equals(other.tipoLineaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoLinea[ tipoLineaId=" + tipoLineaId + " ]";
    }
    
}
