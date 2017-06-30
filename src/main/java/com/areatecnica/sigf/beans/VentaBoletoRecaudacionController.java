package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.controllers.VentaBoletoFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.IInventarioCajaDao;
import com.areatecnica.sigf.dao.IVentaBoletoDao;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IInventarioCajaDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaBoletoDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.InventarioCaja;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.models.VentaBoletoRecaudacionDataModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private List<Bus> busesList;
    private List<Guia> guiaList;
    private Map<Integer, ProcesoRecaudacion> procesosMap;
    private IInventarioCajaDao inventarioCajaDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IGuiaDao guiaDao;
    private IVentaBoletoDao ventaBoletoDao;
    private IBusDao busDao;
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

        /*this.ventaBoletoDao = new IVentaBoletoDaoImpl();
        this.setItems(this.ventaBoletoDao.findAll());*/
        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.setCajaRecaudacionList((List<CajaRecaudacion>) this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser()));
        this.setFechaVentaBoleto(new Date());

        this.guiaDao = new IGuiaDaoImpl();
        this.guiaList = new ArrayList<>();
        this.setBusesList(new ArrayList<>());

        List<CajaRecaudacion> cajaList = this.getCurrentUser().getCajaRecaudacionList();
        if (cajaList.size() > 1) {
            this.setProcesosMap((Map<Integer, ProcesoRecaudacion>) new HashMap());
            for (CajaRecaudacion c : cajaList) {
                for (CajaProceso caja : c.getCajaProcesoList()) {
                    this.getProcesosMap().put(caja.getCajaProcesoIdProceso().getProcesoRecaudacionId(), caja.getCajaProcesoIdProceso());
                }
            }

            for (Map.Entry<Integer, ProcesoRecaudacion> entry : getProcesosMap().entrySet()) {
                ProcesoRecaudacion proceso = (ProcesoRecaudacion) entry.getValue();
                List<Guia> auxListGuia = this.guiaDao.findByProcesoFechaGuia(proceso, fechaVentaBoleto);

                this.guiaList.addAll(auxListGuia);
            }
        } else {
            this.setProcesosMap((Map<Integer, ProcesoRecaudacion>) new HashMap());
            this.cajaRecaudacion = cajaList.get(0);
            for (CajaProceso caja : this.cajaRecaudacion.getCajaProcesoList()) {
                this.getProcesosMap().put(caja.getCajaProcesoIdProceso().getProcesoRecaudacionId(), caja.getCajaProcesoIdProceso());
            }
            for (Map.Entry<Integer, ProcesoRecaudacion> entry : getProcesosMap().entrySet()) {
                ProcesoRecaudacion proceso = (ProcesoRecaudacion) entry.getValue();
                List<Guia> auxListGuia = this.guiaDao.findByProcesoFechaGuia(proceso, fechaVentaBoleto);

                this.guiaList.addAll(auxListGuia);
            }
        }

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
     * @return the guiaList
     */
    public List<Guia> getGuiaList() {
        return guiaList;
    }

    /**
     * @param guiaList the items to set
     */
    public void setGuiaList(List<Guia> guiaList) {
        this.guiaList = guiaList;
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
     * @return the busesList
     */
    public List<Bus> getBusesList() {
        return busesList;
    }

    /**
     * @param busesList the busesList to set
     */
    public void setBusesList(List<Bus> busesList) {
        this.busesList = busesList;
    }

    /**
     * @return the procesosMap
     */
    public Map<Integer, ProcesoRecaudacion> getProcesosMap() {
        return procesosMap;
    }

    /**
     * @param procesosMap the procesosMap to set
     */
    public void setProcesosMap(Map<Integer, ProcesoRecaudacion> procesosMap) {
        this.procesosMap = procesosMap;
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

    @Override
    public VentaBoleto prepareCreate(ActionEvent event) {
        super.prepareCreate(event);
        this.getSelected().setVentaBoletoFechaIngreso(new Date());
        this.getSelected().setVentaBoletoFecha(new Date());
        this.getSelected().setVentaBoletoUtilizado(Boolean.FALSE);

        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.ejbFacade.create(this.getSelected());
        this.items.add(this.getSelected());
        JsfUtil.addSuccessMessage("Se ha registrado una venta de boleto (" + this.getSelected().getVentaBoletoIdInventarioCaja().getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto().getBoletoNombre() + ") al Bus N°: " + this.getSelected().getVentaBoletoIdGuia().getGuiaIdBus().getBusNumero());
        this.setSelected(prepareCreate(event));
    }

    public void handleBoletoChange(ActionEvent event) {
        //POR MODIFICAR
        this.setInventarioCajaList((List<InventarioCaja>) this.inventarioCajaDao.findByBoletoEstado(this.cajaRecaudacion, this.getBoletoItem(), Boolean.FALSE));
    }

    public void load() {
        this.ventaBoletoDao = new IVentaBoletoDaoImpl();
        this.setItems((List<VentaBoleto>) this.ventaBoletoDao.findByCajaDate(getCajaRecaudacion(), getFechaVentaBoleto()));
        this.setModel(new VentaBoletoRecaudacionDataModel(getItems()));
    }
}
