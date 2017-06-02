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
@Table(name = "valor_rollo_unidad", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorRolloUnidad.findAll", query = "SELECT v FROM ValorRolloUnidad v")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadId", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadId = :valorRolloUnidadId")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadCompra", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadCompra = :valorRolloUnidadCompra")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadVenta", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadVenta = :valorRolloUnidadVenta")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadFechaIngreso", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadFechaIngreso = :valorRolloUnidadFechaIngreso")})
public class ValorRolloUnidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_rollo_unidad_id")
    private Integer valorRolloUnidadId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_rollo_unidad_compra")
    private int valorRolloUnidadCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_rollo_unidad_venta")
    private int valorRolloUnidadVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_rollo_unidad_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valorRolloUnidadFechaIngreso;
    @JoinColumn(name = "valor_rollo_unidad_id_unidad", referencedColumnName = "unidad_negocio_id")
    @ManyToOne(optional = false)
    private UnidadNegocio valorRolloUnidadIdUnidad;

    public ValorRolloUnidad() {
    }

    public ValorRolloUnidad(Integer valorRolloUnidadId) {
        this.valorRolloUnidadId = valorRolloUnidadId;
    }

    public ValorRolloUnidad(Integer valorRolloUnidadId, int valorRolloUnidadCompra, int valorRolloUnidadVenta, Date valorRolloUnidadFechaIngreso) {
        this.valorRolloUnidadId = valorRolloUnidadId;
        this.valorRolloUnidadCompra = valorRolloUnidadCompra;
        this.valorRolloUnidadVenta = valorRolloUnidadVenta;
        this.valorRolloUnidadFechaIngreso = valorRolloUnidadFechaIngreso;
    }

    public Integer getValorRolloUnidadId() {
        return valorRolloUnidadId;
    }

    public void setValorRolloUnidadId(Integer valorRolloUnidadId) {
        this.valorRolloUnidadId = valorRolloUnidadId;
    }

    public int getValorRolloUnidadCompra() {
        return valorRolloUnidadCompra;
    }

    public void setValorRolloUnidadCompra(int valorRolloUnidadCompra) {
        this.valorRolloUnidadCompra = valorRolloUnidadCompra;
    }

    public int getValorRolloUnidadVenta() {
        return valorRolloUnidadVenta;
    }

    public void setValorRolloUnidadVenta(int valorRolloUnidadVenta) {
        this.valorRolloUnidadVenta = valorRolloUnidadVenta;
    }

    public Date getValorRolloUnidadFechaIngreso() {
        return valorRolloUnidadFechaIngreso;
    }

    public void setValorRolloUnidadFechaIngreso(Date valorRolloUnidadFechaIngreso) {
        this.valorRolloUnidadFechaIngreso = valorRolloUnidadFechaIngreso;
    }

    public UnidadNegocio getValorRolloUnidadIdUnidad() {
        return valorRolloUnidadIdUnidad;
    }

    public void setValorRolloUnidadIdUnidad(UnidadNegocio valorRolloUnidadIdUnidad) {
        this.valorRolloUnidadIdUnidad = valorRolloUnidadIdUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorRolloUnidadId != null ? valorRolloUnidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorRolloUnidad)) {
            return false;
        }
        ValorRolloUnidad other = (ValorRolloUnidad) object;
        if ((this.valorRolloUnidadId == null && other.valorRolloUnidadId != null) || (this.valorRolloUnidadId != null && !this.valorRolloUnidadId.equals(other.valorRolloUnidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ValorRolloUnidad[ valorRolloUnidadId=" + valorRolloUnidadId + " ]";
    }
    
}
