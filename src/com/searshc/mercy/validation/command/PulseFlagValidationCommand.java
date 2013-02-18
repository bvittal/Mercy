package com.searshc.mercy.validation.command;

import com.upas.sears.service.domain.Order;

public class PulseFlagValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "PulseFlag"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getPulseFlag();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }

}
