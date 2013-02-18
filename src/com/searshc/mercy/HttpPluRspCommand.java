package com.searshc.mercy;

public class HttpPluRspCommand extends AbstractScriptResponseCommand
{
  public HttpPluRspCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP"; }
}
