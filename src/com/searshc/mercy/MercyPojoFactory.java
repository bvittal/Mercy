package com.searshc.mercy;

public class MercyPojoFactory
{
  public MercyPojo getPojo(String name)
  {
    MercyPojo obj;
    
    if(name.equals(MercyPojo.COUPON_KEY) ||
       name.equals(MercyPojo.OPTED_PROMO_KEY)) 
    {
      obj = new MercyJsonArray();
      obj.setName(name);
    }
    else
    {
      obj = new MercyJsonObject();
      obj.setName(name);
    }
    return obj;
  }
}
