package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.controllers.VentaCombustibleFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.IPrecioCombustibleDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IPrecioCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "ventaCombustibleController")
@ViewScoped
public class VentaCombustibleController extends AbstractController<VentaCombustible> {

    @Inject
    private VentaCombustibleFacade ejbFacade;
    @Inject
    private GuiaController ventaCombustibleIdGuiaController;
    @Inject
    private SurtidorController ventaCombustibleIdSurtidorController;

    private List<Bus> busItems;
    private List<Guia> guiaItems;
    private List<UnidadNegocio> unidadItems;
    private Map unidadMap;
    private Guia guiaSelected;
    private Bus busSelected;
    private UnidadNegocio unidadSelected;
    private IGuiaDao guiaDao;
    private IBusDao busDao;
    private PrecioCombustible precioCombustible;
    private IPrecioCombustibleDao precioCombustibleDao;
    private String tipoVenta;
    private Date fecha;

    /**
     * Initialize the concrete VentaCombustible controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.fecha = new Date();

        this.busDao = new IBusDaoImpl();
        this.busItems = this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal());
        
        unidadMap = new HashMap();
        
        for (Bus b : this.busItems) {
            unidadMap.put(b.getBusIdUnidadNegocio(), b.getBusIdUnidadNegocio());
        }
        
        this.unidadItems = new ArrayList(this.unidadMap.values());

        this.precioCombustibleDao = new IPrecioCombustibleDaoImpl();
        this.precioCombustible = this.precioCombustibleDao.findLastPrecio(this.getUserCount());
        System.err.println("VALOR COMBUSTIBLE: " + this.precioCombustible.getPrecioCombustibleValor());
    }

    public VentaCombustibleController() {
        // Inform the Abstract parent controller of the concrete VentaCombustible Entity
        super(VentaCombustible.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ventaCombustibleIdGuiaController.setSelected(null);
        ventaCombustibleIdSurtidorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Guia controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaCombustibleIdGuia(ActionEvent event) {
        if (this.getSelected() != null && ventaCombustibleIdGuiaController.getSelected() == null) {
            ventaCombustibleIdGuiaController.setSelected(this.getSelected().getVentaCombustibleIdGuia());
        }
    }

    /**
     * Sets the "selected" attribute of the Surtidor controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaCombustibleIdSurtidor(ActionEvent event) {
        if (this.getSelected() != null && ventaCombustibleIdSurtidorController.getSelected() == null) {
            ventaCombustibleIdSurtidorController.setSelected(this.getSelected().getVentaCombustibleIdSurtidor());
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Guia> getGuiaItems() {
        return guiaItems;
    }

    public void setGuiaItems(List<Guia> guiaItems) {
        this.guiaItems = guiaItems;
    }

    public Guia getGuiaSelected() {
        return guiaSelected;
    }

    public void setGuiaSelected(Guia guiaSelected) {
        this.guiaSelected = guiaSelected;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public List<UnidadNegocio> getUnidadItems() {
        return unidadItems;
    }

    public void setUnidadItems(List<UnidadNegocio> unidadItems) {
        this.unidadItems = unidadItems;
    }
        
    public void setUnidadMap(Map unidadMap) {
        this.unidadMap = unidadMap;
    }

    public Map getUnidadMap() {
        return unidadMap;
    }

    public UnidadNegocio getUnidadSelected() {
        return unidadSelected;
    }

    public void setUnidadSelected(UnidadNegocio unidadSelected) {
        this.unidadSelected = unidadSelected;
    }
    
    

    @Override
    public VentaCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setVentaCombustibleFecha(new Date());
        this.getSelected().setVentaCombustibleValorCombustible(precioCombustible.getPrecioCombustibleValor());
        return this.getSelected();
    }

    public void handleTipoVentaChange() {
        System.err.println("TIPO VENTA:" + this.tipoVenta);
        //this.guiaItems = this.guiaDao.findB

    }

}
