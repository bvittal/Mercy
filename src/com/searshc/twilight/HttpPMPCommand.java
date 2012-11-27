package com.searshc.twilight;

public class HttpPMPCommand extends AbstractScriptResponseCommand
{
  public HttpPMPCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PMP"; }
}
