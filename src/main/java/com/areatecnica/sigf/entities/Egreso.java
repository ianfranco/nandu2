/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "egreso", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e"), 
    @NamedQuery(name = "Egreso.findAllByCuenta", query = "SELECT e FROM Egreso e WHERE e.egresoIdCuenta = :idCuenta")
    , @NamedQuery(name = "Egreso.findByEgresoId", query = "SELECT e FROM Egreso e WHERE e.egresoId = :egresoId")
    , @NamedQuery(name = "Egreso.findByEgresoNombreEgreso", query = "SELECT e FROM Egreso e WHERE e.egresoNombreEgreso = :egresoNombreEgreso")
    , @NamedQuery(name = "Egreso.findByEgresoObligatorio", query = "SELECT e FROM Egreso e WHERE e.egresoObligatorio = :egresoObligatorio")
    , @NamedQuery(name = "Egreso.findByEgresoActivo", query = "SELECT e FROM Egreso e WHERE e.egresoActivo = :egresoActivo")
    , @NamedQuery(name = "Egreso.findByCuenta", query = "SELECT e FROM Egreso e WHERE e.egresoIdCuenta = :egresoIdCuenta")
    , @NamedQuery(name = "Egreso.findByEgresoFechaIngreso", query = "SELECT e FROM Egreso e WHERE e.egresoFechaIngreso = :egresoFechaIngreso")})
public class Egreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_id")
    private Integer egresoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "egreso_nombre_egreso")
    private String egresoNombreEgreso;
    @Column(name = "egreso_valor_defecto")
    private Integer egresoValorDefecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "egreso_porcentaje")
    private BigDecimal egresoPorcentaje;
    @Column(name = "egreso_obligatorio")
    private Boolean egresoObligatorio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_activo")
    private boolean egresoActivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date egresoFechaIngreso;
    @JoinColumn(name = "egreso_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta egresoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoCajaRecaudacionIdEgreso")
    private List<EgresoCajaRecaudacion> egresoCajaRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoRecaudacionIdEgreso")
    private List<EgresoRecaudacion> egresoRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoBusIdEgreso")
    private List<EgresoBus> egresoBusList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoGuiaIdEgreso")
    private List<EgresoGuia> egresoGuiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoFlotaIdEgreso")
    private List<EgresoFlota> egresoFlotaList;

    public Egreso() {
    }

    public Egreso(Integer egresoId) {
        this.egresoId = egresoId;
    }

    public Egreso(Integer egresoId, String egresoNombreEgreso, boolean egresoActivo, Date egresoFechaIngreso) {
        this.egresoId = egresoId;
        this.egresoNombreEgreso = egresoNombreEgreso;
        this.egresoActivo = egresoActivo;
        this.egresoFechaIngreso = egresoFechaIngreso;
    }

    public Integer getEgresoId() {
        return egresoId;
    }

    public void setEgresoId(Integer egresoId) {
        this.egresoId = egresoId;
    }

    public String getEgresoNombreEgreso() {
        return egresoNombreEgreso;
    }

    public void setEgresoNombreEgreso(String egresoNombreEgreso) {
        this.egresoNombreEgreso = egresoNombreEgreso;
    }

    /**
     * @return the egresoValorDefecto
     */
    public Integer getEgresoValorDefecto() {
        return egresoValorDefecto;
    }

    /**
     * @param egresoValorDefecto the egresoValorDefecto to set
     */
    public void setEgresoValorDefecto(Integer egresoValorDefecto) {
        this.egresoValorDefecto = egresoValorDefecto;
    }

    /**
     * @return the egresoPorcentaje
     */
    public BigDecimal getEgresoPorcentaje() {
        return egresoPorcentaje;
    }

    /**
     * @param egresoPorcentaje the egresoPorcentaje to set
     */
    public void setEgresoPorcentaje(BigDecimal egresoPorcentaje) {
        this.egresoPorcentaje = egresoPorcentaje;
    }

    public Boolean getEgresoObligatorio() {
        return egresoObligatorio;
    }

    public void setEgresoObligatorio(Boolean egresoObligatorio) {
        this.egresoObligatorio = egresoObligatorio;
    }

    public boolean getEgresoActivo() {
        return egresoActivo;
    }

    public void setEgresoActivo(boolean egresoActivo) {
        this.egresoActivo = egresoActivo;
    }

    public Date getEgresoFechaIngreso() {
        return egresoFechaIngreso;
    }

    public void setEgresoFechaIngreso(Date egresoFechaIngreso) {
        this.egresoFechaIngreso = egresoFechaIngreso;
    }

    public Cuenta getEgresoIdCuenta() {
        return egresoIdCuenta;
    }

    public void setEgresoIdCuenta(Cuenta egresoIdCuenta) {
        this.egresoIdCuenta = egresoIdCuenta;
    }

    @XmlTransient
    public List<EgresoCajaRecaudacion> getEgresoCajaRecaudacionList() {
        return egresoCajaRecaudacionList;
    }

    public void setEgresoCajaRecaudacionList(List<EgresoCajaRecaudacion> egresoCajaRecaudacionList) {
        this.egresoCajaRecaudacionList = egresoCajaRecaudacionList;
    }

    @XmlTransient
    public List<EgresoRecaudacion> getEgresoRecaudacionList() {
        return egresoRecaudacionList;
    }

    public void setEgresoRecaudacionList(List<EgresoRecaudacion> egresoRecaudacionList) {
        this.egresoRecaudacionList = egresoRecaudacionList;
    }

    @XmlTransient
    public List<EgresoBus> getEgresoBusList() {
        return egresoBusList;
    }

    public void setEgresoBusList(List<EgresoBus> egresoBusList) {
        this.egresoBusList = egresoBusList;
    }

    @XmlTransient
    public List<EgresoGuia> getEgresoGuiaList() {
        return egresoGuiaList;
    }

    public void setEgresoGuiaList(List<EgresoGuia> egresoGuiaList) {
        this.egresoGuiaList = egresoGuiaList;
    }

    @XmlTransient
    public List<EgresoFlota> getEgresoFlotaList() {
        return egresoFlotaList;
    }

    public void setEgresoFlotaList(List<EgresoFlota> egresoFlotaList) {
        this.egresoFlotaList = egresoFlotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoId != null ? egresoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egreso)) {
            return false;
        }
        Egreso other = (Egreso) object;
        if ((this.egresoId == null && other.egresoId != null) || (this.egresoId != null && !this.egresoId.equals(other.egresoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Egreso[ egresoId=" + egresoId + " ]";
    }

}
