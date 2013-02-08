package com.searshc.twilight.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.twilight.TwilightJsonObject;
import com.searshc.twilight.service.TwilightConstants;
import com.searshc.twilight.util.DecoderUtils;
import com.searshc.twilight.util.PropertyLoader;

public class PluInquiryParser
{
  private static Logger logger = Logger.getLogger(PluInquiryParser.class);
  private static Properties prop;
  
  //Plu Inquiry - D8 - parameters
  private static final String PLU_INQ_DIVISION = "division";
  private static final String PLU_INQ_ITEM_NUMBER = "itemNumber";
  private static final String PLU_INQ_SKU = "SKU";
  private static final String PLU_INQ_TYPE = "pluInquiryType";
  private static final String PLU_INQ_SEGMENT_LEVEL = "segmentLevel";
  
  //Plu Inquiry - D8 - default Values
  private static final String DEFAULT_PLU_INQ_DIVISION = "pluInq_division";
  private static final String DEFAULT_PLU_INQ_ITEM_NUMBER = "pluInq_itemNumber";
  private static final String DEFAULT_PLU_INQ_SKU = "pluInq_sku";
  private static final String DEFAULT_PLU_INQ_TYPE = "pluInq_type";
  private static final String DEFAULT_PLU_INQ_SEGMENT_LEVEL = "pluInq_segmentLevel";
  
  public PluInquiryParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getPluInquiry(TwilightJsonObject twilightJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processPluInquiryD8(twilightJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processPluInquiryD8(TwilightJsonObject twilightJsonObject)
  {
    String indicator = "D8";
    String division = StringUtils.EMPTY;
    String itemNumber = StringUtils.EMPTY;
    String sku = StringUtils.EMPTY;
    String inqType = StringUtils.EMPTY;
    String segmentLvl = StringUtils.EMPTY;
    
    final Map<String, String> pluInquiryMap = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
 if(pluInquiryMap.size() > 0)
 {
    for (Map.Entry<String, String> entry : pluInquiryMap.entrySet())
    { 
      if(!pluInquiryMap.containsKey(PLU_INQ_DIVISION))
      {
        modifiableMap.put(PLU_INQ_DIVISION, prop.getProperty(DEFAULT_PLU_INQ_DIVISION));
      } 
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluInquiryMap.containsKey(PLU_INQ_ITEM_NUMBER))
      {
        modifiableMap.put(PLU_INQ_ITEM_NUMBER, prop.getProperty(DEFAULT_PLU_INQ_ITEM_NUMBER));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluInquiryMap.containsKey(PLU_INQ_SKU))
      {
        modifiableMap.put(PLU_INQ_SKU, prop.getProperty(DEFAULT_PLU_INQ_SKU));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluInquiryMap.containsKey(PLU_INQ_TYPE))
      {
        modifiableMap.put(PLU_INQ_TYPE, prop.getProperty(DEFAULT_PLU_INQ_TYPE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluInquiryMap.containsKey(PLU_INQ_SEGMENT_LEVEL)) 
      {
        modifiableMap.put(PLU_INQ_SEGMENT_LEVEL, prop.getProperty(DEFAULT_PLU_INQ_SEGMENT_LEVEL));
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
       if(entry.getKey().equalsIgnoreCase(PLU_INQ_DIVISION))
         division = StringUtils.rightPad(entry.getValue(), 3,'0');
       else if(entry.getKey().equalsIgnoreCase(PLU_INQ_ITEM_NUMBER))
         itemNumber = StringUtils.rightPad(entry.getValue(), 5,'0');
       else if(entry.getKey().equalsIgnoreCase(PLU_INQ_SKU))
         sku = StringUtils.rightPad(entry.getValue(), 3,'0');
       else if(entry.getKey().equalsIgnoreCase(PLU_INQ_TYPE))
       {
         inqType= entry.getValue();       
         if(StringUtils.isBlank(inqType) || inqType.equalsIgnoreCase("null"))
         {
           inqType = StringUtils.rightPad(StringUtils.EMPTY, 1, StringUtils.EMPTY);
         }
       }
       else if(entry.getKey().equalsIgnoreCase(PLU_INQ_SEGMENT_LEVEL))
       {
         segmentLvl = entry.getValue();
         if(StringUtils.isNotBlank(segmentLvl))
           segmentLvl = Integer.toHexString(Integer.parseInt(segmentLvl));
        }
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(division.getBytes()))
    .append(this.byteResponse(itemNumber.getBytes()))
    .append(this.byteResponse(sku.getBytes()))
    .append(this.byteResponse(inqType.getBytes()))
    .append(segmentLvl)
    .append(" ")
    .append("00");
    
    if(DecoderUtils.lengthMatch(TwilightConstants.INDICATOR_D8, sb)){
      return sb;
    }
    return null;
  }
  
  private String byteResponse(byte[] buffer){
    StringBuilder sb = new StringBuilder();
    for (byte b : buffer){
      sb.append(String.format("%02x", b).toUpperCase());
      sb.append(" ");
    }
    return sb.toString();
  }
}
