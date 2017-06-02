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
 * @author ianfr
 */
@Entity
@Table(name = "egreso_guia", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoGuia.findAll", query = "SELECT e FROM EgresoGuia e")
    , @NamedQuery(name = "EgresoGuia.findByEgresoGuiaId", query = "SELECT e FROM EgresoGuia e WHERE e.egresoGuiaId = :egresoGuiaId")
    , @NamedQuery(name = "EgresoGuia.findByEgresoGuiaMonto", query = "SELECT e FROM EgresoGuia e WHERE e.egresoGuiaMonto = :egresoGuiaMonto")
    , @NamedQuery(name = "EgresoGuia.findByEgresoGuiaRecaudado", query = "SELECT e FROM EgresoGuia e WHERE e.egresoGuiaRecaudado = :egresoGuiaRecaudado")
    , @NamedQuery(name = "EgresoGuia.findByEgresoGuiaFechaIngreso", query = "SELECT e FROM EgresoGuia e WHERE e.egresoGuiaFechaIngreso = :egresoGuiaFechaIngreso")})
public class EgresoGuia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_guia_id")
    private Integer egresoGuiaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_guia_monto")
    private int egresoGuiaMonto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_guia_recaudado")
    private boolean egresoGuiaRecaudado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_guia_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date egresoGuiaFechaIngreso;
    @JoinColumn(name = "egreso_guia_id_egreso", referencedColumnName = "egreso_id")
    @ManyToOne(optional = false)
    private Egreso egresoGuiaIdEgreso;
    @JoinColumn(name = "egreso_guia_id_guia", referencedColumnName = "guia_id")
    @ManyToOne(optional = false)
    private Guia egresoGuiaIdGuia;

    public EgresoGuia() {
    }

    public EgresoGuia(Integer egresoGuiaId) {
        this.egresoGuiaId = egresoGuiaId;
    }

    public EgresoGuia(Integer egresoGuiaId, int egresoGuiaMonto, boolean egresoGuiaRecaudado, Date egresoGuiaFechaIngreso) {
        this.egresoGuiaId = egresoGuiaId;
        this.egresoGuiaMonto = egresoGuiaMonto;
        this.egresoGuiaRecaudado = egresoGuiaRecaudado;
        this.egresoGuiaFechaIngreso = egresoGuiaFechaIngreso;
    }

    public Integer getEgresoGuiaId() {
        return egresoGuiaId;
    }

    public void setEgresoGuiaId(Integer egresoGuiaId) {
        this.egresoGuiaId = egresoGuiaId;
    }

    public int getEgresoGuiaMonto() {
        return egresoGuiaMonto;
    }

    public void setEgresoGuiaMonto(int egresoGuiaMonto) {
        this.egresoGuiaMonto = egresoGuiaMonto;
    }

    public boolean getEgresoGuiaRecaudado() {
        return egresoGuiaRecaudado;
    }

    public void setEgresoGuiaRecaudado(boolean egresoGuiaRecaudado) {
        this.egresoGuiaRecaudado = egresoGuiaRecaudado;
    }

    public Date getEgresoGuiaFechaIngreso() {
        return egresoGuiaFechaIngreso;
    }

    public void setEgresoGuiaFechaIngreso(Date egresoGuiaFechaIngreso) {
        this.egresoGuiaFechaIngreso = egresoGuiaFechaIngreso;
    }

    public Egreso getEgresoGuiaIdEgreso() {
        return egresoGuiaIdEgreso;
    }

    public void setEgresoGuiaIdEgreso(Egreso egresoGuiaIdEgreso) {
        this.egresoGuiaIdEgreso = egresoGuiaIdEgreso;
    }

    public Guia getEgresoGuiaIdGuia() {
        return egresoGuiaIdGuia;
    }

    public void setEgresoGuiaIdGuia(Guia egresoGuiaIdGuia) {
        this.egresoGuiaIdGuia = egresoGuiaIdGuia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoGuiaId != null ? egresoGuiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoGuia)) {
            return false;
        }
        EgresoGuia other = (EgresoGuia) object;
        if ((this.egresoGuiaId == null && other.egresoGuiaId != null) || (this.egresoGuiaId != null && !this.egresoGuiaId.equals(other.egresoGuiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoGuia[ egresoGuiaId=" + egresoGuiaId + " ]";
    }
    
}
