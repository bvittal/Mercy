package com.searshc.twilight;

public class HttpPLU_INQ4Command extends AbstractScriptResponseCommand
{
  public HttpPLU_INQ4Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_INQ4"; }

}
