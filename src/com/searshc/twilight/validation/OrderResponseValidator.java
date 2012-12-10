package com.searshc.twilight.validation;

import com.upas.sears.service.domain.*;
import java.util.*;

import org.codehaus.jackson.map.ObjectMapper;

public class OrderResponseValidator extends OrderResponse implements ResponseValidator
{
  /** list of validation commands - String for now */
  List<String> validationCommands;
  OrderValidator order;
  
  public boolean isValid(OrderResponse orderResponse)
  {
    return order.isValid(orderResponse);
  }
  
  public String getErrorMessageString()
  {
    return "";
  }
  
  public void setData(Order data)
  {
    try
    {
      if(data != null)
      {
        ObjectMapper mapper = new ObjectMapper();
        //System.out.println("need to have validator for each POJO in domain...convert to/from JSON using Jackson to inherently invoke overridden setters which will build the command list...when entire object is instantiated, run through list of commands performing validations....command list needs to live in the OrderResponseValidator (the top level)>>>>");
        order = mapper.readValue(mapper.writeValueAsString(data), com.searshc.twilight.validation.OrderValidator.class);
        super.setData(order);
      }
      else
      {
        System.err.println("data is null");
      }
    }
    catch(Exception e)
    {
      System.err.println("error setting up validator");
    }
  }
 
  
  public void setMessage(List<AppMessage> message)
  {
    super.setMessages(messages);
  }
}