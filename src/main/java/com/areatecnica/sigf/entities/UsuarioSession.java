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
@Table(name = "usuario_session", catalog = "sigf_v2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioSession.findAll", query = "SELECT u FROM UsuarioSession u")
    , @NamedQuery(name = "UsuarioSession.findByUsuarioSessionId", query = "SELECT u FROM UsuarioSession u WHERE u.usuarioSessionId = :usuarioSessionId")
    , @NamedQuery(name = "UsuarioSession.findByUsuarioSessionFechaIngreso", query = "SELECT u FROM UsuarioSession u WHERE u.usuarioSessionFechaIngreso = :usuarioSessionFechaIngreso")
    , @NamedQuery(name = "UsuarioSession.findByUsuarioSessionIpAddress", query = "SELECT u FROM UsuarioSession u WHERE u.usuarioSessionIpAddress = :usuarioSessionIpAddress")})
public class UsuarioSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_session_id")
    private Integer usuarioSessionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_session_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioSessionFechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario_session_ip_address")
    private String usuarioSessionIpAddress;
    @JoinColumn(name = "usuario_session_id_usuario", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioSessionIdUsuario;

    public UsuarioSession() {
    }

    public UsuarioSession(Integer usuarioSessionId) {
        this.usuarioSessionId = usuarioSessionId;
    }

    public UsuarioSession(Integer usuarioSessionId, Date usuarioSessionFechaIngreso, String usuarioSessionIpAddress) {
        this.usuarioSessionId = usuarioSessionId;
        this.usuarioSessionFechaIngreso = usuarioSessionFechaIngreso;
        this.usuarioSessionIpAddress = usuarioSessionIpAddress;
    }

    public Integer getUsuarioSessionId() {
        return usuarioSessionId;
    }

    public void setUsuarioSessionId(Integer usuarioSessionId) {
        this.usuarioSessionId = usuarioSessionId;
    }

    public Date getUsuarioSessionFechaIngreso() {
        return usuarioSessionFechaIngreso;
    }

    public void setUsuarioSessionFechaIngreso(Date usuarioSessionFechaIngreso) {
        this.usuarioSessionFechaIngreso = usuarioSessionFechaIngreso;
    }

    public String getUsuarioSessionIpAddress() {
        return usuarioSessionIpAddress;
    }

    public void setUsuarioSessionIpAddress(String usuarioSessionIpAddress) {
        this.usuarioSessionIpAddress = usuarioSessionIpAddress;
    }

    public Usuario getUsuarioSessionIdUsuario() {
        return usuarioSessionIdUsuario;
    }

    public void setUsuarioSessionIdUsuario(Usuario usuarioSessionIdUsuario) {
        this.usuarioSessionIdUsuario = usuarioSessionIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioSessionId != null ? usuarioSessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSession)) {
            return false;
        }
        UsuarioSession other = (UsuarioSession) object;
        if ((this.usuarioSessionId == null && other.usuarioSessionId != null) || (this.usuarioSessionId != null && !this.usuarioSessionId.equals(other.usuarioSessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.UsuarioSession[ usuarioSessionId=" + usuarioSessionId + " ]";
    }
    
}
