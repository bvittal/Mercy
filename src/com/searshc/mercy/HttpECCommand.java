package com.searshc.mercy;

public class HttpECCommand extends AbstractScriptResponseCommand
{
  public HttpECCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "EC"; }

}
