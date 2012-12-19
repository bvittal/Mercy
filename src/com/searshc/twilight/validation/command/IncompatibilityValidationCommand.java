package com.searshc.twilight.validation.command;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.upas.sears.service.domain.AppMessage;
import com.upas.sears.service.domain.Incompatibility;
import com.upas.sears.service.domain.Order;
import com.upas.sears.service.domain.UnappliedOffer;

public class IncompatibilityValidationCommand extends AbstractStringValidationCommand
{
  Multimap<String, Boolean> results = ArrayListMultimap.create();   
  
  @Override
  public boolean passes(Order order)
  { 
    if(order != null)
    {
      Incompatibility[] incompatibility = order.getIncompatibilities();
      if(baseValueForIncompatibilities != null && baseValueForIncompatibilities.length >0 
          && incompatibility != null && incompatibility.length >0)
    {
     for(Incompatibility orderIncomp : incompatibility) 
     {
       for(Incompatibility baseValueIncomp : baseValueForIncompatibilities)
       {
         String optedPromo = orderIncomp.getOptedPromo();
         String baseOptedPromo = baseValueIncomp.getOptedPromo();
       
         if(StringUtils.isNotBlank(optedPromo) && StringUtils.isNotBlank(baseOptedPromo))
         {
           results.put("optedPromo", optedPromo.trim().equalsIgnoreCase(baseOptedPromo.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         UnappliedOffer[] unappliedOffers = orderIncomp.getUnappliedOffers();
         UnappliedOffer[] baseUnappliedOffers = baseValueIncomp.getUnappliedOffers();
         
         for(UnappliedOffer unappliedOffer : unappliedOffers)
           {
           for(UnappliedOffer baseUnappliedOffer : baseUnappliedOffers)
           {
             String offerId = unappliedOffer.getOfferId();
             String baseOfferId = baseUnappliedOffer.getOfferId();
             
             if(StringUtils.isNotBlank(offerId) && StringUtils.isNotBlank(baseOfferId))
             {
               results.put("offerId", offerId.trim().equalsIgnoreCase(baseOfferId.trim()) ? Boolean.TRUE : Boolean.FALSE);
             }
             
             AppMessage[] reasons = unappliedOffer.getReasons();
             AppMessage[] baseReasons = baseUnappliedOffer.getReasons();
             
             for(AppMessage reason : reasons)
             {
               for(AppMessage baseReason : baseReasons)
               {
                 String code = reason.getCode();
                 String baseCode = baseReason.getCode();
                 
                 if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(baseCode))
                 {
                   results.put("code", code.trim().equalsIgnoreCase(baseCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
                 }
                 
                 String description = reason.getDescription();
                 String baseDescription = baseReason.getDescription();
                 
                 if(StringUtils.isNotBlank(description) && StringUtils.isNotBlank(baseDescription))
                 {
                   results.put("description", description.trim().equalsIgnoreCase(baseDescription.trim()) ? Boolean.TRUE : Boolean.FALSE);
                 }
                 
                 String details = reason.getDetails();
                 String baseDetails = baseReason.getDetails();
                 
                 if(StringUtils.isNotBlank(details) && StringUtils.isNotBlank(baseDetails))
                 {
                   results.put("details", details.trim().equalsIgnoreCase(baseDetails.trim()) ? Boolean.TRUE : Boolean.FALSE);
                 }
                 
                 String[] fields = reason.getFields();
                 String[] baseFields = baseReason.getFields();
                 
                 for(String field : fields)
                 {
                   for(String baseField : baseFields)
                   {
                     if(StringUtils.isNotBlank(field) && StringUtils.isNotBlank(baseField))
                     {
                       results.put("fields", field.trim().equalsIgnoreCase(baseField.trim()) ? Boolean.TRUE : Boolean.FALSE);
                               }
                             }
                           }
                         } 
                       }
                     }
                 }
               }
             }
           }
         }
    
    for (Map.Entry<String, Boolean> entry : results.entries())
    {
           System.out.println("Validating : " + entry.getKey() + " - Match Found : " + entry.getValue() + "\n");
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
    return "Incompatibilities Parameter";
  }
}
