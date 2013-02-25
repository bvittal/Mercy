package com.searshc.mercy;

public class HttpPLU_RSPCommand extends AbstractScriptResponseCommand
{
  public HttpPLU_RSPCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP"; }
  
}
