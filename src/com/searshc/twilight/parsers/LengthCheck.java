package com.searshc.twilight.parsers;

public interface LengthCheck
{
  public boolean lengthCheck(int length, String field);
  
  public String lengthCheckMsg(int length, String field);
  
}
