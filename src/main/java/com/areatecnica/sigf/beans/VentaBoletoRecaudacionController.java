package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.GuiaController;
import com.areatecnica.sigf.beans.InventarioCajaController;
import com.areatecnica.sigf.beans.*;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.controllers.VentaBoletoFacade;
import com.areatecnica.sigf.dao.IInventarioCajaDao;
import com.areatecnica.sigf.dao.impl.IInventarioCajaDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.InventarioCaja;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "ventaBoletoRecaudacionController")
@ViewScoped
public class VentaBoletoRecaudacionController extends AbstractController<VentaBoleto> {

    @Inject
    private VentaBoletoFacade ejbFacade;
    @Inject
    private GuiaController ventaBoletoIdGuiaController;
    @Inject
    private InventarioCajaController ventaBoletoIdInventarioCajaController;
    
    private Boleto boletoItem; 
    private List<InventarioCaja> inventarioCajaList;
    private IInventarioCajaDao iInventarioCajaDao;

    /**
     * Initialize the concrete VentaBoleto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.iInventarioCajaDao = new IInventarioCajaDaoImpl();
    }

    public VentaBoletoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete VentaBoleto Entity
        super(VentaBoleto.class);
    }

    /**
     * @return the boletoItem
     */
    public Boleto getBoletoItem() {
        return boletoItem;
    }

    /**
     * @param boletoItem the boletoItem to set
     */
    public void setBoletoItem(Boleto boletoItem) {
        this.boletoItem = boletoItem;
    }

    /**
     * @return the inventarioCajaList
     */
    public List<InventarioCaja> getInventarioCajaList() {
        return inventarioCajaList;
    }

    /**
     * @param inventarioCajaList the inventarioCajaList to set
     */
    public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
        this.inventarioCajaList = inventarioCajaList;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ventaBoletoIdGuiaController.setSelected(null);
        ventaBoletoIdInventarioCajaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Guia controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdGuia(ActionEvent event) {
        if (this.getSelected() != null && ventaBoletoIdGuiaController.getSelected() == null) {
            ventaBoletoIdGuiaController.setSelected(this.getSelected().getVentaBoletoIdGuia());
        }
    }

    /**
     * Sets the "selected" attribute of the InventarioCaja controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdInventarioCaja(ActionEvent event) {
        if (this.getSelected() != null && ventaBoletoIdInventarioCajaController.getSelected() == null) {
            ventaBoletoIdInventarioCajaController.setSelected(this.getSelected().getVentaBoletoIdInventarioCaja());
        }
    }
    
    public void handleBoletoChange(ActionEvent event) {
        this.setInventarioCajaList((List<InventarioCaja>) this.iInventarioCajaDao.findByBoletoEstado(this.getBoletoItem(), Boolean.FALSE));        
    }
}
