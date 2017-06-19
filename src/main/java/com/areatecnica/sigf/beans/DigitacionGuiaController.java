package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.EgresoGuiaFacade;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.controllers.GuiaFacade;
import com.areatecnica.sigf.controllers.ResumenRecaudacionFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IEstadoGuiaDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.IProcesoRecaudacionDao;
import com.areatecnica.sigf.dao.IResumenRecaudacionDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IEstadoGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IResumenRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoGuia;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.EgresoRecaudacion;
import com.areatecnica.sigf.entities.EstadoGuia;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import com.areatecnica.sigf.models.DigitacionGuiaDataModel;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "digitacionGuiaController")
@ViewScoped
public class DigitacionGuiaController extends AbstractController<Guia> {

    @Inject
    private GuiaFacade ejbFacade;
    @Inject
    private EgresoGuiaFacade egresoGuiaFacade;
    @Inject
    private BusController guiaIdBusController;
    @Inject
    private EstadoGuiaController guiaIdEstadoController;
    @Inject
    private CajaRecaudacionController guiaIdCajaTerminalController;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;
    @Inject
    private ResumenRecaudacionFacade resumenRecaudacionFacade;

    private List<Guia> list;
    private List<Egreso> egresosList;
    private List<EgresoGuia> egresoGuiaList;
    private List<ProcesoRecaudacion> procesoRecaudacionList;
    private List<CajaRecaudacion> cajaRecaudacionList;
    private List<PorcentajeHelper> porcentajesList;
    private List<EgresoRecaudacion> egresosResumenList;
    private List<Bus> busesList;
    private ArrayList<LinkedHashMap> listOfMaps;
    private LinkedHashMap guiaLink;
    private LinkedHashMap totals;
    private List<String> resultsHeader;
    private List<String> resultsTotals;
    private Map folios;
    private ProcesoRecaudacion procesoRecaudacion;
    private ResumenRecaudacion resumenRecaudacion;
    private CajaRecaudacion cajaRecaudacion;
    private EstadoGuia estadoGuia;
    private Date fechaRecaudacion;
    private Boolean permitirEgresoFlota;
    private Boolean permitirEgresoBus;
    private Boolean permitirEgresoProceso;
    private Boolean estadoProceso;
    private int numeroVueltas;
    private int resumenTotal;
    private String resumenTotalFormat;
    private IProcesoRecaudacionDao procesoDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IEstadoGuiaDao estadoGuiaDao;
    private IGuiaDao guiaDao;
    private IBusDao busDao;
    private IResumenRecaudacionDao resumenRecaudacionDao;

    private DigitacionGuiaDataModel model;

    private static String pattern = "###,###.###";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);

    /**
     * Initialize the concrete Guia controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.cajaRecaudacionList = this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser());
        this.estadoGuiaDao = new IEstadoGuiaDaoImpl();
        this.setEstadoGuia(this.estadoGuiaDao.findById(1));

        this.permitirEgresoBus = Boolean.TRUE;
        this.permitirEgresoFlota = Boolean.TRUE;
        this.permitirEgresoProceso = Boolean.TRUE;
        this.resumenRecaudacion = new ResumenRecaudacion();
        this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.FALSE);
        this.estadoProceso = Boolean.FALSE;
    }

    public DigitacionGuiaController() {
        // Inform the Abstract parent controller of the concrete Guia Entity
        super(Guia.class);
        this.fechaRecaudacion = new Date();
    }

    /**
     * @return the egresoGuiaList
     */
    public List<EgresoGuia> getEgresoGuiaList() {
        return egresoGuiaList;
    }

    /**
     * @param egresoGuiaList the egresoGuiaList to set
     */
    public void setEgresoGuiaList(List<EgresoGuia> egresoGuiaList) {
        this.egresoGuiaList = egresoGuiaList;
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
     * @return the procesoRecaudacion
     */
    public ProcesoRecaudacion getProcesoRecaudacion() {
        return procesoRecaudacion;
    }

    /**
     * @param procesoRecaudacion the procesoRecaudacion to set
     */
    public void setProcesoRecaudacion(ProcesoRecaudacion procesoRecaudacion) {
        this.procesoRecaudacion = procesoRecaudacion;
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
     * @return the list
     */
    public List<Guia> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Guia> list) {
        this.list = list;
    }

    /**
     * @return the fechaRecaudacion
     */
    public Date getFechaRecaudacion() {
        return fechaRecaudacion;
    }

    /**
     * @param fechaRecaudacion the fechaRecaudacion to set
     */
    public void setFechaRecaudacion(Date fechaRecaudacion) {
        this.fechaRecaudacion = fechaRecaudacion;
    }

    /**
     * @return the egresosList
     */
    public List<Egreso> getEgresosList() {
        return egresosList;
    }

    /**
     * @param egresosList the egresosList to set
     */
    public void setEgresosList(List<Egreso> egresosList) {
        this.egresosList = egresosList;
    }

    /**
     * @return the procesoRecaudacionList
     */
    public List<ProcesoRecaudacion> getProcesoRecaudacionList() {
        return procesoRecaudacionList;
    }

    /**
     * @param procesoRecaudacionList the procesoRecaudacionList to set
     */
    public void setProcesoRecaudacionList(List<ProcesoRecaudacion> procesoRecaudacionList) {
        this.procesoRecaudacionList = procesoRecaudacionList;
    }

    /**
     * @return the permitirEgresoFlota
     */
    public Boolean getPermitirEgresoFlota() {
        return permitirEgresoFlota;
    }

    /**
     * @param permitirEgresoFlota the permitirEgresoFlota to set
     */
    public void setPermitirEgresoFlota(Boolean permitirEgresoFlota) {
        this.permitirEgresoFlota = permitirEgresoFlota;
    }

    /**
     * @return the permitirEgresoBus
     */
    public Boolean getPermitirEgresoBus() {
        return permitirEgresoBus;
    }

    /**
     * @param permitirEgresoBus the permitirEgresoBus to set
     */
    public void setPermitirEgresoBus(Boolean permitirEgresoBus) {
        this.permitirEgresoBus = permitirEgresoBus;
    }

    /**
     * @return the permitirEgresoProceso
     */
    public Boolean getPermitirEgresoProceso() {
        return permitirEgresoProceso;
    }

    /**
     * @param permitirEgresoProceso the permitirEgresoProceso to set
     */
    public void setPermitirEgresoProceso(Boolean permitirEgresoProceso) {
        this.permitirEgresoProceso = permitirEgresoProceso;
    }

    /**
     * @return the numeroVueltas
     */
    public int getNumeroVueltas() {
        return numeroVueltas;
    }

    /**
     * @param numeroVueltas the numeroVueltas to set
     */
    public void setNumeroVueltas(int numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    /**
     * @return the listOfMaps
     */
    public ArrayList<LinkedHashMap> getListOfMaps() {
        return listOfMaps;
    }

    /**
     * @param listOfMaps the listOfMaps to set
     */
    public void setListOfMaps(ArrayList<LinkedHashMap> listOfMaps) {
        this.listOfMaps = listOfMaps;
    }

    /**
     * @return the totals
     */
    public LinkedHashMap getTotals() {
        return totals;
    }

    /**
     * @param totals the totals to set
     */
    public void setTotals(LinkedHashMap totals) {
        this.totals = totals;
    }

    /**
     * @return the resultsHeader
     */
    public List<String> getResultsHeader() {
        return resultsHeader;
    }

    /**
     * @param resultsHeader the resultsHeader to set
     */
    public void setResultsHeader(List<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    /**
     * @return the resultsTotals
     */
    public List<String> getResultsTotals() {
        return resultsTotals;
    }

    /**
     * @param resultsTotals the resultsTotals to set
     */
    public void setResultsTotals(List<String> resultsTotals) {
        this.resultsTotals = resultsTotals;
    }

    /**
     * @return the folios
     */
    public Map getFolios() {
        return folios;
    }

    /**
     * @param folios the folios to set
     */
    public void setFolios(Map folios) {
        this.folios = folios;
    }

    /**
     * @return the model
     */
    public DigitacionGuiaDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(DigitacionGuiaDataModel model) {
        this.model = model;
    }

    /**
     * @return the egresosResumenList
     */
    public List<EgresoRecaudacion> getEgresosResumenList() {
        return egresosResumenList;
    }

    /**
     * @param egresosResumenList the egresosResumenList to set
     */
    public void setEgresosResumenList(List<EgresoRecaudacion> egresosResumenList) {
        this.egresosResumenList = egresosResumenList;
    }

    /**
     * @return the resumenRecaudacion
     */
    public ResumenRecaudacion getResumenRecaudacion() {
        return resumenRecaudacion;
    }

    /**
     * @param resumenRecaudacion the resumenRecaudacion to set
     */
    public void setResumenRecaudacion(ResumenRecaudacion resumenRecaudacion) {
        this.resumenRecaudacion = resumenRecaudacion;
    }

    /**
     * @return the estadoGuia
     */
    public EstadoGuia getEstadoGuia() {
        return estadoGuia;
    }

    /**
     * @param estadoGuia the estadoGuia to set
     */
    public void setEstadoGuia(EstadoGuia estadoGuia) {
        this.estadoGuia = estadoGuia;
    }

    /**
     * @return the estadoProceso
     */
    public Boolean getEstadoProceso() {
        return estadoProceso;
    }

    /**
     * @param estadoProceso the estadoProceso to set
     */
    public void setEstadoProceso(Boolean estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    /**
     * @return the resumenTotalFormat
     */
    public String getResumenTotalFormat() {
        return resumenTotalFormat;
    }

    /**
     * @param resumenTotalFormat the resumenTotalFormat to set
     */
    public void setResumenTotalFormat(String resumenTotalFormat) {
        this.resumenTotalFormat = resumenTotalFormat;
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
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.egresoGuiaList = egresosGuia(this.getSelected());
        this.getSelected().setGuiaIdCuenta(this.getUserCount());
        this.getSelected().setGuiaRecaudacion(this.fechaRecaudacion);
        this.getSelected().setGuiaFecha(this.fechaRecaudacion);
        this.getSelected().setGuiaFechaIngreso(new Date());
        this.getSelected().setGuiaTotalIngresos(0);
        this.getSelected().setGuiaTotalEgresos(0);
        this.getSelected().setGuiaSaldo(0);
        this.getSelected().setGuiaIdCajaTerminal(this.cajaRecaudacion);
        this.getSelected().setGuiaIdEstado(getEstadoGuia());
        this.getSelected().setGuiaRecaudada(Boolean.TRUE);
        this.getSelected().setGuiaNumeroVueltas(getNumeroVueltas());
        this.getSelected().setGuiaTurno(0);
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {

        this.getSelected().setEgresoGuiaList(egresoGuiaList);
        this.ejbFacade.create(this.getSelected());

        /*
         * Agrega nueva fila a la tabla
         *
         */
        LinkedHashMap auxLink = new LinkedHashMap();
        for (EgresoGuia eg : this.getEgresoGuiaList()) {
            if (eg.getEgresoGuiaIdEgreso().getEgresoObligatorio()) {
                String key = eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso();

                if (totals.containsKey(key)) {
                    int aux = (int) totals.get(key);
                    aux += eg.getEgresoGuiaMonto();
                    totals.put(key, aux);
                } else {
                    totals.put(key, eg.getEgresoGuiaMonto());
                }
                auxLink.put(eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso(), eg.getEgresoGuiaMonto());
            }
        }

        this.getSelected().setLink(auxLink);
        this.getListOfMaps().add(this.getSelected().getLink());

        this.resultsTotals = new ArrayList<>();

        for (Object i : totals.values()) {
            this.resultsTotals.add(decimalFormat.format((int) i));
        }

        for (EgresoRecaudacion eg : this.egresosResumenList) {
            eg.setEgresoRecaudacionTotalEgreso((int) totals.get(eg.getEgresoRecaudacionIdEgreso().getEgresoNombreEgreso()));
        }

        /*
        this.resumenRecaudacion.setResumenRecaudacionTotal(this.resumenTotal);
        this.resumenRecaudacion.setResumenRecaudacionFechaActualizacion(new Date());

        this.resumenRecaudacionFacade.edit(resumenRecaudacion);*/
        this.list.add(this.getSelected());
        JsfUtil.addSuccessMessage("Se ha ingresado la Guia N°:" + this.getSelected().getGuiaFolio());

        this.setSelected(prepareCreate(event));
        this.setResumenTotalFormat(decimalFormat.format(setResumenTotal()));

        /*this.resumenRecaudacion.setEgresoRecaudacionList(egresosResumenList);
        this.resumenRecaudacionFacade.edit(this.resumenRecaudacion);*/
    }

    @Override
    public void save(ActionEvent event) {
        try {
            this.getSelected().setEgresoGuiaList(egresoGuiaList);
            this.ejbFacade.edit(this.getSelected());
            JsfUtil.addSuccessMessage("Se ha editado la Guia N°:" + this.getSelected().getGuiaFolio());

            LinkedHashMap auxLink = new LinkedHashMap();
            for (EgresoGuia eg : this.getEgresoGuiaList()) {
                if (eg.getEgresoGuiaIdEgreso().getEgresoObligatorio()) {
                    String key = eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso();

                    if (totals.containsKey(key)) {
                        int aux = (int) totals.get(key);
                        aux += eg.getEgresoGuiaMonto();
                        totals.put(key, aux);
                    } else {
                        totals.put(key, eg.getEgresoGuiaMonto());
                    }

                    auxLink.put(eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso(), eg.getEgresoGuiaMonto());
                }
            }
            this.getSelected().setLink(auxLink);
            this.listOfMaps.set(this.listOfMaps.indexOf(guiaLink), auxLink);
            this.setSelected(null);

            for (EgresoRecaudacion eg : this.egresosResumenList) {
                eg.setEgresoRecaudacionTotalEgreso((int) totals.get(eg.getEgresoRecaudacionIdEgreso().getEgresoNombreEgreso()));
                if (this.resultsTotals.contains(eg.getEgresoRecaudacionIdEgreso().getEgresoNombreEgreso())) {
                    //this.resultsTotals.set(this.resultsTotals.indexOf(eg.getEgresoRecaudacionIdEgreso().getEgresoNombreEgreso()), decimalFormat.format(eg.getEgresoRecaudacionTotalEgreso()));
                }
            }

            this.resultsTotals.clear();

            for (Object i : totals.values()) {
                this.resultsTotals.add(decimalFormat.format((int) i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setResumenTotalFormat(decimalFormat.format(setResumenTotal()));
    }

    @Override
    public void delete(ActionEvent event) {

        try {
            Guia auxGuia = this.getSelected();

            this.ejbFacade.remove(auxGuia);

            this.list.remove(this.getSelected());
            JsfUtil.addSuccessMessage("Se ha eliminado la Guia N°:" + auxGuia.getGuiaFolio());

            LinkedHashMap auxLink = new LinkedHashMap();
            for (EgresoGuia eg : this.getEgresoGuiaList()) {
                if (eg.getEgresoGuiaIdEgreso().getEgresoObligatorio()) {
                    String key = eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso();

                    if (totals.containsKey(key)) {
                        int aux = (int) totals.get(key);
                        aux -= eg.getEgresoGuiaMonto();
                        totals.put(key, aux);
                    }
                    auxLink.put(eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso(), eg.getEgresoGuiaMonto());
                }
            }

            this.listOfMaps.remove(this.listOfMaps.indexOf(guiaLink));
            this.setSelected(null);

            this.setResumenTotalFormat(decimalFormat.format(setResumenTotal()));

            for (EgresoRecaudacion eg : this.egresosResumenList) {
                eg.setEgresoRecaudacionTotalEgreso((int) totals.get(eg.getEgresoRecaudacionIdEgreso().getEgresoNombreEgreso()));
                //this.resultsTotals.set(this.resultsTotals.indexOf(eg.getEgresoRecaudacionIdEgreso().getEgresoNombreEgreso()), decimalFormat.format(eg.getEgresoRecaudacionTotalEgreso()));
            }
            this.resultsTotals.clear();
            for (Object i : totals.values()) {

                this.resultsTotals.add(decimalFormat.format((int) i));
            }

            this.resumenRecaudacion.setResumenRecaudacionTotal(this.resumenTotal);
            this.resumenRecaudacion.setResumenRecaudacionFechaActualizacion(new Date());

            this.resumenRecaudacionFacade.edit(resumenRecaudacion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setResumenTotalFormat(decimalFormat.format(setResumenTotal()));

    }

    private List<EgresoGuia> egresosGuia(Guia guia) {
        List<EgresoGuia> egresoGuiaAuxList = new ArrayList<>();
        this.porcentajesList = new ArrayList<PorcentajeHelper>();

        int i = 0;

        for (Egreso e : this.egresosList) {

            if (e.getEgresoActivo()) {
                EgresoGuia egresoGuia = new EgresoGuia();
                egresoGuia.setEgresoGuiaIdGuia(guia);
                egresoGuia.setEgresoGuiaIdEgreso(e);
                egresoGuia.setEgresoGuiaMonto(e.getEgresoValorDefecto());
                if (e.getEgresoPorcentaje().compareTo(BigDecimal.ZERO) == 1) {
                    this.porcentajesList.add(new PorcentajeHelper(e.getEgresoPorcentaje(), i));
                }

                egresoGuia.setEgresoGuiaRecaudado(Boolean.TRUE);
                egresoGuia.setEgresoGuiaFechaIngreso(new Date());
                egresoGuiaAuxList.add(egresoGuia);
            }

            i++;
        }

        return egresoGuiaAuxList;
    }

    private List<Bus> busesProceso(ProcesoRecaudacion procesoRecaudacion) {
        this.busDao = new IBusDaoImpl();
        return this.busDao.findByProceso(procesoRecaudacion);
    }

    public void handleCajaRecaudacionChange(ActionEvent event) {
        if (this.cajaRecaudacion != null) {
            this.procesoRecaudacionList = new ArrayList<ProcesoRecaudacion>();

            List<CajaProceso> cajaProcesoList = this.cajaRecaudacion.getCajaProcesoList();

            for (CajaProceso cp : cajaProcesoList) {
                if (cp.getCajaProcesoActivo()) {
                    this.procesoRecaudacionList.add(cp.getCajaProcesoIdProceso());
                }
            }
        }
    }

    public void handleBusChange(ActionEvent event) {
        if (this.getSelected().getGuiaIdBus() != null) {

            List<Guia> auxGuiaTurno = this.guiaDao.findByBusFecha(this.getSelected().getGuiaIdBus(), this.getSelected().getGuiaFecha());

            if (!auxGuiaTurno.isEmpty()) {
                if (auxGuiaTurno.size() == 1) {
                    this.getSelected().setGuiaTurno(2);
                } else {
                    this.getSelected().setGuiaTurno(auxGuiaTurno.get(0).getGuiaTurno() + 1);
                }
                JsfUtil.addExclamationMessage("Existen Guías registradas a la fecha (" + auxGuiaTurno.size() + ")\nSe actualizó el N° de Turno a " + this.getSelected().getGuiaTurno());
            } else {
                this.getSelected().setGuiaTurno(1);
            }
        }
    }

    public void handleFechaChange(ActionEvent event) {
        if (this.getSelected().getGuiaIdBus() != null) {

        }
    }

    public void load() {
        this.egresosList = new ArrayList<>();
        this.setListOfMaps(new ArrayList<LinkedHashMap>());
        this.setFolios(new HashMap<Integer, Integer>());
        this.setResultsHeader(new ArrayList<String>());
        this.resultsTotals = new ArrayList<>();
        this.resumenRecaudacion = new ResumenRecaudacion();
        this.totals = new LinkedHashMap();

        /*NOMBRE DE LAS COLUMNAS*/
        if (this.permitirEgresoProceso) {
            for (EgresoProcesoRecaudacion epr : this.procesoRecaudacion.getEgresoProcesoRecaudacionList()) {
                Egreso egreso = epr.getEgresoProcesoRecaudacionIdEgreso();
                egreso.setEgresoValorDefecto(epr.getEgresoProcesoRecaudacionValorDefecto());
                egreso.setEgresoPorcentaje(epr.getEgresoProcesoRecaudacionPorcentaje());
                this.egresosList.add(egreso);
            }
        }

        this.guiaDao = new IGuiaDaoImpl();
        this.list = this.guiaDao.findByProcesoFechaRecaudacion(procesoRecaudacion, fechaRecaudacion);
        JsfUtil.addSuccessMessage("Cantidad de Guías Registradas: " + this.list.size());

        if (!this.list.isEmpty()) {
            /*Proceso de carga de egresos*/
            for (Guia g : this.list) {
                List<EgresoGuia> auxEgresosGuia = g.getEgresoGuiaList();
                LinkedHashMap auxLink = new LinkedHashMap();

                for (EgresoGuia eg : auxEgresosGuia) {
                    if (eg.getEgresoGuiaIdEgreso().getEgresoObligatorio()) {
                        String key = eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso();
                        this.getResultsHeader().add(key);
                        if (totals.containsKey(key)) {
                            int aux = (int) totals.get(key);
                            aux += eg.getEgresoGuiaMonto();
                            totals.put(key, aux);
                        } else {
                            totals.put(key, eg.getEgresoGuiaMonto());
                        }
                        auxLink.put(eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso(), eg.getEgresoGuiaMonto());
                    }
                }
                g.setLink(auxLink);
                this.getListOfMaps().add(g.getLink());
            }

            for (Object i : totals.values()) {
                resultsTotals.add(decimalFormat.format((int) i));
            }

        } else {
            LinkedHashMap auxLink = new LinkedHashMap();

            for (Egreso eg : this.egresosList) {
                this.totals.put(eg.getEgresoNombreEgreso(), 0);
                this.resultsTotals.add("0");
                this.resultsHeader.add(eg.getEgresoNombreEgreso());
                auxLink.put(eg.getEgresoNombreEgreso(), 0);
            }

            this.listOfMaps.add(auxLink);
        }

        this.setModel(new DigitacionGuiaDataModel(list));

        this.setBusesList(busesProceso(this.procesoRecaudacion));
        JsfUtil.addSuccessMessage("N° de Buses en el Proceso: " + this.getBusesList().size());

        this.resumenRecaudacionDao = new IResumenRecaudacionDaoImpl();
        this.resumenRecaudacion = this.resumenRecaudacionDao.findByCajaProcesoDate(cajaRecaudacion, procesoRecaudacion, fechaRecaudacion);

        if (this.resumenRecaudacion == null) {

            this.resumenRecaudacion = new ResumenRecaudacion();
            this.resumenRecaudacion.setResumenRecaudacionFechaIngreso(new Date());
            this.resumenRecaudacion.setResumenRecaudacionFecha(fechaRecaudacion);
            this.resumenRecaudacion.setResumenRecaudacionIdCaja(cajaRecaudacion);
            this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.FALSE);
            this.resumenRecaudacion.setResumenRecaudacionIdProceso(procesoRecaudacion);
            this.resumenRecaudacion.setResumenRecaudacionTotal(0);

            System.err.println("entra a la condición");
            this.egresosResumenList = new ArrayList<>();
            for (Egreso eg : this.egresosList) {
                EgresoRecaudacion egresoRecaudacion = new EgresoRecaudacion();
                egresoRecaudacion.setEgresoRecaudacionIdResumen(resumenRecaudacion);
                egresoRecaudacion.setEgresoRecaudacionIdEgreso(eg);
                egresoRecaudacion.setEgresoRecaudacionTotalEgreso(0);

                this.egresosResumenList.add(egresoRecaudacion);

            }

            this.listOfMaps.add(new LinkedHashMap());
            this.resumenRecaudacion.setEgresoRecaudacionList(this.egresosResumenList);
            this.resumenRecaudacionFacade.create(this.resumenRecaudacion);

        } else {
            this.egresosResumenList = this.resumenRecaudacion.getEgresoRecaudacionList();

            if (this.egresosResumenList == null) {

            }

        }
        this.setResumenTotalFormat(decimalFormat.format(setResumenTotal()));
    }

    public void loadGuia() {
        if (this.getSelected() != null) {
            this.guiaLink = this.getSelected().getLink();
            this.egresoGuiaList = this.getSelected().getEgresoGuiaList();
            this.porcentajesList = new ArrayList<PorcentajeHelper>();

            for (EgresoGuia eg : this.egresoGuiaList) {
                int val = (int) this.totals.get(eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso());
                val -= eg.getEgresoGuiaMonto();
                this.totals.put(eg.getEgresoGuiaIdEgreso().getEgresoNombreEgreso(), val);
            }
        }
    }

    public void findFolio() {
        if (this.getSelected() != null) {
            Guia auxGuia = this.guiaDao.findByCuentaFolio(this.getUserCount(), this.getSelected().getGuiaFolio());

            if (auxGuia != null) {
                JsfUtil.addErrorMessage("La Guía se encuentra ingresada");
                JsfUtil.isValidationFailed();
                this.getSelected().setGuiaFolio(0);
            }
        }
    }

    public void setEgresosVariables() {
        int i = 0;
        for (PorcentajeHelper a : this.porcentajesList) {
            int var = (int) (a.getBd().floatValue() / 100 * this.getSelected().getGuiaTotalIngresos());
            egresoGuiaList.get(a.getIndex()).setEgresoGuiaMonto(var);
            i++;
        }
        setTotal();
    }

    public int setTotal() {
        int total = 0;
        for (EgresoGuia e : egresoGuiaList) {
            total += e.getEgresoGuiaMonto();
        }
        this.getSelected().setGuiaTotalEgresos(total);
        this.getSelected().setGuiaSaldo(this.getSelected().getGuiaTotalIngresos() - this.getSelected().getGuiaTotalEgresos());

        return total;
    }

    /**
     * @return the resumenTotal
     */
    public int getResumenTotal() {
        return resumenTotal;
    }

    public int setResumenTotal() {
        this.resumenTotal = 0;

        for (Object o : this.totals.values()) {
            this.resumenTotal += (int) o;
        }
        return this.getResumenTotal();
    }

    public void printResumen() {
        if (!this.estadoProceso) {
            JsfUtil.addSuccessMessage("Proceso Cerrado");
            this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.TRUE);
        } else {
            JsfUtil.addSuccessMessage("Proceso en Digitación");
            this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.FALSE);
        }

        this.resumenRecaudacion.setResumenRecaudacionTotal(this.resumenTotal);
        this.resumenRecaudacion.setResumenRecaudacionFechaActualizacion(new Date());
        this.resumenRecaudacionFacade.edit(resumenRecaudacion);
    }

    private class PorcentajeHelper {

        private BigDecimal bd;
        private Integer index;

        public PorcentajeHelper(BigDecimal bd, Integer index) {
            this.bd = bd;
            this.index = index;
        }

        public void setBd(BigDecimal bd) {
            this.bd = bd;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public BigDecimal getBd() {
            return bd;
        }

        public Integer getIndex() {
            return index;
        }

    }

}
