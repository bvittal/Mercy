package com.searshc.mercy.validation.command;

import com.upas.sears.service.domain.Order;

public class AssociateFlagValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "AssociateFlag"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getAssociateFlag();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }
}
