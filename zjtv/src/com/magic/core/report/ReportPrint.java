package com.magic.core.report;

import com.magic.commons.utils.PropertiesUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-31
 * Time: 上午12:05
 * To change this template use File | Settings | File Templates.
 */
public class ReportPrint {
    private String  reportFile, printerName;
    private Map<String, Object> reportParamMap;

    public ReportPrint(String reportFile, String printerName, Map<String, Object> reportParamMap) {
        this.reportFile = reportFile;
        this.printerName = printerName;
        this.reportParamMap = reportParamMap;
    }

    public void print(){
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        InputStream reportis = classpathResourceLoader.getResourceStream("reports/"+reportFile+".jasper");
        try {
            PropertiesUtils databaseProperty = PropertiesUtils.getInstance("db");
            String driverName = databaseProperty.getValue("com.magic.db.driver_class");
            String url = databaseProperty.getValue("com.magic.db.url");
            String username = databaseProperty.getValue("com.magic.db.username");
            String password = databaseProperty.getValue("com.magic.db.password");
            Class.forName(driverName);
            Connection jdbcConnection = DriverManager.getConnection(url, username, password);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportis, reportParamMap, jdbcConnection);
            jdbcConnection.close();


            PrintService printService = null;
            if (printerName.equals("default")) {
                printService = PrintServiceLookup.lookupDefaultPrintService();
            } else {
                PrintService[] printlist = PrintServiceLookup.lookupPrintServices(null, null);
                //printService=printlist[0];
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
