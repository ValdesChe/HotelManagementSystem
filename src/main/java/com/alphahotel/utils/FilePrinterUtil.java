package com.alphahotel.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.export.*;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-24.
 */
public class FilePrinterUtil {

    public static void generateFile(String typeFile, String jasperDir, String fileName, HashMap<String, Object> params, JRBeanCollectionDataSource beanCollectionDataSource)
            throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperDir));
        JasperPrint jasperPrint;
        if(beanCollectionDataSource == null){
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), params, new JREmptyDataSource());
        }
        else{
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), params, beanCollectionDataSource);
        }

        List<JasperPrint> jasperPrints = new ArrayList<>();
        jasperPrints.add(jasperPrint);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();

        byte[] bytes = loadHttpServletResponse(response, jasper, typeFile, fileName, params);

        ServletOutputStream stream = response.getOutputStream();

        switch (typeFile) {
            case "PDF":
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                break;
            case "LOAD_PDF":
                stream.write(bytes, 0, bytes.length);
                break;
            case "PPT":
                exportReportToStream(jasperPrints, stream, new JRPptxExporter(), new SimplePptxExporterConfiguration());
                break;
            case "XLS":
                exportReportToStream(jasperPrints, stream, new JRXlsExporter(), new SimpleXlsxExporterConfiguration());
                break;
            case "DOC":
                exportReportToStream(jasperPrints, stream, new JRDocxExporter(), new SimpleDocxExporterConfiguration());
                break;
        }

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void exportReportToStream(List<JasperPrint> jasperPrints, ServletOutputStream stream,
                                      JRAbstractExporter exporter, SimpleExporterConfiguration configuration) throws JRException {
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    private static byte[] loadHttpServletResponse(final HttpServletResponse response, File jasper, String typeFile,
                                                  String fileName, HashMap<String, Object> params) throws JRException {
        byte[] bytes = null;

        if (typeFile.equalsIgnoreCase("LOAD_PDF")) {
            bytes = JasperRunManager.runReportToPdf(jasper.getPath(), params, new JREmptyDataSource());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
        } else {
            response.addHeader("Content-disposition", "attachment; filename=".concat(fileName));
        }

        return bytes;
    }

}
/*JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOfItems);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/commercial/invoice.jasper");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = FacesContextUtil.getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=Facture.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();*/