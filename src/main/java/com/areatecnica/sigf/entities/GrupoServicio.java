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
@Table(name = "grupo_servicio", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoServicio.findAll", query = "SELECT g FROM GrupoServicio g")
    , @NamedQuery(name = "GrupoServicio.findByGrupoServicioId", query = "SELECT g FROM GrupoServicio g WHERE g.grupoServicioId = :grupoServicioId")
    , @NamedQuery(name = "GrupoServicio.findByGrupoServicioIdentificador", query = "SELECT g FROM GrupoServicio g WHERE g.grupoServicioIdentificador = :grupoServicioIdentificador")
    , @NamedQuery(name = "GrupoServicio.findByGrupoServicioFechaIngreso", query = "SELECT g FROM GrupoServicio g WHERE g.grupoServicioFechaIngreso = :grupoServicioFechaIngreso")})
public class GrupoServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grupo_servicio_id")
    private Integer grupoServicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "grupo_servicio_identificador")
    private String grupoServicioIdentificador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grupo_servicio_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date grupoServicioFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busIdGrupoServicio")
    private List<Bus> busList;
    @JoinColumn(name = "grupo_servicio_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta grupoServicioIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioIdGrupoServicio")
    private List<Servicio> servicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarifaGrupoServicioIdGrupo")
    private List<TarifaGrupoServicio> tarifaGrupoServicioList;

    public GrupoServicio() {
    }

    public GrupoServicio(Integer grupoServicioId) {
        this.grupoServicioId = grupoServicioId;
    }

    public GrupoServicio(Integer grupoServicioId, String grupoServicioIdentificador, Date grupoServicioFechaIngreso) {
        this.grupoServicioId = grupoServicioId;
        this.grupoServicioIdentificador = grupoServicioIdentificador;
        this.grupoServicioFechaIngreso = grupoServicioFechaIngreso;
    }

    public Integer getGrupoServicioId() {
        return grupoServicioId;
    }

    public void setGrupoServicioId(Integer grupoServicioId) {
        this.grupoServicioId = grupoServicioId;
    }

    public String getGrupoServicioIdentificador() {
        return grupoServicioIdentificador;
    }

    public void setGrupoServicioIdentificador(String grupoServicioIdentificador) {
        this.grupoServicioIdentificador = grupoServicioIdentificador;
    }

    public Date getGrupoServicioFechaIngreso() {
        return grupoServicioFechaIngreso;
    }

    public void setGrupoServicioFechaIngreso(Date grupoServicioFechaIngreso) {
        this.grupoServicioFechaIngreso = grupoServicioFechaIngreso;
    }

    @XmlTransient
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public Cuenta getGrupoServicioIdCuenta() {
        return grupoServicioIdCuenta;
    }

    public void setGrupoServicioIdCuenta(Cuenta grupoServicioIdCuenta) {
        this.grupoServicioIdCuenta = grupoServicioIdCuenta;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @XmlTransient
    public List<TarifaGrupoServicio> getTarifaGrupoServicioList() {
        return tarifaGrupoServicioList;
    }

    public void setTarifaGrupoServicioList(List<TarifaGrupoServicio> tarifaGrupoServicioList) {
        this.tarifaGrupoServicioList = tarifaGrupoServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoServicioId != null ? grupoServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoServicio)) {
            return false;
        }
        GrupoServicio other = (GrupoServicio) object;
        if ((this.grupoServicioId == null && other.grupoServicioId != null) || (this.grupoServicioId != null && !this.grupoServicioId.equals(other.grupoServicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.GrupoServicio[ grupoServicioId=" + grupoServicioId + " ]";
    }
    
}
