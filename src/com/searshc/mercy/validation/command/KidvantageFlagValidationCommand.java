package com.searshc.mercy.validation.command;

import com.upas.sears.service.domain.Order;

public class KidvantageFlagValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "KidvantageFlag"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getKidvantageFlag();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }

}
