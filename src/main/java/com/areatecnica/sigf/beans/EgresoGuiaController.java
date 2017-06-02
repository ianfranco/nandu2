package com.areatecnica.sigf.beans;


import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.GuiaController;
import com.areatecnica.sigf.entities.EgresoGuia;
import com.areatecnica.sigf.controllers.EgresoGuiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "egresoGuiaController")
@ViewScoped
public class EgresoGuiaController extends AbstractController<EgresoGuia> {

    @Inject
    private EgresoGuiaFacade ejbFacade;
    @Inject
    private EgresoController egresoGuiaIdEgresoController;
    @Inject
    private GuiaController egresoGuiaIdGuiaController;

    /**
     * Initialize the concrete EgresoGuia controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EgresoGuiaController() {
        // Inform the Abstract parent controller of the concrete EgresoGuia Entity
        super(EgresoGuia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoGuiaIdEgresoController.setSelected(null);
        egresoGuiaIdGuiaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoGuiaIdEgreso(ActionEvent event) {
        if (this.getSelected() != null && egresoGuiaIdEgresoController.getSelected() == null) {
            egresoGuiaIdEgresoController.setSelected(this.getSelected().getEgresoGuiaIdEgreso());
        }
    }

    /**
     * Sets the "selected" attribute of the Guia controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoGuiaIdGuia(ActionEvent event) {
        if (this.getSelected() != null && egresoGuiaIdGuiaController.getSelected() == null) {
            egresoGuiaIdGuiaController.setSelected(this.getSelected().getEgresoGuiaIdGuia());
        }
    }
}
