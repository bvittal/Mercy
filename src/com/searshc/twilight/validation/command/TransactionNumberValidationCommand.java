package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.Order;

public class TransactionNumberValidationCommand extends AbstractStringValidationCommand
{
  protected String getParameterName() { return "TransactionNumber"; }
  
  public boolean passes(Order order)
  {
    String str = order.getTransactionNumber();
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }
}
