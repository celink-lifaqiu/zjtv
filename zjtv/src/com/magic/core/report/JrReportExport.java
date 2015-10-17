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
 * Created by YinJianFeng on 14-6-17.
 */
public class JrReportExport {
    Logger logger = LoggerFactory.getLogger(getClass());

    private String  reportFile;
    private Map<String, Object> reportParamMap;
    private JRDataSource dataSource;

    public JrReportExport(String reportFile, Map<String, Object> reportParamMap, JRDataSource dataSource) {
        this.reportFile = reportFile;
        this.reportParamMap = reportParamMap;
        this.dataSource = dataSource;
    }

    public String doExport(){
        logger.info(Thread.currentThread().getId()+": Start Run Report ...");
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        InputStream reportis = classpathResourceLoader.getResourceStream("reports/"+reportFile+".jasper");
        try {
            logger.info(Thread.currentThread().getId()+": Fill Report ");

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportis, reportParamMap, dataSource);

//            exportDoc(jasperPrint);
            return exportXls(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "";
    }


    private void exportDoc(JasperPrint jasperPrint) throws JRException {
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
    }

    private String exportXls(JasperPrint jasperPrint) throws JRException {
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
        return xlsFileName;
    }
}
