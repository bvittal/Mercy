package com.searshc.twilight;

import com.searshc.twilight.service.TwilightConstants;

public class ScriptCommandFactory
{
  
  private StringBuilder byteArrayObj; 
  private TwilightJsonObject jasonObj;
  
  public AbstractScriptCommand getCommand(String method, String action, Object obj)
  {
    if(obj instanceof TwilightJsonObject){
      this.jasonObj = (TwilightJsonObject) obj;
    }else{
        this.byteArrayObj = (StringBuilder) obj;
      }
    
    if(method.equals(TwilightConstants.METHOD_ADJUST_PRICE))
    {
      if(action.equals(TwilightConstants.ACTION_ADJUST_PRICE))
        return new AdjustPriceCommand(jasonObj);
      else
        return null;
    }
    
    //D3 Inquiry
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_FILE_INQ))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpFileInqCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(TwilightConstants.INDICATOR_PMP))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpPMPCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(TwilightConstants.INDICATOR_MP0))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpMP0Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(TwilightConstants.INDICATOR_D00))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpD00Command(byteArrayObj);
      else
        return null;
    }
    
    //D8 Plu Inquiry
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpPluInqCommand(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpPluRspCommand(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I1))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ1Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R1))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP1Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I2))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ2Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R2))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP2Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I3))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ3Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R3))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP3Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I4))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ4Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R4))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP4Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I5))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ5Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R5))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP5Command(byteArrayObj);
      else
        return null;
    }
    
    //2AA7 Coupon Inquiry
    if(method.equals(TwilightConstants.REQUEST_INDICATOR_COUPON_INQ))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_REQUEST))
        return new HttpCouponInqCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(TwilightConstants.RESPONSE_INDICATOR_COUPON_RESP))
    {
      if(action.equals(TwilightConstants.ACTION_ISP_RESPONSE))
        return new HttpCouponRspCommand(byteArrayObj);
      else
        return null;
    }
    else if(method.equals(TwilightConstants.HTTP_200))
    {
      return new Http200Command(jasonObj);
    }
    else if(method.equals(TwilightConstants.HTTP_400))
    {
      return new Http400Command(jasonObj);
    }
    else if(method.equals(TwilightConstants.HTTP_404))
    {
      return new Http404Command(jasonObj);
    }
    else if(method.equals(TwilightConstants.HTTP_500))
    {
      return new Http500Command(jasonObj);
    }
    else
    {
      return null;
    }
  }
}
