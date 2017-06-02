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
@Table(name = "venta_combustible", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaCombustible.findAll", query = "SELECT v FROM VentaCombustible v")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleId", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleId = :ventaCombustibleId")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleFecha", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleFecha = :ventaCombustibleFecha")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleValor", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleValor = :ventaCombustibleValor")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleCantidad", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleCantidad = :ventaCombustibleCantidad")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleValorCombustible", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleValorCombustible = :ventaCombustibleValorCombustible")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleNumeroBoleta", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleNumeroBoleta = :ventaCombustibleNumeroBoleta")})
public class VentaCombustible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_combustible_id")
    private Integer ventaCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ventaCombustibleFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_valor")
    private int ventaCombustibleValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_cantidad")
    private float ventaCombustibleCantidad;
    @Column(name = "venta_combustible_valor_combustible")
    private Integer ventaCombustibleValorCombustible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_numero_boleta")
    private int ventaCombustibleNumeroBoleta;
    @JoinColumn(name = "venta_combustible_id_guia", referencedColumnName = "guia_id")
    @ManyToOne(optional = false)
    private Guia ventaCombustibleIdGuia;
    @JoinColumn(name = "venta_combustible_id_surtidor", referencedColumnName = "surtidor_id")
    @ManyToOne(optional = false)
    private Surtidor ventaCombustibleIdSurtidor;

    public VentaCombustible() {
    }

    public VentaCombustible(Integer ventaCombustibleId) {
        this.ventaCombustibleId = ventaCombustibleId;
    }

    public VentaCombustible(Integer ventaCombustibleId, Date ventaCombustibleFecha, int ventaCombustibleValor, float ventaCombustibleCantidad, int ventaCombustibleNumeroBoleta) {
        this.ventaCombustibleId = ventaCombustibleId;
        this.ventaCombustibleFecha = ventaCombustibleFecha;
        this.ventaCombustibleValor = ventaCombustibleValor;
        this.ventaCombustibleCantidad = ventaCombustibleCantidad;
        this.ventaCombustibleNumeroBoleta = ventaCombustibleNumeroBoleta;
    }

    public Integer getVentaCombustibleId() {
        return ventaCombustibleId;
    }

    public void setVentaCombustibleId(Integer ventaCombustibleId) {
        this.ventaCombustibleId = ventaCombustibleId;
    }

    public Date getVentaCombustibleFecha() {
        return ventaCombustibleFecha;
    }

    public void setVentaCombustibleFecha(Date ventaCombustibleFecha) {
        this.ventaCombustibleFecha = ventaCombustibleFecha;
    }

    public int getVentaCombustibleValor() {
        return ventaCombustibleValor;
    }

    public void setVentaCombustibleValor(int ventaCombustibleValor) {
        this.ventaCombustibleValor = ventaCombustibleValor;
    }

    public float getVentaCombustibleCantidad() {
        return ventaCombustibleCantidad;
    }

    public void setVentaCombustibleCantidad(float ventaCombustibleCantidad) {
        this.ventaCombustibleCantidad = ventaCombustibleCantidad;
    }

    public Integer getVentaCombustibleValorCombustible() {
        return ventaCombustibleValorCombustible;
    }

    public void setVentaCombustibleValorCombustible(Integer ventaCombustibleValorCombustible) {
        this.ventaCombustibleValorCombustible = ventaCombustibleValorCombustible;
    }

    public int getVentaCombustibleNumeroBoleta() {
        return ventaCombustibleNumeroBoleta;
    }

    public void setVentaCombustibleNumeroBoleta(int ventaCombustibleNumeroBoleta) {
        this.ventaCombustibleNumeroBoleta = ventaCombustibleNumeroBoleta;
    }

    public Guia getVentaCombustibleIdGuia() {
        return ventaCombustibleIdGuia;
    }

    public void setVentaCombustibleIdGuia(Guia ventaCombustibleIdGuia) {
        this.ventaCombustibleIdGuia = ventaCombustibleIdGuia;
    }

    public Surtidor getVentaCombustibleIdSurtidor() {
        return ventaCombustibleIdSurtidor;
    }

    public void setVentaCombustibleIdSurtidor(Surtidor ventaCombustibleIdSurtidor) {
        this.ventaCombustibleIdSurtidor = ventaCombustibleIdSurtidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaCombustibleId != null ? ventaCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaCombustible)) {
            return false;
        }
        VentaCombustible other = (VentaCombustible) object;
        if ((this.ventaCombustibleId == null && other.ventaCombustibleId != null) || (this.ventaCombustibleId != null && !this.ventaCombustibleId.equals(other.ventaCombustibleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.VentaCombustible[ ventaCombustibleId=" + ventaCombustibleId + " ]";
    }
    
}
