package com.magic.core.report;

import com.magic.commons.utils.DateUtils;
import com.magic.commons.utils.PropertiesUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by YinJianFeng on 14-6-15.
 */
public class AsyncJrReportExport implements Runnable {
    Logger logger = LoggerFactory.getLogger(getClass());

    private String  reportFile;
    private Map<String, Object> reportParamMap;
    private JRDataSource dataSource;

    private BaseReportListener listener;

    public AsyncJrReportExport(String reportFile, Map<String, Object> reportParamMap, JRDataSource dataSource) {
        this.reportFile = reportFile;
        this.reportParamMap = reportParamMap;
        this.dataSource = dataSource;
    }

    public void setListener(BaseReportListener listener) {
        this.listener = listener;
        this.listener.setParamMap(reportParamMap);
    }

    @Override
    public void run() {
        logger.info(Thread.currentThread().getId()+": Start Run Report ...");
        if (listener != null) {
            listener.onStart();
        }
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        InputStream reportis = classpathResourceLoader.getResourceStream("reports/"+reportFile+".jasper");
        try {
            logger.info(Thread.currentThread().getId()+": Fill Report ");

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportis, reportParamMap, dataSource);

//            exportDoc(jasperPrint);
            exportXls(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private synchronized void exportDoc(JasperPrint jasperPrint) throws JRException {
        PropertiesUtils configProperty = PropertiesUtils.getInstance("config");
        String reportDir = configProperty.getValue("com.magic.report.dir");
        String docFileName = reportDir+ DateUtils.getDate().getTime()+".docx";

        logger.info(Thread.currentThread().getId()+": Ready to Export Report...");
        logger.info(Thread.currentThread().getId()+": File Name["+docFileName+"]");
        JRDocxExporter jrDocxExporter = new JRDocxExporter();
        jrDocxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        jrDocxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(docFileName));
        jrDocxExporter.exportReport();

        logger.info(Thread.currentThread().getId()+": Export Finished!");
        if (listener != null) {
            listener.onFinished(docFileName);
        }
    }

    private synchronized void exportXls(JasperPrint jasperPrint) throws JRException {
        PropertiesUtils configProperty = PropertiesUtils.getInstance("config");
        String reportDir = configProperty.getValue("com.magic.report.dir");
        String xlsFileName = reportDir+ DateUtils.getDate().getTime()+".xlsx";

        logger.info(Thread.currentThread().getId()+": Ready to Export Report...");
        logger.info(Thread.currentThread().getId()+": File Name["+xlsFileName+"]");
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        jrXlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsFileName));
        jrXlsxExporter.exportReport();

        logger.info(Thread.currentThread().getId()+": Export Finished!");
        if (listener != null) {
            listener.onFinished(xlsFileName);
        }
    }
}
