/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;

/**
 *
 * @author ianfranco
 */
@Named(value = "pdfReportController")
@SessionScoped
public class PdfReportController implements Serializable {
    
     private JRMapArrayDataSource data;
    
    /**
     * Creates a new instance of PdfReportController
     */
    public PdfReportController() {
    }
    
    public void init(){
        JasperReportBuilder report = DynamicReports.report();
        report.setDataSource(data);
        
    }

    public JRMapArrayDataSource getData() {
        return data;
    }

    public void setData(JRMapArrayDataSource data) {
        this.data = data;
    }
    
    
    
}
