package com.searshc.twilight;

public class HttpD00Command extends AbstractScriptResponseCommand
{
  public HttpD00Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "D00"; }
}
