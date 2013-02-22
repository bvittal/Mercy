package com.searshc.mercy;

import com.searshc.mercy.service.MercyConstants;

public class HttpFILE_RSPCommand extends AbstractScriptResponseCommand
{
  public HttpFILE_RSPCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return MercyConstants.RESPONSE_INDICATOR_FILE_RESP; }
  
}
