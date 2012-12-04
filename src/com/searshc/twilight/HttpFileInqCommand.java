package com.searshc.twilight;

public class HttpFileInqCommand extends AbstractScriptResponseCommand
{
  public HttpFileInqCommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "FILE_INQ"; }
}
