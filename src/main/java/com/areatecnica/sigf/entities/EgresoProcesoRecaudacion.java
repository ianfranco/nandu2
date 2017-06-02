/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * findByEgresoProcesoRecaudacionIdProceso
 *
 * @author ianfr
 */
@Entity
@Table(name = "egreso_proceso_recaudacion", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoProcesoRecaudacion.findAll", query = "SELECT e FROM EgresoProcesoRecaudacion e")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionId", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionId = :egresoProcesoRecaudacionId")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionIdProceso", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionIdProceso = :egresoProcesoRecaudacionIdProceso")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionValorDefecto", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionValorDefecto = :egresoProcesoRecaudacionValorDefecto")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionPorcentaje", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionPorcentaje = :egresoProcesoRecaudacionPorcentaje")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionNumeroOrden", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionNumeroOrden = :egresoProcesoRecaudacionNumeroOrden")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionActivo", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionActivo = :egresoProcesoRecaudacionActivo")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionFechaIngreso", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionFechaIngreso = :egresoProcesoRecaudacionFechaIngreso")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionFechaActualizacion", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionFechaActualizacion = :egresoProcesoRecaudacionFechaActualizacion")})
public class EgresoProcesoRecaudacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_proceso_recaudacion_id")
    private Integer egresoProcesoRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_valor_defecto")
    private int egresoProcesoRecaudacionValorDefecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_porcentaje")
    private BigDecimal egresoProcesoRecaudacionPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_numero_orden")
    private int egresoProcesoRecaudacionNumeroOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_activo")
    private boolean egresoProcesoRecaudacionActivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date egresoProcesoRecaudacionFechaIngreso;
    @Column(name = "egreso_proceso_recaudacion_fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date egresoProcesoRecaudacionFechaActualizacion;
    @JoinColumn(name = "egreso_proceso_recaudacion_id_egreso", referencedColumnName = "egreso_id")
    @ManyToOne(optional = false)
    private Egreso egresoProcesoRecaudacionIdEgreso;
    @JoinColumn(name = "egreso_proceso_recaudacion_id_proceso", referencedColumnName = "proceso_recaudacion_id")
    @ManyToOne(optional = false)
    private ProcesoRecaudacion egresoProcesoRecaudacionIdProceso;

    public EgresoProcesoRecaudacion() {
    }

    public EgresoProcesoRecaudacion(Integer egresoProcesoRecaudacionId) {
        this.egresoProcesoRecaudacionId = egresoProcesoRecaudacionId;
    }

    public EgresoProcesoRecaudacion(Integer egresoProcesoRecaudacionId, int egresoProcesoRecaudacionValorDefecto, BigDecimal egresoProcesoRecaudacionPorcentaje, int egresoProcesoRecaudacionNumeroOrden, boolean egresoProcesoRecaudacionActivo, Date egresoProcesoRecaudacionFechaIngreso) {
        this.egresoProcesoRecaudacionId = egresoProcesoRecaudacionId;
        this.egresoProcesoRecaudacionValorDefecto = egresoProcesoRecaudacionValorDefecto;
        this.egresoProcesoRecaudacionPorcentaje = egresoProcesoRecaudacionPorcentaje;
        this.egresoProcesoRecaudacionNumeroOrden = egresoProcesoRecaudacionNumeroOrden;
        this.egresoProcesoRecaudacionActivo = egresoProcesoRecaudacionActivo;
        this.egresoProcesoRecaudacionFechaIngreso = egresoProcesoRecaudacionFechaIngreso;
    }

    public Integer getEgresoProcesoRecaudacionId() {
        return egresoProcesoRecaudacionId;
    }

    public void setEgresoProcesoRecaudacionId(Integer egresoProcesoRecaudacionId) {
        this.egresoProcesoRecaudacionId = egresoProcesoRecaudacionId;
    }

    public int getEgresoProcesoRecaudacionValorDefecto() {
        return egresoProcesoRecaudacionValorDefecto;
    }

    public void setEgresoProcesoRecaudacionValorDefecto(int egresoProcesoRecaudacionValorDefecto) {
        this.egresoProcesoRecaudacionValorDefecto = egresoProcesoRecaudacionValorDefecto;
    }

    public BigDecimal getEgresoProcesoRecaudacionPorcentaje() {
        return egresoProcesoRecaudacionPorcentaje;
    }

    public void setEgresoProcesoRecaudacionPorcentaje(BigDecimal egresoProcesoRecaudacionPorcentaje) {
        this.egresoProcesoRecaudacionPorcentaje = egresoProcesoRecaudacionPorcentaje;
    }

    public int getEgresoProcesoRecaudacionNumeroOrden() {
        return egresoProcesoRecaudacionNumeroOrden;
    }

    public void setEgresoProcesoRecaudacionNumeroOrden(int egresoProcesoRecaudacionNumeroOrden) {
        this.egresoProcesoRecaudacionNumeroOrden = egresoProcesoRecaudacionNumeroOrden;
    }

    public boolean getEgresoProcesoRecaudacionActivo() {
        return egresoProcesoRecaudacionActivo;
    }

    public void setEgresoProcesoRecaudacionActivo(boolean egresoProcesoRecaudacionActivo) {
        this.egresoProcesoRecaudacionActivo = egresoProcesoRecaudacionActivo;
    }

    public Date getEgresoProcesoRecaudacionFechaIngreso() {
        return egresoProcesoRecaudacionFechaIngreso;
    }

    public void setEgresoProcesoRecaudacionFechaIngreso(Date egresoProcesoRecaudacionFechaIngreso) {
        this.egresoProcesoRecaudacionFechaIngreso = egresoProcesoRecaudacionFechaIngreso;
    }

    public Date getEgresoProcesoRecaudacionFechaActualizacion() {
        return egresoProcesoRecaudacionFechaActualizacion;
    }

    public void setEgresoProcesoRecaudacionFechaActualizacion(Date egresoProcesoRecaudacionFechaActualizacion) {
        this.egresoProcesoRecaudacionFechaActualizacion = egresoProcesoRecaudacionFechaActualizacion;
    }

    public Egreso getEgresoProcesoRecaudacionIdEgreso() {
        return egresoProcesoRecaudacionIdEgreso;
    }

    public void setEgresoProcesoRecaudacionIdEgreso(Egreso egresoProcesoRecaudacionIdEgreso) {
        this.egresoProcesoRecaudacionIdEgreso = egresoProcesoRecaudacionIdEgreso;
    }

    public ProcesoRecaudacion getEgresoProcesoRecaudacionIdProceso() {
        return egresoProcesoRecaudacionIdProceso;
    }

    public void setEgresoProcesoRecaudacionIdProceso(ProcesoRecaudacion egresoProcesoRecaudacionIdProceso) {
        this.egresoProcesoRecaudacionIdProceso = egresoProcesoRecaudacionIdProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoProcesoRecaudacionId != null ? egresoProcesoRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoProcesoRecaudacion)) {
            return false;
        }
        EgresoProcesoRecaudacion other = (EgresoProcesoRecaudacion) object;
        if ((this.egresoProcesoRecaudacionId == null && other.egresoProcesoRecaudacionId != null) || (this.egresoProcesoRecaudacionId != null && !this.egresoProcesoRecaudacionId.equals(other.egresoProcesoRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoProcesoRecaudacion[ egresoProcesoRecaudacionId=" + egresoProcesoRecaudacionId + " ]";
    }

}
