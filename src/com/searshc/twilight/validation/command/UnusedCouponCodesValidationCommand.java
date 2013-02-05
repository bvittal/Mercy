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
    int base_index = 0;
    
    if(couponList != null && baseValueforUnusedCoupons != null)
    {
      for(CoupounCode couponCode : couponList)
      {
        for (int index = base_index; index <  baseValueforUnusedCoupons.size();)
        {
          String couponNumber[] = couponCode.getCodes();
          String baseCouponNumber[] = baseValueforUnusedCoupons.get(index).getCodes();
          if(couponNumber.length > 0 && baseCouponNumber.length > 0){
          for(int i=0; i<couponNumber.length; i++){
            for(int j=i; j<baseCouponNumber.length; j++){
            if(StringUtils.isNotBlank(couponNumber[i]) && StringUtils.isNotBlank(baseCouponNumber[j]) && couponNumber[i].equalsIgnoreCase(baseCouponNumber[j]))
            {
            String reasonCode = couponCode.getReason();
            String baseReasonCode = baseValueforUnusedCoupons.get(index).getReason();
              if(StringUtils.isNotBlank(reasonCode) && StringUtils.isNotBlank(baseReasonCode))
                results.put(couponNumber[i], reasonCode.equalsIgnoreCase(baseReasonCode) ? Boolean.TRUE : Boolean.FALSE);
              }else{
                System.err.println("No matching unused coupon found in the script to validate\n");
              }
                  }
                }
              }
              base_index++;
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
