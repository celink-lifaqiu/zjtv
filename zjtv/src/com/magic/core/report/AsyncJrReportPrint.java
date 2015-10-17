package com.magic.core.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import java.io.InputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 14-2-9
 * Time: 下午1:28
 * To change this template use File | Settings | File Templates.
 */
public class AsyncJrReportPrint implements Runnable {
    private String  reportFile, printerName;
    private Map<String, Object> reportParamMap;
    private JRDataSource dataSource;

    public AsyncJrReportPrint(String reportFile, String printerName,
                              Map<String, Object> reportParamMap, JRDataSource dataSource) {
        this.reportFile = reportFile;
        this.printerName = printerName;
        this.reportParamMap = reportParamMap;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        InputStream reportis = classpathResourceLoader.getResourceStream("reports/"+reportFile+".jasper");
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportis, reportParamMap, dataSource);

            PrintService printService = null;
            if (printerName.equals("default")) {
                printService = PrintServiceLookup.lookupDefaultPrintService();
            } else {
                PrintService[] printlist = PrintServiceLookup.lookupPrintServices(null, null);
                for (int i = 0; i < printlist.length; i++) {
                    if (printerName.equals(printlist[i].getName())) {
                        printService = printlist[i];
                        break;
                    }
                }
            }

            if (printService == null) return;

            JRPrintServiceExporter pse=new JRPrintServiceExporter();
            pse.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
//            printServiceAttributeSet.add(new PrinterName("JFeng_Printer", null)); //设定哪台打印机打印
            pse.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            pse.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            pse.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
            pse.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
            pse.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
