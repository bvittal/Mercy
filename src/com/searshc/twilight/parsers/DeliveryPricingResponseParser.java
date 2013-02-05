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

public class DeliveryPricingResponseParser
{
  private static Logger logger = Logger.getLogger(DeliveryPricingResponseParser.class);
  private static Properties prop;
  
  private final int BASE_LENGTH = 109;
  private final int INC_EXCL_LENGTH = 19;
  
  //Delivery Pricing Response - 72B2 - parameters
  private static final String DELIVERY_PRICING_RESP_MESSAGE_VERSION = "messageVersion";  
  private static final String DELIVERY_PRICING_RESP_NUMBER_OF_DELIVERY_CHARGES = "numberOfDeliveryCharges";
  
  private static final String DELIVERY_PRICING_RESP_DELIVERY_CHARGE_TYPE = "deliveryChargeType";
  private static final String DELIVERY_PRICING_RESP_MISC_ACC_NUM_FOR_THIS_DELIVERY_CHRG = "miscAccNumForThisDeliveryChrg";
  private static final String DELIVERY_PRICING_RESP_DELIVERY_CHARGE_AMOUNT = "deliveryChargeAmount";
  private static final String DELIVERY_PRICING_RESP_DELIVERY_CHARGE_DESCRIPTION = "deliveryChargeDescription";
  
  //Delivery Pricing Response - 72B2 - default Values
  private static final String DEFAULT_DELIVERY_PRICING_RESP_MESSAGE_VERSION = "deliveryPricingResp_72B2_messageVersion";  
  private static final String DEFAULT_DELIVERY_PRICING_RESP_NUMBER_OF_DELIVERY_CHARGES = "deliveryPricingResp_72B2_numberOfDeliveryCharges";
  
  private static final String DEFAULT_DELIVERY_PRICING_RESP_DELIVERY_CHARGE_TYPE = "deliveryPricingResp_72B2_deliveryChargeType";
  private static final String DEFAULT_DELIVERY_PRICING_RESP_MISC_ACC_NUM_FOR_THIS_DELIVERY_CHRG = "deliveryPricingResp_72B2_miscAccNumForThisDeliveryChrg";
  private static final String DEFAULT_DELIVERY_PRICING_RESP_DELIVERY_CHARGE_AMOUNT = "deliveryPricingResp_72B2_deliveryChargeAmount";
  private static final String DEFAULT_DELIVERY_PRICING_RESP_DELIVERY_CHARGE_DESCRIPTION = "deliveryPricingResp_72B2_deliveryChargeDescription";
  
    public DeliveryPricingResponseParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getCouponResponse(TwilightJsonObject twilightJsonObject)
  {
    StringBuilder builder = new StringBuilder();
    builder = this.processCouponResponse72B2(twilightJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processCouponResponse72B2(TwilightJsonObject twilightJsonObject)
  {
      String indicator = "72 B2";
      String messageVersion = StringUtils.EMPTY;
      String numberOfDeliveryCharges = StringUtils.EMPTY;
      
      String deliveryChargeType = StringUtils.EMPTY;
      String miscAccNumForThisDeliveryChrg = StringUtils.EMPTY;
      String deliveryChargeAmount = StringUtils.EMPTY;
      String deliveryChargeDescription = StringUtils.EMPTY;   
          
               
      final Map<String, String> deliveryPricingResponseMap = twilightJsonObject.getParameters();
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(deliveryPricingResponseMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : deliveryPricingResponseMap.entrySet())
      { 
        if(!deliveryPricingResponseMap.containsKey(DELIVERY_PRICING_RESP_MESSAGE_VERSION))
        {
            modifiableMap.put(DELIVERY_PRICING_RESP_MESSAGE_VERSION, prop.getProperty(DEFAULT_DELIVERY_PRICING_RESP_MESSAGE_VERSION));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryPricingResponseMap.containsKey(DELIVERY_PRICING_RESP_NUMBER_OF_DELIVERY_CHARGES))
        {
            modifiableMap.put(DELIVERY_PRICING_RESP_NUMBER_OF_DELIVERY_CHARGES, prop.getProperty(DEFAULT_DELIVERY_PRICING_RESP_NUMBER_OF_DELIVERY_CHARGES));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }                      
        
      }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
          if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_RESP_MESSAGE_VERSION))
            messageVersion = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_RESP_NUMBER_OF_DELIVERY_CHARGES))
           numberOfDeliveryCharges = entry.getValue();                
         
        }
      }
        
      
      sb.append(indicator)
      .append(" ")
      //This logic needs to be revisited once Patrick will be back from holidays
      //.append("51 01 ")    
      .append(this.byteResponse(messageVersion.getBytes()))
      .append(this.byteResponse(numberOfDeliveryCharges.getBytes()));    
      
    }
      
    
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
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_TYPE))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_TYPE, prop.getProperty(DEFAULT_DELIVERY_PRICING_RESP_DELIVERY_CHARGE_TYPE));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_RESP_MISC_ACC_NUM_FOR_THIS_DELIVERY_CHRG))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_RESP_MISC_ACC_NUM_FOR_THIS_DELIVERY_CHRG, prop.getProperty(DEFAULT_DELIVERY_PRICING_RESP_MISC_ACC_NUM_FOR_THIS_DELIVERY_CHRG));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_AMOUNT))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_AMOUNT, prop.getProperty(DEFAULT_DELIVERY_PRICING_RESP_DELIVERY_CHARGE_AMOUNT));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_DESCRIPTION))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_DESCRIPTION, prop.getProperty(DEFAULT_DELIVERY_PRICING_RESP_DELIVERY_CHARGE_DESCRIPTION));
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
                if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_TYPE))
                  deliveryChargeType = StringUtils.rightPad(entry.getValue(), 3);                
                else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_RESP_MISC_ACC_NUM_FOR_THIS_DELIVERY_CHRG))
                  miscAccNumForThisDeliveryChrg = StringUtils.rightPad(entry.getValue(), 6,'0');
                else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_AMOUNT))
                  deliveryChargeAmount = StringUtils.rightPad(entry.getValue(), 7,'0');
                else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_RESP_DELIVERY_CHARGE_DESCRIPTION))
                  deliveryChargeDescription = StringUtils.leftPad(entry.getValue(), 12,'0');         
                
                }
              }
          
          sb.append(this.byteResponse(deliveryChargeType.getBytes()))
            .append(this.byteResponse(miscAccNumForThisDeliveryChrg.getBytes()))
            .append(this.byteResponse(deliveryChargeAmount.getBytes()))
            .append(this.byteResponse(deliveryChargeDescription.getBytes()));
            
            }
          }
             
    if(DecoderUtils.lengthMatch(TwilightConstants.INDICATOR_72B2, sb)){
      return sb;
    }
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
