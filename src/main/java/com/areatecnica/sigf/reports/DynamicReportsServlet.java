/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ianfr
 */
public class DynamicReportsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private PdfReportController dynamicReportsManagedBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/pdf");

        OutputStream out = resp.getOutputStream();

        out = dynamicReportsManagedBean.getOS(getServletContext(), out);

        out.close();

    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);

        this.doGet(req, resp);

    }

    public void setDynamicReportsManagedBean(
            PdfReportController dynamicReportsManagedBean) {

        this.dynamicReportsManagedBean = dynamicReportsManagedBean;

    }

    public PdfReportController getDynamicReportsManagedBean() {

        return dynamicReportsManagedBean;

    }

}
