package com.searshc.twilight.reports.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import com.searshc.twilight.reports.beans.DataBean;
import com.searshc.twilight.util.PropertyLoader;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
 
public class Reporter{
  
  private static Properties prop;
  
  public Reporter(){
    try{
      prop = PropertyLoader.loadProperties("config", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      System.out.println("Error " + ex);
    }
  }

  public String generateReport(List<DataBean> dataBeanList, String testReportName) throws JRException{
      String reportUrl = prop.getProperty("reportsDir")+testReportName+".pdf";
      if(dataBeanList.size() > 0){
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
        Map<String,Object> parameters = new HashMap<String,Object>();
        
          JasperDesign jasperDesign = JRXmlLoader.load(PropertyLoader.fileLoader("jasper-template.jrxml"));
          JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
          JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
          if(StringUtils.isNotBlank(reportUrl)){
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportUrl); 
          }
        }
      return reportUrl;
      }
    }
