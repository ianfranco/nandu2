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
@Table(name = "serie_boleto_guia", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SerieBoletoGuia.findAll", query = "SELECT s FROM SerieBoletoGuia s")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaId", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaId = :serieBoletoGuiaId")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaNumeroVuelta", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaNumeroVuelta = :serieBoletoGuiaNumeroVuelta")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaValor", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaValor = :serieBoletoGuiaValor")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaInicio", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaInicio = :serieBoletoGuiaInicio")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaTermino", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaTermino = :serieBoletoGuiaTermino")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaCantidad", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaCantidad = :serieBoletoGuiaCantidad")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaTotal", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaTotal = :serieBoletoGuiaTotal")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaEsNuevo", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaEsNuevo = :serieBoletoGuiaEsNuevo")
    , @NamedQuery(name = "SerieBoletoGuia.findBySerieBoletoGuiaFechaIngreso", query = "SELECT s FROM SerieBoletoGuia s WHERE s.serieBoletoGuiaFechaIngreso = :serieBoletoGuiaFechaIngreso")})
public class SerieBoletoGuia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "serie_boleto_guia_id")
    private Integer serieBoletoGuiaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serie_boleto_guia_numero_vuelta")
    private int serieBoletoGuiaNumeroVuelta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serie_boleto_guia_valor")
    private int serieBoletoGuiaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serie_boleto_guia_inicio")
    private int serieBoletoGuiaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serie_boleto_guia_termino")
    private int serieBoletoGuiaTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serie_boleto_guia_cantidad")
    private int serieBoletoGuiaCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serie_boleto_guia_total")
    private int serieBoletoGuiaTotal;
    @Column(name = "serie_boleto_guia_es_nuevo")
    private Boolean serieBoletoGuiaEsNuevo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serie_boleto_guia_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serieBoletoGuiaFechaIngreso;
    @JoinColumn(name = "serie_boleto_guia_id_boleto", referencedColumnName = "boleto_id")
    @ManyToOne(optional = false)
    private Boleto serieBoletoGuiaIdBoleto;
    @JoinColumn(name = "serie_boleto_guia_id_guia", referencedColumnName = "guia_id")
    @ManyToOne(optional = false)
    private Guia serieBoletoGuiaIdGuia;

    public SerieBoletoGuia() {
    }

    public SerieBoletoGuia(Integer serieBoletoGuiaId) {
        this.serieBoletoGuiaId = serieBoletoGuiaId;
    }

    public SerieBoletoGuia(Integer serieBoletoGuiaId, int serieBoletoGuiaNumeroVuelta, int serieBoletoGuiaValor, int serieBoletoGuiaInicio, int serieBoletoGuiaTermino, int serieBoletoGuiaCantidad, int serieBoletoGuiaTotal, Date serieBoletoGuiaFechaIngreso) {
        this.serieBoletoGuiaId = serieBoletoGuiaId;
        this.serieBoletoGuiaNumeroVuelta = serieBoletoGuiaNumeroVuelta;
        this.serieBoletoGuiaValor = serieBoletoGuiaValor;
        this.serieBoletoGuiaInicio = serieBoletoGuiaInicio;
        this.serieBoletoGuiaTermino = serieBoletoGuiaTermino;
        this.serieBoletoGuiaCantidad = serieBoletoGuiaCantidad;
        this.serieBoletoGuiaTotal = serieBoletoGuiaTotal;
        this.serieBoletoGuiaFechaIngreso = serieBoletoGuiaFechaIngreso;
    }

    public Integer getSerieBoletoGuiaId() {
        return serieBoletoGuiaId;
    }

    public void setSerieBoletoGuiaId(Integer serieBoletoGuiaId) {
        this.serieBoletoGuiaId = serieBoletoGuiaId;
    }

    public int getSerieBoletoGuiaNumeroVuelta() {
        return serieBoletoGuiaNumeroVuelta;
    }

    public void setSerieBoletoGuiaNumeroVuelta(int serieBoletoGuiaNumeroVuelta) {
        this.serieBoletoGuiaNumeroVuelta = serieBoletoGuiaNumeroVuelta;
    }

    public int getSerieBoletoGuiaValor() {
        return serieBoletoGuiaValor;
    }

    public void setSerieBoletoGuiaValor(int serieBoletoGuiaValor) {
        this.serieBoletoGuiaValor = serieBoletoGuiaValor;
    }

    public int getSerieBoletoGuiaInicio() {
        return serieBoletoGuiaInicio;
    }

    public void setSerieBoletoGuiaInicio(int serieBoletoGuiaInicio) {
        this.serieBoletoGuiaInicio = serieBoletoGuiaInicio;
    }

    public int getSerieBoletoGuiaTermino() {
        return serieBoletoGuiaTermino;
    }

    public void setSerieBoletoGuiaTermino(int serieBoletoGuiaTermino) {
        this.serieBoletoGuiaTermino = serieBoletoGuiaTermino;
    }

    public int getSerieBoletoGuiaCantidad() {
        return serieBoletoGuiaCantidad;
    }

    public void setSerieBoletoGuiaCantidad(int serieBoletoGuiaCantidad) {
        this.serieBoletoGuiaCantidad = serieBoletoGuiaCantidad;
    }

    public int getSerieBoletoGuiaTotal() {
        return serieBoletoGuiaTotal;
    }

    public void setSerieBoletoGuiaTotal(int serieBoletoGuiaTotal) {
        this.serieBoletoGuiaTotal = serieBoletoGuiaTotal;
    }

    public Boolean getSerieBoletoGuiaEsNuevo() {
        return serieBoletoGuiaEsNuevo;
    }

    public void setSerieBoletoGuiaEsNuevo(Boolean serieBoletoGuiaEsNuevo) {
        this.serieBoletoGuiaEsNuevo = serieBoletoGuiaEsNuevo;
    }

    public Date getSerieBoletoGuiaFechaIngreso() {
        return serieBoletoGuiaFechaIngreso;
    }

    public void setSerieBoletoGuiaFechaIngreso(Date serieBoletoGuiaFechaIngreso) {
        this.serieBoletoGuiaFechaIngreso = serieBoletoGuiaFechaIngreso;
    }

    public Boleto getSerieBoletoGuiaIdBoleto() {
        return serieBoletoGuiaIdBoleto;
    }

    public void setSerieBoletoGuiaIdBoleto(Boleto serieBoletoGuiaIdBoleto) {
        this.serieBoletoGuiaIdBoleto = serieBoletoGuiaIdBoleto;
    }

    public Guia getSerieBoletoGuiaIdGuia() {
        return serieBoletoGuiaIdGuia;
    }

    public void setSerieBoletoGuiaIdGuia(Guia serieBoletoGuiaIdGuia) {
        this.serieBoletoGuiaIdGuia = serieBoletoGuiaIdGuia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serieBoletoGuiaId != null ? serieBoletoGuiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SerieBoletoGuia)) {
            return false;
        }
        SerieBoletoGuia other = (SerieBoletoGuia) object;
        if ((this.serieBoletoGuiaId == null && other.serieBoletoGuiaId != null) || (this.serieBoletoGuiaId != null && !this.serieBoletoGuiaId.equals(other.serieBoletoGuiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.SerieBoletoGuia[ serieBoletoGuiaId=" + serieBoletoGuiaId + " ]";
    }
    
}
