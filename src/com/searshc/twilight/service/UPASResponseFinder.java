package com.searshc.twilight.service;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.Multimap;
import com.searshc.twilight.util.KeyMatcher;
import com.searshc.twilight.util.ObjectBuilder;

public class UPASResponseFinder
{ 
  private static Logger logger = Logger.getLogger(UPASResponseFinder.class);
  
  private static final String REQUEST_INDICATOR_2AB7 = "2AA7";
  private static final String REQUEST_INDICATOR_D8 = "D8";
  private static final String REQUEST_INDICATOR_D3 = "D3";
  
  private final SegmentFactory segFactory = new SegmentFactory();
  private final Multimap<String, byte[]> results = ObjectBuilder.getObjects();
  
  public byte[] findResponse(byte[] reqBuffer)
  { 
    Segment reqSeg = null;
    Segment respSeg = null;
    
    if (results.size() > 0)
    { 
      final String indicator = this.getIndicator(reqBuffer);
      
        if(isValidRequest(indicator,reqBuffer)){
          if(indicator.equals(REQUEST_INDICATOR_D8)){
                reqSeg = segFactory.getSegment(indicator, reqBuffer);
                String pluSKU = reqSeg.pluSKU();
                String pluDivisionNumber = reqSeg.pluDivisionNumber();
                String pluItemNumber = reqSeg.pluItemNumber();
                
                if(pluSKU.equals("000") && pluDivisionNumber.equals("998") && pluItemNumber.equals("99999")){
                  Collection<byte[]> responseObject = results.get("EA");
                  for (byte[] respBuffer : responseObject){
                      //System.out.println(byteResponse(respBuffer));
                      return respBuffer;
                  }
                }else if(pluSKU.equals("000") && pluDivisionNumber.equals("999") && pluItemNumber.equals("99999")){
                  Collection<byte[]> responseObject = results.get("98");
                  for (byte[] respBuffer : responseObject){
                    //System.out.println(byteResponse(respBuffer));
                    return respBuffer;
                  }
                }
                else 
                {
                    Collection<byte[]> responseObject = results.get("E8");
                    for (byte[] respBuffer : responseObject){
                      //System.out.println(byteResponse(respBuffer));
                      return respBuffer;
                    }  
                  }
                }
                else if(indicator.equals(REQUEST_INDICATOR_2AB7)){
                  Collection<byte[]> responseObject = results.get(indicator);
                  for (byte[] respBuffer : responseObject){
                    reqSeg = segFactory.getSegment(indicator, reqBuffer);
                    respSeg = segFactory.getSegment(indicator, respBuffer);
                    if(reqSeg.couponNumber().equals(respSeg.couponNumber())){
                      //System.out.println(byteResponse(respBuffer));
                      return respBuffer;
                    }
                  }
                }
                else if(indicator.equals(REQUEST_INDICATOR_D3)){
                  String d3respIndicator = StringUtils.EMPTY;
                    if(keyMatch("ÓPMP?????", byteToChar(reqBuffer))) 
                        d3respIndicator ="PMP";
                    else if (keyMatch("ÓMP0000",byteToChar(reqBuffer)))
                       d3respIndicator ="MP0";
                    else if (keyMatch("ÓD00000",byteToChar(reqBuffer)))
                      d3respIndicator ="D00";
                  
                  Collection<byte[]> responseObject = results.get(d3respIndicator);
                  for (byte[] respBuffer : responseObject){  
                    return respBuffer;
                  }
                }
              }
              else
              {
                logger.error("Request not found/corrupted in script for indicator : " + indicator + " please correct your script");
              }
            }
                return null;
        }

  private String byteResponse(byte [] buffer){
    StringBuilder sb = new StringBuilder();

    for (byte b : buffer) {
        sb.append(String.format("%02x", b).toUpperCase());
        sb.append(" ");
    }
    return sb.toString();
  }

  public boolean isValidRequest(String indicator, byte [] request){
    Collection<byte[]> responseObject = results.get(indicator);
    for (byte[] respBuffer : responseObject){
      if (Arrays.equals(request, respBuffer))
        return Boolean.TRUE;
    }
      return Boolean.FALSE;
  }
  
  private String getIndicator(byte[] buffer) {
    String indicator = StringUtils.EMPTY;
    StringBuilder sb = new StringBuilder();
    
    for (byte b : buffer) {
        sb.append(String.format("%02x", b).toUpperCase());
        sb.append(" ");
    }
    String str = sb.toString();
    logger.info("HTTP Request : " + str);
    
    if(str.contains("D3"))
      indicator = "D3";
    else if (str.contains("D8"))
      indicator = "D8";
    else if(str.contains("2A A7"))
     indicator = "2AA7";
    
    return indicator;
  }
  
  private boolean keyMatch(String key, char[] inquiry) {
    KeyMatcher matcher = new KeyMatcher(key, inquiry);
    return matcher.isMatch();
  }
  
  public char[] byteToChar(byte[] bytes) {
    char[] buffer = new char[bytes.length];
    for (int i = 0; i < buffer.length; i++) {
        int unsignedByte = bytes[i] & 0xFF;
        char c = (char) unsignedByte;
        buffer[i] = c;
    }
    return buffer;
  }
}