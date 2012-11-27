package com.searshc.twilight;

public class HttpECCommand extends AbstractScriptResponseCommand
{
  public HttpECCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "EC"; }

}
