package com.searshc.mercy.parsers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.mercy.MercyJsonObject;
import com.searshc.mercy.service.MercyConstants;
import com.searshc.mercy.util.DecoderUtils;
import com.searshc.mercy.util.PropertyLoader;

public class InstantDeliveryOfferInquiryParser
{
  private static Logger logger = Logger.getLogger(InstantDeliveryOfferInquiryParser.class);
  private static Properties prop;
  
  //Instant Delivery Offer Inquiry - 11A6 - parameters
  private static final String INSTANT_DELIVERY_OFFER_INQ_SEGMENT_LEVEL = "segmentLevel";
  private static final String INSTANT_DELIVERY_OFFER_INQ_ITEM_COUNT = "itemCount";
  
  private static final String INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_DIVISION = "division";
  private static final String INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_ITEM_NUMBER = "itemNumber";
  
  
  //Instant Delivery Offer Inquiry - 11A6 - default Values  
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_INQ_SEGMENT_LEVEL = "instantDeliveryOfferInq_11A6_segmentLevel";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_INQ_ITEM_COUNT = "instantDeliveryOfferInq_11A6_itemCount";
  
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_DIVISION = "instantDeliveryOfferInq_11A6_division";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_ITEM_NUMBER = "instantDeliveryOfferInq_11A6_itemNumber";
  
  public InstantDeliveryOfferInquiryParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getInstantDeliveryOfferInquiry(MercyJsonObject mercyJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processInstantDeliveryOfferInquiry11A6(mercyJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processInstantDeliveryOfferInquiry11A6(MercyJsonObject mercyJsonObject)
  {
    String indicator = "11 A6";
    String segmentLevel = StringUtils.EMPTY;
    String itemCount = StringUtils.EMPTY;
    
    String division = StringUtils.EMPTY;
    String itemNumber = StringUtils.EMPTY;
    
    
    final Map<String, String> instantDeliveryOfferInquiryMap = mercyJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(instantDeliveryOfferInquiryMap.size() > 0)
  {
    for (Map.Entry<String, String> entry : instantDeliveryOfferInquiryMap.entrySet())
    { 
      if(!instantDeliveryOfferInquiryMap.containsKey(INSTANT_DELIVERY_OFFER_INQ_SEGMENT_LEVEL))
      {
          modifiableMap.put(INSTANT_DELIVERY_OFFER_INQ_SEGMENT_LEVEL, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_INQ_SEGMENT_LEVEL));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!instantDeliveryOfferInquiryMap.containsKey(INSTANT_DELIVERY_OFFER_INQ_ITEM_COUNT))
      {
          modifiableMap.put(INSTANT_DELIVERY_OFFER_INQ_ITEM_COUNT, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_INQ_ITEM_COUNT));
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
       if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_INQ_SEGMENT_LEVEL))
         segmentLevel = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_INQ_ITEM_COUNT))
         itemCount = entry.getValue();       
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(segmentLevel.getBytes()))    
    .append(this.byteResponse(itemCount.getBytes()));    
    
    
    
    for(MercyJsonObject numOfItmObj : mercyJsonObject.getMercyJsonObject())
    {
      final Iterator<MercyJsonObject> numOfItmItr = numOfItmObj.getMercyJsonObject().iterator();
      
      while(numOfItmItr.hasNext())
      {
        final Map<String, String> numOfItmMap = numOfItmItr.next().getParameters();
        final Map<String, String> numOfItmModifiableMap = new HashMap<String,String>();
        
        if(numOfItmMap.size() > 0)
        {
          for (Map.Entry<String, String> entry : numOfItmMap.entrySet())
          {
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_DIVISION))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_DIVISION, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_DIVISION));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_ITEM_NUMBER))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_ITEM_NUMBER, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_ITEM_NUMBER));
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
                if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_DIVISION))
                  division = StringUtils.rightPad(entry.getValue(), 3);                
                else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_INQ_ITM_COUNT_ITEM_NUMBER))
                  itemNumber = StringUtils.rightPad(entry.getValue(), 5,'0'); 
             
                }
              }
          
          sb.append(this.byteResponse(division.getBytes()))
            .append(this.byteResponse(itemNumber.getBytes()));
            }
          }
    
    if(DecoderUtils.lengthMatch(MercyConstants.INDICATOR_11A6, sb)){
      return sb;
    }
    return null;
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
