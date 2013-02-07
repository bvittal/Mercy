package com.searshc.twilight;

public class HttpPLU_RSP1Command extends AbstractScriptResponseCommand
{
  public HttpPLU_RSP1Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP1"; }
  
}
