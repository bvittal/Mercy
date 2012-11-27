package com.searshc.twilight;

public class Http9CCommand extends AbstractScriptResponseCommand
{
  public Http9CCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "9C"; }
  
}
