/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.sockets;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author ianfr
 */
@Named(value = "notificationController")
@RequestScoped
public class NotificationController implements Serializable {

    private EventBus eventBus;

    /**
     * Creates a new instance of NotificationController
     */
    public NotificationController() {
    }

    public void pushNotify(String channel, String message) {
        eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(channel, new FacesMessage("Información", StringEscapeUtils.escapeHtml3(message)));
    }

}
