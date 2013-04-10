package com.searshc.mercy.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.searshc.mercy.segments.CouponInquiry2AA7Segment;
import com.searshc.mercy.segments.PluInquiryD8Segment;
import com.searshc.mercy.util.DecoderUtils;
import com.searshc.mercy.util.KeyMatcher;
import com.searshc.mercy.util.ObjectBuilder;
import com.starmount.ups.sears.SegmentIndex;


public class UPASResponseFinder
{
  private static Logger logger = Logger.getLogger(UPASResponseFinder.class);
  private final UpasResponseParser parser = new UpasResponseParser();
  private final Map<String, byte[]> inquiryMap = ObjectBuilder.getInqObjects();
  private final Map<String, byte[]> responseMap = ObjectBuilder.getRespObjects();
  private final SegmentFactory factory = new SegmentFactory();
  
  public byte[] findResponse(byte[] reqBuffer){
    final PluInquiryD8Segment pluReqInq = new PluInquiryD8Segment(reqBuffer);
    String pluItemNumber = pluReqInq.getPLUItemNumber();
    String pluSKU = pluReqInq.getPLUSKU();
    String pluDivisionNumber = pluReqInq.getPLUDivisionNumber();
    String d3respIndicator = StringUtils.EMPTY;
    byte [] response = null;
    
    if(reqBuffer != null){
      if (String.format("%02X", reqBuffer[0]).contains(MercyConstants.INDICATOR_D3)){
    	if (keyMatch("îPMP?????", byteToChar(reqBuffer))){
          d3respIndicator = MercyConstants.INDICATOR_PMP;
          System.out.println("Request recieved for file inquiry: " + d3respIndicator);
        }
        else if (keyMatch("îMP0000", byteToChar(reqBuffer))){
          d3respIndicator = MercyConstants.INDICATOR_MP0;
          System.out.println("Request recieved for file inquiry: " + d3respIndicator);
        }
        else if (keyMatch("îD00000", byteToChar(reqBuffer))){
          d3respIndicator = MercyConstants.INDICATOR_D00;
          System.out.println("Request recieved for file inquiry: " + d3respIndicator);
        }
        else if (keyMatch("îGFT00000", byteToChar(reqBuffer))){
          d3respIndicator = MercyConstants.INDICATOR_GFT;
          System.out.println("Request recieved for file inquiry: " + d3respIndicator);
        }
    	response = fileInquiryResponse(reqBuffer);
    	if(response != null && this.validateResponse(response)){
    	  factory.getSegment(d3respIndicator, response);
    		return response;
    	}
      }else if (String.format("%02X", reqBuffer[0]).contains(MercyConstants.INDICATOR_D8)){
          if(StringUtils.isNotBlank(pluItemNumber) && StringUtils.isNotBlank(pluSKU) && StringUtils.isNotBlank(pluDivisionNumber)){
            System.out.println("Request recieved for item no: " + pluDivisionNumber + pluItemNumber + pluSKU);
            response = pluInquiryResponse(reqBuffer);
            if(response != null && this.validateResponse(response)){
            	return response;
            }
          }
      }else if (String.format("%02X%02X", reqBuffer[0], reqBuffer[1]).contains(MercyConstants.INDICATOR_2AA7)){
          final CouponInquiry2AA7Segment segment = new CouponInquiry2AA7Segment(reqBuffer);
          String couponNumber = segment.getCouponNumber();
            if(StringUtils.isNotBlank(couponNumber)){
              System.out.println("Request recieved for coupon number: " + couponNumber);
              response = couponInquiryResponse(couponNumber,reqBuffer);
              if(response != null && this.validateResponse(response)){
              	return response;
              }
            }
      	}
    }
    return null;
  }
  
  private byte[] fileInquiryResponse(byte[] requestBuf){
    if(inquiryMap != null && inquiryMap.size()>0){
    	for (Map.Entry<String, byte[]> entry : inquiryMap.entrySet()){
    		byte [] fileInquiryFrmScript = entry.getValue();
    	 if(fileInquiryFrmScript != null && Arrays.equals(requestBuf, fileInquiryFrmScript)){
    		 String key = entry.getKey();
    		 if(MercyConstants.REQUEST_INDICATOR_FILE_INQ.equalsIgnoreCase(key))
    			 return responseMap.get(MercyConstants.RESPONSE_INDICATOR_FILE_RESP);
    		 else if(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I1.equalsIgnoreCase(key))
    			 return responseMap.get(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R1);
    		 else if(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I2.equalsIgnoreCase(key))
    			 return responseMap.get(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R2);
    		 else if(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I3.equalsIgnoreCase(key))
    			 return responseMap.get(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R3);
    		 else if(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I4.equalsIgnoreCase(key))
    			 return responseMap.get(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R4);
    		 else if(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I5.equalsIgnoreCase(key))
    			 return responseMap.get(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R5);
    	  }
       }
    }
    return null;
  }
  
  private byte[] pluInquiryResponse(byte[] requestBuf){
    if(inquiryMap != null && inquiryMap.size()>0){
      for (Map.Entry<String, byte[]> entry : inquiryMap.entrySet()){
    	  byte [] pluInquiryFrmScript = entry.getValue();
    	if(pluInquiryFrmScript != null && Arrays.equals(requestBuf, pluInquiryFrmScript)){
    	  String key = entry.getKey();
    	if(MercyConstants.REQUEST_INDICATOR_PLU_INQ.equalsIgnoreCase(key))
    		return responseMap.get(MercyConstants.RESPONSE_INDICATOR_PLU_RESP);
        else if(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I1.equalsIgnoreCase(key))
        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R1);
        else if(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I2.equalsIgnoreCase(key))
        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R2);
        else if(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I3.equalsIgnoreCase(key))
        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R3);
        else if(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I4.equalsIgnoreCase(key))
        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R4);
        else if(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I5.equalsIgnoreCase(key))
        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R5);
    	}
      }
    }
   return null;
 }
  
  private byte[] couponInquiryResponse(String couponNumber, byte[] requestBuf){
	    if(inquiryMap != null && inquiryMap.size()>0){
	      for (Map.Entry<String, byte[]> entry : inquiryMap.entrySet()){
	    	  byte [] couponInquiryFrmScript = entry.getValue();
    	  if(couponInquiryFrmScript != null && Arrays.equals(requestBuf, couponInquiryFrmScript)){
        	  String key = entry.getKey();
	    	if(MercyConstants.REQUEST_INDICATOR_COUPON_INQ.equalsIgnoreCase(key))
	    		  return responseMap.get(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP);
		    else if(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I1.equalsIgnoreCase(key))
		    	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R1);
	        else if(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I2.equalsIgnoreCase(key))
	        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R2);
	        else if(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I3.equalsIgnoreCase(key))
	        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R3);
	        else if(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I4.equalsIgnoreCase(key))
	        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R4);
	        else if(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I5.equalsIgnoreCase(key))
	        	return responseMap.get(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R5);
	       	}
	      } 
	    }
	   return null;
   }

  private boolean keyMatch(String key, char[] inquiry){
    KeyMatcher matcher = new KeyMatcher(key, inquiry);
    return matcher.isMatch();
  }
  
  private boolean validateResponse(byte[] buf){
    try{
      List<SegmentIndex> segIndexes = parser.parseResponse(buf); //just to validate that response is correct
      if(segIndexes.size() > 0)
        return Boolean.TRUE;
    }catch(Exception ex){
      System.out.println("Response validation failed for : " + DecoderUtils.showByteResponse(buf) + " " + ex);
    }
    return Boolean.FALSE;
  }
  
  private String byteResponse(byte[] buffer){
    StringBuilder sb = new StringBuilder();
    for (byte b : buffer){
      sb.append(String.format("%02x", b).toUpperCase());
      sb.append(" ");
    }
    return sb.toString();
  }

  private char[] byteToChar(byte[] bytes){
    char[] buffer = new char[bytes.length];
    for (int i = 0; i < buffer.length; i++){
      int unsignedByte = bytes[i] & 0xFF;
      char c = (char) unsignedByte;
      buffer[i] = c;
    }
    return buffer;
  }
}