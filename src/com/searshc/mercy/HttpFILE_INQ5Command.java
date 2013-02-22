package com.searshc.mercy;

import com.searshc.mercy.service.MercyConstants;

public class HttpFILE_INQ5Command extends AbstractScriptResponseCommand
{
  public HttpFILE_INQ5Command(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return MercyConstants.REQUEST_INDICATOR_FILE_INQ_I5; }

}