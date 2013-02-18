package com.searshc.mercy;

import java.util.*;

public class MercyJsonArray implements MercyPojo
{
  private List<String> values = new ArrayList<String>();
  private String name;
  
  public void addValue(String val)
  {
    this.values.add(val);
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public MercyPojoType getType()
  {
    return MercyPojoType.ARRAY;
  }
  
  public String[] getStringArray()
  {
    String[] array = new String[values.size()];
    
    for(int i=0;i<values.size();i++)
    {
      array[i] = values.get(i);
    }
    
    return array;
  }
}
