package com.searshc.mercy;

public class Http500Command extends AbstractScriptResponseCommand
{

  public Http500Command(MercyJsonObject obj) { super(obj); }
  
  public String getMethod() { return "500"; }
}
