package com.searshc.mercy.validation.command;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.upas.sears.service.domain.Allocation;
import com.upas.sears.service.domain.Distribution;
import com.upas.sears.service.domain.Order;

public class AllocationValidationCommand extends AbstractStringValidationCommand
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
        System.out.println("allocations.size() : " + allocations.size());
        System.out.println("baseValueForAllocations.size() : " + baseValueForAllocations.size());
        
     for(Allocation allocation : allocations) 
     {
       for(Allocation baseValueForAllocation : baseValueForAllocations)
       {
         String searsCardProvided = allocation.getSearsCardProvided();
         String baseSearsCardProvided = baseValueForAllocation.getSearsCardProvided();

         System.out.println("searsCardProvided : " + searsCardProvided);
         System.out.println("baseSearsCardProvided : " + baseSearsCardProvided);
         
         if(StringUtils.isNotBlank(searsCardProvided) && StringUtils.isNotBlank(baseSearsCardProvided))
         {
           results.put("searsCardProvided", searsCardProvided.trim().equalsIgnoreCase(baseSearsCardProvided.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         String mrmSelected = allocation.getMrmSelected();
         String baseMrmSelected = baseValueForAllocation.getMrmSelected();

         System.out.println("mrmSelected : " + mrmSelected);
         System.out.println("baseMrmSelected : " + baseMrmSelected);
         
         if(StringUtils.isNotBlank(mrmSelected) && StringUtils.isNotBlank(baseMrmSelected))
         {
           results.put("mrmSelected", mrmSelected.trim().equalsIgnoreCase(baseMrmSelected.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         String currentCartAllocation = allocation.getCurrentCartAllocation();
         String baseCurrentCartAllocation = baseValueForAllocation.getCurrentCartAllocation();

         System.out.println("currentCartAllocation : " + currentCartAllocation);
         System.out.println("baseCurrentCartAllocation : " + baseCurrentCartAllocation);
         
         if(StringUtils.isNotBlank(currentCartAllocation) && StringUtils.isNotBlank(baseCurrentCartAllocation))
         {
           results.put("currentCartAllocation", currentCartAllocation.trim().equalsIgnoreCase(baseCurrentCartAllocation.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }    
      
         List<Distribution> distributions = allocation.getDistributions();
         List<Distribution> baseDistributions = baseValueForAllocation.getDistributions();      
         
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
