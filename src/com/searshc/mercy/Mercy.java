package com.searshc.mercy;

import java.util.*;
import java.io.*;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.mercy.reports.beans.DataBean;
import com.searshc.mercy.reports.engine.Reporter;
import com.searshc.mercy.service.MercyConstants;
import com.searshc.mercy.util.MercyTrace;
import com.searshc.mercy.util.PropertyLoader;
import com.searshc.mercy.util.MercyHttpServer;

public class Mercy
{  
  private static Logger logger = Logger.getLogger(Mercy.class);
  private static Properties prop;
  private String scenarioType = StringUtils.EMPTY;
  
  static{
    try{
      prop = PropertyLoader.loadProperties("log4j", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public Mercy(String tag, String testReportName)
  {
    logger.info("Mercy engine started");
    final HttpServerThread httpServer = new HttpServerThread();
    final Reporter reporter = new Reporter();
    Thread serverThread = new Thread(httpServer);
    serverThread.start();
    searchTag(tag);
    synchronized (this) {
      try {
        System.out.println( "Waiting for 2000 ms to process more request(s) before exitting" );
        wait(2000);
        if(prop.getProperty("generateReport").equalsIgnoreCase("true")){
          System.err.println( "Generating test results report, please wait..." ); 
          final List<DataBean> dataBeanList = AbstractScriptResponseCommand.getReportData();
            if(dataBeanList.size() > 0){
              String url = reporter.generateReport(dataBeanList, testReportName);
              System.err.println( "Report generated successfully at :" + url);
            }
         }
      } catch (InterruptedException e) {
        Thread.currentThread().isInterrupted();
      } catch (JRException ex){
        System.out.println("Error generating jasper report " + ex);
      }
      System.out.println( "Shutting down main thread." );
      System.exit(0);
    }
  }
  
  private String getScenarioType(String commandLine){
    String scenarioType = StringUtils.EMPTY;
    int startingIndex = commandLine.indexOf('[');
      if (startingIndex != -1){
      int endingIndex = commandLine.indexOf(']', startingIndex);
        if (endingIndex != -1){
          scenarioType = commandLine.substring(startingIndex + 1, endingIndex);
        }
      }
      return scenarioType;
    }
  
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{ 
	  String tag = args[0];
	  String reportName = args[1];
	  String trace = args[2];
	  if(StringUtils.isBlank(tag) && StringUtils.isBlank(reportName) && StringUtils.isBlank(trace)){
		  System.err.println("Missing input parameters");
		  System.exit(0);
	  }else{
	  if(trace.equalsIgnoreCase("-v"))
		  MercyTrace.switchTraceOn(Boolean.TRUE);
	  else if(trace.equalsIgnoreCase("-o"))
		  MercyTrace.switchTraceOn(Boolean.FALSE);
	  else
		  System.err.println("Invalid input parameters");
	  new Mercy(tag,reportName);
	  final HttpServerThread httpServer = new HttpServerThread();
	  Thread serverThread = new Thread(httpServer);
	  serverThread.start();
	  }
	}
	
	private void execute(File file)
	{
    List<String> commands = new ArrayList<String>();
    StringBuilder lineBuilder = new StringBuilder();
    logger.info("Parsing script file...");
    try
    {
      /** get the list of commands from the script */
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine())
      {
        String line = scanner.nextLine(); 
        System.out.println(line);
        if (StringUtils.isNotBlank(line) && !line.startsWith("#")){
        	if(line.endsWith(";")){
        		if(lineBuilder != null && lineBuilder.length() > 0){
        			lineBuilder.append(line.trim());
        			commands.add(lineBuilder.toString());
        			lineBuilder.setLength(0);
        		}else{
        			commands.add(line);
        		}
        	}else{
        		lineBuilder.append(line);
        	}
        }else{
          if(StringUtils.isNotBlank(line)){
            scenarioType = this.getScenarioType(line);
            if(StringUtils.isNotBlank(scenarioType))
              commands.add("#:"+scenarioType);
          }
        }
      }
      

      ScriptCommandParser cmdParser;      
      String action, method;
      AbstractScriptRequestCommand lastCommand = null;
      String scenario = StringUtils.EMPTY;
      
      /** execute each line of the script one-by-one */
      for(int i=0; i<commands.size(); i++)
      {
        /** get the script command parameters */
        cmdParser = new ScriptCommandParser(commands.get(i));
        action = cmdParser.getAction();
        method = cmdParser.getMethod();
        
        if(StringUtils.isBlank(action) && StringUtils.isBlank(method)){
          scenario = cmdParser.getScenario();
        }
        Object obj = null;
          
        if(StringUtils.isNotBlank(action) && StringUtils.isNotBlank(method)){
          if(method.equals(MercyConstants.METHOD_ADJUST_PRICE) && 
              (action.equals(MercyConstants.ACTION_ADJUST_PRICE) || 
                  action.equals(MercyConstants.ACTION_SCRIPT_RESPONSE))){
              obj = cmdParser.getJsonObject();
          }else if((method.equals(MercyConstants.HTTP_400) || 
                      method.equals(MercyConstants.HTTP_404) || 
                        method.equals(MercyConstants.HTTP_500)) && 
                          action.equals(MercyConstants.ACTION_SCRIPT_RESPONSE)){
              throw new HttpException();
          }else if(method.equals(MercyConstants.HTTP_200) && 
              action.equals(MercyConstants.ACTION_SCRIPT_RESPONSE)){
            obj = cmdParser.getJsonObject();
          }else if(StringUtils.isNotBlank(scenario)){
            obj = cmdParser.getByteArrayObject();
        }
     
        /** now generate the command object and execute it */
        ScriptCommandFactory scFactory = new ScriptCommandFactory();
        AbstractScriptCommand sc = scFactory.getCommand(method, action, obj);
        
        if(sc != null)
        {
          if(sc.isRequest())
          {
            sc.execute(scenario);
            lastCommand = (AbstractScriptRequestCommand)sc; // keep track of the last command so we can handle the response
          }
          else
          {
            AbstractScriptResponseCommand responseCmd = (AbstractScriptResponseCommand)sc;
            if(lastCommand != null){
              responseCmd.setResponse(lastCommand.getResponse());
            }
            responseCmd.execute(scenario);  // execution of a response is validation
            if(lastCommand != null){
              lastCommand.cleanup();  // clean up resources used by last command
            }
          }
        }
        else
        {
          logger.error("Unrecognized command in script");
        }
      }
     }
    }
    catch (Exception ex)
    {
      System.err.println("Malformed Script Exception " + ex);
    }
	}
	
	private void searchTag(String tags){
    prop = PropertyLoader.loadProperties("config", null);
	  try{
  	  if(tags.equalsIgnoreCase(prop.getProperty("tag0"))){
          final File dir = new File(prop.getProperty(tags + "_dir_url"));
          final File file = new File(dir.getPath());
          logger.info("Scanning script files in dir : " + dir.getAbsolutePath() + " \nNumber of files found : " + file.listFiles().length);
              for (File f : file.listFiles()) {
                 if (f.isFile()) {
                     this.execute(new File(f.getPath()));
                  }
              }
  	       }
        }
	      catch(Exception ex)
	      {
          System.out.println ("Error " + ex);
        }
	    }
	  }

  class HttpServerThread implements Runnable
  { 
    @Override
    public void run()
    {
        System.out.println("Http Server Thread Started");
        final MercyHttpServer server = new MercyHttpServer();
        
        try
        {
          if(!server.checkServerState())
            server.startServer();
        }
        catch (IOException e)
        {
          System.out.print("Server Port already bound : " + e);
          System.exit(0);
        }
      }
    }