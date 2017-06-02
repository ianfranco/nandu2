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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "reconocimiento_deuda", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReconocimientoDeuda.findAll", query = "SELECT r FROM ReconocimientoDeuda r")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaId", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaId = :reconocimientoDeudaId")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaMontoReconocimiento", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaMontoReconocimiento = :reconocimientoDeudaMontoReconocimiento")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaDescripcionReconocimiento", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaDescripcionReconocimiento = :reconocimientoDeudaDescripcionReconocimiento")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaFechaIngreso", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaFechaIngreso = :reconocimientoDeudaFechaIngreso")
    , @NamedQuery(name = "ReconocimientoDeuda.findByReconocimientoDeudaFechaActualizacion", query = "SELECT r FROM ReconocimientoDeuda r WHERE r.reconocimientoDeudaFechaActualizacion = :reconocimientoDeudaFechaActualizacion")})
public class ReconocimientoDeuda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reconocimiento_deuda_id")
    private Integer reconocimientoDeudaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reconocimiento_deuda_monto_reconocimiento")
    private int reconocimientoDeudaMontoReconocimiento;
    @Size(max = 255)
    @Column(name = "reconocimiento_deuda_descripcion_reconocimiento")
    private String reconocimientoDeudaDescripcionReconocimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reconocimiento_deuda_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reconocimientoDeudaFechaIngreso;
    @Column(name = "reconocimiento_deuda_fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reconocimientoDeudaFechaActualizacion;
    @JoinColumn(name = "reconocimiento_deuda_id_empresa", referencedColumnName = "empresa_id")
    @ManyToOne(optional = false)
    private Empresa reconocimientoDeudaIdEmpresa;
    @JoinColumn(name = "reconocimiento_deuda_id_trabajador", referencedColumnName = "trabajador_id")
    @ManyToOne(optional = false)
    private Trabajador reconocimientoDeudaIdTrabajador;

    public ReconocimientoDeuda() {
    }

    public ReconocimientoDeuda(Integer reconocimientoDeudaId) {
        this.reconocimientoDeudaId = reconocimientoDeudaId;
    }

    public ReconocimientoDeuda(Integer reconocimientoDeudaId, int reconocimientoDeudaMontoReconocimiento, Date reconocimientoDeudaFechaIngreso) {
        this.reconocimientoDeudaId = reconocimientoDeudaId;
        this.reconocimientoDeudaMontoReconocimiento = reconocimientoDeudaMontoReconocimiento;
        this.reconocimientoDeudaFechaIngreso = reconocimientoDeudaFechaIngreso;
    }

    public Integer getReconocimientoDeudaId() {
        return reconocimientoDeudaId;
    }

    public void setReconocimientoDeudaId(Integer reconocimientoDeudaId) {
        this.reconocimientoDeudaId = reconocimientoDeudaId;
    }

    public int getReconocimientoDeudaMontoReconocimiento() {
        return reconocimientoDeudaMontoReconocimiento;
    }

    public void setReconocimientoDeudaMontoReconocimiento(int reconocimientoDeudaMontoReconocimiento) {
        this.reconocimientoDeudaMontoReconocimiento = reconocimientoDeudaMontoReconocimiento;
    }

    public String getReconocimientoDeudaDescripcionReconocimiento() {
        return reconocimientoDeudaDescripcionReconocimiento;
    }

    public void setReconocimientoDeudaDescripcionReconocimiento(String reconocimientoDeudaDescripcionReconocimiento) {
        this.reconocimientoDeudaDescripcionReconocimiento = reconocimientoDeudaDescripcionReconocimiento;
    }

    public Date getReconocimientoDeudaFechaIngreso() {
        return reconocimientoDeudaFechaIngreso;
    }

    public void setReconocimientoDeudaFechaIngreso(Date reconocimientoDeudaFechaIngreso) {
        this.reconocimientoDeudaFechaIngreso = reconocimientoDeudaFechaIngreso;
    }

    public Date getReconocimientoDeudaFechaActualizacion() {
        return reconocimientoDeudaFechaActualizacion;
    }

    public void setReconocimientoDeudaFechaActualizacion(Date reconocimientoDeudaFechaActualizacion) {
        this.reconocimientoDeudaFechaActualizacion = reconocimientoDeudaFechaActualizacion;
    }

    public Empresa getReconocimientoDeudaIdEmpresa() {
        return reconocimientoDeudaIdEmpresa;
    }

    public void setReconocimientoDeudaIdEmpresa(Empresa reconocimientoDeudaIdEmpresa) {
        this.reconocimientoDeudaIdEmpresa = reconocimientoDeudaIdEmpresa;
    }

    public Trabajador getReconocimientoDeudaIdTrabajador() {
        return reconocimientoDeudaIdTrabajador;
    }

    public void setReconocimientoDeudaIdTrabajador(Trabajador reconocimientoDeudaIdTrabajador) {
        this.reconocimientoDeudaIdTrabajador = reconocimientoDeudaIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reconocimientoDeudaId != null ? reconocimientoDeudaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReconocimientoDeuda)) {
            return false;
        }
        ReconocimientoDeuda other = (ReconocimientoDeuda) object;
        if ((this.reconocimientoDeudaId == null && other.reconocimientoDeudaId != null) || (this.reconocimientoDeudaId != null && !this.reconocimientoDeudaId.equals(other.reconocimientoDeudaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ReconocimientoDeuda[ reconocimientoDeudaId=" + reconocimientoDeudaId + " ]";
    }
    
}
