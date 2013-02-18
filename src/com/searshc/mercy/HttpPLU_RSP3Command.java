package com.searshc.mercy;

public class HttpPLU_RSP3Command extends AbstractScriptResponseCommand
{
  public HttpPLU_RSP3Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP3"; }
  
}
