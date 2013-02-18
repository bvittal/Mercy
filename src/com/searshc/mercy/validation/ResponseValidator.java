package com.searshc.mercy.validation;

import com.upas.sears.service.domain.*;

public interface ResponseValidator
{ 
  public String getErrorMessageString();
  public boolean isValid(OrderResponse orderResponse);
}
