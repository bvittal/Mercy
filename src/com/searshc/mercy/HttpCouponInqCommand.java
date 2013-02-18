package com.searshc.mercy;

public class HttpCouponInqCommand extends AbstractScriptResponseCommand
{
  public HttpCouponInqCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "COUPON_INQ"; }
}
