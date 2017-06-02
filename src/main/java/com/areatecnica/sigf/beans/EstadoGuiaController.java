package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.EstadoGuia;
import com.areatecnica.sigf.controllers.EstadoGuiaFacade;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estadoGuiaController")
@ViewScoped
public class EstadoGuiaController extends AbstractController<EstadoGuia> {

    @Inject
    private EstadoGuiaFacade ejbFacade;

    /**
     * Initialize the concrete EstadoGuia controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EstadoGuiaController() {
        // Inform the Abstract parent controller of the concrete EstadoGuia Entity
        super(EstadoGuia.class);
    }

    /**
     * Sets the "items" attribute with a collection of Guia entities that are
     * retrieved from EstadoGuia?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Guia page
     */
    public String navigateGuiaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Guia_items", this.getSelected().getGuiaList());
        }
        return "/guia/index";
    }

    @Override
    public EstadoGuia prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setEstadoGuiaIdCuenta(this.getUserCount());
        this.getSelected().setEstadoGuiaFechaIngreso(new Date());
        this.getSelected().setEstadoGuiaActivo(Boolean.TRUE);
        return this.getSelected();
    }

}
