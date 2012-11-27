package com.searshc.twilight;

public class HttpE3Command extends AbstractScriptResponseCommand
{
  public HttpE3Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "E3"; }
  
}
