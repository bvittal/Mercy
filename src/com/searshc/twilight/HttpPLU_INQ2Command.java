package com.searshc.twilight;

public class HttpPLU_INQ2Command extends AbstractScriptResponseCommand
{
  public HttpPLU_INQ2Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_INQ2"; }
  
}
