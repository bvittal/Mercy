package com.searshc.twilight.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.Multimap;
import com.searshc.twilight.util.KeyMatcher;
import com.searshc.twilight.util.ObjectBuilder;
import com.starmount.ups.sears.SegmentIndex;
import com.starmount.ups.sears.responses.segmentE8.ResponseSegmentE8;

public class UPASResponseFinder
{
  private static Logger logger = Logger.getLogger(UPASResponseFinder.class);

  private static final String REQUEST_INDICATOR_COUPON_INQ = "COUPON_INQ";
  private static final String REQUEST_INDICATOR_PLU_INQ = "PLU_INQ";
  private static final String REQUEST_INDICATOR_FILE_INQ = "FILE_INQ";

  private final SegmentFactory segFactory = new SegmentFactory();
  private final Multimap<String, byte[]> results = ObjectBuilder.getObjects();
  private final UpasResponseParser parser = new UpasResponseParser();

  public byte[] findResponse(byte[] reqBuffer)
  {
    Segment reqSeg = null;
    Segment respSeg = null;
    StringBuilder sb = new StringBuilder();

    if (results.size() > 0)
    {
      final String indicator = this.getIndicator(reqBuffer);

      System.out.println("REQUEST RECIEVED    " + byteResponse(reqBuffer));
      if (isValidRequest(indicator, reqBuffer))
      {
        if (indicator.equals(REQUEST_INDICATOR_PLU_INQ))
        {
          reqSeg = segFactory.getSegment(indicator, reqBuffer);
          String pluSKU = reqSeg.pluSKU();
          String pluDivisionNumber = reqSeg.pluDivisionNumber();
          String pluItemNumber = reqSeg.pluItemNumber();

          if (pluSKU.equals("000") && pluDivisionNumber.equals("998")
              && pluItemNumber.equals("99999"))
          {
            Collection<byte[]> responseObject = results.get("EA");
            if (responseObject != null)
            {
              for (byte[] respBuffer : responseObject)
              {
                return respBuffer;
              }
            }
          }
          else if (pluSKU.equals("000") && pluDivisionNumber.equals("099")
              && pluItemNumber.equals("99999"))
          {
            Collection<byte[]> responseObject = results.get("98");
            if (responseObject != null)
            {
              for (byte[] respBuffer : responseObject)
              {
                return respBuffer;
              }
            }
          }
          else
          {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Collection<byte[]> responseObject = results.get("PLU_RSP");
            if (responseObject != null)
            {
              for (byte[] respBuffer : responseObject)
              {
                try
                {
                  System.out.println("THE FULL BUFFER : "
                      + byteResponse(respBuffer));
                  List<SegmentIndex> segmentIndexes = parser
                      .parseResponse(respBuffer);
                  for (SegmentIndex segmentIndex : segmentIndexes)
                  {
                    System.out.println("Segment "
                        + segmentIndex.getIndicatorString() + "\t at position "
                        + segmentIndex.getPosition() + " length of\t "
                        + segmentIndex.getLength());
                    if (segmentIndex.getIndicatorString().equals("E8"))
                    {
                      ResponseSegmentE8 e8Seg = segmentIndex
                          .getAsResponseSegmentE8();
                      if (e8Seg.getPLUItemNumber().equals(pluItemNumber))
                      {
                        System.out.println("Matching product number found : "
                            + pluItemNumber);
                        os.write(respBuffer);
                      }
                    }
                  }
                }
                catch (Exception e)
                {
                  e.printStackTrace();
                }
              }
            }
            return os.toByteArray();
          }
        }
        else if (indicator.equals(REQUEST_INDICATOR_COUPON_INQ))
        {
          Collection<byte[]> responseObject = results.get("COUPON_RSP");
          if (responseObject != null)
          {
            for (byte[] respBuffer : responseObject)
            {
              reqSeg = segFactory.getSegment(indicator, reqBuffer);
              respSeg = segFactory.getSegment(indicator, respBuffer);
              String coupon = reqSeg.couponNumber();
              if (coupon.equals(respSeg.couponNumber()))
              {
                System.out.println("Matching coupon number found " + coupon);
                return respBuffer;
              }
              else
              {
                System.out.println("No Matching Coupon Found ");
              }
            }
          }
        }
        else if (indicator.equals(REQUEST_INDICATOR_FILE_INQ))
        {
          String d3respIndicator = StringUtils.EMPTY;
          if (keyMatch("�PMP?????", byteToChar(reqBuffer))) d3respIndicator = "PMP";
          else if (keyMatch("�MP0000", byteToChar(reqBuffer))) d3respIndicator = "MP0";
          else if (keyMatch("�D00000", byteToChar(reqBuffer))) d3respIndicator = "D00";

          Collection<byte[]> responseObject = results.get(d3respIndicator);
          if (responseObject != null)
          {
            for (byte[] respBuffer : responseObject)
            {
              return respBuffer;
            }
          }
        }
      }
      else
      {
        logger.error("Request not found/corrupted in script for indicator : "
            + indicator + " please correct your script");
      }
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

  public boolean isValidRequest(String indicator, byte[] request)
  {
    Collection<byte[]> responseObject = results.get(indicator);
    if (responseObject != null)
    {
      for (byte[] respBuffer : responseObject)
      {
        if (Arrays.equals(request, respBuffer)) return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  private String getIndicator(byte[] buffer)
  {
    String indicator = StringUtils.EMPTY;
    StringBuilder sb = new StringBuilder();

    for (byte b : buffer)
    {
      sb.append(String.format("%02x", b).toUpperCase());
      sb.append(" ");
    }
    String str = sb.toString();
    logger.info("HTTP Request : " + str);

    if (str.contains("D3")) indicator = "FILE_INQ";
    else if (str.contains("D8")) indicator = "PLU_INQ";
    else if (str.contains("2A A7")) indicator = "COUPON_INQ";

    return indicator;
  }

  private boolean keyMatch(String key, char[] inquiry)
  {
    KeyMatcher matcher = new KeyMatcher(key, inquiry);
    return matcher.isMatch();
  }

  private char[] byteToChar(byte[] bytes)
  {
    char[] buffer = new char[bytes.length];
    for (int i = 0; i < buffer.length; i++)
    {
      int unsignedByte = bytes[i] & 0xFF;
      char c = (char) unsignedByte;
      buffer[i] = c;
    }
    return buffer;
  }
}