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
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.SerieBoletoGuia;
import com.areatecnica.sigf.entities.TarifaGrupoServicio;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.models.GuiaBoletoDataModel;
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

@Named(value = "guiaFirstController")
@ViewScoped
public class GuiaFirstController extends AbstractController<Guia> {

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
    private GuiaBoletoDataModel guiaModel;
    private SerieBoletoGuiaDataModel model;
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
    private Guia auxGuia2;
    private int maxFolio;
    private GrupoServicio grupoServicio;
    private Date fecha;
    private String formatFecha;

    private final static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

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

        
        
        //this.setItems((List<Guia>) this.guiaDao.findByCuentaFecha(this.getUserCount(), this.getFecha()));
        this.grupoServicioDao = new IGrupoServicioDaoImpl();
        this.setGrupoServicioList((List<GrupoServicio>) this.grupoServicioDao.findByTerminal(this.getCurrentUser().getUsuarioIdTerminal()));
        //this.setBusesList(busesProceso(this.procesoRecaudacion));
    }

    public GuiaFirstController() {
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
     * @return the guiaModel
     */
    public GuiaBoletoDataModel getGuiaModel() {
        return guiaModel;
    }

    /**
     * @param guiaModel the model to set
     */
    public void setGuiaModel(GuiaBoletoDataModel guiaModel) {
        this.guiaModel = guiaModel;
    }

    /**
     * @return the model
     */
    public SerieBoletoGuiaDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(SerieBoletoGuiaDataModel model) {
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
     * @return the auxGuia
     */
    public Guia getAuxGuia2() {
        return auxGuia2;
    }

    /**
     * @param auxGuia the auxGuia to set
     */
    public void setAuxGuia2(Guia auxGuia2) {
        this.auxGuia2 = auxGuia2;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        guiaIdBusController.setSelected(null);
        guiaIdEstadoController.setSelected(null);
        guiaIdCajaTerminalController.setSelected(null);
        guiaIdTrabajadorController.setSelected(null);
        this.auxGuia2 = null;
        this.selected = null;
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
        this.auxGuia2 = new Guia();
        this.auxGuia2.setGuiaFecha(fecha);
        this.boletoGuiaList = new ArrayList<>();
        this.auxGuia2.setGuiaIdCuenta(this.getUserCount());
        this.auxGuia2.setGuiaFechaIngreso(new Date());
        this.auxGuia2.setGuiaTotalIngresos(0);
        this.auxGuia2.setGuiaTotalEgresos(0);
        this.auxGuia2.setGuiaSaldo(0);
        this.auxGuia2.setGuiaIdCajaTerminal(this.cajaRecaudacionDao.findById(1));
        this.auxGuia2.setGuiaIdEstado(this.estadoGuiaDao.findById(1));
        this.auxGuia2.setGuiaRecaudada(Boolean.FALSE);
        this.auxGuia2.setGuiaNumeroVueltas(0);
        this.auxGuia2.setGuiaTurno(0);

        this.setSelected(null);
        return this.auxGuia2;
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.auxGuia2 != null) {
            this.auxGuia2.setSerieBoletoGuiaList(this.boletoGuiaList);
            this.ejbFacade.create(this.auxGuia2);
        }
        this.items.add(this.auxGuia2);
        this.boletoGuiaList = null;
        JsfUtil.addSuccessMessage("Se ha ingresado la Nueva Guía al Bus N°: " + this.auxGuia2.getGuiaIdBus().getBusNumero() + " " + this.auxGuia2.getGuiaIdBus().getBusIdUnidadNegocio().getUnidadNegocioNumero());
        this.setSelected(prepareCreate(event));

    }

    @Override
    public void save(ActionEvent event) {
        if (this.auxGuia2 != null) {
            //this.auxGuia2.setSerieBoletoGuiaList(this.boletoGuiaList);
            this.ejbFacade.edit(this.auxGuia2);

            for (SerieBoletoGuia sb : this.boletoGuiaList) {
                sb.setSerieBoletoGuiaNumeroVuelta(sb.getSerieBoletoGuiaNumeroVuelta()+1);
                this.serieBoletoGuiaFacade.edit(sb);
            }

        }
        
        this.boletoGuiaList = null;
        JsfUtil.addSuccessMessage("Se ha ingresado la Nueva Guía al Bus N°: " + this.getSelected().getGuiaIdBus().getBusNumero() + " " + this.getSelected().getGuiaIdBus().getBusIdUnidadNegocio().getUnidadNegocioNumero());
        this.setSelected(null);
        this.auxGuia2 = null;
    }

    public void load() {
        this.guiaDao = new IGuiaDaoImpl();
        this.setItems((List<Guia>) this.guiaDao.findByFechaGrupoServicio(this.grupoServicio, this.getFecha()));

        this.tarifaGrupoServicioDao = new ITarifaGrupoServicioDaoImpl();
        this.busDao = new IBusDaoImpl();
        this.busesList = this.busDao.findByGrupoServicio(this.grupoServicio);

        this.guiaModel = new GuiaBoletoDataModel(items);
    }

    public void loadGuia() {
        if (this.getSelected() != null) {
            System.err.println("NO ES NULA LA GUIA ");
            this.auxGuia2 = this.getSelected();

            this.boletoGuiaList = this.auxGuia2.getSerieBoletoGuiaList();
            this.setSelected(null);
        }
    }

    public void findFolio() {
        if (this.auxGuia2 != null) {
            Guia auxGuiaFolio = this.guiaDao.findByCuentaFolio(this.getUserCount(), this.auxGuia2.getGuiaFolio());

            if (auxGuiaFolio != null) {
                JsfUtil.addErrorMessage("La Guía se encuentra ingresada");
                JsfUtil.isValidationFailed();
                this.auxGuia2.setGuiaFolio(0);
            }
        }
    }

    public void handleBusChange(ActionEvent event) {
        if (this.auxGuia2.getGuiaIdBus() != null) {

            DateTime dateTime = new DateTime(this.fecha);

            setAuxGuia(this.guiaDao.findLastGuiaByBusFecha(this.auxGuia2.getGuiaIdBus(), dateTime.plusDays(1).toDate()));

            if (getAuxGuia() != null) {
                DateTime d1 = new DateTime(getAuxGuia().getGuiaFecha());
                DateTime d2 = new DateTime(this.fecha);
                if (d1.toLocalDate().isEqual(d2.toLocalDate())) {
                    this.auxGuia2.setGuiaTurno(getAuxGuia().getGuiaTurno() + 1);
                    JsfUtil.addExclamationMessage("Existen Guías registradas a la fecha \nSe actualizó el N° de Turno a " + this.auxGuia2.getGuiaTurno());
                }

                this.ventaBoletoDao = new IVentaBoletoDaoImpl();
                this.setVentaBoletoBusList((List<VentaBoleto>) this.ventaBoletoDao.findByBusEstado(this.auxGuia2.getGuiaIdBus()));

                this.boletoGuiaList = new ArrayList<>();

                if (getAuxGuia().getSerieBoletoGuiaList().size() > 0) {
                    for (SerieBoletoGuia s : getAuxGuia().getSerieBoletoGuiaList()) {
                        SerieBoletoGuia newSerie = new SerieBoletoGuia();
                        newSerie.setSerieBoletoGuiaFechaIngreso(new Date());
                        newSerie.setSerieBoletoGuiaIdGuia(this.auxGuia2);
                        newSerie.setSerieBoletoGuiaInicio(s.getSerieBoletoGuiaTermino());
                        newSerie.setSerieBoletoGuiaTotal(0);
                        newSerie.setSerieBoletoGuiaValor((int) this.getValorTarifasMap().get(s.getSerieBoletoGuiaIdVentaBoleto().getVentaBoletoIdInventarioCaja().getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto()));
                        newSerie.setSerieBoletoGuiaNumeroVuelta(1);
                        newSerie.setSerieBoletoGuiaIdVentaBoleto(s.getSerieBoletoGuiaIdVentaBoleto());
                        this.boletoGuiaList.add(newSerie);
                    }
                } else {
                    JsfUtil.addExclamationMessage("No se han encontrado series de boletos, debe completar la información");

                    List<TarifaGrupoServicio> auxList = this.tarifaGrupoServicioDao.findAllByCuenta();

                    this.boletoGuiaList = new ArrayList<>();

                    for (TarifaGrupoServicio t : auxList) {
                        SerieBoletoGuia s = new SerieBoletoGuia();
                        s.setSerieBoletoGuiaFechaIngreso(new Date());
                        s.setSerieBoletoGuiaIdGuia(this.auxGuia2);
                        s.setSerieBoletoGuiaInicio(1);
                        s.setSerieBoletoGuiaEsNuevo(Boolean.TRUE);
                        s.setSerieBoletoGuiaIdVentaBoleto((VentaBoleto) getDefaultVentaBoletoMap().get(t.getTarifaGrupoServicioIdBoleto()));
                        s.setSerieBoletoGuiaValor(t.getTarifaGrupoServicioValor());
                        s.setSerieBoletoGuiaNumeroVuelta(1);

                        this.boletoGuiaList.add(s);
                    }

                    this.setModel(new SerieBoletoGuiaDataModel(boletoGuiaList));
                }

            } else {
                this.auxGuia2.setGuiaTurno(1);
                JsfUtil.addExclamationMessage("No se han encontrado Guías para el Bus seleccionado, debe completar la información de los boletos");

                List<TarifaGrupoServicio> auxList = this.tarifaGrupoServicioDao.findAllByCuenta();

                this.boletoGuiaList = new ArrayList<>();
                //this.getSelected().setSerieBoletoGuiaList(boletoGuiaList);

                for (TarifaGrupoServicio t : auxList) {
                    SerieBoletoGuia s = new SerieBoletoGuia();
                    s.setSerieBoletoGuiaFechaIngreso(new Date());
                    s.setSerieBoletoGuiaIdGuia(this.auxGuia2);
                    s.setSerieBoletoGuiaInicio(1);
                    s.setSerieBoletoGuiaEsNuevo(Boolean.TRUE);
                    s.setSerieBoletoGuiaIdVentaBoleto((VentaBoleto) getDefaultVentaBoletoMap().get(t.getTarifaGrupoServicioIdBoleto()));
                    s.setSerieBoletoGuiaValor(t.getTarifaGrupoServicioValor());
                    s.setSerieBoletoGuiaNumeroVuelta(1);

                    this.boletoGuiaList.add(s);
                }

                this.setModel(new SerieBoletoGuiaDataModel(boletoGuiaList));
            }
        }
    }

    private List<Bus> busesProceso(ProcesoRecaudacion procesoRecaudacion) {
        this.busDao = new IBusDaoImpl();
        return this.busDao.findByProceso(procesoRecaudacion);
    }

}
