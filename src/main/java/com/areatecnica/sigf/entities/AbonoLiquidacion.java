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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "abono_liquidacion", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AbonoLiquidacion.findAll", query = "SELECT a FROM AbonoLiquidacion a")
    , @NamedQuery(name = "AbonoLiquidacion.findByAbonoLiquidacionId", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionId = :abonoLiquidacionId")
    , @NamedQuery(name = "AbonoLiquidacion.findByAbonoLiquidacionMonto", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionMonto = :abonoLiquidacionMonto")
    , @NamedQuery(name = "AbonoLiquidacion.findByAbonoLiquidacionDescripcion", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionDescripcion = :abonoLiquidacionDescripcion")
    , @NamedQuery(name = "AbonoLiquidacion.findByAbonoLiquidacionFechaIngreso", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionFechaIngreso = :abonoLiquidacionFechaIngreso")})
public class AbonoLiquidacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "abono_liquidacion_id")
    private Integer abonoLiquidacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_liquidacion_monto")
    private int abonoLiquidacionMonto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "abono_liquidacion_descripcion")
    private String abonoLiquidacionDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_liquidacion_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date abonoLiquidacionFechaIngreso;
    @JoinColumn(name = "abono_liquidacion_id_abono", referencedColumnName = "abono_bus_id")
    @ManyToOne(optional = false)
    private AbonoBus abonoLiquidacionIdAbono;
    @JoinColumn(name = "abono_liquidacion_id_liquidacion_empresa", referencedColumnName = "liquidacion_empresa_id")
    @ManyToOne(optional = false)
    private LiquidacionEmpresa abonoLiquidacionIdLiquidacionEmpresa;

    public AbonoLiquidacion() {
    }

    public AbonoLiquidacion(Integer abonoLiquidacionId) {
        this.abonoLiquidacionId = abonoLiquidacionId;
    }

    public AbonoLiquidacion(Integer abonoLiquidacionId, int abonoLiquidacionMonto, String abonoLiquidacionDescripcion, Date abonoLiquidacionFechaIngreso) {
        this.abonoLiquidacionId = abonoLiquidacionId;
        this.abonoLiquidacionMonto = abonoLiquidacionMonto;
        this.abonoLiquidacionDescripcion = abonoLiquidacionDescripcion;
        this.abonoLiquidacionFechaIngreso = abonoLiquidacionFechaIngreso;
    }

    public Integer getAbonoLiquidacionId() {
        return abonoLiquidacionId;
    }

    public void setAbonoLiquidacionId(Integer abonoLiquidacionId) {
        this.abonoLiquidacionId = abonoLiquidacionId;
    }

    public int getAbonoLiquidacionMonto() {
        return abonoLiquidacionMonto;
    }

    public void setAbonoLiquidacionMonto(int abonoLiquidacionMonto) {
        this.abonoLiquidacionMonto = abonoLiquidacionMonto;
    }

    public String getAbonoLiquidacionDescripcion() {
        return abonoLiquidacionDescripcion;
    }

    public void setAbonoLiquidacionDescripcion(String abonoLiquidacionDescripcion) {
        this.abonoLiquidacionDescripcion = abonoLiquidacionDescripcion;
    }

    public Date getAbonoLiquidacionFechaIngreso() {
        return abonoLiquidacionFechaIngreso;
    }

    public void setAbonoLiquidacionFechaIngreso(Date abonoLiquidacionFechaIngreso) {
        this.abonoLiquidacionFechaIngreso = abonoLiquidacionFechaIngreso;
    }

    public AbonoBus getAbonoLiquidacionIdAbono() {
        return abonoLiquidacionIdAbono;
    }

    public void setAbonoLiquidacionIdAbono(AbonoBus abonoLiquidacionIdAbono) {
        this.abonoLiquidacionIdAbono = abonoLiquidacionIdAbono;
    }

    public LiquidacionEmpresa getAbonoLiquidacionIdLiquidacionEmpresa() {
        return abonoLiquidacionIdLiquidacionEmpresa;
    }

    public void setAbonoLiquidacionIdLiquidacionEmpresa(LiquidacionEmpresa abonoLiquidacionIdLiquidacionEmpresa) {
        this.abonoLiquidacionIdLiquidacionEmpresa = abonoLiquidacionIdLiquidacionEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (abonoLiquidacionId != null ? abonoLiquidacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonoLiquidacion)) {
            return false;
        }
        AbonoLiquidacion other = (AbonoLiquidacion) object;
        if ((this.abonoLiquidacionId == null && other.abonoLiquidacionId != null) || (this.abonoLiquidacionId != null && !this.abonoLiquidacionId.equals(other.abonoLiquidacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AbonoLiquidacion[ abonoLiquidacionId=" + abonoLiquidacionId + " ]";
    }
    
}
