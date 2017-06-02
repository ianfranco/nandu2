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
@Table(name = "tipo_carga_familiar", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCargaFamiliar.findAll", query = "SELECT t FROM TipoCargaFamiliar t")
    , @NamedQuery(name = "TipoCargaFamiliar.findByTipoCargaFamiliarId", query = "SELECT t FROM TipoCargaFamiliar t WHERE t.tipoCargaFamiliarId = :tipoCargaFamiliarId")
    , @NamedQuery(name = "TipoCargaFamiliar.findByTipoCargaFamiliarNombre", query = "SELECT t FROM TipoCargaFamiliar t WHERE t.tipoCargaFamiliarNombre = :tipoCargaFamiliarNombre")
    , @NamedQuery(name = "TipoCargaFamiliar.findByTipoCargaFamiliarFechaIngreso", query = "SELECT t FROM TipoCargaFamiliar t WHERE t.tipoCargaFamiliarFechaIngreso = :tipoCargaFamiliarFechaIngreso")})
public class TipoCargaFamiliar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_carga_familiar_id")
    private Integer tipoCargaFamiliarId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_carga_familiar_nombre")
    private String tipoCargaFamiliarNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_carga_familiar_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tipoCargaFamiliarFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargaTrabajadorIdTipo")
    private List<CargaTrabajador> cargaTrabajadorList;

    public TipoCargaFamiliar() {
    }

    public TipoCargaFamiliar(Integer tipoCargaFamiliarId) {
        this.tipoCargaFamiliarId = tipoCargaFamiliarId;
    }

    public TipoCargaFamiliar(Integer tipoCargaFamiliarId, String tipoCargaFamiliarNombre, Date tipoCargaFamiliarFechaIngreso) {
        this.tipoCargaFamiliarId = tipoCargaFamiliarId;
        this.tipoCargaFamiliarNombre = tipoCargaFamiliarNombre;
        this.tipoCargaFamiliarFechaIngreso = tipoCargaFamiliarFechaIngreso;
    }

    public Integer getTipoCargaFamiliarId() {
        return tipoCargaFamiliarId;
    }

    public void setTipoCargaFamiliarId(Integer tipoCargaFamiliarId) {
        this.tipoCargaFamiliarId = tipoCargaFamiliarId;
    }

    public String getTipoCargaFamiliarNombre() {
        return tipoCargaFamiliarNombre;
    }

    public void setTipoCargaFamiliarNombre(String tipoCargaFamiliarNombre) {
        this.tipoCargaFamiliarNombre = tipoCargaFamiliarNombre;
    }

    public Date getTipoCargaFamiliarFechaIngreso() {
        return tipoCargaFamiliarFechaIngreso;
    }

    public void setTipoCargaFamiliarFechaIngreso(Date tipoCargaFamiliarFechaIngreso) {
        this.tipoCargaFamiliarFechaIngreso = tipoCargaFamiliarFechaIngreso;
    }

    @XmlTransient
    public List<CargaTrabajador> getCargaTrabajadorList() {
        return cargaTrabajadorList;
    }

    public void setCargaTrabajadorList(List<CargaTrabajador> cargaTrabajadorList) {
        this.cargaTrabajadorList = cargaTrabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCargaFamiliarId != null ? tipoCargaFamiliarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCargaFamiliar)) {
            return false;
        }
        TipoCargaFamiliar other = (TipoCargaFamiliar) object;
        if ((this.tipoCargaFamiliarId == null && other.tipoCargaFamiliarId != null) || (this.tipoCargaFamiliarId != null && !this.tipoCargaFamiliarId.equals(other.tipoCargaFamiliarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoCargaFamiliar[ tipoCargaFamiliarId=" + tipoCargaFamiliarId + " ]";
    }
    
}
