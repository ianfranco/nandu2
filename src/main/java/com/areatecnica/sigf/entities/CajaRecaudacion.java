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
 *
 * @author ianfr
 */
@Entity
@Table(name = "caja_recaudacion", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajaRecaudacion.findAll", query = "SELECT c FROM CajaRecaudacion c")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionId", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionId = :cajaRecaudacionId")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionIdUsuario", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionIdUsuario = :cajaRecaudacionIdUsuario")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionNombreCaja", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionNombreCaja = :cajaRecaudacionNombreCaja")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionTieneEgresos", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionTieneEgresos = :cajaRecaudacionTieneEgresos")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionActiva", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionActiva = :cajaRecaudacionActiva")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionFechaIngreso", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionFechaIngreso = :cajaRecaudacionFechaIngreso")})
public class CajaRecaudacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caja_recaudacion_id")
    private Integer cajaRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "caja_recaudacion_nombre_caja")
    private String cajaRecaudacionNombreCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_recaudacion_tiene_egresos")
    private boolean cajaRecaudacionTieneEgresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_recaudacion_activa")
    private boolean cajaRecaudacionActiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_recaudacion_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cajaRecaudacionFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoCajaRecaudacionIdCaja")
    private List<EgresoCajaRecaudacion> egresoCajaRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaProcesoIdCaja")
    private List<CajaProceso> cajaProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resumenRecaudacionIdCaja")
    private List<ResumenRecaudacion> resumenRecaudacionList;
    @JoinColumn(name = "caja_recaudacion_id_terminal", referencedColumnName = "terminal_id")
    @ManyToOne(optional = false)
    private Terminal cajaRecaudacionIdTerminal;
    @JoinColumn(name = "caja_recaudacion_id_usuario", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario cajaRecaudacionIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guiaIdCajaTerminal")
    private List<Guia> guiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioCajaIdCaja")
    private List<InventarioCaja> inventarioCajaList;

    public CajaRecaudacion() {
    }

    public CajaRecaudacion(Integer cajaRecaudacionId) {
        this.cajaRecaudacionId = cajaRecaudacionId;
    }

    public CajaRecaudacion(Integer cajaRecaudacionId, String cajaRecaudacionNombreCaja, boolean cajaRecaudacionTieneEgresos, boolean cajaRecaudacionActiva, Date cajaRecaudacionFechaIngreso) {
        this.cajaRecaudacionId = cajaRecaudacionId;
        this.cajaRecaudacionNombreCaja = cajaRecaudacionNombreCaja;
        this.cajaRecaudacionTieneEgresos = cajaRecaudacionTieneEgresos;
        this.cajaRecaudacionActiva = cajaRecaudacionActiva;
        this.cajaRecaudacionFechaIngreso = cajaRecaudacionFechaIngreso;
    }

    public Integer getCajaRecaudacionId() {
        return cajaRecaudacionId;
    }

    public void setCajaRecaudacionId(Integer cajaRecaudacionId) {
        this.cajaRecaudacionId = cajaRecaudacionId;
    }

    public String getCajaRecaudacionNombreCaja() {
        return cajaRecaudacionNombreCaja;
    }

    public void setCajaRecaudacionNombreCaja(String cajaRecaudacionNombreCaja) {
        this.cajaRecaudacionNombreCaja = cajaRecaudacionNombreCaja;
    }

    public boolean getCajaRecaudacionTieneEgresos() {
        return cajaRecaudacionTieneEgresos;
    }

    public void setCajaRecaudacionTieneEgresos(boolean cajaRecaudacionTieneEgresos) {
        this.cajaRecaudacionTieneEgresos = cajaRecaudacionTieneEgresos;
    }

    public boolean getCajaRecaudacionActiva() {
        return cajaRecaudacionActiva;
    }

    public void setCajaRecaudacionActiva(boolean cajaRecaudacionActiva) {
        this.cajaRecaudacionActiva = cajaRecaudacionActiva;
    }

    public Date getCajaRecaudacionFechaIngreso() {
        return cajaRecaudacionFechaIngreso;
    }

    public void setCajaRecaudacionFechaIngreso(Date cajaRecaudacionFechaIngreso) {
        this.cajaRecaudacionFechaIngreso = cajaRecaudacionFechaIngreso;
    }

    @XmlTransient
    public List<EgresoCajaRecaudacion> getEgresoCajaRecaudacionList() {
        return egresoCajaRecaudacionList;
    }

    public void setEgresoCajaRecaudacionList(List<EgresoCajaRecaudacion> egresoCajaRecaudacionList) {
        this.egresoCajaRecaudacionList = egresoCajaRecaudacionList;
    }

    @XmlTransient
    public List<CajaProceso> getCajaProcesoList() {
        return cajaProcesoList;
    }

    public void setCajaProcesoList(List<CajaProceso> cajaProcesoList) {
        this.cajaProcesoList = cajaProcesoList;
    }

    @XmlTransient
    public List<ResumenRecaudacion> getResumenRecaudacionList() {
        return resumenRecaudacionList;
    }

    public void setResumenRecaudacionList(List<ResumenRecaudacion> resumenRecaudacionList) {
        this.resumenRecaudacionList = resumenRecaudacionList;
    }

    public Terminal getCajaRecaudacionIdTerminal() {
        return cajaRecaudacionIdTerminal;
    }

    public void setCajaRecaudacionIdTerminal(Terminal cajaRecaudacionIdTerminal) {
        this.cajaRecaudacionIdTerminal = cajaRecaudacionIdTerminal;
    }

    public Usuario getCajaRecaudacionIdUsuario() {
        return cajaRecaudacionIdUsuario;
    }

    public void setCajaRecaudacionIdUsuario(Usuario cajaRecaudacionIdUsuario) {
        this.cajaRecaudacionIdUsuario = cajaRecaudacionIdUsuario;
    }

    @XmlTransient
    public List<Guia> getGuiaList() {
        return guiaList;
    }

    public void setGuiaList(List<Guia> guiaList) {
        this.guiaList = guiaList;
    }

    @XmlTransient
    public List<InventarioCaja> getInventarioCajaList() {
        return inventarioCajaList;
    }

    public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
        this.inventarioCajaList = inventarioCajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cajaRecaudacionId != null ? cajaRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaRecaudacion)) {
            return false;
        }
        CajaRecaudacion other = (CajaRecaudacion) object;
        if ((this.cajaRecaudacionId == null && other.cajaRecaudacionId != null) || (this.cajaRecaudacionId != null && !this.cajaRecaudacionId.equals(other.cajaRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CajaRecaudacion[ cajaRecaudacionId=" + cajaRecaudacionId + " ]";
    }

}
