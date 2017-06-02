package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ValorMinuto;
import com.areatecnica.sigf.controllers.ValorMinutoFacade;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "valorMinutoController")
@ViewScoped
public class ValorMinutoController extends AbstractController<ValorMinuto> {

    @Inject
    private ValorMinutoFacade ejbFacade;

    /**
     * Initialize the concrete ValorMinuto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ValorMinutoController() {
        // Inform the Abstract parent controller of the concrete ValorMinuto Entity
        super(ValorMinuto.class);
    }

    /**
     * Sets the "items" attribute with a collection of RegistroMinuto entities
     * that are retrieved from ValorMinuto?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RegistroMinuto page
     */
    public String navigateRegistroMinutoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroMinuto_items", this.getSelected().getRegistroMinutoList());
        }
        return "/registroMinuto/index";
    }

    @Override
    public ValorMinuto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setValorMinutoIdCuenta(this.getUserCount());
        this.getSelected().setValorMinutoFechaIngreso(new Date());
        return this.getSelected();
    }

}
