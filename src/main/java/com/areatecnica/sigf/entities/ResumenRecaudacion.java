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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 *
 * @author ianfr
 */
@Entity
@Table(name = "resumen_recaudacion", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResumenRecaudacion.findAll", query = "SELECT r FROM ResumenRecaudacion r")
    , @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionId", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionId = :resumenRecaudacionId")
    , @NamedQuery(name = "ResumenRecaudacion.findByCajaProcesoDate", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionIdCaja = :resumenRecaudacionIdCaja AND r.resumenRecaudacionIdProceso = :resumenRecaudacionIdProceso AND r.resumenRecaudacionFecha = :resumenRecaudacionFecha")
    , @NamedQuery(name = "ResumenRecaudacion.findAllByCajaProcesoBetweenDates", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionIdCaja = :resumenRecaudacionIdCaja AND r.resumenRecaudacionIdProceso = :resumenRecaudacionIdProceso AND r.resumenRecaudacionFecha BETWEEN :from AND :to")
    , @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionFecha", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionFecha = :resumenRecaudacionFecha")
    , @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionTotal", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionTotal = :resumenRecaudacionTotal")
    , @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionTieneTransporte", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionTieneTransporte = :resumenRecaudacionTieneTransporte")
    , @NamedQuery(name = "ResumenRecaudacion.findByResumenRecaudacionFechaIngreso", query = "SELECT r FROM ResumenRecaudacion r WHERE r.resumenRecaudacionFechaIngreso = :resumenRecaudacionFechaIngreso")})
public class ResumenRecaudacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "resumen_recaudacion_id")
    private Integer resumenRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_cerrado")
    private boolean resumenRecaudacionCerrado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_fecha")
    @Temporal(TemporalType.DATE)
    private Date resumenRecaudacionFecha;
    @Column(name = "resumen_recaudacion_fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resumenRecaudacionFechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_total")
    private int resumenRecaudacionTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_tiene_transporte")
    private int resumenRecaudacionTieneTransporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resumen_recaudacion_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resumenRecaudacionFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleResumenRecaudacionIdResumen")
    private List<DetalleResumenRecaudacion> detalleResumenRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoRecaudacionIdResumen")
    private List<EgresoRecaudacion> egresoRecaudacionList;
    @JoinColumn(name = "resumen_recaudacion_id_caja", referencedColumnName = "caja_recaudacion_id")
    @ManyToOne(optional = false)
    private CajaRecaudacion resumenRecaudacionIdCaja;
    @JoinColumn(name = "resumen_recaudacion_id_proceso", referencedColumnName = "proceso_recaudacion_id")
    @ManyToOne(optional = false)
    private ProcesoRecaudacion resumenRecaudacionIdProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ctvResumenIdResumenRecaudacion")
    private List<CtvResumen> ctvResumenList;

    public ResumenRecaudacion() {
    }

    public ResumenRecaudacion(Integer resumenRecaudacionId) {
        this.resumenRecaudacionId = resumenRecaudacionId;
    }

    public ResumenRecaudacion(Integer resumenRecaudacionId, Date resumenRecaudacionFecha, int resumenRecaudacionTotal, int resumenRecaudacionTieneTransporte, Date resumenRecaudacionFechaIngreso) {
        this.resumenRecaudacionId = resumenRecaudacionId;
        this.resumenRecaudacionFecha = resumenRecaudacionFecha;
        this.resumenRecaudacionTotal = resumenRecaudacionTotal;
        this.resumenRecaudacionTieneTransporte = resumenRecaudacionTieneTransporte;
        this.resumenRecaudacionFechaIngreso = resumenRecaudacionFechaIngreso;
    }

    public Integer getResumenRecaudacionId() {
        return resumenRecaudacionId;
    }

    public void setResumenRecaudacionId(Integer resumenRecaudacionId) {
        this.resumenRecaudacionId = resumenRecaudacionId;
    }

    public Date getResumenRecaudacionFecha() {
        return resumenRecaudacionFecha;
    }

    public void setResumenRecaudacionFecha(Date resumenRecaudacionFecha) {
        this.resumenRecaudacionFecha = resumenRecaudacionFecha;
    }

    public int getResumenRecaudacionTotal() {
        return resumenRecaudacionTotal;
    }

    public void setResumenRecaudacionTotal(int resumenRecaudacionTotal) {
        this.resumenRecaudacionTotal = resumenRecaudacionTotal;
    }

    public boolean getResumenRecaudacionCerrado() {
        return resumenRecaudacionCerrado;
    }

    public void setResumenRecaudacionCerrado(boolean resumenRecaudacionCerrado) {
        this.resumenRecaudacionCerrado = resumenRecaudacionCerrado;
    }

    public int getResumenRecaudacionTieneTransporte() {
        return resumenRecaudacionTieneTransporte;
    }

    public void setResumenRecaudacionTieneTransporte(int resumenRecaudacionTieneTransporte) {
        this.resumenRecaudacionTieneTransporte = resumenRecaudacionTieneTransporte;
    }

    public Date getResumenRecaudacionFechaIngreso() {
        return resumenRecaudacionFechaIngreso;
    }

    public void setResumenRecaudacionFechaIngreso(Date resumenRecaudacionFechaIngreso) {
        this.resumenRecaudacionFechaIngreso = resumenRecaudacionFechaIngreso;
    }

    public Date getResumenRecaudacionFechaActualizacion() {
        return resumenRecaudacionFechaActualizacion;
    }

    public void setResumenRecaudacionFechaActualizacion(Date resumenRecaudacionFechaActualizacion) {
        this.resumenRecaudacionFechaActualizacion = resumenRecaudacionFechaActualizacion;
    }

    @XmlTransient
    public List<DetalleResumenRecaudacion> getDetalleResumenRecaudacionList() {
        return detalleResumenRecaudacionList;
    }

    public void setDetalleResumenRecaudacionList(List<DetalleResumenRecaudacion> detalleResumenRecaudacionList) {
        this.detalleResumenRecaudacionList = detalleResumenRecaudacionList;
    }

    @XmlTransient
    public List<EgresoRecaudacion> getEgresoRecaudacionList() {
        return egresoRecaudacionList;
    }

    public void setEgresoRecaudacionList(List<EgresoRecaudacion> egresoRecaudacionList) {
        this.egresoRecaudacionList = egresoRecaudacionList;
    }

    public CajaRecaudacion getResumenRecaudacionIdCaja() {
        return resumenRecaudacionIdCaja;
    }

    public void setResumenRecaudacionIdCaja(CajaRecaudacion resumenRecaudacionIdCaja) {
        this.resumenRecaudacionIdCaja = resumenRecaudacionIdCaja;
    }

    public ProcesoRecaudacion getResumenRecaudacionIdProceso() {
        return resumenRecaudacionIdProceso;
    }

    public void setResumenRecaudacionIdProceso(ProcesoRecaudacion resumenRecaudacionIdProceso) {
        this.resumenRecaudacionIdProceso = resumenRecaudacionIdProceso;
    }

    @XmlTransient
    public List<CtvResumen> getCtvResumenList() {
        return ctvResumenList;
    }

    public void setCtvResumenList(List<CtvResumen> ctvResumenList) {
        this.ctvResumenList = ctvResumenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resumenRecaudacionId != null ? resumenRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenRecaudacion)) {
            return false;
        }
        ResumenRecaudacion other = (ResumenRecaudacion) object;
        if ((this.resumenRecaudacionId == null && other.resumenRecaudacionId != null) || (this.resumenRecaudacionId != null && !this.resumenRecaudacionId.equals(other.resumenRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ResumenRecaudacion[ resumenRecaudacionId=" + resumenRecaudacionId + " ]";
    }

}
