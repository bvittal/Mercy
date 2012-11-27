package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.Order;

public class CraftsmanClubFlagValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "CraftsmanClubFlag"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getCraftsmanClubFlag();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }

}
