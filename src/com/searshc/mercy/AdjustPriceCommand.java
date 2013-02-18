package com.searshc.mercy;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;

public class AdjustPriceCommand extends AbstractScriptRequestCommand
{ 
  private static Logger logger = Logger.getLogger(AdjustPriceCommand.class);
  
  /** the method named expected by the service */
  private static final String METHOD = "adjustprice";
  
  public AdjustPriceCommand(MercyJsonObject obj)
  { 
    this.toOrder(obj, true);
  }
  
  @Override
  public void execute(String scenario)
  {
    try
    {
      HttpPost postRequest = new HttpPost(BASE_URL + METHOD);
      String requestBody = mapper.writeValueAsString(order);
      logger.debug("Actual request posted " + requestBody);
      
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
