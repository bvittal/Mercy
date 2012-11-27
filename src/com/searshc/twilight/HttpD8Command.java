package com.searshc.twilight;

public class HttpD8Command extends AbstractScriptResponseCommand
{
  public HttpD8Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "D8"; }
  
}
