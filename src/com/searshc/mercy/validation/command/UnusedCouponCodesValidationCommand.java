package com.searshc.mercy.validation.command;

import java.util.Iterator;
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
  
  public boolean passes(Order order){
    Multimap<String, Boolean> results = ArrayListMultimap.create();   
    List<CoupounCode> couponList = order.getUnusedCouponCodes();
    boolean flag = Boolean.FALSE;
    
    if(couponList != null && couponList.size() > 0 && baseValueforUnusedCoupons.size() > 0){
      Iterator<CoupounCode> couponItr = couponList.iterator();
      Iterator<CoupounCode> baseCouponItr = baseValueforUnusedCoupons.iterator();
      
      while(couponItr.hasNext()){
        CoupounCode code = couponItr.next();
        String couponNumber[] = code.getCodes();
        while(baseCouponItr.hasNext()){
          CoupounCode baseCode = baseCouponItr.next();
          String baseCouponNumber[] = baseCode.getCodes();
          if(couponNumber.length > 0 && baseCouponNumber.length > 0){
            for(int i=0; i<couponNumber.length; i++){
              for(int j=0; j<baseCouponNumber.length; j++){
                if(StringUtils.isNotBlank(couponNumber[i]) && StringUtils.isNotBlank(baseCouponNumber[j]) 
                    && couponNumber[i].equalsIgnoreCase(baseCouponNumber[j])){
                  String reasonCode = code.getReason();
                  String baseReasonCode = baseCode.getReason();
                  if(StringUtils.isNotBlank(reasonCode) && StringUtils.isNotBlank(baseReasonCode)){
                    results.put(couponNumber[i], reasonCode.equalsIgnoreCase(baseReasonCode) ? Boolean.TRUE : Boolean.FALSE);
                    flag = Boolean.TRUE;
                  }
                }
              }
            }
          }
          if(flag == Boolean.TRUE)
            break;
        }
      }
    }else{
    	return Boolean.FALSE;
    }
    
    for (Map.Entry<String, Boolean> entry : results.entries())
    {
           System.out.println("\nValidating UnusedCouponCodes for coupon : " + entry.getKey() + " - reason code found is : " + entry.getValue() + "\n");
    }
    
    if(results.containsValue(Boolean.FALSE))
    {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
   }
}
