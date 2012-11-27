package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.Order;

public class AssociateIdValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "AssociateId"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getAssociateId();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }

}
