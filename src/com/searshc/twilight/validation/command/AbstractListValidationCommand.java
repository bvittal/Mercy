package com.searshc.twilight.validation.command;

import com.upas.sears.service.domain.LineItem;
import java.util.*;

import org.apache.log4j.Logger;

public abstract class AbstractListValidationCommand extends AbstractValidationCommand
{
  private static Logger logger = Logger.getLogger(AbstractListValidationCommand.class);
  
  public AbstractListValidationCommand()
  {
    logger.info("Validating list: " + getParameterName()+"\n");
  }
  
  private String message = "";
  
  public abstract boolean passes(List<LineItem> list);
  
  protected abstract String getParameterName();
  
  public String getMessage() { return this.message; }
  
  public void setMessage(String msg) { this.message = msg; }
}
