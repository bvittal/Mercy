package com.searshc.mercy;

import com.searshc.mercy.service.MercyConstants;

public class ScriptCommandFactory
{
  
  private StringBuilder byteArrayObj; 
  private MercyJsonObject jasonObj;
  
  public AbstractScriptCommand getCommand(String method, String action, Object obj)
  {
    if(obj instanceof MercyJsonObject){
      this.jasonObj = (MercyJsonObject) obj;
    }else{
        this.byteArrayObj = (StringBuilder) obj;
      }
    
    if(method.equals(MercyConstants.METHOD_ADJUST_PRICE))
    {
      if(action.equals(MercyConstants.ACTION_ADJUST_PRICE))
        return new AdjustPriceCommand(jasonObj);
      else
        return null;
    }
    
    //D3 Inquiry
    if(method.equals(MercyConstants.REQUEST_INDICATOR_FILE_INQ))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpFILE_INQCommand(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_FILE_RESP))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpFILE_RSPCommand(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I1))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpFILE_INQ1Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R1))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpFILE_RSP1Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I2))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpFILE_INQ2Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R2))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpFILE_RSP2Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I3))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpFILE_INQ3Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R3))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpFILE_RSP3Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I4))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpFILE_INQ4Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R4))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpFILE_RSP4Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_FILE_INQ_I5))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpFILE_INQ5Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_FILE_RESP_R5))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpFILE_RSP5Command(byteArrayObj);
      else
        return null;
    }
    
    //D8 Plu Inquiry
    if(method.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQCommand(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSPCommand(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I1))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ1Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R1))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP1Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I2))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ2Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R2))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP2Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I3))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ3Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R3))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP3Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I4))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ4Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R4))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP4Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I5))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpPLU_INQ5Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R5))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpPLU_RSP5Command(byteArrayObj);
      else
        return null;
    }
    
    //2AA7 Coupon Inquiry
    if(method.equals(MercyConstants.REQUEST_INDICATOR_COUPON_INQ))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpCOUPON_INQCommand(byteArrayObj);
      else
        return null;
    }
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpCOUPON_RSPCommand(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I1))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpCOUPON_INQ1Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R1))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpCOUPON_RSP1Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I2))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpCOUPON_INQ2Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R2))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpCOUPON_RSP2Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I3))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpCOUPON_INQ3Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R3))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpCOUPON_RSP3Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I4))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpCOUPON_INQ4Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R4))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpCOUPON_RSP4Command(byteArrayObj);
      else
        return null;
    }
    
    if(method.equals(MercyConstants.REQUEST_INDICATOR_COUPON_INQ_I5))
    {
      if(action.equals(MercyConstants.ACTION_ISP_REQUEST))
        return new HttpCOUPON_INQ5Command(byteArrayObj);
      else
        return null;
    }
    if(method.equals(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP_R5))
    {
      if(action.equals(MercyConstants.ACTION_ISP_RESPONSE))
        return new HttpCOUPON_RSP5Command(byteArrayObj);
      else
        return null;
    }
    else if(method.equals(MercyConstants.HTTP_200))
    {
      return new Http200Command(jasonObj);
    }
    else if(method.equals(MercyConstants.HTTP_400))
    {
      return new Http400Command(jasonObj);
    }
    else if(method.equals(MercyConstants.HTTP_404))
    {
      return new Http404Command(jasonObj);
    }
    else if(method.equals(MercyConstants.HTTP_500))
    {
      return new Http500Command(jasonObj);
    }
    else
    {
      return null;
    }
  }
}
