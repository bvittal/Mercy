package com.searshc.mercy;

public class HttpPLU_INQCommand extends AbstractScriptResponseCommand
{
  public HttpPLU_INQCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_INQ"; }

}
