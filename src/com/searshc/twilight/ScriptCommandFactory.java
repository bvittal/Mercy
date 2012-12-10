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
  private static final String HTTP_FILE_INQ = "FILE_INQ";
  private static final String HTTP_PMP = "PMP";
  private static final String HTTP_MP0 = "MP0";
  private static final String HTTP_D00 = "D00";
  
  //D8 Plu
  private static final String HTTP_PLU_INQ = "PLU_INQ";
  private static final String HTTP_PLU_RSP = "PLU_RSP";
  private static final String HTTP_EA = "EA";
  private static final String HTTP_EC = "EC";
  private static final String HTTP_9C = "9C";
  private static final String HTTP_98 = "98";
  private static final String HTTP_40BA = "40BA";
  private static final String HTTP_60B1 = "60B1";
  
  //2AA7 Coupon
  private static final String HTTP_COUPON_INQ = "COUPON_INQ";
  private static final String HTTP_COUPON_RSP = "COUPON_RSP";
  
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
    if(method.equals(HTTP_FILE_INQ))
    {
      if(action.equals(ACTION_ISP_REQUEST))
        return new HttpFileInqCommand(byteArrayObj);
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
    if(method.equals(HTTP_PLU_INQ))
    {
      if(action.equals(ACTION_ISP_REQUEST))
        return new HttpPluInqCommand(byteArrayObj);
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
    if(method.equals(HTTP_PLU_RSP))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpPluRspCommand(byteArrayObj);
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
    if(method.equals(HTTP_COUPON_INQ))
    {
      if(action.equals(ACTION_ISP_REQUEST))
        return new HttpCouponInqCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(HTTP_COUPON_RSP))
    {
      if(action.equals(ACTION_ISP_RESPONSE))
        return new HttpCouponRspCommand(byteArrayObj);
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