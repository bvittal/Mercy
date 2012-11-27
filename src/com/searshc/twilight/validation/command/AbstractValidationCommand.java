package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.Order;

public abstract class AbstractValidationCommand
{
  
  public AbstractValidationCommand()
  {
    System.out.println("Validating parameter: " + getParameterName());
  }
  
  private String message = "";
  
  public abstract boolean passes(Order order);
  
  protected abstract String getParameterName();
  
  public String getMessage() { return this.message; }
  
  public void setMessage(String msg) { this.message = msg; }
}
