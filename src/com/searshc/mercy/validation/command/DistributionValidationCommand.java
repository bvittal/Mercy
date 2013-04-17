package com.searshc.mercy.validation.command;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.upas.sears.service.domain.Allocation;
import com.upas.sears.service.domain.Distribution;
import com.upas.sears.service.domain.Order;

public class DistributionValidationCommand extends AbstractStringValidationCommand
{
  Multimap<String, Boolean> results = ArrayListMultimap.create();   
  
  @Override
  public boolean passes(Order order)
  { 
    if(order != null)
    {
      List<Allocation> allocations = order.getAllocations();
      if(baseValueForAllocations != null && baseValueForAllocations.size() >0 
          && allocations != null && allocations.size() >0)
    {
        
     for(Allocation allocation : allocations) 
     {
       for(Allocation baseValueAllocation : baseValueForAllocations)
       {
         List<Distribution> distributions = allocation.getDistributions();
         List<Distribution> baseDistributions = baseValueAllocation.getDistributions();    
         
         System.out.println("distributions.size() : " + distributions.size());
         System.out.println("baseDistributions.size() : " + baseDistributions.size());
         
       if(distributions != null && distributions.size() > 0 && baseDistributions != null && baseDistributions.size() > 0)
       {
         for(Distribution distribution : distributions)
           {
           for(Distribution baseDistribution : baseDistributions)
           {
             String distributionAmount = distribution.getAmount();
             String baseDistributionAmount = baseDistribution.getAmount();
             
             System.out.println("distributionAmount : " + distributionAmount);
             System.out.println("baseDistributionAmount : " + baseDistributionAmount);
             
             if(StringUtils.isNotBlank(distributionAmount) && StringUtils.isNotBlank(baseDistributionAmount))
             {
               results.put("distributionAmount", distributionAmount.trim().equalsIgnoreCase(baseDistributionAmount.trim()) ? Boolean.TRUE : Boolean.FALSE);
             }
             
             break;
               }
             }
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
    return "allocations Parameter";
  }
}
