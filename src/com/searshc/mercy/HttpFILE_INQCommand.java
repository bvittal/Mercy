package com.searshc.mercy;

import com.searshc.mercy.service.MercyConstants;

public class HttpFILE_INQCommand extends AbstractScriptResponseCommand
{
  public HttpFILE_INQCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return MercyConstants.REQUEST_INDICATOR_FILE_INQ; }

}
