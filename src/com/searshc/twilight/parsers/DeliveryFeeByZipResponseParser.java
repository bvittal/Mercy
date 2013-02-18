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

public class DeliveryFeeByZipResponseParser
{
  private static Logger logger = Logger.getLogger(DeliveryFeeByZipResponseParser.class);
  private static Properties prop;
  
  private final int BASE_LENGTH = 109;
  private final int INC_EXCL_LENGTH = 19;
  
  //Delivery Fee By Zip Response - 70B4 - parameters
  private static final String DELIVERY_FEE_BY_ZIP_RESP_RESPONSE_CODE = "responseCode";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_DELIVERY_TYPE = "deliveryType";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_BASE_DELIVERY_CHARGE = "baseDeliveryCharge";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_ADDITIONAL_PIECE_CHARGE = "additionalPieceCharge";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_WEEKEND_UP_CHARGE = "weekendUpCharge";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_HAUL_AWAY_CHARGE = "haulAwayCharge";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_FLAG = "morningPremiumWindowFlag";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_FLAG = "eveningPremiumWindowFlag";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_DESCRIPTION =  "morningPremiumWindowDescription";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_DESCRIPTION = "eveningPremiumWindowDescription";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_FEE_AMOUNT = "morningPremiumFeeAmount";
  private static final String DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_FEE_AMOUNT = "eveningPremiumFeeAmount";
  
  
  //Delivery Fee By Zip Response - 70B4 - default Values
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_RESPONSE_CODE = "deliveryFeeByZipResp_70B4_responseCode";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_DELIVERY_TYPE = "deliveryFeeByZipResp_70B4_deliveryType";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_BASE_DELIVERY_CHARGE = "deliveryFeeByZipResp_70B4_baseDeliveryCharge";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_ADDITIONAL_PIECE_CHARGE = "deliveryFeeByZipResp_70B4_additionalPieceCharge";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_WEEKEND_UP_CHARGE = "deliveryFeeByZipResp_70B4_weekendUpCharge";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_HAUL_AWAY_CHARGE = "deliveryFeeByZipResp_70B4_haulAwayCharge";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_FLAG = "deliveryFeeByZipResp_70B4_morningPremiumWindowFlag";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_FLAG = "deliveryFeeByZipResp_70B4_eveningPremiumWindowFlag";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_DESCRIPTION =  "deliveryFeeByZipResp_70B4_morningPremiumWindowDescription";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_DESCRIPTION = "deliveryFeeByZipResp_70B4_eveningPremiumWindowDescription";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_FEE_AMOUNT = "deliveryFeeByZipResp_70B4_morningPremiumFeeAmount";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_FEE_AMOUNT = "deliveryFeeByZipResp_70B4_eveningPremiumFeeAmount";
  
    public DeliveryFeeByZipResponseParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getDeliveryFeeByZipResponse(TwilightJsonObject twilightJsonObject)
  {
    StringBuilder builder = new StringBuilder();
    builder = this.processDeliveryFeeByZipResponse70B4(twilightJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processDeliveryFeeByZipResponse70B4(TwilightJsonObject twilightJsonObject)
  {
      String indicator = "70 B4";
      String responseCode = StringUtils.EMPTY;
      String deliveryType = StringUtils.EMPTY;
      String baseDeliveryCharge = StringUtils.EMPTY;
      String additionalPieceCharge = StringUtils.EMPTY;
      String weekendUpCharge = StringUtils.EMPTY;
      String haulAwayCharge = StringUtils.EMPTY;
      String morningPremiumWindowFlag = StringUtils.EMPTY;
      String eveningPremiumWindowFlag = StringUtils.EMPTY;
      String morningPremiumWindowDescription = StringUtils.EMPTY;
      String eveningPremiumWindowDescription = StringUtils.EMPTY;
      String morningPremiumFeeAmount = StringUtils.EMPTY;
      String eveningPremiumFeeAmount = StringUtils.EMPTY;
            
       
      final Map<String, String> deliveryFeeByZipResponseMap = twilightJsonObject.getParameters();
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(deliveryFeeByZipResponseMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : deliveryFeeByZipResponseMap.entrySet())
      { 
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_RESPONSE_CODE))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_RESPONSE_CODE, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_RESPONSE_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_DELIVERY_TYPE))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_DELIVERY_TYPE, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_DELIVERY_TYPE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_BASE_DELIVERY_CHARGE))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_BASE_DELIVERY_CHARGE, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_BASE_DELIVERY_CHARGE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_ADDITIONAL_PIECE_CHARGE))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_ADDITIONAL_PIECE_CHARGE, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_ADDITIONAL_PIECE_CHARGE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_WEEKEND_UP_CHARGE))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_WEEKEND_UP_CHARGE, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_WEEKEND_UP_CHARGE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_HAUL_AWAY_CHARGE))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_HAUL_AWAY_CHARGE, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_HAUL_AWAY_CHARGE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_FLAG))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_FLAG, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_FLAG))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_FLAG, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_DESCRIPTION))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_DESCRIPTION, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_DESCRIPTION));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_DESCRIPTION))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_DESCRIPTION, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_DESCRIPTION));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_FEE_AMOUNT))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_FEE_AMOUNT, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_FEE_AMOUNT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!deliveryFeeByZipResponseMap.containsKey(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_FEE_AMOUNT))
        {
            modifiableMap.put(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_FEE_AMOUNT, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_FEE_AMOUNT));
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
         if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_RESPONSE_CODE))        
           responseCode = entry.getValue();         
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_DELIVERY_TYPE))
           deliveryType = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_BASE_DELIVERY_CHARGE))
           baseDeliveryCharge = StringUtils.rightPad(entry.getValue(), 6,'0');         
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_ADDITIONAL_PIECE_CHARGE))
           additionalPieceCharge = StringUtils.rightPad(entry.getValue(), 6,'0');
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_WEEKEND_UP_CHARGE))
           weekendUpCharge = StringUtils.rightPad(entry.getValue(), 6,'0');
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_HAUL_AWAY_CHARGE))
           haulAwayCharge = StringUtils.rightPad(entry.getValue(), 6,'0');
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_FLAG))
           morningPremiumWindowFlag = StringUtils.rightPad(entry.getValue(), 1,'N');         
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_FLAG))
           eveningPremiumWindowFlag = StringUtils.rightPad(entry.getValue(), 1,'N');        
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_WINDOW_DESCRIPTION)){           
           if(StringUtils.isBlank(morningPremiumWindowDescription) || morningPremiumWindowDescription.equalsIgnoreCase("null"))
             morningPremiumWindowDescription = StringUtils.leftPad(StringUtils.EMPTY, 30,StringUtils.EMPTY);          
         }        
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_WINDOW_DESCRIPTION)){
           if(StringUtils.isBlank(eveningPremiumWindowDescription) || eveningPremiumWindowDescription.equalsIgnoreCase("null"))
           eveningPremiumWindowDescription = StringUtils.leftPad(StringUtils.EMPTY, 30,StringUtils.EMPTY);         
        }     
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_MORNING_PREMIUM_FEE_AMOUNT))
           morningPremiumFeeAmount = StringUtils.rightPad(entry.getValue().replace(".", ""), 6,'0');         
         else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_RESP_EVENING_PREMIUM_FEE_AMOUNT))
           eveningPremiumFeeAmount = StringUtils.rightPad(entry.getValue().replace(".", ""), 6,'0');        
        }
      }
        
      
      sb.append(indicator)
      .append(" ")      
      .append(this.byteResponse(responseCode.getBytes()))
      .append(this.byteResponse(deliveryType.getBytes()))
      .append(this.byteResponse(baseDeliveryCharge.getBytes()))
      .append(this.byteResponse(additionalPieceCharge.getBytes()))
      .append(this.byteResponse(weekendUpCharge.getBytes()))
      .append(this.byteResponse(haulAwayCharge.getBytes()))
      .append(this.byteResponse(morningPremiumWindowFlag.getBytes()))
      .append(this.byteResponse(eveningPremiumWindowFlag.getBytes()))
      .append(this.byteResponse(morningPremiumWindowDescription.getBytes()))
      .append(this.byteResponse(eveningPremiumWindowDescription.getBytes()))
      .append(this.byteResponse(morningPremiumFeeAmount.getBytes())) 
      .append(this.byteResponse(eveningPremiumFeeAmount.getBytes()));    
      
    }
        
      
        
    if(DecoderUtils.lengthMatch(TwilightConstants.INDICATOR_70B4, sb)){
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
