package com.searshc.twilight.validation.command;

public abstract class AbstractStringValidationCommand extends AbstractValidationCommand
{
  protected String baseValue;
  
  public AbstractStringValidationCommand setBaseValue(String str)
  {
    this.baseValue = str;
    return this;
  }
  
  public String getBaseValue()
  {
    return this.baseValue;
  }
}