package com.searshc.twilight;

public class HttpPluRspCommand extends AbstractScriptResponseCommand
{
  public HttpPluRspCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_RSP"; }
}
