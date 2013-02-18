package com.searshc.mercy;

public class HttpPLU_RSP4Command extends AbstractScriptResponseCommand
{
  public HttpPLU_RSP4Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP4"; }
  
}
