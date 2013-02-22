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

public class CouponInquiryParser
{
  private static Logger logger = Logger.getLogger(CouponInquiryParser.class);
  private static Properties prop;
  
  //Coupon Inquiry - 2AA7 - parameters
  private static final String COUPON_INQ_SEGMENT_LEVEL = "segmentLevel";
  private static final String COUPON_INQ_SEGMENT_LENGTH = "segmentLength";
  private static final String COUPON_INQ_COUPON_NUMBER = "couponNumber";
  private static final String COUPON_INQ_STORE_NUMBER = "storeNumber";
  
  //Coupon Inquiry - 2AA7 - default Values
  private static final String DEFAULT_COUPON_INQ_SEGMENT_LEVEL = "couponInq_segmentLevel";
  private static final String DEFAULT_COUPON_INQ_SEGMENT_LENGTH = "couponInq_segmentLength";
  private static final String DEFAULT_COUPON_INQ_COUPON_NUMBER = "couponInq_couponNumber";
  private static final String DEFAULT_COUPON_INQ_STORE_NUMBER = "couponInq_storeNumber";
  
  public CouponInquiryParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getCouponInquiry(MercyJsonObject mercyJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processCouponInquiry2AA7(mercyJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processCouponInquiry2AA7(MercyJsonObject mercyJsonObject)
  {
    String indicator = "2A A7";
    String segmentLvl = StringUtils.EMPTY;
    String segmentLength = StringUtils.EMPTY;
    String couponNumber = StringUtils.EMPTY;
    String storeNumber = StringUtils.EMPTY;
    
    final Map<String, String> couponInquiryMap = mercyJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(couponInquiryMap.size() > 0)
  {
    for (Map.Entry<String, String> entry : couponInquiryMap.entrySet())
    { 
      if(!couponInquiryMap.containsKey(COUPON_INQ_SEGMENT_LEVEL))
      {
          modifiableMap.put(COUPON_INQ_SEGMENT_LEVEL, prop.getProperty(DEFAULT_COUPON_INQ_SEGMENT_LEVEL));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!couponInquiryMap.containsKey(COUPON_INQ_SEGMENT_LENGTH))
      {
          modifiableMap.put(COUPON_INQ_SEGMENT_LENGTH, prop.getProperty(DEFAULT_COUPON_INQ_SEGMENT_LENGTH));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!couponInquiryMap.containsKey(COUPON_INQ_COUPON_NUMBER))
      {
          modifiableMap.put(COUPON_INQ_COUPON_NUMBER, prop.getProperty(DEFAULT_COUPON_INQ_COUPON_NUMBER));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!couponInquiryMap.containsKey(COUPON_INQ_STORE_NUMBER))
      {
          modifiableMap.put(COUPON_INQ_STORE_NUMBER, prop.getProperty(DEFAULT_COUPON_INQ_STORE_NUMBER));
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
       if(entry.getKey().equalsIgnoreCase(COUPON_INQ_SEGMENT_LEVEL))
         segmentLvl = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(COUPON_INQ_SEGMENT_LENGTH))
       {
         segmentLength = entry.getValue();
         if(StringUtils.isNotBlank(segmentLength))
           segmentLength = Integer.toHexString(Integer.parseInt(segmentLength));
       }
       else if(entry.getKey().equalsIgnoreCase(COUPON_INQ_COUPON_NUMBER))
         couponNumber = StringUtils.rightPad(entry.getValue(), 8,'0');
       else if(entry.getKey().equalsIgnoreCase(COUPON_INQ_STORE_NUMBER))
         storeNumber = StringUtils.rightPad(entry.getValue(), 5,'0');
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(segmentLvl.substring(0,2))
    .append(" ")
    .append(segmentLvl.substring(2,4))
    .append(" ")
    .append(segmentLength)
    .append(" ")
    .append("00")
    .append(" ")
    .append(this.byteResponse(couponNumber.getBytes()))
    .append(this.byteResponse(storeNumber.getBytes()));
    
    if(DecoderUtils.lengthMatch(MercyConstants.INDICATOR_2AA7, sb)){
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
