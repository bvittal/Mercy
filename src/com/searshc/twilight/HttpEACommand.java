package com.searshc.twilight;

public class HttpEACommand extends AbstractScriptResponseCommand
{
  public HttpEACommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "EA"; }
  
}
