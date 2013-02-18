package com.searshc.twilight.parsers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.twilight.TwilightJsonObject;
import com.searshc.twilight.service.TwilightConstants;
import com.searshc.twilight.util.DecoderUtils;
import com.searshc.twilight.util.PropertyLoader;

public class CutToCloseOfferInquiryParser
{
  private static Logger logger = Logger.getLogger(CutToCloseOfferInquiryParser.class);
  private static Properties prop;
  
  //Cut To Close Offer Inquiry - 12A1 - parameters
  private static final String CUT_TO_CLOSE_OFFER_INQ_SEGMENT_LEVEL = "segmentLevel";
  private static final String CUT_TO_CLOSE_OFFER_INQ_ITEM_COUNT = "itemCount";
  private static final String CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_DIVISION = "division";
  private static final String CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_ITEM_NUMBER = "itemNumber";
  
  
  //Cut To Close Offer Inquiry - 12A1 - default Values  
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_INQ_SEGMENT_LEVEL = "cutToCloseOfferInq_12A1_segmentLevel";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_INQ_ITEM_COUNT = "cutToCloseOfferInq_12A1_itemCount";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_DIVISION = "cutToCloseOfferInq_12A1_division";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_ITEM_NUMBER = "cutToCloseOfferInq_12A1_itemNumber";
  
  public CutToCloseOfferInquiryParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getCutToCloseOfferInquiry(TwilightJsonObject twilightJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processCutToCloseOfferInquiry12A1(twilightJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processCutToCloseOfferInquiry12A1(TwilightJsonObject twilightJsonObject)
  {
    String indicator = "12 A1";
    String segmentLevel = StringUtils.EMPTY;
    String itemCount = StringUtils.EMPTY;
    
    String division = StringUtils.EMPTY;
    String itemNumber = StringUtils.EMPTY;
    
    
    final Map<String, String> cutToCloseOfferInquiryMap = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(cutToCloseOfferInquiryMap.size() > 0)
  {
    for (Map.Entry<String, String> entry : cutToCloseOfferInquiryMap.entrySet())
    { 
      if(!cutToCloseOfferInquiryMap.containsKey(CUT_TO_CLOSE_OFFER_INQ_SEGMENT_LEVEL))
      {
          modifiableMap.put(CUT_TO_CLOSE_OFFER_INQ_SEGMENT_LEVEL, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_INQ_SEGMENT_LEVEL));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!cutToCloseOfferInquiryMap.containsKey(CUT_TO_CLOSE_OFFER_INQ_ITEM_COUNT))
      {
          modifiableMap.put(CUT_TO_CLOSE_OFFER_INQ_ITEM_COUNT, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_INQ_ITEM_COUNT));
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
       if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_INQ_SEGMENT_LEVEL))
         segmentLevel = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_INQ_ITEM_COUNT))
         itemCount = entry.getValue();       
      }
    }
    
    sb.append(indicator)
      .append(" ")
      .append(this.byteResponse(segmentLevel.getBytes()))    
      .append(this.byteResponse(itemCount.getBytes()));    
    
    
    
    for(TwilightJsonObject numOfItmObj : twilightJsonObject.getTwilightJsonObject())
    {
      final Iterator<TwilightJsonObject> numOfItmItr = numOfItmObj.getTwilightJsonObject().iterator();
      
      while(numOfItmItr.hasNext())
      {
        final Map<String, String> numOfItmMap = numOfItmItr.next().getParameters();
        final Map<String, String> numOfItmModifiableMap = new HashMap<String,String>();
        
        if(numOfItmMap.size() > 0)
        {
          for (Map.Entry<String, String> entry : numOfItmMap.entrySet())
          {
              if(!numOfItmMap.containsKey(CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_DIVISION))
              {
                numOfItmModifiableMap.put(CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_DIVISION, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_DIVISION));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_ITEM_NUMBER))
              {
                numOfItmModifiableMap.put(CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_ITEM_NUMBER, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_ITEM_NUMBER));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }                     
            }
          }
        
          if(numOfItmModifiableMap.size() > 0)
          {
            for (Map.Entry<String, String> entry : numOfItmModifiableMap.entrySet())
            { 
              if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_DIVISION))
                division = StringUtils.rightPad(entry.getValue(), 3);                
              else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_INQ_ITM_COUNT_ITEM_NUMBER))
                itemNumber = StringUtils.rightPad(entry.getValue(), 5,'0'); 
           
              }
            }
        sb.append(this.byteResponse(division.getBytes()))
          .append(this.byteResponse(itemNumber.getBytes()));
          }
        }
    
    if(DecoderUtils.lengthMatch(TwilightConstants.INDICATOR_12A1, sb)){
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
