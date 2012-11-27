package com.searshc.twilight.service;

public class SegmentFactory
{
  public Segment getSegment(String indicator, byte [] buffer){
    Segment segment = null;
    
    if (indicator.contains("D8"))
      segment = new ResponseSegD8Validation(buffer);
    else if (indicator.contains("EA"))
      segment = new ResponseSegEAValidation(buffer);
    else if (indicator.equals("E8"))
      segment = new ResponseSegE8Validation(buffer);
    else if (indicator.equals("EC"))
      segment = new ResponseSegECValidation(buffer);
    else if (indicator.equals("9C"))
      segment = new ResponseSeg9CValidation(buffer);
    else if(String.format("%02X%02X", buffer[0], buffer[1]).contains("2AA7"))
      segment = new ResponseSeg2AA7Validation(buffer);
    else if(String.format("%02X%02X", buffer[0], buffer[1]).contains("2AB7"))
        segment = new ResponseSeg2AB7Validation(buffer);
    else if (String.format("%02X%02X", buffer[0], buffer[1]).contains("40BA"))
      segment = new ResponseSeg40BAValidation(buffer);
    else if (String.format("%02X%02X", buffer[0], buffer[1]).contains("60B1"))
      segment = new ResponseSeg60B1Validation(buffer);
    return segment;
      }
  }