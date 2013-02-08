package com.searshc.twilight;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import org.codehaus.jackson.map.*;
import com.searshc.twilight.reports.beans.DataBean;
import com.searshc.twilight.service.TwilightConstants;
import com.searshc.twilight.util.ObjectBuilder;
import com.searshc.twilight.validation.OrderResponseValidator;
import com.upas.sears.service.domain.OrderResponse;

public abstract class AbstractScriptResponseCommand extends AbstractScriptCommand
{
  private static Logger logger = Logger.getLogger(AbstractScriptResponseCommand.class);

  protected HttpResponse response;  // the response from the service
  protected HashMap<String,String> cmdParameters;
  protected TwilightJsonObject jasonObj; // the script command in JSON format
  protected StringBuilder byteArrayObj; // the script values
  private final ObjectMapper mapper = new ObjectMapper();
  private DataBean dataBean = new DataBean();
  private static List<DataBean> dataBeanList = new ArrayList<DataBean>();
  abstract public String getMethod();
  int i = 0;
  
  public AbstractScriptResponseCommand(Object obj)
  {
   if(obj !=null)
    if(obj instanceof TwilightJsonObject){
      this.jasonObj = (TwilightJsonObject) obj;
    }else{
      this.byteArrayObj = (StringBuilder) obj;
    }
  }
  
  @Override
  public boolean isRequest()
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  public static List<DataBean> getReportData(){
    return dataBeanList;
  }
  
  public void setResponse(HttpResponse response)
  {
    this.response = response;
  }
  

  protected String getHttpResponse(InputStream stream)
  {
    String output = "";
    try
    {
      String line = "";
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      while((line = br.readLine()) != null) { 
        output += line; 
        }
      br.close();
      stream.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    return output;
  }
  
  private String getContentType(HttpResponse response)
  {
    String ctypeString = response.getHeaders("content-type")[0].getValue();
    return ctypeString.substring(0, ctypeString.indexOf(";"));
  }
  
  public void execute(String scenario)
  { 
    String recvdMethod = StringUtils.EMPTY;
    try
    {
      if(response != null){
        recvdMethod = Integer.toString(response.getStatusLine().getStatusCode());
      }
      
      if(StringUtils.isNotBlank(recvdMethod) && this.getMethod().equals(recvdMethod)){
        System.out.println("Recieved " + recvdMethod + " " + response.getStatusLine().getReasonPhrase());
        
        if(this.getContentType(response).equals("application/json")){
        
          String rsp = getHttpResponse(response.getEntity().getContent());
          System.out.println("Actual response recieved : " + rsp);
          OrderResponse orderResp = mapper.readValue(rsp, com.upas.sears.service.domain.OrderResponse.class);
          
          /** now convert OrderResponse to JSON and then JSON to OrderResponseValidator */
          this.toOrderResponse(jasonObj);
          OrderResponseValidator orderRespValidator = mapper.readValue(mapper.writeValueAsString(orderResponse), com.searshc.twilight.validation.OrderResponseValidator.class);
            
            if(orderRespValidator.isValid(orderResp))
            {
              dataBean.setScenarioType(scenario);
              dataBean.setTestResults("PASS");
              dataBeanList.add(dataBean);
              System.out.println("********* TEST PASS **********");
              logger.info("********* TEST PASS **********");
              logger.info("\nProcessing next batch in script");
              recvdMethod = StringUtils.EMPTY;
              response = null;
              ObjectBuilder.getObjects().clear();
              ObjectBuilder.getInqObjects().clear();
            }
            else
            {
              dataBean.setScenarioType(scenario);
              dataBean.setTestResults("FAIL");
              dataBeanList.add(dataBean);
              System.err.println("########## TEST FAILED ###########");
              logger.info("########## TEST FAILED ###########");
              logger.info("\nProcessing next batch in script");
              recvdMethod = StringUtils.EMPTY;
              response = null;
              ObjectBuilder.getObjects().clear();
              ObjectBuilder.getInqObjects().clear();
           }
        }
        else
        {
          /** print the response and fail the test */
          logger.error("incorrect content-type");
          logger.error(getHttpResponse(response.getEntity().getContent()));
        }
      }
      else
      { 
        if(StringUtils.isNotBlank(this.getMethod()) && !this.getMethod().equals("200"))
        { 
          String indicator = this.getMethod();
          byte [] buffer = byteArrayConverter();
          
          if(StringUtils.isNotBlank(indicator) && 
              indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_PMP)){
              if(buffer != null){
                ObjectBuilder.setInqObjects(indicator, buffer);
              }
          }else if(StringUtils.isNotBlank(indicator) && 
              indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_MP0)){
            if(buffer != null){
              ObjectBuilder.setInqObjects(indicator, buffer);
            }
          }else if(StringUtils.isNotBlank(indicator) && 
              indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_D00)){
            if(buffer != null){
              ObjectBuilder.setInqObjects(indicator, buffer);
            }
          }else if(buffer != null && this.getMethod().equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I1) || 
              this.getMethod().equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I1) || 
              this.getMethod().equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I2) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I3) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I4) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I5) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R1) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R2) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R3) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R4) ||
              this.getMethod().equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R5)){
              ObjectBuilder.setInqObjects(this.getMethod(), buffer);
          }
          else if(buffer != null)
            ObjectBuilder.setObjects(buffer);
        }
        else
        {
          logger.error("Failed to process request (check script to correct response) - Error code recieved : " + recvdMethod);
          logger.error("Processing next batch in script\n");
          ObjectBuilder.getObjects().clear();
          }
        }
      }
    catch(Exception ex)
    {
      System.out.println ("Error " + ex);
    }
  }
  
  private byte[] byteArrayConverter() {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    for (int i = 0; i < byteArrayObj.length(); i += 3) {
        String s = byteArrayObj.substring(i, i + 2);
        int unsignedByte = Integer.parseInt(s, 16);
        os.write(unsignedByte);
    }
    return os.toByteArray();
  }
  
  
}
