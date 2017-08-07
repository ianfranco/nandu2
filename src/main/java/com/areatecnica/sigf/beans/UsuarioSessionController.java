package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.controllers.UsuarioSessionFacade;
import com.areatecnica.sigf.entities.UsuarioSession;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "usuarioSessionController")
@ViewScoped
public class UsuarioSessionController extends AbstractController<UsuarioSession> {

    @Inject
    private UsuarioSessionFacade ejbFacade;
    
    /**
     * Initialize the concrete Usuario controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public UsuarioSessionController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(UsuarioSession.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        
    }
   

    @Override
    public UsuarioSession prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        return this.getSelected();
    }

}
