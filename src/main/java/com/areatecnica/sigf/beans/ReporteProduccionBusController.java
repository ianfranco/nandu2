/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoGuia;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import com.areatecnica.sigf.reports.PdfReportController;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.joda.time.LocalDate;

/**
 *
 * @author ianfr
 */
@Named(value = "reporteProduccionbusController")
@ViewScoped
public class ReporteProduccionBusController extends AbstractController<Guia> {

    @Inject
    private PdfReportController pdfReportController;
    private IGuiaDao dao;
    private List<Egreso> egresosList;
    private List<EgresoGuia> egresoGuiaList;
    private ArrayList<LinkedHashMap> listOfMaps;
    private LinkedHashMap guiaLink;
    private LinkedHashMap totals;
    private List<String> resultsHeader;
    private List<String> resultsTotals;
    private Map folios;
    private List<Bus> itemsBus;
    private List<CajaRecaudacion> cajaRecaudacionList;
    private List<Guia> items;
    private Bus bus;
    private Date from;
    private Date to;
    private int mes;
    private int anio;
    private int resumenTotal;
    private String resumenTotalFormat;
    private String stringDate;
    private Calendar calendar;
    private static String pattern = "###,###.###";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates a new instance of ResumenDinerosController
     */
    public ReporteProduccionBusController() {
        super(Guia.class);
    }

    @PostConstruct
    @Override
    public void init() {

        calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(Calendar.DATE, 1);
        this.setFrom(calendar.getTime());

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        this.setTo(calendar.getTime());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        this.itemsBus = this.getCurrentUser().getUsuarioIdTerminal().getBusList();
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
     * @return the from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Date from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Date getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Date to) {
        this.to = to;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the resumenTotal
     */
    public int getResumenTotal() {
        return resumenTotal;
    }

    /**
     * @param resumenTotal the resumenTotal to set
     */
    public void setResumenTotal(int resumenTotal) {
        this.resumenTotal = resumenTotal;
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

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Bus> getItemsBus() {
        return itemsBus;
    }

    public void setItemsBus(List<Bus> itemsBus) {
        this.itemsBus = itemsBus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @Override
    public Collection<Guia> getItems() {
        return this.items;
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

    public void load() {

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, mes - 1);
        this.setFrom(calendar.getTime());

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        this.setTo(calendar.getTime());

        LocalDate fechaInicio = new LocalDate(this.from);
        LocalDate fechaFinal = fechaInicio.dayOfMonth().withMaximumValue();

        this.egresosList = new ArrayList<>();
        this.setListOfMaps(new ArrayList<LinkedHashMap>());
        this.setFolios(new HashMap<Integer, Integer>());
        this.setResultsHeader(new ArrayList<String>());
        this.resultsTotals = new ArrayList<>();
        this.totals = new LinkedHashMap();

        for (EgresoProcesoRecaudacion epr : this.bus.getBusIdProcesoRecaudacion().getEgresoProcesoRecaudacionList()) {
            Egreso egreso = epr.getEgresoProcesoRecaudacionIdEgreso();
            egreso.setEgresoValorDefecto(epr.getEgresoProcesoRecaudacionValorDefecto());
            egreso.setEgresoPorcentaje(epr.getEgresoProcesoRecaudacionPorcentaje());
            this.egresosList.add(egreso);
        }

        this.dao = new IGuiaDaoImpl();

        this.items = this.dao.findByBusBetweenFechaRecaudacion(bus, fechaInicio.toDate(), fechaFinal.toDate());

        if (!this.items.isEmpty()) {
            /*Proceso de carga de egresos a partir de cada guía*/
            for (Guia g : this.items) {
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

        }

    }

    public void exportPdf(ActionEvent event) {

        try {
            this.pdfReportController.PDF(event);
        } catch (JRException ex) {
            Logger.getLogger(DigitacionGuiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DigitacionGuiaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void exportDoc(ActionEvent event) {

        try {
            this.pdfReportController.DOCX(event);
        } catch (JRException ex) {
            Logger.getLogger(DigitacionGuiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DigitacionGuiaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
