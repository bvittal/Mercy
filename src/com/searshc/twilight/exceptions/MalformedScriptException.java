package com.searshc.twilight.exceptions;

public class MalformedScriptException extends Exception
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public MalformedScriptException() {
    
  }

  public MalformedScriptException(final String description){
   
    super(description);
   }
}
