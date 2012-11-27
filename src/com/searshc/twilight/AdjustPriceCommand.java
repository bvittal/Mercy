package com.searshc.twilight;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

public class AdjustPriceCommand extends AbstractScriptRequestCommand
{ 
  /** the method named expected by the service */
  private static final String METHOD = "adjustprice";
  
  public AdjustPriceCommand(TwilightJsonObject obj)
  { 
    this.toOrder(obj, true);
  }
  
  @Override
  public void execute()
  {
    try
    {
      HttpPost postRequest = new HttpPost(BASE_URL + METHOD);
      String requestBody = mapper.writeValueAsString(order);
      System.out.println("Actual request posted " + requestBody);
      
      StringEntity input = new StringEntity(requestBody);
        
      input.setContentType(CONTENT_TYPE);
      postRequest.setEntity(input);
   
      this.response = httpClient.execute(postRequest);
    }
    catch(Exception e)
    {
      System.out.println("Error " + e);
    }
  }

}
