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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "venta_boleto", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaBoleto.findAll", query = "SELECT v FROM VentaBoleto v"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoId", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoId = :ventaBoletoId"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdGuia", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdGuia = :ventaBoletoIdGuia"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdGuiaBus", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdGuia.guiaIdBus = :ventaBoletoIdBus"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdGuiaBusEstado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdGuia.guiaIdBus = :ventaBoletoIdBus AND v.ventaBoletoUtilizado = 0"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdGuiaBusBoletoEstado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdGuia.guiaIdBus = :ventaBoletoIdBus AND v.ventaBoletoIdInventarioCaja.inventarioCajaIdInventarioInterno.inventarioInternoIdBoleto = :inventarioInternoIdBoleto AND v.ventaBoletoUtilizado = false ORDER BY v.ventaBoletoFechaIngreso DESC"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoDefaultIdGuia", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdGuia.guiaId = 1"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdCajaDate", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdInventarioCaja.inventarioCajaIdCaja = :inventarioCajaIdCaja AND v.ventaBoletoFecha  = :ventaBoletoFecha ORDER BY v.ventaBoletoIdGuia.guiaIdBus.busNumero ASC"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoNumeroBoleta", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoNumeroBoleta = :ventaBoletoNumeroBoleta"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoValorVentaBoleto", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoValorVentaBoleto = :ventaBoletoValorVentaBoleto"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoRecaudado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoRecaudado = :ventaBoletoRecaudado"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoUtilizado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoUtilizado = :ventaBoletoUtilizado"),
    @NamedQuery(name = "VentaBoleto.findByVentaBoletoFechaIngreso", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoFechaIngreso = :ventaBoletoFechaIngreso")})
public class VentaBoleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_boleto_id")
    private Integer ventaBoletoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_numero_boleta")
    private int ventaBoletoNumeroBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_fecha")
    @Temporal(TemporalType.DATE)
    private Date ventaBoletoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_valor_venta_boleto")
    private int ventaBoletoValorVentaBoleto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_recaudado")
    private boolean ventaBoletoRecaudado;
    @Column(name = "venta_boleto_utilizado")
    private Boolean ventaBoletoUtilizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ventaBoletoFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serieBoletoGuiaIdVentaBoleto")
    private List<SerieBoletoGuia> serieBoletoGuiaList;
    @JoinColumn(name = "venta_boleto_id_guia", referencedColumnName = "guia_id")
    @ManyToOne(optional = false)
    private Guia ventaBoletoIdGuia;
    @JoinColumn(name = "venta_boleto_id_inventario_caja", referencedColumnName = "inventario_caja_id")
    @ManyToOne(optional = false)
    private InventarioCaja ventaBoletoIdInventarioCaja;

    public VentaBoleto() {
    }

    public VentaBoleto(Integer ventaBoletoId) {
        this.ventaBoletoId = ventaBoletoId;
    }

    public VentaBoleto(Integer ventaBoletoId, Date ventaBoletoFecha, int ventaBoletoNumeroBoleta, int ventaBoletoValorVentaBoleto, boolean ventaBoletoRecaudado, Date ventaBoletoFechaIngreso) {
        this.ventaBoletoId = ventaBoletoId;
        this.ventaBoletoFecha = ventaBoletoFecha;
        this.ventaBoletoNumeroBoleta = ventaBoletoNumeroBoleta;
        this.ventaBoletoValorVentaBoleto = ventaBoletoValorVentaBoleto;
        this.ventaBoletoRecaudado = ventaBoletoRecaudado;
        this.ventaBoletoFechaIngreso = ventaBoletoFechaIngreso;
    }

    public Integer getVentaBoletoId() {
        return ventaBoletoId;
    }

    public void setVentaBoletoId(Integer ventaBoletoId) {
        this.ventaBoletoId = ventaBoletoId;
    }

    public Date getVentaBoletoFecha() {
        return ventaBoletoFecha;
    }

    public int getVentaBoletoNumeroBoleta() {
        return ventaBoletoNumeroBoleta;
    }

    public void setVentaBoletoFecha(Date ventaBoletoFecha) {
        this.ventaBoletoFecha = ventaBoletoFecha;
    }

    public void setVentaBoletoNumeroBoleta(int ventaBoletoNumeroBoleta) {
        this.ventaBoletoNumeroBoleta = ventaBoletoNumeroBoleta;
    }

    public int getVentaBoletoValorVentaBoleto() {
        return ventaBoletoValorVentaBoleto;
    }

    public void setVentaBoletoValorVentaBoleto(int ventaBoletoValorVentaBoleto) {
        this.ventaBoletoValorVentaBoleto = ventaBoletoValorVentaBoleto;
    }

    public boolean getVentaBoletoRecaudado() {
        return ventaBoletoRecaudado;
    }

    public void setVentaBoletoRecaudado(boolean ventaBoletoRecaudado) {
        this.ventaBoletoRecaudado = ventaBoletoRecaudado;
    }

    public Boolean getVentaBoletoUtilizado() {
        return ventaBoletoUtilizado;
    }

    public void setVentaBoletoUtilizado(Boolean ventaBoletoUtilizado) {
        this.ventaBoletoUtilizado = ventaBoletoUtilizado;
    }

    public Date getVentaBoletoFechaIngreso() {
        return ventaBoletoFechaIngreso;
    }

    public void setVentaBoletoFechaIngreso(Date ventaBoletoFechaIngreso) {
        this.ventaBoletoFechaIngreso = ventaBoletoFechaIngreso;
    }

    @XmlTransient
    public List<SerieBoletoGuia> getSerieBoletoGuiaList() {
        return serieBoletoGuiaList;
    }

    public void setSerieBoletoGuiaList(List<SerieBoletoGuia> serieBoletoGuiaList) {
        this.serieBoletoGuiaList = serieBoletoGuiaList;
    }

    public Guia getVentaBoletoIdGuia() {
        return ventaBoletoIdGuia;
    }

    public void setVentaBoletoIdGuia(Guia ventaBoletoIdGuia) {
        this.ventaBoletoIdGuia = ventaBoletoIdGuia;
    }

    public InventarioCaja getVentaBoletoIdInventarioCaja() {
        return ventaBoletoIdInventarioCaja;
    }

    public void setVentaBoletoIdInventarioCaja(InventarioCaja ventaBoletoIdInventarioCaja) {
        this.ventaBoletoIdInventarioCaja = ventaBoletoIdInventarioCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaBoletoId != null ? ventaBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaBoleto)) {
            return false;
        }
        VentaBoleto other = (VentaBoleto) object;
        if ((this.ventaBoletoId == null && other.ventaBoletoId != null) || (this.ventaBoletoId != null && !this.ventaBoletoId.equals(other.ventaBoletoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.entities.VentaBoleto[ ventaBoletoId=" + ventaBoletoId + " ]";
    }

}
