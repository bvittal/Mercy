package com.searshc.twilight;

import java.util.*;

public class JsonObjectBuilder
{ 
  /** the object we're building */
  private TwilightPojo pojo = null;
  
  /** utility for building the object */
  private Stack<TwilightPojo> stack;
  private TwilightPojoFactory pojoFactory;
  
  public JsonObjectBuilder()
  {
    this.stack = new Stack<TwilightPojo>();
    this.pojoFactory = new TwilightPojoFactory();
  }
  
  public void addListItem(String str)
  {
    ((TwilightJsonArray)pojo).addValue(str);
  }
  
  public void addParameter(String key, String val)
  {
    ((TwilightJsonObject)pojo).addParameter(key,val);
  }
  
  public void newObject(String name)
  {
    if(pojo != null) { 
      stack.push(pojo); 
      }
    pojo = pojoFactory.getPojo(name);
  }
  
  public boolean finishObject()
  {
        
    if(!stack.isEmpty())
    {
      TwilightPojo prevObj = stack.pop();
      
      if(prevObj.getType() == TwilightPojoType.OBJECT)
      {
        if(pojo.getType() == TwilightPojoType.ARRAY)
        {
          ((TwilightJsonObject)prevObj).addTwilightJsonArray((TwilightJsonArray)pojo);    
        }
        else
        {
          ((TwilightJsonObject)prevObj).addTwilightJsonObject((TwilightJsonObject)pojo);       
        }
      }
      else
      {
        /** if this happens there is a malformed script containing a pojo inside an array */
        System.err.println("Malformed Script...you can not nest inside a list dummy!!! ;-)");
      }

      pojo = prevObj;
      return false;
    }
    else
    {
      return true;
    }
  }
  
  public TwilightJsonObject getJsonObject()
  {
    return (TwilightJsonObject)pojo;
  }
}
