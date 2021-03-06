package com.searshc.mercy.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.mercy.MercyJsonObject;
import com.searshc.mercy.service.MercyConstants;
import com.searshc.mercy.util.DecoderUtils;
import com.searshc.mercy.util.PropertyLoader;

public class DeliveryFeeByZipInquiryParser implements LengthCheck
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
  
  public StringBuilder getDeliveryFeeByZipInquiry(MercyJsonObject mercyJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processDeliveryFeeByZipInquiry70A4(mercyJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processDeliveryFeeByZipInquiry70A4(MercyJsonObject mercyJsonObject)
  {
    String indicator = "70 A4";
    String clientId = StringUtils.EMPTY;
    String userId = StringUtils.EMPTY;
    String password = StringUtils.EMPTY;
    String serviceIndicator = StringUtils.EMPTY;
    String zipCode = StringUtils.EMPTY;
    
    final Map<String, String> deliveryFeeByZipInquiryMap = mercyJsonObject.getParameters();
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
        if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_CLIENT_ID)){
          if(this.lengthCheck(3, entry.getValue()))
            clientId = StringUtils.rightPad(entry.getValue(), 3);
          else
            System.err.println(lengthCheckMsg(3,entry.getKey()));
        }
        else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_USER_ID)){
          if(this.lengthCheck(8, entry.getValue()))
            userId = StringUtils.rightPad(entry.getValue(), 8,'0');
          else
            System.err.println(lengthCheckMsg(8,entry.getKey()));
        }
        else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_PASSWORD)){
          if(this.lengthCheck(8, entry.getValue()))
            password = StringUtils.rightPad(entry.getValue(), 8);
          else
            System.err.println(lengthCheckMsg(8,entry.getKey()));
        }
        else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_SERIVICE_INDICATOR))
          serviceIndicator = entry.getValue();        
        else if(entry.getKey().equalsIgnoreCase(DELIVERY_FEE_BY_ZIP_INQ_ZIP_CODE)){
          if(this.lengthCheck(5, entry.getValue()))
            zipCode = StringUtils.rightPad(entry.getValue(), 5,'0');
          else
            System.err.println(lengthCheckMsg(5,entry.getKey()));
        }      
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(clientId.getBytes()))    
    .append(this.byteResponse(userId.getBytes()))    
    .append(this.byteResponse(password.getBytes()))   
    .append(this.byteResponse(serviceIndicator.getBytes()))   
    .append(this.byteResponse(zipCode.getBytes()));    
    
    if(DecoderUtils.lengthMatch(MercyConstants.INDICATOR_70A4, sb)){
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
  
  @Override
  public boolean lengthCheck(int length, String field)
  {
   if(!(StringUtils.isNotBlank(field) && field.length() > length)){
     return Boolean.TRUE;
   }
    return Boolean.FALSE;
  }

  @Override
  public String lengthCheckMsg(int length, String field)
  {
    return "Indicator : " + MercyConstants.INDICATOR_70A4+ " length check failed for : " + field + " field, required " + length +" char(s)";
  }
}
