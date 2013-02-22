package com.searshc.mercy;

public class HttpCOUPON_INQCommand extends AbstractScriptResponseCommand
{
  public HttpCOUPON_INQCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "COUPON_INQ"; }

}
