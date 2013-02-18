package com.searshc.mercy;

import java.util.*;

public class MercyJsonObject implements MercyPojo
{
  private String name;  // the name of the object
  private HashMap<String,String> map;  // the key/value pairs
  private List<MercyJsonObject> jsonObj;  // the nested objects
  private List<MercyJsonArray> jsonArray; // the parameter lists (coupon list or opted promos)
  
  public MercyJsonObject()
  {
    this.map = new HashMap<String,String>();
    this.jsonObj = new ArrayList<MercyJsonObject>();
    this.jsonArray = new ArrayList<MercyJsonArray>();
  }
  
  @SuppressWarnings("rawtypes")
  public String toString()
  {
    String retVal = "[" + name + "]\n";
    
    Set keys = map.keySet();
    Iterator keyIterator = keys.iterator();
    
    while(keyIterator.hasNext())
    {
      String key = (String)keyIterator.next();
      retVal += "[" + name + "]: [" + key + "] = [" + map.get(key) + "]\n";
    }
    
    for(int i=0;i<jsonObj.size();i++)
    {
      retVal += jsonObj.get(i).toString();
    }
    
    return retVal;
  }
  
  @SuppressWarnings("rawtypes")
  public String toJsonString()
  {
    String retVal = "{ ";
    boolean prevValue = false;
    
    Set keys = map.keySet();
    Iterator keyIterator = keys.iterator();
    
    while(keyIterator.hasNext())
    {
      String key = (String)keyIterator.next();
      if(prevValue) { retVal += ", "; }
      retVal += "\"" + key + "\" : \"" + map.get(key) + "\"";
      prevValue = true;
    }
    
    for(int i=0;i<jsonObj.size();i++)
    {
      if(prevValue) { retVal += ", "; }
      retVal += "\"" + jsonObj.get(i).getName() + "\" : ";
      retVal += jsonObj.get(i).toJsonString();
      prevValue = true;
    }
    
    return retVal + " }";
  }
  
  public MercyPojoType getType()
  {
    return MercyPojoType.OBJECT;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void addParameter(String key, String val)
  {
    this.map.put(key, val);
  }
  
  public HashMap<String,String> getParameters()
  {
    return this.map;
  }
  
  public void addMercyJsonObject(MercyJsonObject obj)
  {
    this.jsonObj.add(obj);
  }
  
  public List<MercyJsonObject> getMercyJsonObject()
  {
    return this.jsonObj;
  }
  
  public void addMercyJsonArray(MercyJsonArray array)
  {
    this.jsonArray.add(array);
  }
  
  public List<MercyJsonArray> getMercyJsonArray()
  {
    return this.jsonArray;
  }
}
