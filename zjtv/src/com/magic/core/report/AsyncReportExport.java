package com.magic.core.report;

import com.magic.commons.utils.DateUtils;
import com.magic.commons.utils.PropertiesUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by YinJianFeng on 14-6-15.
 */
public class AsyncReportExport implements Runnable {
    private String reportFile;
    private Map<String, Object> reportParamMap;

    private BaseReportListener listener;

    public AsyncReportExport(String reportFile, Map<String, Object> reportParamMap) {
        this.reportFile = reportFile;
        this.reportParamMap = reportParamMap;
    }

    public void setListener(BaseReportListener listener) {
        this.listener = listener;
        this.listener.setParamMap(reportParamMap);
    }

    @Override
    public void run() {
        if (listener != null) {
            listener.onStart();
        }
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        InputStream reportis = classpathResourceLoader.getResourceStream("reports/" + reportFile + ".jasper");
        try {
            PropertiesUtils configProperty = PropertiesUtils.getInstance("config");
            String reportDir = configProperty.getValue("com.magic.report.dir");

            PropertiesUtils databaseProperty = PropertiesUtils.getInstance("db");
            String driverName = databaseProperty.getValue("com.magic.db.driver_class");
            String url = databaseProperty.getValue("com.magic.db.url");
            String username = databaseProperty.getValue("com.magic.db.username");
            String password = databaseProperty.getValue("com.magic.db.password");
            Class.forName(driverName);
            Connection jdbcConnection = DriverManager.getConnection(url, username, password);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportis, reportParamMap, jdbcConnection);
            jdbcConnection.close();


            String docFileName = reportDir + DateUtils.getDate().getTime() + ".docx";

            JRDocxExporter jrDocxExporter = new JRDocxExporter();
            jrDocxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            jrDocxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(docFileName));
            jrDocxExporter.exportReport();

            if (listener != null) {
                listener.onFinished(docFileName);
            }
        } catch (JRException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
