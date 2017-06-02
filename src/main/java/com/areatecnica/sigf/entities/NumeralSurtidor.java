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
@Table(name = "numeral_surtidor", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NumeralSurtidor.findAll", query = "SELECT n FROM NumeralSurtidor n")
    , @NamedQuery(name = "NumeralSurtidor.findByNumeralSurtidorId", query = "SELECT n FROM NumeralSurtidor n WHERE n.numeralSurtidorId = :numeralSurtidorId")
    , @NamedQuery(name = "NumeralSurtidor.findByNumeralSurtidorFechaNumeral", query = "SELECT n FROM NumeralSurtidor n WHERE n.numeralSurtidorFechaNumeral = :numeralSurtidorFechaNumeral")
    , @NamedQuery(name = "NumeralSurtidor.findByNumeralSurtidorNumeralInicial", query = "SELECT n FROM NumeralSurtidor n WHERE n.numeralSurtidorNumeralInicial = :numeralSurtidorNumeralInicial")
    , @NamedQuery(name = "NumeralSurtidor.findByNumeralSurtidorNumeralFinal", query = "SELECT n FROM NumeralSurtidor n WHERE n.numeralSurtidorNumeralFinal = :numeralSurtidorNumeralFinal")
    , @NamedQuery(name = "NumeralSurtidor.findByNumeralSurtidorIndicadorMuestra", query = "SELECT n FROM NumeralSurtidor n WHERE n.numeralSurtidorIndicadorMuestra = :numeralSurtidorIndicadorMuestra")
    , @NamedQuery(name = "NumeralSurtidor.findByNumeralSurtidorFechaIngreso", query = "SELECT n FROM NumeralSurtidor n WHERE n.numeralSurtidorFechaIngreso = :numeralSurtidorFechaIngreso")})
public class NumeralSurtidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numeral_surtidor_id")
    private Integer numeralSurtidorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeral_surtidor_fecha_numeral")
    @Temporal(TemporalType.DATE)
    private Date numeralSurtidorFechaNumeral;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeral_surtidor_numeral_inicial")
    private int numeralSurtidorNumeralInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeral_surtidor_numeral_final")
    private int numeralSurtidorNumeralFinal;
    @Column(name = "numeral_surtidor_indicador_muestra")
    private Integer numeralSurtidorIndicadorMuestra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeral_surtidor_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date numeralSurtidorFechaIngreso;
    @JoinColumn(name = "numeral_surtidor_id_surtidor", referencedColumnName = "surtidor_id")
    @ManyToOne(optional = false)
    private Surtidor numeralSurtidorIdSurtidor;

    public NumeralSurtidor() {
    }

    public NumeralSurtidor(Integer numeralSurtidorId) {
        this.numeralSurtidorId = numeralSurtidorId;
    }

    public NumeralSurtidor(Integer numeralSurtidorId, Date numeralSurtidorFechaNumeral, int numeralSurtidorNumeralInicial, int numeralSurtidorNumeralFinal, Date numeralSurtidorFechaIngreso) {
        this.numeralSurtidorId = numeralSurtidorId;
        this.numeralSurtidorFechaNumeral = numeralSurtidorFechaNumeral;
        this.numeralSurtidorNumeralInicial = numeralSurtidorNumeralInicial;
        this.numeralSurtidorNumeralFinal = numeralSurtidorNumeralFinal;
        this.numeralSurtidorFechaIngreso = numeralSurtidorFechaIngreso;
    }

    public Integer getNumeralSurtidorId() {
        return numeralSurtidorId;
    }

    public void setNumeralSurtidorId(Integer numeralSurtidorId) {
        this.numeralSurtidorId = numeralSurtidorId;
    }

    public Date getNumeralSurtidorFechaNumeral() {
        return numeralSurtidorFechaNumeral;
    }

    public void setNumeralSurtidorFechaNumeral(Date numeralSurtidorFechaNumeral) {
        this.numeralSurtidorFechaNumeral = numeralSurtidorFechaNumeral;
    }

    public int getNumeralSurtidorNumeralInicial() {
        return numeralSurtidorNumeralInicial;
    }

    public void setNumeralSurtidorNumeralInicial(int numeralSurtidorNumeralInicial) {
        this.numeralSurtidorNumeralInicial = numeralSurtidorNumeralInicial;
    }

    public int getNumeralSurtidorNumeralFinal() {
        return numeralSurtidorNumeralFinal;
    }

    public void setNumeralSurtidorNumeralFinal(int numeralSurtidorNumeralFinal) {
        this.numeralSurtidorNumeralFinal = numeralSurtidorNumeralFinal;
    }

    public Integer getNumeralSurtidorIndicadorMuestra() {
        return numeralSurtidorIndicadorMuestra;
    }

    public void setNumeralSurtidorIndicadorMuestra(Integer numeralSurtidorIndicadorMuestra) {
        this.numeralSurtidorIndicadorMuestra = numeralSurtidorIndicadorMuestra;
    }

    public Date getNumeralSurtidorFechaIngreso() {
        return numeralSurtidorFechaIngreso;
    }

    public void setNumeralSurtidorFechaIngreso(Date numeralSurtidorFechaIngreso) {
        this.numeralSurtidorFechaIngreso = numeralSurtidorFechaIngreso;
    }

    public Surtidor getNumeralSurtidorIdSurtidor() {
        return numeralSurtidorIdSurtidor;
    }

    public void setNumeralSurtidorIdSurtidor(Surtidor numeralSurtidorIdSurtidor) {
        this.numeralSurtidorIdSurtidor = numeralSurtidorIdSurtidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeralSurtidorId != null ? numeralSurtidorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NumeralSurtidor)) {
            return false;
        }
        NumeralSurtidor other = (NumeralSurtidor) object;
        if ((this.numeralSurtidorId == null && other.numeralSurtidorId != null) || (this.numeralSurtidorId != null && !this.numeralSurtidorId.equals(other.numeralSurtidorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.NumeralSurtidor[ numeralSurtidorId=" + numeralSurtidorId + " ]";
    }
    
}
