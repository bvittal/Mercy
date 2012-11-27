package com.searshc.twilight;

public class HttpD3Command extends AbstractScriptResponseCommand
{
  public HttpD3Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "D3"; }
}
