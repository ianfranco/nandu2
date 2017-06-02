package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.EgresoRecaudacion;
import com.areatecnica.sigf.controllers.EgresoRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "egresoRecaudacionController")
@ViewScoped
public class EgresoRecaudacionController extends AbstractController<EgresoRecaudacion> {

    @Inject
    private EgresoRecaudacionFacade ejbFacade;
    @Inject
    private EgresoController egresoRecaudacionIdEgresoController;
    @Inject
    private ResumenRecaudacionController egresoRecaudacionIdResumenRecaudacionController;

    /**
     * Initialize the concrete EgresoRecaudacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EgresoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete EgresoRecaudacion Entity
        super(EgresoRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoRecaudacionIdEgresoController.setSelected(null);
        egresoRecaudacionIdResumenRecaudacionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoRecaudacionIdEgreso(ActionEvent event) {
        if (this.getSelected() != null && egresoRecaudacionIdEgresoController.getSelected() == null) {
            egresoRecaudacionIdEgresoController.setSelected(this.getSelected().getEgresoRecaudacionIdEgreso());
        }
    }

    /**
     * Sets the "selected" attribute of the ResumenRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoRecaudacionIdResumenRecaudacion(ActionEvent event) {
        if (this.getSelected() != null && egresoRecaudacionIdResumenRecaudacionController.getSelected() == null) {
            egresoRecaudacionIdResumenRecaudacionController.setSelected(this.getSelected().getEgresoRecaudacionIdResumen());
        }
    }
}
