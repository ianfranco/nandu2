package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.BusController;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.controllers.GuiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "guiaController")
@ViewScoped
public class GuiaController extends AbstractController<Guia> {

    @Inject
    private GuiaFacade ejbFacade;
    @Inject
    private BusController guiaIdBusController;
    @Inject
    private EstadoGuiaController guiaIdEstadoController;
    @Inject
    private CajaRecaudacionController guiaIdCajaTerminalController;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;

    /**
     * Initialize the concrete Guia controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public GuiaController() {
        // Inform the Abstract parent controller of the concrete Guia Entity
        super(Guia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        guiaIdBusController.setSelected(null);
        guiaIdEstadoController.setSelected(null);
        guiaIdCajaTerminalController.setSelected(null);
        guiaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of SerieBoletoGuia entities
     * that are retrieved from Guia?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for SerieBoletoGuia page
     */
    public String navigateSerieBoletoGuiaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SerieBoletoGuia_items", this.getSelected().getSerieBoletoGuiaList());
        }
        return "/serieBoletoGuia/index";
    }
    
    /**
     * Sets the "items" attribute with a collection of VentaCombustible entities
     * that are retrieved from Guia?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for VentaCombustible page
     */
    public String navigateVentaCombustibleList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaCombustible_items", this.getSelected().getVentaCombustibleList());
        }
        return "/ventaCombustible/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdBus(ActionEvent event) {
        if (this.getSelected() != null && guiaIdBusController.getSelected() == null) {
            guiaIdBusController.setSelected(this.getSelected().getGuiaIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the EstadoGuia controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdEstado(ActionEvent event) {
        if (this.getSelected() != null && guiaIdEstadoController.getSelected() == null) {
            guiaIdEstadoController.setSelected(this.getSelected().getGuiaIdEstado());
        }
    }

    /**
     * Sets the "selected" attribute of the CajaTerminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdCajaTerminal(ActionEvent event) {
        if (this.getSelected() != null && guiaIdCajaTerminalController.getSelected() == null) {
            guiaIdCajaTerminalController.setSelected(this.getSelected().getGuiaIdCajaTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && guiaIdTrabajadorController.getSelected() == null) {
            guiaIdTrabajadorController.setSelected(this.getSelected().getGuiaIdTrabajador());
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoGuia entities that
     * are retrieved from Guia?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoGuia page
     */
    public String navigateEgresoGuiaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoGuia_items", this.getSelected().getEgresoGuiaList());
        }
        return "/egresoGuia/index";
    }

}
