package com.searshc.twilight;

public class HttpE8Command extends AbstractScriptResponseCommand
{
  public HttpE8Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "E8"; }
}
