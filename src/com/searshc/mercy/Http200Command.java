package com.searshc.mercy;

public class Http200Command extends AbstractScriptResponseCommand
{ 
  public Http200Command(MercyJsonObject obj) { super(obj); }
  
  public String getMethod() { return "200"; }
}
