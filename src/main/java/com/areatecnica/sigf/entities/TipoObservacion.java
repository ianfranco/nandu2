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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tipo_observacion", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoObservacion.findAll", query = "SELECT t FROM TipoObservacion t")
    , @NamedQuery(name = "TipoObservacion.findByTipoObservacionId", query = "SELECT t FROM TipoObservacion t WHERE t.tipoObservacionId = :tipoObservacionId")
    , @NamedQuery(name = "TipoObservacion.findByTipoObservacionNombre", query = "SELECT t FROM TipoObservacion t WHERE t.tipoObservacionNombre = :tipoObservacionNombre")
    , @NamedQuery(name = "TipoObservacion.findByTipoObservacionFechaIngreso", query = "SELECT t FROM TipoObservacion t WHERE t.tipoObservacionFechaIngreso = :tipoObservacionFechaIngreso")})
public class TipoObservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_observacion_id")
    private Integer tipoObservacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_observacion_nombre")
    private String tipoObservacionNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_observacion_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tipoObservacionFechaIngreso;
    @JoinColumn(name = "tipo_observacion_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta tipoObservacionIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "observacionTrabajadorIdTipoObservacion")
    private List<ObservacionTrabajador> observacionTrabajadorList;

    public TipoObservacion() {
    }

    public TipoObservacion(Integer tipoObservacionId) {
        this.tipoObservacionId = tipoObservacionId;
    }

    public TipoObservacion(Integer tipoObservacionId, String tipoObservacionNombre, Date tipoObservacionFechaIngreso) {
        this.tipoObservacionId = tipoObservacionId;
        this.tipoObservacionNombre = tipoObservacionNombre;
        this.tipoObservacionFechaIngreso = tipoObservacionFechaIngreso;
    }

    public Integer getTipoObservacionId() {
        return tipoObservacionId;
    }

    public void setTipoObservacionId(Integer tipoObservacionId) {
        this.tipoObservacionId = tipoObservacionId;
    }

    public String getTipoObservacionNombre() {
        return tipoObservacionNombre;
    }

    public void setTipoObservacionNombre(String tipoObservacionNombre) {
        this.tipoObservacionNombre = tipoObservacionNombre;
    }

    public Date getTipoObservacionFechaIngreso() {
        return tipoObservacionFechaIngreso;
    }

    public void setTipoObservacionFechaIngreso(Date tipoObservacionFechaIngreso) {
        this.tipoObservacionFechaIngreso = tipoObservacionFechaIngreso;
    }

    public Cuenta getTipoObservacionIdCuenta() {
        return tipoObservacionIdCuenta;
    }

    public void setTipoObservacionIdCuenta(Cuenta tipoObservacionIdCuenta) {
        this.tipoObservacionIdCuenta = tipoObservacionIdCuenta;
    }

    @XmlTransient
    public List<ObservacionTrabajador> getObservacionTrabajadorList() {
        return observacionTrabajadorList;
    }

    public void setObservacionTrabajadorList(List<ObservacionTrabajador> observacionTrabajadorList) {
        this.observacionTrabajadorList = observacionTrabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoObservacionId != null ? tipoObservacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoObservacion)) {
            return false;
        }
        TipoObservacion other = (TipoObservacion) object;
        if ((this.tipoObservacionId == null && other.tipoObservacionId != null) || (this.tipoObservacionId != null && !this.tipoObservacionId.equals(other.tipoObservacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoObservacion[ tipoObservacionId=" + tipoObservacionId + " ]";
    }
    
}
