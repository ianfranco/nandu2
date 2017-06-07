package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.controllers.VentaBoletoFacade;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.IInventarioCajaDao;
import com.areatecnica.sigf.dao.IVentaBoletoDao;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IInventarioCajaDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaBoletoDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.InventarioCaja;
import com.areatecnica.sigf.models.VentaBoletoRecaudacionDataModel;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "ventaBoletoRecaudacionController")
@ViewScoped
public class VentaBoletoRecaudacionController extends AbstractController<VentaBoleto> {

    @Inject
    private VentaBoletoFacade ejbFacade;
    @Inject
    private GuiaController ventaBoletoIdGuiaController;
    @Inject
    private InventarioCajaController ventaBoletoIdInventarioCajaController;

    private Boleto boletoItem;
    private List<VentaBoleto> items;
    private List<InventarioCaja> inventarioCajaList;
    private List<CajaRecaudacion> cajaRecaudacionList;
    private IInventarioCajaDao inventarioCajaDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IGuiaDao guiaDao;
    private IVentaBoletoDao ventaBoletoDao;
    private Date fechaVentaBoleto;
    private CajaRecaudacion cajaRecaudacion;
    private VentaBoletoRecaudacionDataModel model;

    /**
     * Initialize the concrete VentaBoleto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.inventarioCajaDao = new IInventarioCajaDaoImpl();

        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.setCajaRecaudacionList((List<CajaRecaudacion>) this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser()));
        this.setFechaVentaBoleto(new Date());
    }

    public VentaBoletoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete VentaBoleto Entity
        super(VentaBoleto.class);
    }

    /**
     * @return the boletoItem
     */
    public Boleto getBoletoItem() {
        return boletoItem;
    }

    /**
     * @param boletoItem the boletoItem to set
     */
    public void setBoletoItem(Boleto boletoItem) {
        this.boletoItem = boletoItem;
    }

    /**
     * @return the inventarioCajaList
     */
    public List<InventarioCaja> getInventarioCajaList() {
        return inventarioCajaList;
    }

    /**
     * @param inventarioCajaList the inventarioCajaList to set
     */
    public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
        this.inventarioCajaList = inventarioCajaList;
    }

    /**
     * @return the items
     */
    public List<VentaBoleto> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<VentaBoleto> items) {
        this.items = items;
    }

    /**
     * @return the cajaRecaudacionList
     */
    public List<CajaRecaudacion> getCajaRecaudacionList() {
        return cajaRecaudacionList;
    }

    /**
     * @param cajaRecaudacionList the cajaRecaudacionList to set
     */
    public void setCajaRecaudacionList(List<CajaRecaudacion> cajaRecaudacionList) {
        this.cajaRecaudacionList = cajaRecaudacionList;
    }

    /**
     * @return the fechaVentaBoleto
     */
    public Date getFechaVentaBoleto() {
        return fechaVentaBoleto;
    }

    /**
     * @param fechaVentaBoleto the fechaVentaBoleto to set
     */
    public void setFechaVentaBoleto(Date fechaVentaBoleto) {
        this.fechaVentaBoleto = fechaVentaBoleto;
    }

    /**
     * @return the cajaRecaudacion
     */
    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    /**
     * @param cajaRecaudacion the cajaRecaudacion to set
     */
    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    /**
     * @return the model
     */
    public VentaBoletoRecaudacionDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(VentaBoletoRecaudacionDataModel model) {
        this.model = model;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ventaBoletoIdGuiaController.setSelected(null);
        ventaBoletoIdInventarioCajaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Guia controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdGuia(ActionEvent event) {
        if (this.getSelected() != null && ventaBoletoIdGuiaController.getSelected() == null) {
            ventaBoletoIdGuiaController.setSelected(this.getSelected().getVentaBoletoIdGuia());
        }
    }

    /**
     * Sets the "selected" attribute of the InventarioCaja controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdInventarioCaja(ActionEvent event) {
        if (this.getSelected() != null && ventaBoletoIdInventarioCajaController.getSelected() == null) {
            ventaBoletoIdInventarioCajaController.setSelected(this.getSelected().getVentaBoletoIdInventarioCaja());
        }
    }

    public void handleBoletoChange(ActionEvent event) {
        //POR MODIFICAR
        //this.setInventarioCajaList((List<InventarioCaja>) this.iInventarioCajaDao.findByBoletoEstado(this.getBoletoItem(), Boolean.FALSE));        
    }

    public void load() {
        this.ventaBoletoDao = new IVentaBoletoDaoImpl();
        this.setItems((List<VentaBoleto>) this.ventaBoletoDao.findByCajaDate(getCajaRecaudacion(), getFechaVentaBoleto()));
        this.setModel(new VentaBoletoRecaudacionDataModel(getItems()));
    }
}
