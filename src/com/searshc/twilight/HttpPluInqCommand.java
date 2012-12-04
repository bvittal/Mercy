package com.searshc.twilight;

public class HttpPluInqCommand extends AbstractScriptResponseCommand
{
  public HttpPluInqCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_INQ"; }
  
}
