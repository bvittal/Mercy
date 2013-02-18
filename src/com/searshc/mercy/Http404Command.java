package com.searshc.mercy;

public class Http404Command extends AbstractScriptResponseCommand
{
  public Http404Command(MercyJsonObject obj) { super(obj); }
  
  public String getMethod() { return "404"; }

}
