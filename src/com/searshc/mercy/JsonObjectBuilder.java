package com.searshc.mercy;

import java.util.*;

public class JsonObjectBuilder
{ 
  /** the object we're building */
  private MercyPojo pojo = null;
  
  /** utility for building the object */
  private Stack<MercyPojo> stack;
  private MercyPojoFactory pojoFactory;
  
  public JsonObjectBuilder()
  {
    this.stack = new Stack<MercyPojo>();
    this.pojoFactory = new MercyPojoFactory();
  }
  
  public void addListItem(String str)
  {
    ((MercyJsonArray)pojo).addValue(str);
  }
  
  public void addParameter(String key, String val)
  {
    ((MercyJsonObject)pojo).addParameter(key,val);
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
      MercyPojo prevObj = stack.pop();
      
      if(prevObj.getType() == MercyPojoType.OBJECT)
      {
        if(pojo.getType() == MercyPojoType.ARRAY)
        {
          ((MercyJsonObject)prevObj).addMercyJsonArray((MercyJsonArray)pojo);    
        }
        else
        {
          ((MercyJsonObject)prevObj).addMercyJsonObject((MercyJsonObject)pojo);       
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
  
  public MercyJsonObject getJsonObject()
  {
    return (MercyJsonObject)pojo;
  }
}
