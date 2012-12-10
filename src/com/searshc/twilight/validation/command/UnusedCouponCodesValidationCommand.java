package com.searshc.twilight.validation.command;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.upas.sears.service.domain.CoupounCode;
import com.upas.sears.service.domain.Order;

public class UnusedCouponCodesValidationCommand extends AbstractStringValidationCommand
{
  protected String getParameterName() { return "UnusedCouponCodes Parameter"; }
  
  public boolean passes(Order order)
  {
    Multimap<String, Boolean> results = ArrayListMultimap.create();   
    List<CoupounCode> couponList = order.getUnusedCouponCodes();
    int index = 0;
    
    if(couponList != null && baseValueforUnusedCoupons != null)
    {
      for(CoupounCode couponCode : couponList)
      {
        for(CoupounCode baseCouponCode : baseValueforUnusedCoupons)
        {
          String couponNumber = couponCode.getCodes()[index];
          String baseCouponNumber = baseCouponCode.getCodes()[index];
            if(StringUtils.isNotBlank(couponNumber) && StringUtils.isNotBlank(baseCouponNumber) && couponNumber.equalsIgnoreCase(baseCouponNumber))
            {
            String reasonCode = couponCode.getReason();
            String baseReasonCode = baseCouponCode.getReason();
              if(StringUtils.isNotBlank(reasonCode) && StringUtils.isNotBlank(baseReasonCode))
                results.put(couponNumber, reasonCode.equalsIgnoreCase(baseReasonCode) ? Boolean.TRUE : Boolean.FALSE);
              }else{
                System.err.println("No matching unused coupon found in script to validate\n");
              }
            index++;
            break;
            }
          }
        }
    
    for (Map.Entry<String, Boolean> entry : results.entries())
    {
           System.out.println("Validating UnusedCouponCodes for coupon : " + entry.getKey() + " - reason code found is : " + entry.getValue() + "\n");
    }
    
    if(results.containsValue(Boolean.FALSE))
    {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
   }
}
