/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 *
 * @author ianfr
 */
@Entity
@Table(name = "guia", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guia.findAll", query = "SELECT g FROM Guia g")
    , @NamedQuery(name = "Guia.findByGuiaId", query = "SELECT g FROM Guia g WHERE g.guiaId = :guiaId")
    , @NamedQuery(name = "Guia.findByCajaRecaudacionProcesoBus", query = "SELECT g FROM Guia g WHERE g.guiaIdCajaTerminal = :guiaIdCajaTerminal AND g.guiaIdBus.busIdProcesoRecaudacion = :busIdProcesoRecaudacion")
    , @NamedQuery(name = "Guia.findByProcesoFechaRecaudacion", query = "SELECT g FROM Guia g WHERE g.guiaIdBus.busIdProcesoRecaudacion = :busIdProcesoRecaudacion AND g.guiaRecaudacion = :guiaRecaudacion ORDER BY g.guiaIdBus.busNumero ASC, g.guiaFecha ASC")
    , @NamedQuery(name = "Guia.findByBusFecha", query = "SELECT g FROM Guia g WHERE g.guiaIdBus = :guiaIdBus AND g.guiaFecha = :guiaFecha  ORDER BY g.guiaTurno DESC")
    , @NamedQuery(name = "Guia.findByCuentaFolio", query = "SELECT g FROM Guia g WHERE g.guiaIdCuenta = :guiaIdCuenta AND g.guiaFolio = :guiaFolio")
    , @NamedQuery(name = "Guia.findByGuiaFolio", query = "SELECT g FROM Guia g WHERE g.guiaFolio = :guiaFolio")
    , @NamedQuery(name = "Guia.findByGuiaFecha", query = "SELECT g FROM Guia g WHERE g.guiaFecha = :guiaFecha")
    , @NamedQuery(name = "Guia.findByGuiaRecaudacion", query = "SELECT g FROM Guia g WHERE g.guiaRecaudacion = :guiaRecaudacion")
    , @NamedQuery(name = "Guia.findByGuiaTotalIngresos", query = "SELECT g FROM Guia g WHERE g.guiaTotalIngresos = :guiaTotalIngresos")
    , @NamedQuery(name = "Guia.findByGuiaTotalEgresos", query = "SELECT g FROM Guia g WHERE g.guiaTotalEgresos = :guiaTotalEgresos")
    , @NamedQuery(name = "Guia.findByGuiaViajeEspecial", query = "SELECT g FROM Guia g WHERE g.guiaViajeEspecial = :guiaViajeEspecial")
    , @NamedQuery(name = "Guia.findByGuiaOtrosIngresos", query = "SELECT g FROM Guia g WHERE g.guiaOtrosIngresos = :guiaOtrosIngresos")
    , @NamedQuery(name = "Guia.findByGuiaSaldo", query = "SELECT g FROM Guia g WHERE g.guiaSaldo = :guiaSaldo")
    , @NamedQuery(name = "Guia.findByGuiaNumeroVueltas", query = "SELECT g FROM Guia g WHERE g.guiaNumeroVueltas = :guiaNumeroVueltas")
    , @NamedQuery(name = "Guia.findByGuiaTurno", query = "SELECT g FROM Guia g WHERE g.guiaTurno = :guiaTurno")
    , @NamedQuery(name = "Guia.findByGuiaObservacion", query = "SELECT g FROM Guia g WHERE g.guiaObservacion = :guiaObservacion")
    , @NamedQuery(name = "Guia.findByGuiaRecaudada", query = "SELECT g FROM Guia g WHERE g.guiaRecaudada = :guiaRecaudada")
    , @NamedQuery(name = "Guia.findByGuiaFechaIngreso", query = "SELECT g FROM Guia g WHERE g.guiaFechaIngreso = :guiaFechaIngreso")})
public class Guia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "guia_id")
    private Integer guiaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guia_folio")
    private int guiaFolio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guia_fecha")
    @Temporal(TemporalType.DATE)
    private Date guiaFecha;
    @Column(name = "guia_recaudacion")
    @Temporal(TemporalType.DATE)
    private Date guiaRecaudacion;
    @Column(name = "guia_total_ingresos")
    private Integer guiaTotalIngresos;
    @Column(name = "guia_total_egresos")
    private Integer guiaTotalEgresos;
    @Column(name = "guia_viaje_especial")
    private Integer guiaViajeEspecial;
    @Column(name = "guia_otros_ingresos")
    private Integer guiaOtrosIngresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guia_saldo")
    private int guiaSaldo;
    @Column(name = "guia_numero_vueltas")
    private Integer guiaNumeroVueltas;
    @Column(name = "guia_turno")
    private Integer guiaTurno;
    @Size(max = 100)
    @Column(name = "guia_observacion")
    private String guiaObservacion;
    @Column(name = "guia_recaudada")
    private Boolean guiaRecaudada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guia_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date guiaFechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serieBoletoGuiaIdGuia")
    private List<SerieBoletoGuia> serieBoletoGuiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaBoletoIdGuia")
    private List<VentaBoleto> ventaBoletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaCombustibleIdGuia")
    private List<VentaCombustible> ventaCombustibleList;
    @JoinColumn(name = "guia_id_bus", referencedColumnName = "bus_id")
    @ManyToOne(optional = false)
    private Bus guiaIdBus;
    @JoinColumn(name = "guia_id_caja_terminal", referencedColumnName = "caja_recaudacion_id")
    @ManyToOne(optional = false)
    private CajaRecaudacion guiaIdCajaTerminal;
    @JoinColumn(name = "guia_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta guiaIdCuenta;
    @JoinColumn(name = "guia_id_estado", referencedColumnName = "estado_guia_id")
    @ManyToOne(optional = false)
    private EstadoGuia guiaIdEstado;
    @JoinColumn(name = "guia_id_trabajador", referencedColumnName = "trabajador_id")
    @ManyToOne(optional = false)
    private Trabajador guiaIdTrabajador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoGuiaIdGuia")
    private List<EgresoGuia> egresoGuiaList;
    @Transient
    private LinkedHashMap link;

    public Guia() {
    }

    public Guia(Integer guiaId) {
        this.guiaId = guiaId;
    }

    public Guia(Integer guiaId, int guiaFolio, Date guiaFecha, int guiaSaldo, Date guiaFechaIngreso) {
        this.guiaId = guiaId;
        this.guiaFolio = guiaFolio;
        this.guiaFecha = guiaFecha;
        this.guiaSaldo = guiaSaldo;
        this.guiaFechaIngreso = guiaFechaIngreso;
    }

    public Integer getGuiaId() {
        return guiaId;
    }

    public void setGuiaId(Integer guiaId) {
        this.guiaId = guiaId;
    }

    public int getGuiaFolio() {
        return guiaFolio;
    }

    public void setGuiaFolio(int guiaFolio) {
        this.guiaFolio = guiaFolio;
    }

    public Date getGuiaFecha() {
        return guiaFecha;
    }

    public void setGuiaFecha(Date guiaFecha) {
        this.guiaFecha = guiaFecha;
    }

    public Date getGuiaRecaudacion() {
        return guiaRecaudacion;
    }

    public void setGuiaRecaudacion(Date guiaRecaudacion) {
        this.guiaRecaudacion = guiaRecaudacion;
    }

    public Integer getGuiaTotalIngresos() {
        return guiaTotalIngresos;
    }

    public void setGuiaTotalIngresos(Integer guiaTotalIngresos) {
        this.guiaTotalIngresos = guiaTotalIngresos;
    }

    public Integer getGuiaTotalEgresos() {
        return guiaTotalEgresos;
    }

    public void setGuiaTotalEgresos(Integer guiaTotalEgresos) {
        this.guiaTotalEgresos = guiaTotalEgresos;
    }

    public Integer getGuiaViajeEspecial() {
        return guiaViajeEspecial;
    }

    public void setGuiaViajeEspecial(Integer guiaViajeEspecial) {
        this.guiaViajeEspecial = guiaViajeEspecial;
    }

    public Integer getGuiaOtrosIngresos() {
        return guiaOtrosIngresos;
    }

    public void setGuiaOtrosIngresos(Integer guiaOtrosIngresos) {
        this.guiaOtrosIngresos = guiaOtrosIngresos;
    }

    public int getGuiaSaldo() {
        return guiaSaldo;
    }

    public void setGuiaSaldo(int guiaSaldo) {
        this.guiaSaldo = guiaSaldo;
    }

    public Integer getGuiaNumeroVueltas() {
        return guiaNumeroVueltas;
    }

    public void setGuiaNumeroVueltas(Integer guiaNumeroVueltas) {
        this.guiaNumeroVueltas = guiaNumeroVueltas;
    }

    public Integer getGuiaTurno() {
        return guiaTurno;
    }

    public void setGuiaTurno(Integer guiaTurno) {
        this.guiaTurno = guiaTurno;
    }

    public String getGuiaObservacion() {
        return guiaObservacion;
    }

    public void setGuiaObservacion(String guiaObservacion) {
        this.guiaObservacion = guiaObservacion;
    }

    public Boolean getGuiaRecaudada() {
        return guiaRecaudada;
    }

    public void setGuiaRecaudada(Boolean guiaRecaudada) {
        this.guiaRecaudada = guiaRecaudada;
    }

    public Date getGuiaFechaIngreso() {
        return guiaFechaIngreso;
    }

    public void setGuiaFechaIngreso(Date guiaFechaIngreso) {
        this.guiaFechaIngreso = guiaFechaIngreso;
    }

    @XmlTransient
    public List<SerieBoletoGuia> getSerieBoletoGuiaList() {
        return serieBoletoGuiaList;
    }

    public void setSerieBoletoGuiaList(List<SerieBoletoGuia> serieBoletoGuiaList) {
        this.serieBoletoGuiaList = serieBoletoGuiaList;
    }

    @XmlTransient
    public List<VentaBoleto> getVentaBoletoList() {
        return ventaBoletoList;
    }

    public void setVentaBoletoList(List<VentaBoleto> ventaBoletoList) {
        this.ventaBoletoList = ventaBoletoList;
    }

    @XmlTransient
    public List<VentaCombustible> getVentaCombustibleList() {
        return ventaCombustibleList;
    }

    public void setVentaCombustibleList(List<VentaCombustible> ventaCombustibleList) {
        this.ventaCombustibleList = ventaCombustibleList;
    }

    public Bus getGuiaIdBus() {
        return guiaIdBus;
    }

    public void setGuiaIdBus(Bus guiaIdBus) {
        this.guiaIdBus = guiaIdBus;
    }

    public CajaRecaudacion getGuiaIdCajaTerminal() {
        return guiaIdCajaTerminal;
    }

    public void setGuiaIdCajaTerminal(CajaRecaudacion guiaIdCajaTerminal) {
        this.guiaIdCajaTerminal = guiaIdCajaTerminal;
    }

    public EstadoGuia getGuiaIdEstado() {
        return guiaIdEstado;
    }

    public Cuenta getGuiaIdCuenta() {
        return guiaIdCuenta;
    }

    public void setGuiaIdCuenta(Cuenta guiaIdCuenta) {
        this.guiaIdCuenta = guiaIdCuenta;
    }

    public void setGuiaIdEstado(EstadoGuia guiaIdEstado) {
        this.guiaIdEstado = guiaIdEstado;
    }

    public Trabajador getGuiaIdTrabajador() {
        return guiaIdTrabajador;
    }

    public void setGuiaIdTrabajador(Trabajador guiaIdTrabajador) {
        this.guiaIdTrabajador = guiaIdTrabajador;
    }

    @XmlTransient
    public List<EgresoGuia> getEgresoGuiaList() {
        return egresoGuiaList;
    }

    public void setEgresoGuiaList(List<EgresoGuia> egresoGuiaList) {
        this.egresoGuiaList = egresoGuiaList;
    }

    /**
     * @return the link
     */
    public LinkedHashMap getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(LinkedHashMap link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guiaId != null ? guiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guia)) {
            return false;
        }
        Guia other = (Guia) object;
        if ((this.guiaId == null && other.guiaId != null) || (this.guiaId != null && !this.guiaId.equals(other.guiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Guia[ guiaId=" + guiaId + " ]";
    }

}
