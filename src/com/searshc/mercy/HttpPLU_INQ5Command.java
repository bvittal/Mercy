package com.searshc.mercy;

public class HttpPLU_INQ5Command extends AbstractScriptResponseCommand
{
  public HttpPLU_INQ5Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_INQ5"; }

}
