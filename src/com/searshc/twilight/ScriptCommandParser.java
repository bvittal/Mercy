package com.searshc.twilight;

import java.util.*;

import org.apache.log4j.Logger;

public class ScriptCommandParser
{
  private HashMap<String,String> cartParameters;
  private List<HashMap> itemParameters = new ArrayList<HashMap>();
  private static Logger logger = Logger.getLogger(ScriptCommandParser.class);
  private TwilightJsonObject obj;
  private StringBuilder byteArrayObj;
  private String action;
  private String method;

  public ScriptCommandParser(String commandLine)
  { 
    logger.info("Parsing command: [" + commandLine + "]");
    parse(commandLine);
  }
   
  public void parse(String commandLines)
  {  
    JsonObjectBuilder builder = new JsonObjectBuilder();
    
    Stack<String> stack = new Stack<String>();
    String token;
    String key, val;
    StringTokenizer t1;
    
    StringTokenizer tok1 = new StringTokenizer(commandLines, " ");
    this.action = tok1.nextToken();
    StringTokenizer tok2 = new StringTokenizer(tok1.nextToken(),"(");
    this.method = tok2.nextToken();
    
    (t1 = new StringTokenizer(commandLines)).nextToken();
    StringTokenizer tok = new StringTokenizer(t1.nextToken(), "(),=", true);
    
    if(!(method.equals("adjustprice") && action.equals("POST") || action.equals("RECV"))){
      parseByteArrayObject(commandLines);
    }
    else
    {
    while(tok.hasMoreTokens())
    {
      token = tok.nextToken();
      if(token.equals("(") || token.equals(","))
      {
        String nextToken = tok.nextToken();
        //System.out.println("Token " + nextToken);
        if(nextToken.equals(TwilightPojo.ITEMS_KEY))
        {
          builder.newObject(TwilightPojo.ITEMS_KEY);
        }
        else if(nextToken.equals(TwilightPojo.ITEM_KEY))
        {
          builder.newObject(TwilightPojo.ITEM_KEY); 
        }
        /**
        if(nextToken.equals(TwilightPojo.ADJUSTMENTS_KEY))
        {
          System.out.println("Token " + nextToken);
          builder.newObject(TwilightPojo.ADJUSTMENTS_KEY);
        }
        else if(nextToken.equals(TwilightPojo.ADJUSTMENT_KEY))
        {
          System.out.println("Token " + nextToken);
          builder.newObject(TwilightPojo.ADJUSTMENT_KEY); 
        }
        if(nextToken.equals(TwilightPojo.DCADJUSTMENTS_KEY))
        {
          System.out.println("INSIDE DC ADJUSTMENTS Token " + nextToken);
          builder.newObject(TwilightPojo.DCADJUSTMENTS_KEY);
        }
        else if(nextToken.equals(TwilightPojo.DCADJUSTMENT_KEY))
        {
          System.out.println("Token " + nextToken);
          builder.newObject(TwilightPojo.DCADJUSTMENT_KEY); 
        }*/
        else if(nextToken.equals(TwilightPojo.COUPON_KEY))
        {
          builder.newObject(TwilightPojo.COUPON_KEY);    
        }
        else if(nextToken.equals(TwilightPojo.OPTED_PROMO_KEY))
        {
          builder.newObject(TwilightPojo.OPTED_PROMO_KEY);    
        }
        else
        {
          stack.push(nextToken);
        }
      }
      else if(token.equals(")"))
      {
        while(!stack.isEmpty())
        {
          builder.addListItem(stack.pop());
        }
        
        builder.finishObject();
      }
      else if(token.equals("="))
      {
        key = stack.pop();
        val = tok.nextToken();
        builder.addParameter(key, val);
      }
      else if(token.equals("adjustprice"))
      {
        builder.newObject(token);
      }
      else if(isValidResponse(token))
      {
        builder.newObject(token);
      }
      else
      {
        System.err.println("Malformed Script Command...parse error. [" + commandLines + "]");
      }
    }
    this.obj = builder.getJsonObject();
    }
  }
  
  public void parseByteArrayObject(String commandLines){
    StringBuilder sb = new StringBuilder();
    int startingIndex = commandLines.indexOf("(");
      if (startingIndex != -1) {  
      int endingIndex = commandLines.indexOf(")", startingIndex);  
        if (endingIndex != -1)  
          sb.append(commandLines.substring(startingIndex + 1, endingIndex));
          this.byteArrayObj = sb;
       }
    }
  
  
  private boolean isValidResponse(String rsp)
  {
    if(rsp.equals("200") || rsp.equals("400") || rsp.equals("404") || rsp.equals("500"))
      return true;
    else
      return false;
  }
  
  public TwilightJsonObject getJsonObject()
  {
    return this.obj;
  }


  public StringBuilder getByteArrayObject()
  {
    return this.byteArrayObj;
  }
  
  private boolean isToken(String str)
  {
    if(str.equals("(") || str.equals(")") || str.equals(",") || str.equals("="))
      return true;
    else
      return false;
  }
  
  public String getMethod()
  {
    return this.method;
  }
  
  public String getAction()
  {
    return this.action;
  }
  
  public HashMap<String,String> getCartParameters()
  {
    return this.cartParameters;
  }
  
  public List<HashMap> getItemParameters()
  {
    return this.itemParameters;
  }
}
