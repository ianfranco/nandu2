/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "egreso_recaudacion", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoRecaudacion.findAll", query = "SELECT e FROM EgresoRecaudacion e")
    , @NamedQuery(name = "EgresoRecaudacion.findByEgresoRecaudacionId", query = "SELECT e FROM EgresoRecaudacion e WHERE e.egresoRecaudacionId = :egresoRecaudacionId")
    , @NamedQuery(name = "EgresoRecaudacion.findByEgresoRecaudacionTotalEgreso", query = "SELECT e FROM EgresoRecaudacion e WHERE e.egresoRecaudacionTotalEgreso = :egresoRecaudacionTotalEgreso")})
public class EgresoRecaudacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_recaudacion_id")
    private Integer egresoRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_recaudacion_total_egreso")
    private int egresoRecaudacionTotalEgreso;
    @JoinColumn(name = "egreso_recaudacion_id_egreso", referencedColumnName = "egreso_id")
    @ManyToOne(optional = false)
    private Egreso egresoRecaudacionIdEgreso;
    @JoinColumn(name = "egreso_recaudacion_id_resumen", referencedColumnName = "resumen_recaudacion_id")
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    private ResumenRecaudacion egresoRecaudacionIdResumen;

    public EgresoRecaudacion() {
    }

    public EgresoRecaudacion(Integer egresoRecaudacionId) {
        this.egresoRecaudacionId = egresoRecaudacionId;
    }

    public EgresoRecaudacion(Integer egresoRecaudacionId, int egresoRecaudacionTotalEgreso) {
        this.egresoRecaudacionId = egresoRecaudacionId;
        this.egresoRecaudacionTotalEgreso = egresoRecaudacionTotalEgreso;
    }

    public Integer getEgresoRecaudacionId() {
        return egresoRecaudacionId;
    }

    public void setEgresoRecaudacionId(Integer egresoRecaudacionId) {
        this.egresoRecaudacionId = egresoRecaudacionId;
    }

    public int getEgresoRecaudacionTotalEgreso() {
        return egresoRecaudacionTotalEgreso;
    }

    public void setEgresoRecaudacionTotalEgreso(int egresoRecaudacionTotalEgreso) {
        this.egresoRecaudacionTotalEgreso = egresoRecaudacionTotalEgreso;
    }

    public Egreso getEgresoRecaudacionIdEgreso() {
        return egresoRecaudacionIdEgreso;
    }

    public void setEgresoRecaudacionIdEgreso(Egreso egresoRecaudacionIdEgreso) {
        this.egresoRecaudacionIdEgreso = egresoRecaudacionIdEgreso;
    }

    public ResumenRecaudacion getEgresoRecaudacionIdResumen() {
        return egresoRecaudacionIdResumen;
    }

    public void setEgresoRecaudacionIdResumen(ResumenRecaudacion egresoRecaudacionIdResumen) {
        this.egresoRecaudacionIdResumen = egresoRecaudacionIdResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoRecaudacionId != null ? egresoRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoRecaudacion)) {
            return false;
        }
        EgresoRecaudacion other = (EgresoRecaudacion) object;
        if ((this.egresoRecaudacionId == null && other.egresoRecaudacionId != null) || (this.egresoRecaudacionId != null && !this.egresoRecaudacionId.equals(other.egresoRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoRecaudacion[ egresoRecaudacionId=" + egresoRecaudacionId + " ]";
    }
    
}
