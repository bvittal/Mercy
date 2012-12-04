package com.searshc.twilight.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ObjectBuilder
{
  
  private static Multimap<String, byte[]> scriptMultimap = ArrayListMultimap.create();
  
  public static Multimap<String, byte[]> getObjects()
  {
    return scriptMultimap;
  }

  public static void setObjects(String indicator, byte [] buf)
  {
    ObjectBuilder.scriptMultimap.put(indicator, buf);
  }
}