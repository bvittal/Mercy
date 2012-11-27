package com.searshc.twilight;

public class ScriptCommandFactory
{
  
  private StringBuilder byteArrayObj; 
  private TwilightJsonObject jasonObj;
  
  /** supported methods */
  private static final String METHOD_ADJUST_PRICE = "adjustprice";
  //private static final String METHOD_PROMOTIONS = "promotions";
  //private static final String METHOD_PRODUCT_DETAILS = "productdetails";
  
  /** corresponding actions */
  private static final String ACTION_ADJUST_PRICE = "POST";
  //private static final String ACTION_PROMOTIONS = "GET";
  private static final String ACTION_ISP_REQUEST = "REQT";
  private static final String ACTION_ISP_RESPONSE = "RESP";
  
  /** supported HTTP responses */
  private static final String HTTP_200 = "200";
  private static final String HTTP_400 = "400";
  private static final String HTTP_404 = "404";
  private static final String HTTP_500 = "500";
  
  /** supported CUSTOM responses */
  //D3 Inquiry
  private static final String HTTP_D3 = "D3";
  private static final String HTTP_PMP = "PMP";
  private static final String HTTP_MP0 = "MP0";
  private static final String HTTP_D00 = "D00";
  
  //D8 Plu
  private static final String HTTP_D8 = "D8";
  private static final String HTTP_EA = "EA";
  private static final String HTTP_E8 = "E8";
  private static final String HTTP_EC = "EC";
  private static final String HTTP_9C = "9C";
  private static final String HTTP_98 = "98";
  private static final String HTTP_40BA = "40BA";
  private static final String HTTP_60B1 = "60B1";
  
  //2AA7 Coupon
  private static final String HTTP_2AA7 = "2AA7";
  private static final String HTTP_2AB7 = "2AB7";
  
  public AbstractScriptCommand getCommand(String method, String action, Object obj)
  {
    if(obj instanceof TwilightJsonObject){
      this.jasonObj = (TwilightJsonObject) obj;
    }else{
        this.byteArrayObj = (StringBuilder) obj;
      }
    
    if(method.equals(METHOD_ADJUST_PRICE))
    {
      if(action.equals(ACTION_ADJUST_PRICE))
        return new AdjustPriceCommand(jasonObj);
      else
        return null;
    }
    
    //D3 Inquiry
    if(method.equals(HTTP_D3))
    {
      if(action.equals(ACTION_ISP_REQUEST))
        return new HttpD3Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_PMP))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpPMPCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_MP0))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpMP0Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_D00))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpD00Command(byteArrayObj);
      else
        return null;
    }
    
    //D8 Plu Inquiry
    if(method.equals(HTTP_D8))
    {
      if(action.equals(ACTION_ISP_REQUEST))
        return new HttpD8Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_EA))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpEACommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_E8))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpE8Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_EC))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpECCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_9C))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new Http9CCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_98))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new Http98Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_40BA))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new Http40BACommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_60B1))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new Http60B1Command(byteArrayObj);
      else
        return null;
    }
    
    //2AA7 Coupon Inquiry
    if(method.equals(HTTP_2AA7))
    {
      if(action.equals(ACTION_ISP_REQUEST))
        return new Http2AA7Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_2AB7))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new Http2AB7Command(byteArrayObj);
      else
        return null;
    }
    else if(method.equals(HTTP_200))
    {
      return new Http200Command(jasonObj);
    }
    else if(method.equals(HTTP_400))
    {
      return new Http400Command(jasonObj);
    }
    else if(method.equals(HTTP_404))
    {
      return new Http404Command(jasonObj);
    }
    else if(method.equals(HTTP_500))
    {
      return new Http500Command(jasonObj);
    }
    else
    {
      return null;
    }
  }
}
