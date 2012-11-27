package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.Order;

public class PhysicalStoreNumberValidationCommand extends
    AbstractStringValidationCommand
{
  protected String getParameterName() { return "PhysicalStore"; }
  
  @Override
  public boolean passes(Order order)
  {
    String str = order.getPhysicalStoreNumber();
    System.out.println("Order:" + str + " " + baseValue);
    
    if(str != null && str.equals(baseValue))
      return true;
    else
      return false;
  }
}
