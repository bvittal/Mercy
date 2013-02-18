package com.searshc.mercy;

public class HttpCouponRspCommand extends AbstractScriptResponseCommand
{
  public HttpCouponRspCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "COUPON_RSP"; }
}