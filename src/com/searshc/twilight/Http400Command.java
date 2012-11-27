package com.searshc.twilight;

public class Http400Command extends AbstractScriptResponseCommand
{
  public Http400Command(TwilightJsonObject obj) { super(obj); }
  
  public String getMethod() { return "400"; }
}
