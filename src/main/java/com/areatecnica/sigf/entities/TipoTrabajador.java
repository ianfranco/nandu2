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
@Table(name = "tipo_trabajador", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTrabajador.findAll", query = "SELECT t FROM TipoTrabajador t")
    , @NamedQuery(name = "TipoTrabajador.findByTipoTrabajadorId", query = "SELECT t FROM TipoTrabajador t WHERE t.tipoTrabajadorId = :tipoTrabajadorId")
    , @NamedQuery(name = "TipoTrabajador.findByTipoTrabajadorNombre", query = "SELECT t FROM TipoTrabajador t WHERE t.tipoTrabajadorNombre = :tipoTrabajadorNombre")
    , @NamedQuery(name = "TipoTrabajador.findByTipoTrabajadorFechaIngreso", query = "SELECT t FROM TipoTrabajador t WHERE t.tipoTrabajadorFechaIngreso = :tipoTrabajadorFechaIngreso")})
public class TipoTrabajador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_trabajador_id")
    private Integer tipoTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_trabajador_nombre")
    private String tipoTrabajadorNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_trabajador_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tipoTrabajadorFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionLaboralIdTipoTrabajador")
    private List<RelacionLaboral> relacionLaboralList;
    @JoinColumn(name = "tipo_trabajador_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta tipoTrabajadorIdCuenta;

    public TipoTrabajador() {
    }

    public TipoTrabajador(Integer tipoTrabajadorId) {
        this.tipoTrabajadorId = tipoTrabajadorId;
    }

    public TipoTrabajador(Integer tipoTrabajadorId, String tipoTrabajadorNombre, Date tipoTrabajadorFechaIngreso) {
        this.tipoTrabajadorId = tipoTrabajadorId;
        this.tipoTrabajadorNombre = tipoTrabajadorNombre;
        this.tipoTrabajadorFechaIngreso = tipoTrabajadorFechaIngreso;
    }

    public Integer getTipoTrabajadorId() {
        return tipoTrabajadorId;
    }

    public void setTipoTrabajadorId(Integer tipoTrabajadorId) {
        this.tipoTrabajadorId = tipoTrabajadorId;
    }

    public String getTipoTrabajadorNombre() {
        return tipoTrabajadorNombre;
    }

    public void setTipoTrabajadorNombre(String tipoTrabajadorNombre) {
        this.tipoTrabajadorNombre = tipoTrabajadorNombre;
    }

    public Date getTipoTrabajadorFechaIngreso() {
        return tipoTrabajadorFechaIngreso;
    }

    public void setTipoTrabajadorFechaIngreso(Date tipoTrabajadorFechaIngreso) {
        this.tipoTrabajadorFechaIngreso = tipoTrabajadorFechaIngreso;
    }

    @XmlTransient
    public List<RelacionLaboral> getRelacionLaboralList() {
        return relacionLaboralList;
    }

    public void setRelacionLaboralList(List<RelacionLaboral> relacionLaboralList) {
        this.relacionLaboralList = relacionLaboralList;
    }

    public Cuenta getTipoTrabajadorIdCuenta() {
        return tipoTrabajadorIdCuenta;
    }

    public void setTipoTrabajadorIdCuenta(Cuenta tipoTrabajadorIdCuenta) {
        this.tipoTrabajadorIdCuenta = tipoTrabajadorIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoTrabajadorId != null ? tipoTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTrabajador)) {
            return false;
        }
        TipoTrabajador other = (TipoTrabajador) object;
        if ((this.tipoTrabajadorId == null && other.tipoTrabajadorId != null) || (this.tipoTrabajadorId != null && !this.tipoTrabajadorId.equals(other.tipoTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoTrabajador[ tipoTrabajadorId=" + tipoTrabajadorId + " ]";
    }
    
}
