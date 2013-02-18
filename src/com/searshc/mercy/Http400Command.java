package com.searshc.mercy;

public class Http400Command extends AbstractScriptResponseCommand
{
  public Http400Command(MercyJsonObject obj) { super(obj); }
  
  public String getMethod() { return "400"; }
}
