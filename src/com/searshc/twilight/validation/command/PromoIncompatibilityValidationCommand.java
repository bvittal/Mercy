package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.Order;

public class PromoIncompatibilityValidationCommand extends AbstractStringValidationCommand
{

  @Override
  public boolean passes(Order order)
  {
    return false;
  }

  @Override
  protected String getParameterName()
  {
    // TODO Auto-generated method stub
    return null;
  }

}
