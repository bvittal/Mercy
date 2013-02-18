package com.searshc.mercy.validation.command;

import com.upas.sears.service.domain.Order;

public class SearsCardValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "SearsCard"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getSearsCard();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }

}
