package com.searshc.twilight;

public class Http404Command extends AbstractScriptResponseCommand
{
  public Http404Command(TwilightJsonObject obj) { super(obj); }
  
  public String getMethod() { return "404"; }

}
