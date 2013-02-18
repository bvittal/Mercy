package com.searshc.mercy;

public class HttpPLU_RSP5Command extends AbstractScriptResponseCommand
{
  public HttpPLU_RSP5Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP5"; }
  
}
