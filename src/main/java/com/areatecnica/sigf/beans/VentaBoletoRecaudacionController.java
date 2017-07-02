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
import com.areatecnica.sigf.entities.SerieBoletoGuia;
import com.areatecnica.sigf.models.VentaBoletoRecaudacionDataModel;
import java.text.SimpleDateFormat;
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
    private List<VentaBoletoHelper> ventaBoletosList;
    private List<Bus> busesList;
    private List<Guia> guiaList;
    private Map<Integer, ProcesoRecaudacion> procesosMap;
    private IInventarioCajaDao inventarioCajaDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IGuiaDao guiaDao;
    private IVentaBoletoDao ventaBoletoDao;
    private IBusDao busDao;
    private Guia busGuiaItem;
    private Date fechaVentaBoleto;
    private String formatFechaVentaBoleto;
    private final static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
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
        this.formatFechaVentaBoleto = format.format(fechaVentaBoleto);
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

        this.ventaBoletoDao = new IVentaBoletoDaoImpl();
        this.setItems(this.ventaBoletoDao.findByCajaDate(cajaRecaudacion, fechaVentaBoleto));
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
     * @return the Guia
     */
    public Guia getBusGuiaItem() {
        return busGuiaItem;
    }

    /**
     * @param Guia the boletoItem to set
     */
    public void setBusGuiaItem(Guia busGuiaItem) {
        this.busGuiaItem = busGuiaItem;
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
     * @return the fechaVentaBoleto
     */
    public String getFormatFechaVentaBoleto() {
        return formatFechaVentaBoleto;
    }

    /**
     * @param formatFechaVentaBoleto the fechaVentaBoleto to set
     */
    public void setFormatFechaVentaBoleto(String formatFechaVentaBoleto) {
        this.formatFechaVentaBoleto = formatFechaVentaBoleto;
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
     * @return the ventaBoletosList
     */
    public List<VentaBoletoHelper> getVentaBoletosList() {
        return ventaBoletosList;
    }

    /**
     * @param ventaBoletosList the ventaBoletosList to set
     */
    public void setVentaBoletosList(List<VentaBoletoHelper> ventaBoletosList) {
        this.ventaBoletosList = ventaBoletosList;
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

        for (VentaBoletoHelper v : this.ventaBoletosList) {
            if (v.porVender) {
                if (v.inventarioCaja != null) {
                    VentaBoleto ventaBoleto = new VentaBoleto();
                    ventaBoleto.setVentaBoletoFecha(fechaVentaBoleto);
                    ventaBoleto.setVentaBoletoFechaIngreso(new Date());
                    ventaBoleto.setVentaBoletoIdGuia(busGuiaItem);
                    ventaBoleto.setVentaBoletoIdInventarioCaja(v.inventarioCaja);
                    ventaBoleto.setVentaBoletoNumeroBoleta(this.getSelected().getVentaBoletoNumeroBoleta());
                    ventaBoleto.setVentaBoletoRecaudado(Boolean.TRUE);
                    ventaBoleto.setVentaBoletoUtilizado(Boolean.FALSE);
                    ventaBoleto.setVentaBoletoValorVentaBoleto(v.valor);
                    v.inventarioCaja.setInventarioCajaEstado(Boolean.TRUE);

                    this.ejbFacade.create(ventaBoleto);
                    this.ventaBoletoIdInventarioCajaController.getFacade().edit(v.inventarioCaja);
                    JsfUtil.addSuccessMessage("Se ingresado una nueva Venta al Bus N°:" + busGuiaItem.getGuiaIdBus().getBusNumero());

                    this.items.add(ventaBoleto);
                }
            }
        }

        this.setSelected(prepareCreate(event));
    }

    public void handleBusChange(ActionEvent event) {
        this.setVentaBoletosList(new ArrayList<>());
        int i = 0;
        for (SerieBoletoGuia sbg : busGuiaItem.getSerieBoletoGuiaList()) {

            int cantidad = 1000 - sbg.getSerieBoletoGuiaTermino();
            VentaBoletoHelper ventaBoleto = new VentaBoletoHelper();
            if (cantidad <= 200) {
                Boleto auxBoleto = sbg.getSerieBoletoGuiaIdVentaBoleto().getVentaBoletoIdInventarioCaja().getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto();
                this.inventarioCajaDao = new IInventarioCajaDaoImpl();
                this.ventaBoletoDao = new IVentaBoletoDaoImpl();
                ventaBoleto.setGuia(busGuiaItem);
                ventaBoleto.setBoleto(auxBoleto);
                ventaBoleto.setCantidad(cantidad);
                ventaBoleto.setPorVender(Boolean.TRUE);
                ventaBoleto.setFechaCompra(sbg.getSerieBoletoGuiaIdVentaBoleto().getVentaBoletoFecha());
                ventaBoleto.setInventarioCajaList(this.inventarioCajaDao.findByBoletoEstado(cajaRecaudacion, ventaBoleto.getBoleto(), Boolean.FALSE));
                VentaBoleto auxVentaBoleto = ventaBoletoDao.findByBusBoletoEstado(busGuiaItem.getGuiaIdBus(), auxBoleto);
                if (auxVentaBoleto != null) {
                    ventaBoleto.setError(Boolean.TRUE);
                } else {
                    ventaBoleto.setError(Boolean.FALSE);
                }

            } else {
                ventaBoleto.setGuia(busGuiaItem);
                ventaBoleto.setBoleto(sbg.getSerieBoletoGuiaIdVentaBoleto().getVentaBoletoIdInventarioCaja().getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto());
                ventaBoleto.setCantidad(cantidad);
                ventaBoleto.setFechaCompra(sbg.getSerieBoletoGuiaIdVentaBoleto().getVentaBoletoFecha());
                ventaBoleto.setVendido(Boolean.FALSE);
                ventaBoleto.setPorVender(Boolean.FALSE);
                ventaBoleto.setError(Boolean.FALSE);
            }
            this.ventaBoletosList.add(ventaBoleto);
        }
    }

    public void handleBoletoChange(ActionEvent event) {

        //
        //POR MODIFICAR
        this.setInventarioCajaList((List<InventarioCaja>) this.inventarioCajaDao.findByBoletoEstado(this.cajaRecaudacion, this.getBoletoItem(), Boolean.FALSE));
    }

    public void load() {
        this.ventaBoletoDao = new IVentaBoletoDaoImpl();
        this.setItems((List<VentaBoleto>) this.ventaBoletoDao.findByCajaDate(getCajaRecaudacion(), getFechaVentaBoleto()));
        this.setModel(new VentaBoletoRecaudacionDataModel(getItems()));
    }

    public class VentaBoletoHelper {

        private int id;
        private Boleto boleto;
        private int cantidad;
        private int valor;
        private boolean vendido;
        private boolean porVender;
        private boolean error;
        private Date fechaCompra;
        private String identificador;
        private InventarioCaja inventarioCaja;
        private List<InventarioCaja> inventarioCajaList;
        private Guia guia;

        public VentaBoletoHelper() {
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public Date getFechaCompra() {
            return fechaCompra;
        }

        public void setFechaCompra(Date fechaCompra) {
            this.fechaCompra = fechaCompra;
        }

        public Boleto getBoleto() {
            return boleto;
        }

        public void setBoleto(Boleto boleto) {
            this.boleto = boleto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        public void setPorVender(boolean porVender) {
            this.porVender = porVender;
        }

        public boolean isPorVender() {
            return porVender;
        }

        public boolean isVendido() {
            return vendido;
        }

        public void setVendido(boolean vendido) {
            this.vendido = vendido;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getIdentificador() {
            return identificador;
        }

        public void setIdentificador(String identificador) {
            this.identificador = identificador;
        }

        public InventarioCaja getInventarioCaja() {
            return inventarioCaja;
        }

        public void setInventarioCaja(InventarioCaja inventarioCaja) {
            this.inventarioCaja = inventarioCaja;
        }

        public List<InventarioCaja> getInventarioCajaList() {
            return inventarioCajaList;
        }

        public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
            this.inventarioCajaList = inventarioCajaList;
        }

        public Guia getGuia() {
            return guia;
        }

        public void setGuia(Guia guia) {
            this.guia = guia;
        }

    }
}
