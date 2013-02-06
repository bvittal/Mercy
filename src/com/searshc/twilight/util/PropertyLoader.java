package com.searshc.twilight.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class PropertyLoader
{
	private static final boolean THROW_ON_LOAD_FAILURE = true;
    private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;
    private static final String SUFFIX = ".properties";
    
    public static Properties loadProperties (String name, ClassLoader loader)
    {
        if (name == null)
            throw new IllegalArgumentException ("null input: name");
        
        if (name.startsWith ("/"))
            name = name.substring (1);
            
        if (name.endsWith (SUFFIX))
            name = name.substring (0, name.length () - SUFFIX.length ());
        
        Properties result = null;
        
        InputStream in = null;
        try
        {
            if (loader == null) loader = ClassLoader.getSystemClassLoader ();
            
            if (LOAD_AS_RESOURCE_BUNDLE)
            {    
                name = name.replace ('/', '.');
                // Throws MissingResourceException on lookup failures:
                final ResourceBundle rb = ResourceBundle.getBundle (name, Locale.getDefault (), loader);
                
                result = new Properties ();
                for (Enumeration<String> keys = rb.getKeys (); keys.hasMoreElements ();)
                {
                    final String key = (String) keys.nextElement ();
                    final String value = rb.getString (key);
                    
                    result.put (key, value);
                } 
            }
            else
            {
              String path = System.getProperty("user.dir");  
              
              name = name.replace ('.', '/');
                
                if (! name.endsWith (SUFFIX))
                    name = name.concat (SUFFIX);
                  
                // Returns null on lookup failures:
                //in = loader.getResourceAsStream ("/" + name);
                String url = path + "/resources/" + name;
                result = new Properties ();
                result.load (new FileInputStream(url));
                /**
                in = loader.getResourceAsStream (url);
                if (in != null)
                {
                    result = new Properties ();
                    result.load (in); // Can throw IOException
                }*/
            }
        }
        catch (Exception e)
        {
            result = null;
        }
        finally
        {
            if (in != null) try { in.close (); } catch (Throwable ignore) {}
        }
        
        if (THROW_ON_LOAD_FAILURE && (result == null))
        {
            throw new IllegalArgumentException ("could not load [" + name + "]"+
                " as " + (LOAD_AS_RESOURCE_BUNDLE
                ? "a resource bundle"
                : "a classloader resource"));
        }
        
        return result;
    }
    
    /**
     * A convenience overload of {@link #loadProperties(String, ClassLoader)}
     * that uses the current thread's context classloader.
     */
    public static Properties loadProperties (final String name)
    {
        return loadProperties (name,
            Thread.currentThread ().getContextClassLoader ());
    }
    
    public static InputStream fileLoader(String fileName){
      InputStream inputStream = null;
      String path = System.getProperty("user.dir");  
      String url = path + "/resources/templates/" + fileName;
      try{
        inputStream = new FileInputStream(url);
      }catch(Exception ex){
        System.out.println("Exception " + ex);
      }
      return inputStream;
    }
} // End of class