package com.searshc.mercy.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.mercy.MercyJsonObject;
import com.searshc.mercy.util.PropertyLoader;

public class FileInquiryParser
{

  private static Logger logger = Logger.getLogger(FileInquiryParser.class);
  private static Properties prop;
  
  //File Inquiry - D3 - parameters
  private static final String FILE_INQ_FILE_NAME = "fileName";
  private static final String FILE_INQ_PADDING = "padding";
  
  //File Inquiry - D3 - default Values
  private static final String DEFAULT_FILE_INQ_FILE_NAME = "fileInq_fileName";
  private static final String DEFAULT_FILE_INQ_PADDING = "fileInq_padding";
  
  public FileInquiryParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getFileInquiry(MercyJsonObject mercyJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processFileInquiry(mercyJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processFileInquiry(MercyJsonObject mercyJsonObject)
  {
    String indicator = "D3";
    String fileName = StringUtils.EMPTY;
    String paddedString = StringUtils.EMPTY;
    
    final Map<String, String> fileInquiryMap = mercyJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
   if(fileInquiryMap.size() > 0)
   {
      for (Map.Entry<String, String> entry : fileInquiryMap.entrySet())
      { 
        if(!fileInquiryMap.containsKey(FILE_INQ_FILE_NAME))
        {
            modifiableMap.put(FILE_INQ_FILE_NAME, prop.getProperty(DEFAULT_FILE_INQ_FILE_NAME));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!fileInquiryMap.containsKey(FILE_INQ_PADDING))
        {
            modifiableMap.put(FILE_INQ_PADDING, prop.getProperty(DEFAULT_FILE_INQ_PADDING));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
    
    if(modifiableMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : modifiableMap.entrySet())
      { 
        if(entry.getKey().trim().equalsIgnoreCase(FILE_INQ_FILE_NAME))
          fileName = entry.getValue();
        else if(entry.getKey().trim().equalsIgnoreCase(FILE_INQ_PADDING) && StringUtils.isNotBlank(fileName))
          paddedString = StringUtils.rightPad(fileName, Integer.parseInt(entry.getValue().trim()));
      }
    }
    sb.append(indicator).append(" ").append(this.byteResponse(paddedString.getBytes()));
    
    return sb;
  }
  
  private String byteResponse(byte[] buffer)
  {
    StringBuilder sb = new StringBuilder();

    for (byte b : buffer)
    {
      sb.append(String.format("%02x", b).toUpperCase());
      sb.append(" ");
    }
    return sb.toString();
  }
}
