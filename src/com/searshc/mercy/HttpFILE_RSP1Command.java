package com.searshc.mercy;

import com.searshc.mercy.service.MercyConstants;

public class HttpFILE_RSP1Command extends AbstractScriptResponseCommand
{
  public HttpFILE_RSP1Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R1; }
  
}
