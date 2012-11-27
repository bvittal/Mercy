package com.searshc.twilight;

public class Http40BACommand extends AbstractScriptResponseCommand
{
  public Http40BACommand(StringBuilder byteArrayObj) { super(byteArrayObj); }
  
  public String getMethod() { return "40BA"; }

}
