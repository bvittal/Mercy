package com.searshc.mercy.validation.command;

import org.apache.log4j.Logger;
import com.upas.sears.service.domain.Order;

public abstract class AbstractValidationCommand
{
  private static Logger logger = Logger.getLogger(AbstractValidationCommand.class);
  
  public AbstractValidationCommand()
  {
    logger.info("Validating parameter: " + getParameterName());
  }
  
  private String message = "";
  
  public abstract boolean passes(Order order);
  
  protected abstract String getParameterName();
  
  public String getMessage() { return this.message; }
  
  public void setMessage(String msg) { this.message = msg; }
}
