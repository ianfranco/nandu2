package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.GuiaController;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.controllers.VentaCombustibleFacade;
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

    /**
     * Initialize the concrete VentaCombustible controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
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
}
