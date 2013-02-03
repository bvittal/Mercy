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
        System.out.println("incompatibility.length : " + incompatibility.length);
        System.out.println("baseValueForIncompatibilities.length : " + baseValueForIncompatibilities.length);
        
     for(Incompatibility orderIncomp : incompatibility) 
     {
       for(Incompatibility baseValueIncomp : baseValueForIncompatibilities)
       {
         String optedPromo = orderIncomp.getOptedPromo();
         String baseOptedPromo = baseValueIncomp.getOptedPromo();

         System.out.println("optedPromo : " + optedPromo);
         System.out.println("baseOptedPromo : " + baseOptedPromo);
         
         if(StringUtils.isNotBlank(optedPromo) && StringUtils.isNotBlank(baseOptedPromo))
         {
           results.put("optedPromo", optedPromo.trim().equalsIgnoreCase(baseOptedPromo.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         String optedPromoType = orderIncomp.getOptedPromoType();
         String baseOptedPromoType = baseValueIncomp.getOptedPromoType();

         System.out.println("optedPromoType : " + optedPromoType);
         System.out.println("baseOptedPromoType : " + baseOptedPromoType);
         
         if(StringUtils.isNotBlank(optedPromoType) && StringUtils.isNotBlank(baseOptedPromoType))
         {
           results.put("optedPromoType", optedPromoType.trim().equalsIgnoreCase(baseOptedPromoType.trim()) ? Boolean.TRUE : Boolean.FALSE);
         }
         
         UnappliedOffer[] unappliedOffers = orderIncomp.getUnappliedOffers();
         UnappliedOffer[] baseUnappliedOffers = baseValueIncomp.getUnappliedOffers();
         
       if(unappliedOffers != null && baseUnappliedOffers != null)
       {
         for(UnappliedOffer unappliedOffer : unappliedOffers)
           {
           for(UnappliedOffer baseUnappliedOffer : baseUnappliedOffers)
           {
             String offerId = unappliedOffer.getOfferId();
             String baseOfferId = baseUnappliedOffer.getOfferId();
             
             System.out.println("offerId : " + offerId);
             System.out.println("baseOfferId : " + baseOfferId);
             
             if(StringUtils.isNotBlank(offerId) && StringUtils.isNotBlank(baseOfferId))
             {
               results.put("offerId", offerId.trim().equalsIgnoreCase(baseOfferId.trim()) ? Boolean.TRUE : Boolean.FALSE);
             }
             
             AppMessage[] reasons = unappliedOffer.getReasons();
             AppMessage[] baseReasons = baseUnappliedOffer.getReasons();
             
          if(reasons != null && baseReasons != null)
          {
            int reasonBase_index = 0;
            
             for(AppMessage reason : reasons)
             {
               //for(AppMessage baseReason : baseReasons)
               for (int index = reasonBase_index; index <  baseReasons.length;)
               {
                 String code = reason.getCode();
                 String baseCode = baseReasons[index].getCode();
                 
                 System.out.println("AppMessage - code : " + code);
                 System.out.println("AppMessage - baseCode : " + baseCode);
                 
                 if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(baseCode))
                 {
                   results.put("code", code.trim().equalsIgnoreCase(baseCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
                 }
                 
                 String description = reason.getDescription();
                 String baseDescription = baseReasons[index].getDescription();
                 
                 System.out.println("AppMessage - description : " + description);
                 System.out.println("AppMessage - baseDescription : " + baseDescription);
                 
                 if(StringUtils.isNotBlank(description) && StringUtils.isNotBlank(baseDescription))
                 {
                   results.put("description", description.trim().equalsIgnoreCase(baseDescription.trim()) ? Boolean.TRUE : Boolean.FALSE);
                 }
                 
                 String details = reason.getDetails();
                 String baseDetails = baseReasons[index].getDetails();
                 
                 System.out.println("AppMessage - details : " + details);
                 System.out.println("AppMessage - baseDetails : " + baseDetails);
                 
                 if(StringUtils.isNotBlank(details) && StringUtils.isNotBlank(baseDetails))
                 {
                   results.put("details", details.trim().equalsIgnoreCase(baseDetails.trim()) ? Boolean.TRUE : Boolean.FALSE);
                 }
                 
                 String[] fields = reason.getFields();
                 String[] baseFields = baseReasons[index].getFields();
                 
              if(fields != null && baseFields != null)
              {
                 for(String field : fields)
                 {
                   for(String baseField : baseFields)
                   {
                     if(StringUtils.isNotBlank(field) && StringUtils.isNotBlank(baseField))
                     {
                       results.put("fields", field.trim().equalsIgnoreCase(baseField.trim()) ? Boolean.TRUE : Boolean.FALSE);
                        }
                     break;
                     }
                   }
                }
                reasonBase_index++;
                break;
                 } 
               }
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
