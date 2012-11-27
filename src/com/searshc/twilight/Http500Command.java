package com.searshc.twilight;

public class Http500Command extends AbstractScriptResponseCommand
{

  public Http500Command(TwilightJsonObject obj) { super(obj); }
  
  public String getMethod() { return "500"; }
}
