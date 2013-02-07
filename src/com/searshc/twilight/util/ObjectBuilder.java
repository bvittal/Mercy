package com.searshc.twilight.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ObjectBuilder
{ 
  private static List<byte[]> scriptObjectList = new ArrayList<byte[]>();
  private static Map<String,byte[]> inquiryObjectMap = new HashMap<String,byte[]>();
  
  public static List<byte[]> getObjects()
  {
    return scriptObjectList;
  }

  public static void setObjects(byte [] buf)
  {
    if(buf != null){
      scriptObjectList.add(buf);
    }
  }

  public static Map<String,byte[]> getInqObjects()
  {
    return inquiryObjectMap;
  }

  public static void setInqObjects(String indicator, byte [] buf)
  {
    if(StringUtils.isNotBlank(indicator) && buf != null){
      inquiryObjectMap.put(indicator, buf);
    }
  }
}