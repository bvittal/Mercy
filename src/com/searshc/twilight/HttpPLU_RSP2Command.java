package com.searshc.twilight;

public class HttpPLU_RSP2Command extends AbstractScriptResponseCommand
{
  public HttpPLU_RSP2Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP2"; }
  
}
