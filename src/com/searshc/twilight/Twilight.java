package com.searshc.twilight;

import java.util.*;
import java.io.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.twilight.util.PropertyLoader;
import com.searshc.twilight.util.TwilightHttpServer;

public class Twilight
{  
  private static Logger logger = Logger.getLogger(Twilight.class);
  private static Properties prop;
  //private static final ClassLoader classLoader = Twilight.class.getClassLoader();
  
  static{
    try{
      prop = PropertyLoader.loadProperties("log4j", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public Twilight(String tag)
  {
    logger.info("Twilight engine started");
    final HttpServerThread httpServer = new HttpServerThread();
    Thread serverThread = new Thread(httpServer);
    serverThread.start();
    searchTag(tag);
    
    synchronized (this) {
      try {
        System.out.println( "Waiting for 1000 ms to process more request(s) before exitting" );
        wait(1000);
        System.out.println( "No new request found. Exitting twilight" );
      } catch (InterruptedException e) {
        Thread.currentThread().isInterrupted();
      }
      System.out.println( "Shutting down main thread." );
      System.exit(0);
    }
  }
  
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{ 
	  String tag = args[0];
	  new Twilight(tag);
	  final HttpServerThread httpServer = new HttpServerThread();
	  Thread serverThread = new Thread(httpServer);
	  serverThread.start();
	}
	
	private void execute(File file)
	{
    List<String> commands = new ArrayList<String>();
    try
    {
      /** get the list of commands from the script */
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine())
      {
        commands.add(scanner.nextLine());
      }

      ScriptCommandParser cmdParser;      
      String action, method;
      AbstractScriptRequestCommand lastCommand = null;
      
      /** execute each line of the script one-by-one */
      for(int i=0; i<commands.size(); i++)
      {
        /** get the script command parameters */
        cmdParser = new ScriptCommandParser(commands.get(i));
        action = cmdParser.getAction();
        method = cmdParser.getMethod();
        Object obj = null;
          
        if(method.equals("adjustprice") && action.equals("POST") || action.equals("RECV")){
          obj = cmdParser.getJsonObject();
        }
        else{
            obj = cmdParser.getByteArrayObject();
        }

        /** now generate the command object and execute it */
        ScriptCommandFactory scFactory = new ScriptCommandFactory();
        AbstractScriptCommand sc = scFactory.getCommand(method, action, obj);
        
        if(sc != null)
        {
          if(sc.isRequest())
          {
            sc.execute();
            lastCommand = (AbstractScriptRequestCommand)sc; // keep track of the last command so we can handle the response
          }
          else
          {
            AbstractScriptResponseCommand responseCmd = (AbstractScriptResponseCommand)sc;
            if(lastCommand != null){
              responseCmd.setResponse(lastCommand.getResponse());
            }
            responseCmd.execute();  // execution of a response is validation
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
    catch (Exception e)
    {
      e.printStackTrace();
    }
	}
	
	private void searchTag(String tags){
    prop = PropertyLoader.loadProperties("config", null);
	  try{
  	  if(tags.equalsIgnoreCase(prop.getProperty("tag0"))){
          final File dir = new File(prop.getProperty(tags + "_dir_url"));
          final File file = new File(dir.getPath());
          logger.info("Scanning files in dir : " + dir.getAbsolutePath() + " \nNumber of files found : " + file.listFiles().length);
              for (File f : file.listFiles()) {
                 if (f.isFile()) {
                     this.execute(new File(f.getPath()));
                  }
              }
  	       }
        }
	      catch(Exception ex)
	      {
          logger.error("Error " + ex);
        }
	    }
	  }

  class HttpServerThread implements Runnable
  { 
    @Override
    public void run()
    {
        System.out.println("Http Server Thread Started");
        final TwilightHttpServer server = new TwilightHttpServer();
        
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