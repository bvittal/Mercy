package com.searshc.twilight.validation;

import com.searshc.twilight.validation.command.*;
import com.upas.sears.service.domain.*;

import java.util.*;

import org.codehaus.jackson.map.ObjectMapper;

public class OrderValidator extends Order implements ResponseValidator
{
  private boolean validationPassed = true;
  private List<AbstractValidationCommand> commands = new ArrayList<AbstractValidationCommand>();
  
  public void setAssociateFlag(String associateFlag)
  { 
    commands.add(new AssociateFlagValidationCommand().setBaseValue(associateFlag));
    super.setAssociateFlag(associateFlag);
  }
  
  public void setAssociateId(String associateId)
  {
    commands.add(new AssociateIdValidationCommand().setBaseValue(associateId));
    super.setAssociateId(associateId);
  }
  
  public void setCouponCodes(Coupon[] couponCodes)
  {
    super.setCouponCodes(couponCodes);
  }
  
  public void setCraftsmanClubFlag(String craftsmanClubFlag)
  {
    commands.add(new CraftsmanClubFlagValidationCommand().setBaseValue(craftsmanClubFlag));
    super.setCraftsmanClubFlag(craftsmanClubFlag);
  }
  
  public void setDeliveryDetails(DeliveryDetails deliveryDetails)
  {
    super.setDeliveryDetails(deliveryDetails);
  }
  
  public void setKidvantageFlag(String kidvantageFlag)
  {
    commands.add(new KidvantageFlagValidationCommand().setBaseValue(kidvantageFlag));
    super.setKidvantageFlag(kidvantageFlag);
  }
  
  public void setLineItems(List<LineItem> lineItems)
  {
    List<LineItem> lineItemValidators = new ArrayList<LineItem>();
    
    try
    {
      if(lineItems != null)
      {
        ObjectMapper mapper = new ObjectMapper();
        for(int i=0; i<lineItems.size(); i++)
        {
          LineItemValidator lineItemValidator = mapper.readValue(mapper.writeValueAsString(lineItems.get(i)), com.searshc.twilight.validation.LineItemValidator.class);
          lineItemValidators.add(lineItemValidator);
        }
        
        commands.add(new LineItemValidationCommand().setBaseValue(lineItems));
        super.setLineItems(lineItemValidators);
      }
      else
      {
        System.err.println("data is null");
      }
    }
    catch(Exception e)
    {
      System.err.println("error setting up validator");
    }
  }
  
  public void setOptedPromotions(String[] optedPromotions)
  {
    super.setOptedPromotions(optedPromotions);
  }
  
  public void setPhysicalStoreNumber(String physicalStoreNumber)
  {
    commands.add(new PhysicalStoreNumberValidationCommand().setBaseValue(physicalStoreNumber));
    super.setPhysicalStoreNumber(physicalStoreNumber);
  }

  public void setIncompatibilities(Incompatibility[] incompatibilities)
  {
    commands.add(new IncompatibilityValidationCommand().setBaseValueForIncompatibilities(incompatibilities));
    super.setIncompatibilities(incompatibilities);
  }
  
  public void setPulseFlag(String pulseFlag)
  {
    commands.add(new PulseFlagValidationCommand().setBaseValue(pulseFlag));
    super.setPulseFlag(pulseFlag);
  }
  
  public void setRegisterNumber(String registerNumber)
  {
    commands.add(new RegisterNumberValidationCommand().setBaseValue(registerNumber));
    super.setRegisterNumber(registerNumber);
  }
  
  public void setSearsCard(String searsCard)
  {
    commands.add(new SearsCardValidationCommand().setBaseValue(searsCard));
    super.setSearsCard(searsCard);
  }
  
  public void setSywrNumber(String sywrNumber)
  {
    commands.add(new SywrNumberValidationCommand().setBaseValue(sywrNumber));
    super.setSywrNumber(sywrNumber);
  }
  
  public void setTransactionNumber(String transactionNumber)
  {
    commands.add(new TransactionNumberValidationCommand().setBaseValue(transactionNumber));
    super.setTransactionNumber(transactionNumber);
  }
  
  public void setUnusedCouponCodes(List<CoupounCode> unusedCouponCodes)
  {
    commands.add(new UnusedCouponCodesValidationCommand().setBaseValueforUnusedCoupons(unusedCouponCodes));
    super.setUnusedCouponCodes(unusedCouponCodes);
  }

  @Override
  public String getErrorMessageString()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isValid(OrderResponse orderResponse)
  {
    boolean passes = true;
    String message = "";
    
    for(int i=0; i<commands.size(); i++)
    {
      if(!commands.get(i).passes(orderResponse.getData()))
      {
        passes = false;
        message += commands.get(i).getMessage();
      }
    }
    
    return passes;    
  }
}
