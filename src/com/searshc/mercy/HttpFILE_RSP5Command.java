package com.searshc.mercy;

import com.searshc.mercy.service.MercyConstants;

public class HttpFILE_RSP5Command extends AbstractScriptResponseCommand
{
  public HttpFILE_RSP5Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R5; }
  
}
