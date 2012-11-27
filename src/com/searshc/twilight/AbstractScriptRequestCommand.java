package com.searshc.twilight;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class AbstractScriptRequestCommand extends AbstractScriptCommand
{
  protected HttpResponse response;
  protected DefaultHttpClient httpClient = new DefaultHttpClient();
  
  public boolean isRequest()
  {
    return true;
  }
  
  public HttpResponse getResponse()
  {
    return this.response;
  }
    
  public void cleanup()
  {
    httpClient.getConnectionManager().shutdown();
  }
}