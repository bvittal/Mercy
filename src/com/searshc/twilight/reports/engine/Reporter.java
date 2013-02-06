package com.searshc.twilight.reports.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.searshc.twilight.reports.DataBeanMaker;
import com.searshc.twilight.reports.beans.DataBean;
import com.searshc.twilight.util.PropertyLoader;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
 
public class Reporter {
private static Properties prop;

public static void main(String[] args) throws Exception {
  try{
    prop = PropertyLoader.loadProperties("config", null);
    PropertyConfigurator.configure(prop);
  }catch(Exception ex){
    System.out.println("Error " + ex);
  }
DataBeanMaker dataBeanMaker = new DataBeanMaker();
ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
 
JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
Map<String,Object> parameters = new HashMap<String,Object>();

JasperDesign jasperDesign = JRXmlLoader.load(PropertyLoader.fileLoader("jasper-template.jrxml"));
JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
JasperExportManager.exportReportToPdfFile(jasperPrint, prop.getProperty("reportsDir")); 
  }
}
