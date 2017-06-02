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
@Table(name = "moneda_pactada_institucion_salud", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonedaPactadaInstitucionSalud.findAll", query = "SELECT m FROM MonedaPactadaInstitucionSalud m")
    , @NamedQuery(name = "MonedaPactadaInstitucionSalud.findByMonedaPactadaInstitucionSaludId", query = "SELECT m FROM MonedaPactadaInstitucionSalud m WHERE m.monedaPactadaInstitucionSaludId = :monedaPactadaInstitucionSaludId")
    , @NamedQuery(name = "MonedaPactadaInstitucionSalud.findByMonedaPactadaInstitucionSaludNombre", query = "SELECT m FROM MonedaPactadaInstitucionSalud m WHERE m.monedaPactadaInstitucionSaludNombre = :monedaPactadaInstitucionSaludNombre")
    , @NamedQuery(name = "MonedaPactadaInstitucionSalud.findByMonedaPactadaInstitucionSaludFechaIngreso", query = "SELECT m FROM MonedaPactadaInstitucionSalud m WHERE m.monedaPactadaInstitucionSaludFechaIngreso = :monedaPactadaInstitucionSaludFechaIngreso")})
public class MonedaPactadaInstitucionSalud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "moneda_pactada_institucion_salud_id")
    private Integer monedaPactadaInstitucionSaludId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "moneda_pactada_institucion_salud_nombre")
    private String monedaPactadaInstitucionSaludNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "moneda_pactada_institucion_salud_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date monedaPactadaInstitucionSaludFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorAdicionalSaludIdMoneda")
    private List<TrabajadorAdicionalSalud> trabajadorAdicionalSaludList;

    public MonedaPactadaInstitucionSalud() {
    }

    public MonedaPactadaInstitucionSalud(Integer monedaPactadaInstitucionSaludId) {
        this.monedaPactadaInstitucionSaludId = monedaPactadaInstitucionSaludId;
    }

    public MonedaPactadaInstitucionSalud(Integer monedaPactadaInstitucionSaludId, String monedaPactadaInstitucionSaludNombre, Date monedaPactadaInstitucionSaludFechaIngreso) {
        this.monedaPactadaInstitucionSaludId = monedaPactadaInstitucionSaludId;
        this.monedaPactadaInstitucionSaludNombre = monedaPactadaInstitucionSaludNombre;
        this.monedaPactadaInstitucionSaludFechaIngreso = monedaPactadaInstitucionSaludFechaIngreso;
    }

    public Integer getMonedaPactadaInstitucionSaludId() {
        return monedaPactadaInstitucionSaludId;
    }

    public void setMonedaPactadaInstitucionSaludId(Integer monedaPactadaInstitucionSaludId) {
        this.monedaPactadaInstitucionSaludId = monedaPactadaInstitucionSaludId;
    }

    public String getMonedaPactadaInstitucionSaludNombre() {
        return monedaPactadaInstitucionSaludNombre;
    }

    public void setMonedaPactadaInstitucionSaludNombre(String monedaPactadaInstitucionSaludNombre) {
        this.monedaPactadaInstitucionSaludNombre = monedaPactadaInstitucionSaludNombre;
    }

    public Date getMonedaPactadaInstitucionSaludFechaIngreso() {
        return monedaPactadaInstitucionSaludFechaIngreso;
    }

    public void setMonedaPactadaInstitucionSaludFechaIngreso(Date monedaPactadaInstitucionSaludFechaIngreso) {
        this.monedaPactadaInstitucionSaludFechaIngreso = monedaPactadaInstitucionSaludFechaIngreso;
    }

    @XmlTransient
    public List<TrabajadorAdicionalSalud> getTrabajadorAdicionalSaludList() {
        return trabajadorAdicionalSaludList;
    }

    public void setTrabajadorAdicionalSaludList(List<TrabajadorAdicionalSalud> trabajadorAdicionalSaludList) {
        this.trabajadorAdicionalSaludList = trabajadorAdicionalSaludList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monedaPactadaInstitucionSaludId != null ? monedaPactadaInstitucionSaludId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonedaPactadaInstitucionSalud)) {
            return false;
        }
        MonedaPactadaInstitucionSalud other = (MonedaPactadaInstitucionSalud) object;
        if ((this.monedaPactadaInstitucionSaludId == null && other.monedaPactadaInstitucionSaludId != null) || (this.monedaPactadaInstitucionSaludId != null && !this.monedaPactadaInstitucionSaludId.equals(other.monedaPactadaInstitucionSaludId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.MonedaPactadaInstitucionSalud[ monedaPactadaInstitucionSaludId=" + monedaPactadaInstitucionSaludId + " ]";
    }
    
}
