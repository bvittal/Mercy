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

public class DeliveryFeeByZipInquiryParser
{
  private static Logger logger = Logger.getLogger(DeliveryFeeByZipInquiryParser.class);
  private static Properties prop;
  
  //Delivery Fee By Zip Inquiry - 70A4 - parameters
  private static final String DELIVERY_FEE_BY_ZIP_INQ_CLIENT_ID = "clientId";
  private static final String DELIVERY_FEE_BY_ZIP_INQ_USER_ID = "userId";
  private static final String DELIVERY_FEE_BY_ZIP_INQ_PASSWORD = "password";
  private static final String DELIVERY_FEE_BY_ZIP_INQ_SERIVICE_INDICATOR = "serviceIndicator";
  private static final String DELIVERY_FEE_BY_ZIP_INQ_ZIP_CODE = "zipCode";
  
  //Delivery Fee By Zip Inquiry - 70A4 - default Values
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_CLIENT_ID = "deliveryFeeByZipInq_clientId";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_USER_ID = "deliveryFeeByZipInq_userId";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_PASSWORD = "deliveryFeeByZipInq_password";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_SERIVICE_INDICATOR = "deliveryFeeByZipInq_serviceIndicator";
  private static final String DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_ZIP_CODE = "deliveryFeeByZipInq_zipCode";
  
  public DeliveryFeeByZipInquiryParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getDeliveryFeeByZipInquiry(TwilightJsonObject twilightJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processDeliveryFeeByZipInquiry70A4(twilightJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processDeliveryFeeByZipInquiry70A4(TwilightJsonObject twilightJsonObject)
  {
    String indicator = "70 A4";
    String clientId = StringUtils.EMPTY;
    String userId = StringUtils.EMPTY;
    String password = StringUtils.EMPTY;
    String serviceIndicator = StringUtils.EMPTY;
    String zipCode = StringUtils.EMPTY;
    
    final Map<String, String> deliveryFeeByZipInquiryMap = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(deliveryFeeByZipInquiryMap.size() > 0)
  {
    for (Map.Entry<String, String> entry : deliveryFeeByZipInquiryMap.entrySet())
    { 
      if(!deliveryFeeByZipInquiryMap.containsKey(DELIVERY_FEE_BY_ZIP_INQ_CLIENT_ID))
      {
          modifiableMap.put(DELIVERY_FEE_BY_ZIP_INQ_CLIENT_ID, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_CLIENT_ID));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!deliveryFeeByZipInquiryMap.containsKey(DELIVERY_FEE_BY_ZIP_INQ_USER_ID))
      {
          modifiableMap.put(DELIVERY_FEE_BY_ZIP_INQ_USER_ID, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_USER_ID));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!deliveryFeeByZipInquiryMap.containsKey(DELIVERY_FEE_BY_ZIP_INQ_PASSWORD))
      {
          modifiableMap.put(DELIVERY_FEE_BY_ZIP_INQ_PASSWORD, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_PASSWORD));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!deliveryFeeByZipInquiryMap.containsKey(DELIVERY_FEE_BY_ZIP_INQ_SERIVICE_INDICATOR))
      {
          modifiableMap.put(DELIVERY_FEE_BY_ZIP_INQ_SERIVICE_INDICATOR, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_SERIVICE_INDICATOR));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      if(!deliveryFeeByZipInquiryMap.containsKey(DELIVERY_FEE_BY_ZIP_INQ_ZIP_CODE))
      {
          modifiableMap.put(DELIVERY_FEE_BY_ZIP_INQ_ZIP_CODE, prop.getProperty(DEFAULT_DELIVERY_FEE_BY_ZIP_INQ_ZIP_CODE));
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
       if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_CLIENT_ID))
         clientId = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_USER_ID))
         userId = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_PASSWORD))
         password = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_SERIVICE_INDICATOR))
         serviceIndicator = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_ZIP_CODE))
         zipCode = entry.getValue();
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(clientId.getBytes()))    
    .append(this.byteResponse(userId.getBytes()))    
    .append(this.byteResponse(password.getBytes()))   
    .append(this.byteResponse(serviceIndicator.getBytes()))   
    .append(this.byteResponse(zipCode.getBytes()));    
    
    if(DecoderUtils.lengthMatch(TwilightConstants.INDICATOR_70A4, sb)){
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
