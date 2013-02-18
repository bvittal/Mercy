package com.searshc.mercy.util;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.searshc.mercy.service.UPASResponseFinder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public final class MercyHttpServer
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
          new MercyHttpServer().startServer();
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
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        OutputStream os = null;     
        InputStream is = t.getRequestBody();
        Headers headers = t.getRequestHeaders();
        
        byte[] requestBuffer = new byte[is.available()];
        is.read(requestBuffer);
        is.close();
        
        int respLength = 0;
        byte [] responseObject = null;
        int endPosition = findXMLHeaderEnd(requestBuffer);
        byte[] buffer = new byte[requestBuffer.length - endPosition];
        buffer = Arrays.copyOfRange(requestBuffer, endPosition, requestBuffer.length);
        byte[] inquryResp = finder.findResponse(buffer);
        
        if(inquryResp != null){
          System.out.println("RESPONSE SENT : " + byteResponse(inquryResp));
          respLength = createXMLResponseHeader().length + inquryResp.length;
          logger.debug("HTTP Response length : " + respLength);
          logger.debug("HTTP Response : " + new String(inquryResp));
          outputStream.write(createXMLResponseHeader());
          outputStream.write(inquryResp);
          responseObject = outputStream.toByteArray();
          t.sendResponseHeaders(HTTP_OK, respLength);
        }else{
          outputStream.write(createXMLResponseHeader());
          outputStream.write("Response not found".getBytes());
          responseObject = outputStream.toByteArray();
          t.sendResponseHeaders(HTTP_INTERNAL_ERROR, respLength);
        }
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