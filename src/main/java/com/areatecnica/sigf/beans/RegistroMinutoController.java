package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.BusController;
import com.areatecnica.sigf.beans.ValorMinutoController;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.controllers.RegistroMinutoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "registroMinutoController")
@ViewScoped
public class RegistroMinutoController extends AbstractController<RegistroMinuto> {

    @Inject
    private RegistroMinutoFacade ejbFacade;
    @Inject
    private ValorMinutoController registroMinutoIdValorMinutoController;
    @Inject
    private BusController registroMinutoDesdeIdBusController;
    @Inject
    private BusController registroMinutoHastaIdBusController;

    /**
     * Initialize the concrete RegistroMinuto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RegistroMinutoController() {
        // Inform the Abstract parent controller of the concrete RegistroMinuto Entity
        super(RegistroMinuto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        registroMinutoIdValorMinutoController.setSelected(null);
        registroMinutoDesdeIdBusController.setSelected(null);
        registroMinutoHastaIdBusController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the ValorMinuto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroMinutoIdValorMinuto(ActionEvent event) {
        if (this.getSelected() != null && registroMinutoIdValorMinutoController.getSelected() == null) {
            registroMinutoIdValorMinutoController.setSelected(this.getSelected().getRegistroMinutoIdValorMinuto());
        }
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroMinutoDesdeIdBus(ActionEvent event) {
        if (this.getSelected() != null && registroMinutoDesdeIdBusController.getSelected() == null) {
            registroMinutoDesdeIdBusController.setSelected(this.getSelected().getRegistroMinutoDesdeIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroMinutoHastaIdBus(ActionEvent event) {
        if (this.getSelected() != null && registroMinutoHastaIdBusController.getSelected() == null) {
            registroMinutoHastaIdBusController.setSelected(this.getSelected().getRegistroMinutoHastaIdBus());
        }
    }
}
