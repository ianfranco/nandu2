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
@Table(name = "surtidor", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surtidor.findAll", query = "SELECT s FROM Surtidor s")
    , @NamedQuery(name = "Surtidor.findBySurtidorId", query = "SELECT s FROM Surtidor s WHERE s.surtidorId = :surtidorId")
    , @NamedQuery(name = "Surtidor.findBySurtidorIdentificador", query = "SELECT s FROM Surtidor s WHERE s.surtidorIdentificador = :surtidorIdentificador")
    , @NamedQuery(name = "Surtidor.findBySurtidorObservaciones", query = "SELECT s FROM Surtidor s WHERE s.surtidorObservaciones = :surtidorObservaciones")
    , @NamedQuery(name = "Surtidor.findBySurtidorFechaIngreso", query = "SELECT s FROM Surtidor s WHERE s.surtidorFechaIngreso = :surtidorFechaIngreso")})
public class Surtidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "surtidor_id")
    private Integer surtidorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "surtidor_identificador")
    private String surtidorIdentificador;
    @Size(max = 200)
    @Column(name = "surtidor_observaciones")
    private String surtidorObservaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "surtidor_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date surtidorFechaIngreso;
    @JoinColumn(name = "surtidor_id_terminal", referencedColumnName = "terminal_id")
    @ManyToOne(optional = false)
    private Terminal surtidorIdTerminal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaCombustibleIdSurtidor")
    private List<VentaCombustible> ventaCombustibleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeralSurtidorIdSurtidor")
    private List<NumeralSurtidor> numeralSurtidorList;

    public Surtidor() {
    }

    public Surtidor(Integer surtidorId) {
        this.surtidorId = surtidorId;
    }

    public Surtidor(Integer surtidorId, String surtidorIdentificador, Date surtidorFechaIngreso) {
        this.surtidorId = surtidorId;
        this.surtidorIdentificador = surtidorIdentificador;
        this.surtidorFechaIngreso = surtidorFechaIngreso;
    }

    public Integer getSurtidorId() {
        return surtidorId;
    }

    public void setSurtidorId(Integer surtidorId) {
        this.surtidorId = surtidorId;
    }

    public String getSurtidorIdentificador() {
        return surtidorIdentificador;
    }

    public void setSurtidorIdentificador(String surtidorIdentificador) {
        this.surtidorIdentificador = surtidorIdentificador;
    }

    public String getSurtidorObservaciones() {
        return surtidorObservaciones;
    }

    public void setSurtidorObservaciones(String surtidorObservaciones) {
        this.surtidorObservaciones = surtidorObservaciones;
    }

    public Date getSurtidorFechaIngreso() {
        return surtidorFechaIngreso;
    }

    public void setSurtidorFechaIngreso(Date surtidorFechaIngreso) {
        this.surtidorFechaIngreso = surtidorFechaIngreso;
    }

    public Terminal getSurtidorIdTerminal() {
        return surtidorIdTerminal;
    }

    public void setSurtidorIdTerminal(Terminal surtidorIdTerminal) {
        this.surtidorIdTerminal = surtidorIdTerminal;
    }

    @XmlTransient
    public List<VentaCombustible> getVentaCombustibleList() {
        return ventaCombustibleList;
    }

    public void setVentaCombustibleList(List<VentaCombustible> ventaCombustibleList) {
        this.ventaCombustibleList = ventaCombustibleList;
    }

    @XmlTransient
    public List<NumeralSurtidor> getNumeralSurtidorList() {
        return numeralSurtidorList;
    }

    public void setNumeralSurtidorList(List<NumeralSurtidor> numeralSurtidorList) {
        this.numeralSurtidorList = numeralSurtidorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surtidorId != null ? surtidorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Surtidor)) {
            return false;
        }
        Surtidor other = (Surtidor) object;
        if ((this.surtidorId == null && other.surtidorId != null) || (this.surtidorId != null && !this.surtidorId.equals(other.surtidorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Surtidor[ surtidorId=" + surtidorId + " ]";
    }
    
}
