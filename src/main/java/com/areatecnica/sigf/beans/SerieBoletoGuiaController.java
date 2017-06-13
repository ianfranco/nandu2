package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.GuiaController;
import com.areatecnica.sigf.entities.SerieBoletoGuia;
import com.areatecnica.sigf.controllers.SerieBoletoGuiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "serieBoletoGuiaController")
@ViewScoped
public class SerieBoletoGuiaController extends AbstractController<SerieBoletoGuia> {

    @Inject
    private SerieBoletoGuiaFacade ejbFacade;
    @Inject
    private VentaBoletoController serieBoletoGuiaIdVentaBoletoController;
    @Inject
    private GuiaController serieBoletoGuiaIdGuiaController;

    /**
     * Initialize the concrete SerieBoletoGuia controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public SerieBoletoGuiaController() {
        // Inform the Abstract parent controller of the concrete SerieBoletoGuia Entity
        super(SerieBoletoGuia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        serieBoletoGuiaIdVentaBoletoController.setSelected(null);
        serieBoletoGuiaIdGuiaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSerieBoletoGuiaIdVentaBoleto(ActionEvent event) {
        if (this.getSelected() != null && serieBoletoGuiaIdVentaBoletoController.getSelected() == null) {
            serieBoletoGuiaIdVentaBoletoController.setSelected(this.getSelected().getSerieBoletoGuiaIdVentaBoleto());
        }
    }

    /**
     * Sets the "selected" attribute of the Guia controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSerieBoletoGuiaIdGuia(ActionEvent event) {
        if (this.getSelected() != null && serieBoletoGuiaIdGuiaController.getSelected() == null) {
            serieBoletoGuiaIdGuiaController.setSelected(this.getSelected().getSerieBoletoGuiaIdGuia());
        }
    }
}
