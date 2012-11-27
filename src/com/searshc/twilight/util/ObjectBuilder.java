package com.searshc.twilight.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ObjectBuilder
{
  /**
  private static HashMap<String, byte[]> objects = new HashMap<String, byte[]>();

  public static HashMap<String, byte[]> getObjects()
  {
    return objects;
  }

  public static void setObjects(String indicator, byte [] buf)
  {
    ObjectBuilder.objects.put(indicator, buf);
  }*/
  
  private static Multimap<String, byte[]> myMultimap = ArrayListMultimap.create();
  
  public static Multimap<String, byte[]> getObjects()
  {
    return myMultimap;
  }

  public static void setObjects(String indicator, byte [] buf)
  {
    ObjectBuilder.myMultimap.put(indicator, buf);
  }
  
  /**
  private static Multimap<String, Multimap<String, byte[]>> myMultimap = ArrayListMultimap.create();
  
  public static Multimap<String, Multimap<String, byte[]>> getObjects()
  {
    return myMultimap;
  }

  public static void setObjects(String reqIndicator, Multimap<String, byte[]> multimap)
  {
    ObjectBuilder.myMultimap.put(reqIndicator, multimap);
  }*/
  
}
