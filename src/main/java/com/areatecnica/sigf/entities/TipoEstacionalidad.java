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
@Table(name = "tipo_estacionalidad", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstacionalidad.findAll", query = "SELECT t FROM TipoEstacionalidad t")
    , @NamedQuery(name = "TipoEstacionalidad.findByTipoEstacionalidadId", query = "SELECT t FROM TipoEstacionalidad t WHERE t.tipoEstacionalidadId = :tipoEstacionalidadId")
    , @NamedQuery(name = "TipoEstacionalidad.findByTipoEstacionalidadNombre", query = "SELECT t FROM TipoEstacionalidad t WHERE t.tipoEstacionalidadNombre = :tipoEstacionalidadNombre")
    , @NamedQuery(name = "TipoEstacionalidad.findByTipoEstacionalidadFechaIngreso", query = "SELECT t FROM TipoEstacionalidad t WHERE t.tipoEstacionalidadFechaIngreso = :tipoEstacionalidadFechaIngreso")})
public class TipoEstacionalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_estacionalidad_id")
    private Integer tipoEstacionalidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_estacionalidad_nombre")
    private String tipoEstacionalidadNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_estacionalidad_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tipoEstacionalidadFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horarioServicioIdTipoEstacionalidad")
    private List<HorarioServicio> horarioServicioList;

    public TipoEstacionalidad() {
    }

    public TipoEstacionalidad(Integer tipoEstacionalidadId) {
        this.tipoEstacionalidadId = tipoEstacionalidadId;
    }

    public TipoEstacionalidad(Integer tipoEstacionalidadId, String tipoEstacionalidadNombre, Date tipoEstacionalidadFechaIngreso) {
        this.tipoEstacionalidadId = tipoEstacionalidadId;
        this.tipoEstacionalidadNombre = tipoEstacionalidadNombre;
        this.tipoEstacionalidadFechaIngreso = tipoEstacionalidadFechaIngreso;
    }

    public Integer getTipoEstacionalidadId() {
        return tipoEstacionalidadId;
    }

    public void setTipoEstacionalidadId(Integer tipoEstacionalidadId) {
        this.tipoEstacionalidadId = tipoEstacionalidadId;
    }

    public String getTipoEstacionalidadNombre() {
        return tipoEstacionalidadNombre;
    }

    public void setTipoEstacionalidadNombre(String tipoEstacionalidadNombre) {
        this.tipoEstacionalidadNombre = tipoEstacionalidadNombre;
    }

    public Date getTipoEstacionalidadFechaIngreso() {
        return tipoEstacionalidadFechaIngreso;
    }

    public void setTipoEstacionalidadFechaIngreso(Date tipoEstacionalidadFechaIngreso) {
        this.tipoEstacionalidadFechaIngreso = tipoEstacionalidadFechaIngreso;
    }

    @XmlTransient
    public List<HorarioServicio> getHorarioServicioList() {
        return horarioServicioList;
    }

    public void setHorarioServicioList(List<HorarioServicio> horarioServicioList) {
        this.horarioServicioList = horarioServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoEstacionalidadId != null ? tipoEstacionalidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstacionalidad)) {
            return false;
        }
        TipoEstacionalidad other = (TipoEstacionalidad) object;
        if ((this.tipoEstacionalidadId == null && other.tipoEstacionalidadId != null) || (this.tipoEstacionalidadId != null && !this.tipoEstacionalidadId.equals(other.tipoEstacionalidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoEstacionalidad[ tipoEstacionalidadId=" + tipoEstacionalidadId + " ]";
    }
    
}
