package com.searshc.mercy;

public class HttpMP0Command extends AbstractScriptResponseCommand
{
  public HttpMP0Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "MP0"; }
}
