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

public class CutToCloseOfferResponseParser
{
  private static Logger logger = Logger.getLogger(CutToCloseOfferResponseParser.class);
  private static Properties prop;
  
  //Instant Delivery Offer Response - 12B1 - parameters
  private static final String CUT_TO_CLOSE_OFFER_RESP_SEGMENT_LEVEL = "segmentLevel";
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFER_COUNT = "offerCount";
  
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_COUPON_NUMBER = "couponNumber";
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT = "delvryFeeReduceAmt";
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT = "haulAwyFeeReduceAmt";
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL = "zeroPercntFinElgbl";
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS = "validWthFrndsAndFamCpns";
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS = "validWthSearsCardDiscnts";
  private static final String CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_CUT_TO_CLOSE_POINT_VALUE = "cutToClosePointValue";
  
  
  //Instant Delivery Offer Response - 12B1 - default Values  
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_SEGMENT_LEVEL = "cutToCloseOfferResp_12B1_segmentLevel";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_ITEM_COUNT = "cutToCloseOfferResp_12B1_offerCount";
  
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_COUPON_NUMBER = "cutToCloseOfferResp_12B1_couponNumber";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT = "cutToCloseOfferResp_12B1_delvryFeeReduceAmt";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT = "cutToCloseOfferResp_12B1_haulAwyFeeReduceAmt";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL = "cutToCloseOfferResp_12B1_zeroPercntFinElgbl";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS = "cutToCloseOfferResp_12B1_validWthFrndsAndFamCpns";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS = "cutToCloseOfferResp_12B1_validWthSearsCardDiscnts";
  private static final String DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_CUT_TO_CLOSE_POINT_VALUE = "cutToCloseOfferResp_12B1_cutToClosePointValue";
  
  public CutToCloseOfferResponseParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getCutToCloseOfferResponse(MercyJsonObject mercyJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processCutToCloseOfferResponse12B1(mercyJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processCutToCloseOfferResponse12B1(MercyJsonObject mercyJsonObject)
  {
    String indicator = "12 B1";
    String segmentLevel = StringUtils.EMPTY;
    String offerCount = StringUtils.EMPTY;
    String couponNumber= StringUtils.EMPTY;
    String delvryFeeReduceAmt= StringUtils.EMPTY;
    String haulAwyFeeReduceAmt= StringUtils.EMPTY;
    String zeroPercntFinElgbl= StringUtils.EMPTY;
    String validWthFrndsAndFamCpns= StringUtils.EMPTY;
    String validWthSearsCardDiscnts= StringUtils.EMPTY;
    String cutToClosePointValue= StringUtils.EMPTY;
    
    final Map<String, String> cutToCloseOfferResponseMap = mercyJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(cutToCloseOfferResponseMap.size() > 0)
  {
    for (Map.Entry<String, String> entry : cutToCloseOfferResponseMap.entrySet())
    { 
      if(!cutToCloseOfferResponseMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_SEGMENT_LEVEL))
      {
          modifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_SEGMENT_LEVEL, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_SEGMENT_LEVEL));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!cutToCloseOfferResponseMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFER_COUNT))
      {
          modifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFER_COUNT, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_ITEM_COUNT));
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
       if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_SEGMENT_LEVEL))
         segmentLevel = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFER_COUNT))
         offerCount = entry.getValue();       
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(segmentLevel.getBytes()))    
    .append(this.byteResponse(offerCount.getBytes()));    
    
    
    
    for(MercyJsonObject numOfOffrObj : mercyJsonObject.getMercyJsonObject())
    {
      final Iterator<MercyJsonObject> numOfOffrItr = numOfOffrObj.getMercyJsonObject().iterator();
      
      while(numOfOffrItr.hasNext())
      {
        final Map<String, String> numOfOffrMap = numOfOffrItr.next().getParameters();
        final Map<String, String> numOfOffrModifiableMap = new HashMap<String,String>();
        
        if(numOfOffrMap.size() > 0)
        {
          for (Map.Entry<String, String> entry : numOfOffrMap.entrySet())
          {
              if(!numOfOffrMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_COUPON_NUMBER))
              {
                numOfOffrModifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_COUPON_NUMBER, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_COUPON_NUMBER));
              }
              else
              {
                numOfOffrModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfOffrMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT))
              {
                numOfOffrModifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT));
              }
              else
              {
                numOfOffrModifiableMap.put(entry.getKey(), entry.getValue());
              }    
              if(!numOfOffrMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT))
              {
                numOfOffrModifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT));
              }
              else
              {
                numOfOffrModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfOffrMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL))
              {
                numOfOffrModifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL));
              }
              else
              {
                numOfOffrModifiableMap.put(entry.getKey(), entry.getValue());
              }     
              if(!numOfOffrMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS))
              {
                numOfOffrModifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS));
              }
              else
              {
                numOfOffrModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfOffrMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS))
              {
                numOfOffrModifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS));
              }
              else
              {
                numOfOffrModifiableMap.put(entry.getKey(), entry.getValue());
              }    
              if(!numOfOffrMap.containsKey(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_CUT_TO_CLOSE_POINT_VALUE))
              {
                numOfOffrModifiableMap.put(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_CUT_TO_CLOSE_POINT_VALUE, prop.getProperty(DEFAULT_CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_CUT_TO_CLOSE_POINT_VALUE));
              }
              else
              {
                numOfOffrModifiableMap.put(entry.getKey(), entry.getValue());
              }
            }
          }
        
            if(numOfOffrModifiableMap.size() > 0)
            {
              for (Map.Entry<String, String> entry : numOfOffrModifiableMap.entrySet())
              { 
                if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_COUPON_NUMBER))
                  couponNumber = StringUtils.rightPad(entry.getValue(), 3);                
                else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_DELVRY_FEE_REDUCE_AMT))
                  delvryFeeReduceAmt = StringUtils.rightPad(entry.getValue().replace(".", ""), 8,'0'); 
                else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_HAUL_AWY_FEE_REDUCE_AMT))
                  haulAwyFeeReduceAmt = StringUtils.rightPad(entry.getValue().replace(".", ""), 8,'0');                
                else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_ZERO_PERCNT_FIN_ELGBL))
                  zeroPercntFinElgbl = StringUtils.rightPad(entry.getValue(), 1,'N'); 
                else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_FRNDS_AND_FAM_CPNS))
                  validWthFrndsAndFamCpns = StringUtils.rightPad(entry.getValue(), 1,'N');                 
                else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_VALID_WTH_SEARS_CARD_DISCNTS))
                  validWthSearsCardDiscnts = StringUtils.rightPad(entry.getValue(), 1,'N'); 
                else if(entry.getKey().equalsIgnoreCase(CUT_TO_CLOSE_OFFER_RESP_OFFR_CNT_CUT_TO_CLOSE_POINT_VALUE))
                  cutToClosePointValue = StringUtils.rightPad(entry.getValue(), 3,'0'); 
                }
              }
          
          sb.append(this.byteResponse(couponNumber.getBytes()))
            .append(this.byteResponse(delvryFeeReduceAmt.getBytes()))
            .append(this.byteResponse(haulAwyFeeReduceAmt.getBytes()))
            .append(this.byteResponse(zeroPercntFinElgbl.getBytes()))
            .append(this.byteResponse(validWthFrndsAndFamCpns.getBytes()))
            .append(this.byteResponse(validWthSearsCardDiscnts.getBytes()))
            .append(this.byteResponse(cutToClosePointValue.getBytes()));
            }
          }
    
    if(DecoderUtils.lengthMatch(MercyConstants.INDICATOR_12B1, sb)){
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
