package com.searshc.twilight;

import java.util.*;

public class TwilightJsonObject implements TwilightPojo
{
  private String name;  // the name of the object
  private HashMap<String,String> map;  // the key/value pairs
  private List<TwilightJsonObject> jsonObj;  // the nested objects
  private List<TwilightJsonArray> jsonArray; // the parameter lists (coupon list or opted promos)
  
  public TwilightJsonObject()
  {
    this.map = new HashMap<String,String>();
    this.jsonObj = new ArrayList<TwilightJsonObject>();
    this.jsonArray = new ArrayList<TwilightJsonArray>();
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
  
  public TwilightPojoType getType()
  {
    return TwilightPojoType.OBJECT;
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
  
  public void addTwilightJsonObject(TwilightJsonObject obj)
  {
    this.jsonObj.add(obj);
  }
  
  public List<TwilightJsonObject> getTwilightJsonObject()
  {
    return this.jsonObj;
  }
  
  public void addTwilightJsonArray(TwilightJsonArray array)
  {
    this.jsonArray.add(array);
  }
  
  public List<TwilightJsonArray> getTwilightJsonArray()
  {
    return this.jsonArray;
  }
}
