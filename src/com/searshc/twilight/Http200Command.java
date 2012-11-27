package com.searshc.twilight;

public class Http200Command extends AbstractScriptResponseCommand
{ 
  public Http200Command(TwilightJsonObject obj) { super(obj); }
  
  public String getMethod() { return "200"; }
}
