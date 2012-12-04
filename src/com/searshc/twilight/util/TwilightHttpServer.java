package com.searshc.twilight.util;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.searshc.twilight.service.UPASResponseFinder;
import com.searshc.twilight.service.UpasResponseParser;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public final class TwilightHttpServer
{ 
  private static boolean bound = Boolean.FALSE;
  
    public void startServer() throws IOException {
        final HttpServer server;
        final Properties prop = PropertyLoader.loadProperties("config", null);
        final String HOST = prop.getProperty("serverHost");
        final int PORT = Integer.parseInt(prop.getProperty("serverPort"));
        final InetSocketAddress endPoint = new InetSocketAddress(HOST, PORT);
        
        server = HttpServer.create(endPoint, 10);
        server.createContext("/SearsSimulator/simulator", new EchoHandler());
        
          server.start();
          bound = Boolean.TRUE;
          System.out.println("Server started at : " + endPoint.getAddress() + ":" + endPoint.getPort());
        }
    
    public boolean checkServerState(){
      return bound;
      }
    }

    /**
    public static void main(String args[]){
        try
        {
          for(int i=0; i<2; i++){
          new TwilightHttpServer().startServer();
          }
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }*/

class EchoHandler implements HttpHandler
{
    private static Logger logger = Logger.getLogger(EchoHandler.class);
    
    public void handle(HttpExchange t) throws IOException {
        
        final UPASResponseFinder finder = new UPASResponseFinder();
        final UpasResponseParser parser = new UpasResponseParser();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        OutputStream os = null;     
        InputStream is = t.getRequestBody();
        Headers headers = t.getRequestHeaders();
        /**
        for (String name : headers.keySet()) {
          List<String> values = headers.get(name);
          for (String value : values) {
              System.out.println(name + ": " + value);
          }
        }*/
        
        
        byte[] requestBuffer = new byte[is.available()];
        is.read(requestBuffer);
        is.close();
        
        int endPosition = findXMLHeaderEnd(requestBuffer);
        byte[] buffer = new byte[requestBuffer.length - endPosition];
        buffer = Arrays.copyOfRange(requestBuffer, endPosition, requestBuffer.length);
        byte[] inquryResp = finder.findResponse(buffer);
        System.out.println("RESPONSE SENT     " + byteResponse(inquryResp));
        int respLength = createXMLResponseHeader().length + inquryResp.length;
        
        System.out.println("HTTP Response length : " + respLength);
        System.out.println("HTTP Response : " + new String(inquryResp));
        
        outputStream.write(createXMLResponseHeader());
        outputStream.write(inquryResp);
        byte [] responseObject = outputStream.toByteArray();
        /**
        if(responseObject != null){
          try
          {
            parser.parseResponse(responseObject);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }*/
        
          t.sendResponseHeaders(HTTP_OK, respLength);
          os = t.getResponseBody();
          os.write(responseObject);
          os.close();
          t.close();
      }
    
    private int findXMLHeaderEnd(byte[] bytes) {
      String s = new String(bytes);
      logger.info("Searching String : " + s);

      String searchString = "</POSREQ>";
      logger.info("Searching String is " + searchString);

      int i = StringUtils.indexOf(s, searchString);
      logger.info("Found String at position :" + i + " length of searchString is + " + searchString.length());
      return i + searchString.length();
  }

  protected byte[] createXMLResponseHeader() {
      String s =
              "<POSRESP><type>NR45</type><unitNumber>00000</unitNumber><id></id><returnCode>0</returnCode><responseDescription>success</responseDescription></POSRESP>";
      return s.getBytes();
  }
  
  private String byteResponse(byte [] buffer){
    StringBuilder sb = new StringBuilder();

    for (byte b : buffer) {
        sb.append(String.format("%02x", b).toUpperCase());
        sb.append(" ");
    }
    return sb.toString();
  }
}