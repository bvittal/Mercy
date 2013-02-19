package com.searshc.mercy;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.searshc.mercy.service.ResponseSeg60B1Validation;
import com.searshc.mercy.service.MercyConstants;
import com.searshc.mercy.util.DecoderUtils;

public class Test
{
  
  private void testMethod(){
    
    StringBuilder baseBuilder = new StringBuilder();
    //baseBuilder.append("E8 30 30 33 35 32 39 39 35 30 30 30 30 33 31 30 32 30 30 33 30 53 4F 4E 59 20 44 53 43 54 39 2C 53 4E 59 20 30 20 53 4C 56 52 31 32 4D 50 2D 30 30 30 30 30 31 2D 30 30 30 30 30 31 33 20 20 30 30 30 30 30 30 10 08 00 90");
    baseBuilder.append("60 B1 30 32 4E 30 30 30 30 30 30 31 38 30 38 4E 49 57 50 31 38 4D 4F 30 30 30 30 30 30 31 4E 59 59 59 30 35 34 39 20 20 20 20 20 20 20 20 20 20 4E 4F 20 49 4E 54 45 52 45 53 54 20 46 4F 52 20 31 38 20 4D 4F 4E 54 48 53 20 20 20 20 20 4F 4E 20 54 48 49 53 20 50 55 52 43 48 41 53 45 20 20 20 20 20 20 20 20 20 20 20 20 20 20 34 4E 4F 20 49 4E 54 45 52 45 53 54 20 49 46 20 50 41 49 44 20 49 4E 20 46 55 4C 4C 20 57 49 54 48 49 4E 20 20 20 20 20 20 31 38 20 4D 4F 4E 54 48 53 20 44 45 46 45 52 52 45 44 20 49 4E 54 45 52 45 53 54 20 20 20 20 20 20 20 20 20 20 20 20 20 46 49 4E 41 4E 43 49 4E 47 20 57 48 45 4E 20 59 4F 55 20 55 53 45 20 59 4F 55 52 20 53 45 41 52 53 20 20 20 20 20 20 20 43 41 52 44 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20");
    
    ResponseSeg60B1Validation valid = new ResponseSeg60B1Validation(buildRequest(baseBuilder));

    String indicator = "60 B1";
    String segmentLevel = "02";
    String offerType = "N";
    String offerId = "1808";
    String financialCode = "NIWP18MO00";
    String thresholdDollarAmount = "49.99";
    String associateDiscountFlag = "N";
    String miscellaneousReductionsFlag = "Y";
    String instantRebateFlag = "Y";
    String mailInRebateFlag = "Y";
    String delayedBillingEndDateInterval = "549";
    String delayedBillingEndDate = "null";
    String registerPromoDescriptionLine1 = "null";
    String registerPromoDescriptionLine2 = "null";
    String nbrOfReceiptPromoDescriptionLines = "4";
    String receiptPromoDescriptionLine1 = "null";
    String receiptPromoDescriptionLine2 = "null";
    String receiptPromoDescriptionLine3 = "null";
    String receiptPromoDescriptionLine4 = "null";
    
      segmentLevel = StringUtils.rightPad(segmentLevel, 2,'0');
      offerType = StringUtils.rightPad(offerType, 1,'N');
      offerId = StringUtils.leftPad(offerId, 10,'0');
      financialCode = StringUtils.rightPad(financialCode, 10,'0');
      thresholdDollarAmount = StringUtils.leftPad(thresholdDollarAmount.replace(".",StringUtils.EMPTY), 5);
      associateDiscountFlag = StringUtils.rightPad(associateDiscountFlag, 1,'N');
      miscellaneousReductionsFlag = StringUtils.rightPad(miscellaneousReductionsFlag, 1,'N');
      instantRebateFlag = StringUtils.rightPad(instantRebateFlag, 1,'N');
      mailInRebateFlag = StringUtils.rightPad(mailInRebateFlag, 1,'N');
      delayedBillingEndDateInterval = StringUtils.leftPad(delayedBillingEndDateInterval, 4, '0');
      delayedBillingEndDate = StringUtils.rightPad(StringUtils.EMPTY, 10);
      registerPromoDescriptionLine1 = StringUtils.leftPad(StringUtils.EMPTY, 30, StringUtils.EMPTY);
      registerPromoDescriptionLine2 = StringUtils.leftPad(StringUtils.EMPTY, 30, StringUtils.EMPTY);
      nbrOfReceiptPromoDescriptionLines = StringUtils.rightPad(nbrOfReceiptPromoDescriptionLines, 1,'1');
      receiptPromoDescriptionLine1 = StringUtils.leftPad(StringUtils.EMPTY, 40);
      receiptPromoDescriptionLine2 = StringUtils.leftPad(StringUtils.EMPTY, 40);
      receiptPromoDescriptionLine3 = StringUtils.leftPad(StringUtils.EMPTY, 40);
      receiptPromoDescriptionLine4 = StringUtils.leftPad(StringUtils.EMPTY, 40);
      
    StringBuilder sb = new StringBuilder();
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(segmentLevel.getBytes()))
    .append(this.byteResponse(offerType.getBytes()))
    .append(this.byteResponse(offerId.getBytes()))
    .append(this.byteResponse(financialCode.getBytes()))
    .append(this.byteResponse(thresholdDollarAmount.getBytes()))
    .append(this.byteResponse(associateDiscountFlag.getBytes()))
    .append(this.byteResponse(miscellaneousReductionsFlag.getBytes()))
    .append(this.byteResponse(instantRebateFlag.getBytes()))
    .append(this.byteResponse(mailInRebateFlag.getBytes()))
    .append(this.byteResponse(delayedBillingEndDateInterval.getBytes()))
    .append(this.byteResponse(delayedBillingEndDate.getBytes()))
    .append(this.byteResponse(registerPromoDescriptionLine1.getBytes()))
    .append(this.byteResponse(registerPromoDescriptionLine2.getBytes()))
    .append(this.byteResponse(nbrOfReceiptPromoDescriptionLines.getBytes()))
    .append(this.byteResponse(receiptPromoDescriptionLine1.getBytes()))
    .append(this.byteResponse(receiptPromoDescriptionLine2.getBytes()))
    .append(this.byteResponse(receiptPromoDescriptionLine3.getBytes()))
    .append(this.byteResponse(receiptPromoDescriptionLine4.getBytes()));
    
    System.out.println("BASE REQUEST " + byteResponse(buildRequest(baseBuilder)));
    System.out.println("NEWW REQUEST " + byteResponse(buildRequest(sb)));
    
    if(DecoderUtils.lengthMatch(MercyConstants.INDICATOR_60B1, sb)){
      System.out.println("TRUE");
    }
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
