package com.searshc.twilight;

public class HttpPLU_INQ3Command extends AbstractScriptResponseCommand
{
  public HttpPLU_INQ3Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_INQ3"; }
  
}
