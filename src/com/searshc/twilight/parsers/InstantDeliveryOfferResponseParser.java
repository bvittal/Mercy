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

public class InstantDeliveryOfferResponseParser
{
  private static Logger logger = Logger.getLogger(InstantDeliveryOfferResponseParser.class);
  private static Properties prop;
  
  //Instant Delivery Offer Response - 11B6 - parameters
  private static final String INSTANT_DELIVERY_OFFER_RESP_SEGMENT_LEVEL = "segmentLevel";
  private static final String INSTANT_DELIVERY_OFFER_RESP_OFFER_COUNT = "offerCount";
  
  private static final String INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_COUPON_NUMBER = "couponNumber";
  private static final String INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT = "delvryFeeReduceAmt";
  private static final String INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT = "haulAwyFeeReduceAmt";
  private static final String INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL = "zeroPercntFinElgbl";
  private static final String INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS = "validWthFrndsAndFamCpns";
  private static final String INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS = "validWthSearsCardDiscnts";
  
  
  //Instant Delivery Offer Response - 11B6 - default Values  
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_SEGMENT_LEVEL = "instantDeliveryOfferResp_11B6_segmentLevel";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_ITEM_COUNT = "instantDeliveryOfferResp_11B6_offerCount";
  
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_COUPON_NUMBER = "instantDeliveryOfferResp_11B6_couponNumber";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT = "instantDeliveryOfferResp_11B6_delvryFeeReduceAmt";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT = "instantDeliveryOfferResp_11B6_haulAwyFeeReduceAmt";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL = "instantDeliveryOfferResp_11B6_zeroPercntFinElgbl";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS = "instantDeliveryOfferResp_11B6_validWthFrndsAndFamCpns";
  private static final String DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS = "instantDeliveryOfferResp_11B6_validWthSearsCardDiscnts";
  
  public InstantDeliveryOfferResponseParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getInstantDeliveryOfferResponse(TwilightJsonObject twilightJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processInstantDeliveryOfferResponse11B6(twilightJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processInstantDeliveryOfferResponse11B6(TwilightJsonObject twilightJsonObject)
  {
    String indicator = "11 B6";
    String segmentLevel = StringUtils.EMPTY;
    String offerCount = StringUtils.EMPTY;
    
    String couponNumber= StringUtils.EMPTY;
    String delvryFeeReduceAmt= StringUtils.EMPTY;
    String haulAwyFeeReduceAmt= StringUtils.EMPTY;
    String zeroPercntFinElgbl= StringUtils.EMPTY;
    String validWthFrndsAndFamCpns= StringUtils.EMPTY;
    String validWthSearsCardDiscnts= StringUtils.EMPTY;
    
    
    final Map<String, String> instantDeliveryOfferResponseMap = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(instantDeliveryOfferResponseMap.size() > 0)
  {
    for (Map.Entry<String, String> entry : instantDeliveryOfferResponseMap.entrySet())
    { 
      if(!instantDeliveryOfferResponseMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_SEGMENT_LEVEL))
      {
          modifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_SEGMENT_LEVEL, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_SEGMENT_LEVEL));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!instantDeliveryOfferResponseMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_OFFER_COUNT))
      {
          modifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_OFFER_COUNT, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_ITEM_COUNT));
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
       if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_SEGMENT_LEVEL))
         segmentLevel = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_OFFER_COUNT))
         offerCount = entry.getValue();       
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(segmentLevel.getBytes()))    
    .append(this.byteResponse(offerCount.getBytes()));    
    
    
    
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
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_COUPON_NUMBER))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_COUPON_NUMBER, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_COUPON_NUMBER));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }    
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }     
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS))
              {
                numOfItmModifiableMap.put(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS, prop.getProperty(DEFAULT_INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS));
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
                if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_COUPON_NUMBER))
                  couponNumber = StringUtils.rightPad(entry.getValue(), 3);                
                else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT))
                  delvryFeeReduceAmt = StringUtils.rightPad(entry.getValue(), 8,'0'); 
                else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT))
                  haulAwyFeeReduceAmt = StringUtils.rightPad(entry.getValue(), 8,'0');                
                else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL))
                  zeroPercntFinElgbl = StringUtils.rightPad(entry.getValue(), 1,'N'); 
                else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS))
                  validWthFrndsAndFamCpns = StringUtils.rightPad(entry.getValue(), 1,'N');                 
                else if(entry.getKey().equalsIgnoreCase(INSTANT_DELIVERY_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS))
                  validWthSearsCardDiscnts = StringUtils.rightPad(entry.getValue(), 1,'N'); 
                }
              }
          
          sb.append(this.byteResponse(couponNumber.getBytes()))
            .append(this.byteResponse(delvryFeeReduceAmt.getBytes()))
            .append(this.byteResponse(haulAwyFeeReduceAmt.getBytes()))
            .append(this.byteResponse(zeroPercntFinElgbl.getBytes()))
            .append(this.byteResponse(validWthFrndsAndFamCpns.getBytes()))
            .append(this.byteResponse(validWthSearsCardDiscnts.getBytes()));
            }
          }
    
    if(DecoderUtils.lengthMatch(TwilightConstants.INDICATOR_11B6, sb)){
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
