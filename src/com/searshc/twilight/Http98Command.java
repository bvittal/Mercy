package com.searshc.twilight;

public class Http98Command extends AbstractScriptResponseCommand
{
  public Http98Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "98"; }
  
}
