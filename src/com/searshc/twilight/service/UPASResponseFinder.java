package com.searshc.twilight.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.searshc.twilight.segments.CouponInquiry2AA7Segment;
import com.searshc.twilight.segments.PluInquiryD8Segment;
import com.searshc.twilight.util.DecoderUtils;
import com.searshc.twilight.util.KeyMatcher;
import com.searshc.twilight.util.ObjectBuilder;
import com.starmount.ups.sears.SegmentIndex;


public class UPASResponseFinder
{
  private static Logger logger = Logger.getLogger(UPASResponseFinder.class);
  private final UpasResponseParser parser = new UpasResponseParser();
  private final List<byte[]> results = ObjectBuilder.getObjects();
  private final Map<String, byte[]> fileInquiryMap = ObjectBuilder.getFileInqObjects();
  private final SegmentFactory factory = new SegmentFactory();
  
  public byte[] findResponse(byte[] reqBuffer)
  {
    final PluInquiryD8Segment pluReqInq = new PluInquiryD8Segment(reqBuffer);
    String pluItemNumber = pluReqInq.getPLUItemNumber();
    String pluSKU = pluReqInq.getPLUSKU();
    String pluDivisionNumber = pluReqInq.getPLUDivisionNumber();
    String d3respIndicator = StringUtils.EMPTY;
    if(reqBuffer != null){
      if (String.format("%02X", reqBuffer[0]).contains(TwilightConstants.INDICATOR_D3)){
        if (keyMatch("îPMP?????", byteToChar(reqBuffer))){
          d3respIndicator = TwilightConstants.INDICATOR_PMP;
          System.out.println("Request recieved for file inquiry: " + d3respIndicator);
        }
        else if (keyMatch("îMP0000", byteToChar(reqBuffer))){
          d3respIndicator = TwilightConstants.INDICATOR_MP0;
          System.out.println("Request recieved for file inquiry: " + d3respIndicator);
        }
        else if (keyMatch("îD00000", byteToChar(reqBuffer))){
          d3respIndicator = TwilightConstants.INDICATOR_D00;
          System.out.println("Request recieved for file inquiry: " + d3respIndicator);
        }
          return fileInquiryResponse(d3respIndicator);
      }else if (String.format("%02X", reqBuffer[0]).contains(TwilightConstants.INDICATOR_D8)){
        if(StringUtils.isNotBlank(pluItemNumber) && StringUtils.isNotBlank(pluSKU) && StringUtils.isNotBlank(pluDivisionNumber)){
            System.out.println("Request recieved for item no: " + pluDivisionNumber + pluItemNumber + pluSKU);
          if (pluSKU.equals("000") && pluDivisionNumber.equals("998") && pluItemNumber.equals("99999")){
            return pluInquiryEAResponse(TwilightConstants.INDICATOR_EA);
          }else 
            if (pluSKU.equals("000") && pluDivisionNumber.equals("099") && pluItemNumber.equals("99999")){
              System.out.println("Request recieved for item no: " + pluDivisionNumber + pluItemNumber + pluSKU);
            return pluInquiry98Response(TwilightConstants.INDICATOR_98);
          }else{
            if(StringUtils.isNotBlank(pluItemNumber) && StringUtils.isNotBlank(pluSKU) && StringUtils.isNotBlank(pluDivisionNumber)){
              System.out.println("Request recieved for item no: " + pluDivisionNumber + pluItemNumber + pluSKU);
            return pluInquiryResponse(pluItemNumber);
            }
          }
        }
      }else if (String.format("%02X%02X", reqBuffer[0], reqBuffer[1]).contains(TwilightConstants.INDICATOR_2AA7)){
          final CouponInquiry2AA7Segment segment = new CouponInquiry2AA7Segment(reqBuffer);
          String couponNumber = segment.getCouponNumber();
          System.out.println("Request recieved for coupon number: " + couponNumber);
            if(StringUtils.isNotBlank(couponNumber)){
              return couponInquiryResponse(couponNumber);
            }
          }
        }
    return null;
  }
  
  private byte[] fileInquiryResponse(String indicator){
    byte [] fileInqResp = null;
    if(fileInquiryMap != null){
      if(StringUtils.isNotBlank(indicator)){
        fileInqResp = fileInquiryMap.get(indicator);
      }
    }
    return fileInqResp;
  }
  
  private byte[] pluInquiryResponse(String itemNumber){
    StringBuilder builder = new StringBuilder();
    if(results.size() > 0){
      Iterator<byte[]> itr = results.iterator();
        while(itr.hasNext()){
            byte buf[] = itr.next();
            if(buf != null){
              if(String.format("%02X", buf[0]).contains(TwilightConstants.INDICATOR_E8))
                builder.append(byteResponse(buf));
              else if(String.format("%02X", buf[0]).contains(TwilightConstants.INDICATOR_E9))
                builder.append(byteResponse(buf));
              else if(String.format("%02X", buf[0]).contains(TwilightConstants.INDICATOR_EC))
                builder.append(byteResponse(buf));
              else if(String.format("%02X", buf[0]).contains(TwilightConstants.INDICATOR_95))
                builder.append(byteResponse(buf));
              else if(String.format("%02X", buf[0]).contains(TwilightConstants.INDICATOR_9C))
                builder.append(byteResponse(buf));
              else if(String.format("%02X%02X", buf[0], buf[1]).contains(TwilightConstants.INDICATOR_40BA))
                builder.append(byteResponse(buf));
              else if(String.format("%02X%02X", buf[0], buf[1]).contains(TwilightConstants.INDICATOR_58B1))
                builder.append(byteResponse(buf));
              else if(String.format("%02X%02X", buf[0], buf[1]).contains(TwilightConstants.INDICATOR_60B1))
                builder.append(byteResponse(buf));
              else if(String.format("%02X%02X", buf[0], buf[1]).contains(TwilightConstants.INDICATOR_62B1))
                builder.append(byteResponse(buf));
              }
            }
          }
      if(validateResponse(DecoderUtils.buildResponse(builder))){
        return DecoderUtils.buildResponse(builder);
      }else 
        return null;
    }
  
  private byte[] pluInquiryEAResponse(String indicator){
    if(results.size() > 0){
      Iterator<byte[]> itr = results.iterator();
        while(itr.hasNext()){
            byte buf[] = itr.next();
            if(buf != null){
              if (String.format("%02X", buf[0]).contains(indicator)){
                if(validateResponse(buf))
                  return buf;
              }
            }
         }
      }
    return null;
  }
  
  private byte[] pluInquiry98Response(String indicator){
    if(results.size() > 0){
      Iterator<byte[]> itr = results.iterator();
        while(itr.hasNext()){
            byte buf[] = itr.next();
            if(buf != null){
              if (String.format("%02X", buf[0]).contains(indicator)){
                if(validateResponse(buf))
                  return buf;
              }
            }
         }
      }
    return null;
  }
  
  private byte[] couponInquiryResponse(String requestCoupon){
    if(results.size() > 0){
      Iterator<byte[]> itr = results.iterator();
        while(itr.hasNext()){
            byte buf[] = itr.next();
            if(buf != null){
              if (String.format("%02X%02X", buf[0], buf[1]).contains(TwilightConstants.INDICATOR_2AB7)){
                final CouponInquiry2AA7Segment segment = new CouponInquiry2AA7Segment(buf);
                String responseCoupon = segment.getCouponNumber();
                if(StringUtils.isNotBlank(responseCoupon) && responseCoupon.equalsIgnoreCase(requestCoupon)){
                  if(validateResponse(buf))
                    return buf;
                }
              }
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
      List<SegmentIndex> segIndexes = parser.parseResponse(buf); //just to check response is correct
      if(segIndexes.size() > 0)
        return Boolean.TRUE;
    }catch(Exception ex){
      System.out.println("Exception " + ex);
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