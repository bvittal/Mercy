package com.searshc.mercy.validation.command;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.upas.sears.service.domain.Rebate;
import com.upas.sears.service.domain.Order;


public class RebateValidationCommand extends AbstractStringValidationCommand
{
  Multimap<String, Boolean> results = ArrayListMultimap.create();   
  
  @Override
  public boolean passes(Order order)
  { 
    if(order != null)
    {
      List<Rebate> rebates = order.getRebates();
      if(baseValueForRebates != null && baseValueForRebates.size() >0 
          && rebates != null && rebates.size() >0)
    {
        System.out.println("rebates.size() : " + rebates.size());
        System.out.println("baseValueForRebates.size() : " + baseValueForRebates.size());
        
     for(Rebate rebate : rebates) 
     {
       for(Rebate baseValueRebate : baseValueForRebates)
       {
         String rebateAmount = rebate.getAmount();
         String baseRebateAmount = baseValueRebate.getAmount();

         System.out.println("rebateAmount : " + rebateAmount);
         System.out.println("baseRebateAmount : " + baseRebateAmount);
         
         if(StringUtils.isNotBlank(rebateAmount) && StringUtils.isNotBlank(baseRebateAmount))
         {
           results.put("rebateAmount", rebateAmount.trim().equalsIgnoreCase(baseRebateAmount.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         String rebateQuantity = rebate.getQuantity();
         String baseRebateQuantity = baseValueRebate.getQuantity();

         System.out.println("rebateQuantity : " + rebateQuantity);
         System.out.println("baseRebateQuantity : " + baseRebateQuantity);
         
         if(StringUtils.isNotBlank(rebateQuantity) && StringUtils.isNotBlank(baseRebateQuantity))
         {
           results.put("rebateQuantity", rebateQuantity.trim().equalsIgnoreCase(baseRebateQuantity.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         String rebateMethod = rebate.getMethod();
         String baseRebateMethod = baseValueRebate.getMethod();
         
         System.out.println("rebateMethod : " + rebateMethod);
         System.out.println("baseRebateMethod : " + baseRebateMethod);
         
         if(StringUtils.isNotBlank(rebateMethod) && StringUtils.isNotBlank(baseRebateMethod))
         {
           results.put("rebateMethod", rebateMethod.trim().equalsIgnoreCase(baseRebateMethod.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
             
         
         String rebateThreshold = rebate.getThreshold();
         String baseRebateThreshold = baseValueRebate.getThreshold();
         
         System.out.println("rebateThreshold : " + rebateThreshold);
         System.out.println("baseRebateThreshold : " + baseRebateThreshold);
         
         if(StringUtils.isNotBlank(rebateThreshold) && StringUtils.isNotBlank(baseRebateThreshold))
         {
           results.put("rebateThreshold", rebateThreshold.trim().equalsIgnoreCase(baseRebateThreshold.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         String rebateMiscAccountNumber = rebate.getMiscAccountNumber();
         String baseRebateMiscAccountNumber = baseValueRebate.getMiscAccountNumber();
         
         System.out.println("rebateMiscAccountNumber : " + rebateMiscAccountNumber);
         System.out.println("baseRebateMiscAccountNumber : " + baseRebateMiscAccountNumber);
         
         if(StringUtils.isNotBlank(rebateMiscAccountNumber) && StringUtils.isNotBlank(baseRebateMiscAccountNumber))
         {
           results.put("rebateMiscAccountNumber", rebateMiscAccountNumber.trim().equalsIgnoreCase(baseRebateMiscAccountNumber.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         }
         break;
          }
        }
      }
    
    for (Map.Entry<String, Boolean> entry : results.entries())
    {
           System.out.println("Validating : " + entry.getKey() + " - Match Found : " + entry.getValue());
    }
    
    if(results.containsValue(Boolean.FALSE))
    {
      return Boolean.FALSE;
    }
    return true;
   }

  @Override
  protected String getParameterName()
  {
    return "Rebates Parameter";
  }
}
