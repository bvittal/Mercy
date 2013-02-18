package com.searshc.mercy.validation.command;

import com.upas.sears.service.domain.Order;

public class RegisterNumberValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "RegisterNumber"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getRegisterNumber();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }

}
