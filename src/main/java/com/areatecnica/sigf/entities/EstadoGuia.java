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
@Table(name = "estado_guia", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoGuia.findAll", query = "SELECT e FROM EstadoGuia e"),
    @NamedQuery(name = "EstadoGuia.findByEstadoGuiaId", query = "SELECT e FROM EstadoGuia e WHERE e.estadoGuiaId = :estadoGuiaId"),
    @NamedQuery(name = "EstadoGuia.findByEstadoGuiaIdCuenta", query = "SELECT e FROM EstadoGuia e WHERE e.estadoGuiaIdCuenta = :estadoGuiaIdCuenta"),
    @NamedQuery(name = "EstadoGuia.findAllByCuentaInspector", query = "SELECT e FROM EstadoGuia e WHERE e.estadoGuiaIdCuenta = :estadoGuiaIdCuenta AND e.estadoGuiaInspector = true"),
    @NamedQuery(name = "EstadoGuia.findAllByCuentaRecaudador", query = "SELECT e FROM EstadoGuia e WHERE e.estadoGuiaIdCuenta = :estadoGuiaIdCuenta AND e.estadoGuiaRecaudacion = true"),
    @NamedQuery(name = "EstadoGuia.findByEstadoGuiaNombre", query = "SELECT e FROM EstadoGuia e WHERE e.estadoGuiaNombre = :estadoGuiaNombre"),
    @NamedQuery(name = "EstadoGuia.findByEstadoGuiaActivo", query = "SELECT e FROM EstadoGuia e WHERE e.estadoGuiaActivo = :estadoGuiaActivo"),
    @NamedQuery(name = "EstadoGuia.findByEstadoGuiaFechaIngreso", query = "SELECT e FROM EstadoGuia e WHERE e.estadoGuiaFechaIngreso = :estadoGuiaFechaIngreso")})
public class EstadoGuia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "estado_guia_id")
    private Integer estadoGuiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado_guia_nombre")
    private String estadoGuiaNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_guia_activo")
    private boolean estadoGuiaActivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_guia_recaudacion")
    private boolean estadoGuiaRecaudacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_guia_inspector")
    private boolean estadoGuiaInspector;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_guia_permitir_operacion")
    private boolean estadoGuiaPermitirOperacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_guia_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estadoGuiaFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guiaIdEstado")
    private List<Guia> guiaList;
    @JoinColumn(name = "estado_guia_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta estadoGuiaIdCuenta;

    public EstadoGuia() {
    }

    public EstadoGuia(Integer estadoGuiaId) {
        this.estadoGuiaId = estadoGuiaId;
    }

    public EstadoGuia(Integer estadoGuiaId, String estadoGuiaNombre, boolean estadoGuiaActivo, Date estadoGuiaFechaIngreso) {
        this.estadoGuiaId = estadoGuiaId;
        this.estadoGuiaNombre = estadoGuiaNombre;
        this.estadoGuiaActivo = estadoGuiaActivo;
        this.estadoGuiaFechaIngreso = estadoGuiaFechaIngreso;
    }

    public Integer getEstadoGuiaId() {
        return estadoGuiaId;
    }

    public void setEstadoGuiaId(Integer estadoGuiaId) {
        this.estadoGuiaId = estadoGuiaId;
    }

    public String getEstadoGuiaNombre() {
        return estadoGuiaNombre;
    }

    public void setEstadoGuiaNombre(String estadoGuiaNombre) {
        this.estadoGuiaNombre = estadoGuiaNombre;
    }

    public boolean getEstadoGuiaActivo() {
        return estadoGuiaActivo;
    }

    public void setEstadoGuiaActivo(boolean estadoGuiaActivo) {
        this.estadoGuiaActivo = estadoGuiaActivo;
    }

    public boolean getEstadoGuiaRecaudacion() {
        return estadoGuiaRecaudacion;
    }

    public void setEstadoGuiaRecaudacion(boolean estadoGuiaRecaudacion) {
        this.estadoGuiaRecaudacion = estadoGuiaRecaudacion;
    }

    public boolean getEstadoGuiaInspector() {
        return estadoGuiaInspector;
    }

    public void setEstadoGuiaInspector(boolean estadoGuiaInspector) {
        this.estadoGuiaInspector = estadoGuiaInspector;
    }

    public boolean getEstadoGuiaPermitirOperacion() {
        return estadoGuiaPermitirOperacion;
    }

    public void setEstadoGuiaPermitirOperacion(boolean estadoGuiaPermitirOperacion) {
        this.estadoGuiaPermitirOperacion = estadoGuiaPermitirOperacion;
    }

    public Date getEstadoGuiaFechaIngreso() {
        return estadoGuiaFechaIngreso;
    }

    public void setEstadoGuiaFechaIngreso(Date estadoGuiaFechaIngreso) {
        this.estadoGuiaFechaIngreso = estadoGuiaFechaIngreso;
    }

    @XmlTransient
    public List<Guia> getGuiaList() {
        return guiaList;
    }

    public void setGuiaList(List<Guia> guiaList) {
        this.guiaList = guiaList;
    }

    public Cuenta getEstadoGuiaIdCuenta() {
        return estadoGuiaIdCuenta;
    }

    public void setEstadoGuiaIdCuenta(Cuenta estadoGuiaIdCuenta) {
        this.estadoGuiaIdCuenta = estadoGuiaIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoGuiaId != null ? estadoGuiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoGuia)) {
            return false;
        }
        EstadoGuia other = (EstadoGuia) object;
        if ((this.estadoGuiaId == null && other.estadoGuiaId != null) || (this.estadoGuiaId != null && !this.estadoGuiaId.equals(other.estadoGuiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EstadoGuia[ estadoGuiaId=" + estadoGuiaId + " ]";
    }

}
