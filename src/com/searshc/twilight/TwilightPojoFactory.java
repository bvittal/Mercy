package com.searshc.twilight;

public class TwilightPojoFactory
{
  public TwilightPojo getPojo(String name)
  {
    TwilightPojo obj;
    
    if(name.equals(TwilightPojo.COUPON_KEY) ||
       name.equals(TwilightPojo.OPTED_PROMO_KEY)) 
       //name.equals(TwilightPojo.INCOMPATIBILITIES_FIELDS_KEY))
    {
      obj = new TwilightJsonArray();
      obj.setName(name);
    }
    else
    {
      obj = new TwilightJsonObject();
      obj.setName(name);
    }
    return obj;
  }
}
