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
 *
 * @author ianfr
 */
@Entity
@Table(name = "caja_proceso", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajaProceso.findAll", query = "SELECT c FROM CajaProceso c")
    , @NamedQuery(name = "CajaProceso.findByCajaProcesoId", query = "SELECT c FROM CajaProceso c WHERE c.cajaProcesoId = :cajaProcesoId")
    , @NamedQuery(name = "CajaProceso.findByCajaProcesoIdCaja", query = "SELECT c FROM CajaProceso c WHERE c.cajaProcesoIdCaja = :cajaProcesoIdCaja")
    , @NamedQuery(name = "CajaProceso.findByCajaProcesoIdCajaIdProceso", query = "SELECT c FROM CajaProceso c WHERE c.cajaProcesoIdCaja = :cajaProcesoIdCaja AND c.cajaProcesoIdProceso = :cajaProcesoIdProceso")
    , @NamedQuery(name = "CajaProceso.findByCajaProcesoFechaIngreso", query = "SELECT c FROM CajaProceso c WHERE c.cajaProcesoFechaIngreso = :cajaProcesoFechaIngreso")
    , @NamedQuery(name = "CajaProceso.findByCajaProcesoFechaActualizacion", query = "SELECT c FROM CajaProceso c WHERE c.cajaProcesoFechaActualizacion = :cajaProcesoFechaActualizacion")})
public class CajaProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caja_proceso_id")
    private Integer cajaProcesoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_proceso_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cajaProcesoFechaIngreso;
    @Column(name = "caja_proceso_fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cajaProcesoFechaActualizacion;
    @Column(name = "caja_proceso_activo")
    private Boolean cajaProcesoActivo;
    @JoinColumn(name = "caja_proceso_id_caja", referencedColumnName = "caja_recaudacion_id")
    @ManyToOne(optional = false)
    private CajaRecaudacion cajaProcesoIdCaja;
    @JoinColumn(name = "caja_proceso_id_proceso", referencedColumnName = "proceso_recaudacion_id")
    @ManyToOne(optional = false)
    private ProcesoRecaudacion cajaProcesoIdProceso;

    public CajaProceso() {
    }

    public CajaProceso(Integer cajaProcesoId) {
        this.cajaProcesoId = cajaProcesoId;
    }

    public CajaProceso(Integer cajaProcesoId, Date cajaProcesoFechaIngreso) {
        this.cajaProcesoId = cajaProcesoId;
        this.cajaProcesoFechaIngreso = cajaProcesoFechaIngreso;
    }

    public Integer getCajaProcesoId() {
        return cajaProcesoId;
    }

    public void setCajaProcesoId(Integer cajaProcesoId) {
        this.cajaProcesoId = cajaProcesoId;
    }

    public Date getCajaProcesoFechaIngreso() {
        return cajaProcesoFechaIngreso;
    }

    public void setCajaProcesoFechaIngreso(Date cajaProcesoFechaIngreso) {
        this.cajaProcesoFechaIngreso = cajaProcesoFechaIngreso;
    }

    public Date getCajaProcesoFechaActualizacion() {
        return cajaProcesoFechaActualizacion;
    }

    public void setCajaProcesoFechaActualizacion(Date cajaProcesoFechaActualizacion) {
        this.cajaProcesoFechaActualizacion = cajaProcesoFechaActualizacion;
    }

    public Boolean getCajaProcesoActivo() {
        return cajaProcesoActivo;
    }

    public void setCajaProcesoActivo(Boolean cajaProcesoActivo) {
        this.cajaProcesoActivo = cajaProcesoActivo;
    }

    public CajaRecaudacion getCajaProcesoIdCaja() {
        return cajaProcesoIdCaja;
    }

    public void setCajaProcesoIdCaja(CajaRecaudacion cajaProcesoIdCaja) {
        this.cajaProcesoIdCaja = cajaProcesoIdCaja;
    }

    public ProcesoRecaudacion getCajaProcesoIdProceso() {
        return cajaProcesoIdProceso;
    }

    public void setCajaProcesoIdProceso(ProcesoRecaudacion cajaProcesoIdProceso) {
        this.cajaProcesoIdProceso = cajaProcesoIdProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cajaProcesoId != null ? cajaProcesoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaProceso)) {
            return false;
        }
        CajaProceso other = (CajaProceso) object;
        if ((this.cajaProcesoId == null && other.cajaProcesoId != null) || (this.cajaProcesoId != null && !this.cajaProcesoId.equals(other.cajaProcesoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CajaProceso[ cajaProcesoId=" + cajaProcesoId + " ]";
    }

}
