package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.Order;

public class SywrNumberValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "SYWRNumber"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getSywrNumber();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }

}
