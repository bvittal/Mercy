package com.searshc.twilight;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public class Test
{
  
  private void testMethod(){
    
    StringBuilder baseBuilder = new StringBuilder();
    baseBuilder.append("E8 30 30 33 35 32 39 39 35 30 30 30 30 33 31 30 32 30 30 33 30 53 4F 4E 59 20 44 53 43 54 39 2C 53 4E 59 20 30 20 53 4C 56 52 31 32 4D 50 2D 30 30 30 30 30 31 2D 30 30 30 30 30 31 33 20 20 30 30 30 30 30 30 10 08 00 90");
    
    String indicator = "E8";
    String pluDivisionNumber = "003";
    String pluItemNumber = "52995";
    String pluSku = "000";
    String pluLineNumber = "031";
    String pluSubLineNumber = "020";
    String pluSubLineVariableNumber = "030";
    String pluItemDescription = StringUtils.EMPTY;
    String pluRegularPrice = "-0.01"; //-000001
    String pluPrice = "-0.01";
    String pluPriceType = "3";
    String sourceOrderProcessingTime = StringUtils.EMPTY;
    String biasCount = "000";
    String commitedQuantity = "000";
    String productRegistrationFlags = "";
    String flag = "2";
    
    pluDivisionNumber = StringUtils.rightPad(pluDivisionNumber, 3,'0');
    pluItemDescription = StringUtils.leftPad(pluItemDescription, 25, StringUtils.EMPTY);
    pluRegularPrice = StringUtils.rightPad(pluRegularPrice.replace(".", ""), 7,'0');
    pluPrice = StringUtils.rightPad(pluPrice.replace(".", ""), 7,'0');
    sourceOrderProcessingTime = StringUtils.rightPad(sourceOrderProcessingTime, 2, "0");
    pluPriceType = StringUtils.rightPad(pluPriceType, 1, "0");
    productRegistrationFlags = StringUtils.rightPad(productRegistrationFlags, 4, "0");
    flag = StringUtils.rightPad(flag, 1, StringUtils.EMPTY);
    
    StringBuilder sb = new StringBuilder();
    sb.append(indicator)
    .append(" ")
    .append(byteResponse(pluDivisionNumber.getBytes()))
    .append(byteResponse(pluItemNumber.getBytes()))
    .append(byteResponse(pluSku.getBytes()))
    .append(byteResponse(pluLineNumber.getBytes()))
    .append(byteResponse(pluSubLineNumber.getBytes()))
    .append(byteResponse(pluSubLineVariableNumber.getBytes()))
    .append(byteResponse(pluItemDescription.getBytes()))
    .append(byteResponse(pluRegularPrice.getBytes()))
    .append(byteResponse(pluPrice.getBytes()))
    .append(byteResponse(pluPriceType.getBytes()))
    .append(byteResponse(sourceOrderProcessingTime.getBytes()))
    .append(byteResponse(biasCount.getBytes()))
    .append(byteResponse(commitedQuantity.getBytes()))
    .append(byteResponse(productRegistrationFlags.getBytes()))
    .append(byteResponse(flag.getBytes()));
    
    System.out.println("BASE REQUEST " + byteResponse(buildRequest(baseBuilder)));
    System.out.println("NEWW REQUEST " + byteResponse(buildRequest(sb)));
    
    System.out.println(Arrays.equals(buildRequest(baseBuilder), buildRequest(sb)));
    
  }
  
  public byte[] buildRequest(StringBuilder sb) {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    for (int i = 0; i < sb.length(); i += 3) {
        String s = sb.substring(i, i + 2);
        int unsignedByte = Integer.parseInt(s, 16);
        os.write(unsignedByte);
    }
    return os.toByteArray();
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

  /**
   * @param args
   */
  public static void main(String[] args)
  {
      new Test().testMethod();
  }
}
