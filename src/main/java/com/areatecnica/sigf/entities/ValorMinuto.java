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
@Table(name = "valor_minuto", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorMinuto.findAll", query = "SELECT v FROM ValorMinuto v")
    , @NamedQuery(name = "ValorMinuto.findByValorMinutoId", query = "SELECT v FROM ValorMinuto v WHERE v.valorMinutoId = :valorMinutoId")
    , @NamedQuery(name = "ValorMinuto.findByValorMinutoNombre", query = "SELECT v FROM ValorMinuto v WHERE v.valorMinutoNombre = :valorMinutoNombre")
    , @NamedQuery(name = "ValorMinuto.findByValorMinutoPrecio", query = "SELECT v FROM ValorMinuto v WHERE v.valorMinutoPrecio = :valorMinutoPrecio")
    , @NamedQuery(name = "ValorMinuto.findByValorMinutoFechaIngreso", query = "SELECT v FROM ValorMinuto v WHERE v.valorMinutoFechaIngreso = :valorMinutoFechaIngreso")})
public class ValorMinuto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_minuto_id")
    private Integer valorMinutoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "valor_minuto_nombre")
    private String valorMinutoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_minuto_precio")
    private int valorMinutoPrecio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_minuto_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valorMinutoFechaIngreso;
    @JoinColumn(name = "valor_minuto_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta valorMinutoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroMinutoIdValorMinuto")
    private List<RegistroMinuto> registroMinutoList;

    public ValorMinuto() {
    }

    public ValorMinuto(Integer valorMinutoId) {
        this.valorMinutoId = valorMinutoId;
    }

    public ValorMinuto(Integer valorMinutoId, String valorMinutoNombre, int valorMinutoPrecio, Date valorMinutoFechaIngreso) {
        this.valorMinutoId = valorMinutoId;
        this.valorMinutoNombre = valorMinutoNombre;
        this.valorMinutoPrecio = valorMinutoPrecio;
        this.valorMinutoFechaIngreso = valorMinutoFechaIngreso;
    }

    public Integer getValorMinutoId() {
        return valorMinutoId;
    }

    public void setValorMinutoId(Integer valorMinutoId) {
        this.valorMinutoId = valorMinutoId;
    }

    public String getValorMinutoNombre() {
        return valorMinutoNombre;
    }

    public void setValorMinutoNombre(String valorMinutoNombre) {
        this.valorMinutoNombre = valorMinutoNombre;
    }

    public int getValorMinutoPrecio() {
        return valorMinutoPrecio;
    }

    public void setValorMinutoPrecio(int valorMinutoPrecio) {
        this.valorMinutoPrecio = valorMinutoPrecio;
    }

    public Date getValorMinutoFechaIngreso() {
        return valorMinutoFechaIngreso;
    }

    public void setValorMinutoFechaIngreso(Date valorMinutoFechaIngreso) {
        this.valorMinutoFechaIngreso = valorMinutoFechaIngreso;
    }

    public Cuenta getValorMinutoIdCuenta() {
        return valorMinutoIdCuenta;
    }

    public void setValorMinutoIdCuenta(Cuenta valorMinutoIdCuenta) {
        this.valorMinutoIdCuenta = valorMinutoIdCuenta;
    }

    @XmlTransient
    public List<RegistroMinuto> getRegistroMinutoList() {
        return registroMinutoList;
    }

    public void setRegistroMinutoList(List<RegistroMinuto> registroMinutoList) {
        this.registroMinutoList = registroMinutoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorMinutoId != null ? valorMinutoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorMinuto)) {
            return false;
        }
        ValorMinuto other = (ValorMinuto) object;
        if ((this.valorMinutoId == null && other.valorMinutoId != null) || (this.valorMinutoId != null && !this.valorMinutoId.equals(other.valorMinutoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ValorMinuto[ valorMinutoId=" + valorMinutoId + " ]";
    }
    
}
