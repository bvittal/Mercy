package com.searshc.mercy;

public class HttpPLU_INQ1Command extends AbstractScriptResponseCommand
{
  public HttpPLU_INQ1Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "PLU_INQ1"; }

}
