package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.controllers.GuiaFacade;
import com.areatecnica.sigf.controllers.SerieBoletoGuiaFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IEstadoGuiaDao;
import com.areatecnica.sigf.dao.IGrupoServicioDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.ISerieBoletoDao;
import com.areatecnica.sigf.dao.ITarifaGrupoServicioDao;
import com.areatecnica.sigf.dao.IVentaBoletoDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IEstadoGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IGrupoServicioDaoImpl;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.ITarifaGrupoServicioDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaBoletoDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.SerieBoletoGuia;
import com.areatecnica.sigf.entities.TarifaGrupoServicio;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.models.GuiaGrupalDataModel;
import com.areatecnica.sigf.models.SerieBoletoGuiaDataModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;

@Named(value = "guiaGrupalController")
@ViewScoped
public class GuiaGrupalController extends AbstractController<Guia> {

    @Inject
    private GuiaFacade ejbFacade;
    @Inject
    private SerieBoletoGuiaFacade serieBoletoGuiaFacade;
    @Inject
    private BusController guiaIdBusController;
    @Inject
    private EstadoGuiaController guiaIdEstadoController;
    @Inject
    private CajaRecaudacionController guiaIdCajaTerminalController;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;

    private List<Guia> items;
    private List<Bus> busesList;
    private List<SerieBoletoGuia> boletoGuiaList;
    private List<GrupoServicio> grupoServicioList;
    private List<VentaBoleto> ventaBoletoBusList;
    private Map defaultVentaBoletoMap;
    private Map valorTarifasMap;
    private GuiaGrupalDataModel model;
    private IGuiaDao guiaDao;
    private IBusDao busDao;
    private ISerieBoletoDao serieBoletoGuiaDao;
    private IGrupoServicioDao grupoServicioDao;
    private ITarifaGrupoServicioDao tarifaGrupoServicioDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IEstadoGuiaDao estadoGuiaDao;
    private IVentaBoletoDao ventaBoletoDao;
    private Guia selected;
    private Guia auxGuia;
    private GrupoServicio grupoServicio;
    private Date fecha;
    private Date fechaMin;
    private Date fechaMax;
    private String formatFecha;
    private int folioInicio;
    private final static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
    private Boolean mantenerEstado;
    private Boolean mantenerConductor;
    private Boolean deshabilitarFolio;

    /**
     * Initialize the concrete Guia controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setFecha(new Date());
        this.ventaBoletoDao = new IVentaBoletoDaoImpl();
        this.tarifaGrupoServicioDao = new ITarifaGrupoServicioDaoImpl();
        List<TarifaGrupoServicio> auxList = this.tarifaGrupoServicioDao.findAllByCuenta();
        this.setValorTarifasMap(new HashMap());
        auxList.forEach((tgs) -> {
            this.getValorTarifasMap().put(tgs.getTarifaGrupoServicioIdBoleto(), tgs.getTarifaGrupoServicioValor());
        });

        List<VentaBoleto> defaultVenta = this.ventaBoletoDao.findByDefaultBus();
        this.setDefaultVentaBoletoMap(new HashMap());
        defaultVenta.forEach((vb) -> {
            this.getDefaultVentaBoletoMap().put(vb.getVentaBoletoIdInventarioCaja().getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto(), vb);
        });

        //this.guiaDao = new IGuiaDaoImpl();
        //this.setItems((List<Guia>) this.guiaDao.findByCuentaFecha(this.getUserCount(), this.getFecha()));
        this.grupoServicioDao = new IGrupoServicioDaoImpl();
        this.setGrupoServicioList((List<GrupoServicio>) this.grupoServicioDao.findByTerminal(this.getCurrentUser().getUsuarioIdTerminal()));
        //this.setBusesList(busesProceso(this.procesoRecaudacion));

        this.estadoGuiaDao = new IEstadoGuiaDaoImpl();
        this.guiaIdEstadoController.setItems(this.estadoGuiaDao.findAllByCuentaInspector(this.getUserCount()));

        DateTime time = new DateTime();
        this.fechaMin = new Date();
        this.fechaMax = time.plusDays(1).toDate();
        this.fecha = this.fechaMax;
        this.deshabilitarFolio = Boolean.TRUE;
        this.mantenerConductor = Boolean.TRUE;
        this.mantenerEstado = Boolean.TRUE;
    }

    public GuiaGrupalController() {
        // Inform the Abstract parent controller of the concrete Guia Entity
        super(Guia.class);
    }

    /**
     * @return the items
     */
    @Override
    public List<Guia> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Guia> items) {
        this.items = items;
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
     * @return the selected
     */
    @Override
    public Guia getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    @Override
    public void setSelected(Guia selected) {
        this.selected = selected;
    }

    /**
     * @return the boletoGuiaList
     */
    public List<SerieBoletoGuia> getBoletoGuiaList() {
        return boletoGuiaList;
    }

    /**
     * @param boletoGuiaList the boletoGuiaList to set
     */
    public void setBoletoGuiaList(List<SerieBoletoGuia> boletoGuiaList) {
        this.boletoGuiaList = boletoGuiaList;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the fechaMin
     */
    public Date getFechaMin() {
        return fechaMin;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFechaMin(Date fechaMin) {
        this.fechaMin = fechaMin;
    }

    /**
     * @return the fechaMax
     */
    public Date getFechaMax() {
        return fechaMax;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFechaMax(Date fechaMax) {
        this.fechaMax = fechaMax;
    }

    /**
     * @return the formatFecha
     */
    public String getFormatFecha() {
        return format.format(fecha);
    }

    /**
     * @param formatFecha the formatFecha to set
     */
    public void setFormatFecha(String formatFecha) {
        this.formatFecha = formatFecha;
    }

    /**
     * @return the grupoServicioList
     */
    public List<GrupoServicio> getGrupoServicioList() {
        return grupoServicioList;
    }

    /**
     * @param grupoServicioList the grupoServicioList to set
     */
    public void setGrupoServicioList(List<GrupoServicio> grupoServicioList) {
        this.grupoServicioList = grupoServicioList;
    }

    /**
     * @return the grupoServicio
     */
    public GrupoServicio getGrupoServicio() {
        return grupoServicio;
    }

    /**
     * @param grupoServicio the grupoServicio to set
     */
    public void setGrupoServicio(GrupoServicio grupoServicio) {
        this.grupoServicio = grupoServicio;
    }

    /**
     * @return the model
     */
    public GuiaGrupalDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(GuiaGrupalDataModel model) {
        this.model = model;
    }

    /**
     * @return the ventaBoletoBusList
     */
    public List<VentaBoleto> getVentaBoletoBusList() {
        return ventaBoletoBusList;
    }

    /**
     * @param ventaBoletoBusList the ventaBoletoBusList to set
     */
    public void setVentaBoletoBusList(List<VentaBoleto> ventaBoletoBusList) {
        this.ventaBoletoBusList = ventaBoletoBusList;
    }

    /**
     * @return the defaultVentaBoletoMap
     */
    public Map getDefaultVentaBoletoMap() {
        return defaultVentaBoletoMap;
    }

    /**
     * @param defaultVentaBoletoMap the defaultVentaBoletoMap to set
     */
    public void setDefaultVentaBoletoMap(Map defaultVentaBoletoMap) {
        this.defaultVentaBoletoMap = defaultVentaBoletoMap;
    }

    /**
     * @return the valorTarifasMap
     */
    public Map getValorTarifasMap() {
        return valorTarifasMap;
    }

    /**
     * @param valorTarifasMap the valorTarifasMap to set
     */
    public void setValorTarifasMap(Map valorTarifasMap) {
        this.valorTarifasMap = valorTarifasMap;
    }

    /**
     * @return the auxGuia
     */
    public Guia getAuxGuia() {
        return auxGuia;
    }

    /**
     * @param auxGuia the auxGuia to set
     */
    public void setAuxGuia(Guia auxGuia) {
        this.auxGuia = auxGuia;
    }

    /**
     * @return the mantenerEstado
     */
    public Boolean getMantenerEstado() {
        return mantenerEstado;
    }

    /**
     * @param mantenerEstado the auxGuia to set
     */
    public void setMantenerEstado(Boolean mantenerEstado) {
        this.mantenerEstado = mantenerEstado;
    }

    /**
     * @return the mantenerConductor
     */
    public Boolean getMantenerConductor() {
        return mantenerConductor;
    }

    /**
     * @param mantenerConductor the auxGuia to set
     */
    public void setMantenerConductor(Boolean mantenerConductor) {
        this.mantenerConductor = mantenerConductor;
    }

    /**
     * @return the deshabilitarFolio
     */
    public Boolean getDeshabilitarFolio() {
        return deshabilitarFolio;
    }

    /**
     * @param deshabilitarFolio the auxGuia to set
     */
    public void setDeshabilitarFolio(Boolean deshabilitarFolio) {
        this.deshabilitarFolio = deshabilitarFolio;
    }

    public int getFolioInicio() {
        return folioInicio;
    }

    public void setFolioInicio(int folioInicio) {
        this.folioInicio = folioInicio;
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

    @Override
    public Guia prepareCreate(ActionEvent event) {

        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.estadoGuiaDao = new IEstadoGuiaDaoImpl();
        this.selected = new Guia();
        this.selected.setGuiaFecha(fecha);
        this.boletoGuiaList = new ArrayList<>();
        this.getSelected().setGuiaIdCuenta(this.getUserCount());
        this.getSelected().setGuiaFechaIngreso(new Date());
        this.getSelected().setGuiaTotalIngresos(0);
        this.getSelected().setGuiaTotalEgresos(0);
        this.getSelected().setGuiaSaldo(0);
        this.getSelected().setGuiaIdCajaTerminal(this.cajaRecaudacionDao.findById(1));
        this.getSelected().setGuiaIdEstado(this.estadoGuiaDao.findById(1));
        this.getSelected().setGuiaRecaudada(Boolean.TRUE);
        this.getSelected().setGuiaNumeroVueltas(0);
        this.getSelected().setGuiaTurno(0);

        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (!this.getItems().isEmpty()) {

            for (Guia g : this.items) {
                this.ejbFacade.create(g);
            }
            int tamaño = this.items.size();
            this.items = new ArrayList<>();

            JsfUtil.addSuccessMessage("Se han creado " + tamaño + " Guías");
        } else {
            JsfUtil.addErrorMessage("Debe generar las guías");
        }

    }

    public void load() {
        this.guiaDao = new IGuiaDaoImpl();
        //this.setItems((List<Guia>) this.guiaDao.findByFechaGrupoServicio(this.grupoServicio, this.getFecha()));

        this.tarifaGrupoServicioDao = new ITarifaGrupoServicioDaoImpl();
        this.busDao = new IBusDaoImpl();
        this.busesList = this.busDao.findByGrupoServicio(this.grupoServicio);
        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.guiaDao = new IGuiaDaoImpl();

        CajaRecaudacion c = this.cajaRecaudacionDao.findById(1);

        this.setItems(new ArrayList<>());
        for (Bus b : this.busesList) {

            Guia g = new Guia();
            g.setGuiaFecha(new Date());
            g.setGuiaFolio(folioInicio);
            g.setGuiaFechaIngreso(new Date());
            g.setGuiaIdBus(b);
            g.setGuiaIdCajaTerminal(c);
            g.setGuiaIdCuenta(this.getUserCount());
            folioInicio++;

            Guia aux = this.guiaDao.findLastGuiaByBusFecha(b, fecha);

            if (aux != null) {
                g.setGuiaIdEstado(aux.getGuiaIdEstado());
                g.setGuiaIdTrabajador(aux.getGuiaIdTrabajador());
            }

            this.getItems().add(g);
        }
        
        this.model = new GuiaGrupalDataModel(this.items);
    }

    public void findFolio() {
        if (this.getSelected() != null) {
            Guia auxGuiaFolio = this.guiaDao.findByCuentaFolio(this.getUserCount(), this.getSelected().getGuiaFolio());

            if (auxGuiaFolio != null) {
                JsfUtil.addErrorMessage("La Guía se encuentra ingresada");
                JsfUtil.isValidationFailed();
                this.getSelected().setGuiaFolio(0);
            }
        }
    }

    private List<Bus> busesProceso(ProcesoRecaudacion procesoRecaudacion) {
        this.busDao = new IBusDaoImpl();
        return this.busDao.findByProceso(procesoRecaudacion);
    }

}
