package com.searshc.twilight.validation;

import com.upas.sears.service.domain.LineItem;
import com.upas.sears.service.domain.OrderResponse;

public class LineItemValidator extends LineItem implements ResponseValidator
{

  @Override
  public String getErrorMessageString()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isValid(OrderResponse orderResponse)
  {
    // TODO Auto-generated method stub
    //System.out.println("validating line item");
    return false;
  }

}