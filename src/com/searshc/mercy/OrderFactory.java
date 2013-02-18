package com.searshc.mercy;

import java.util.*;
import com.upas.sears.service.domain.*;

public class OrderFactory
{
  Order order; /** this is the Order being built */
  
  /** these are the keys used in the script */
  private static final String REGISTER_NUM = "registerNumber";
  private static final String PHYSICAL_STORE = "physicalStoreNumber";
  private static final String TRANS_NUM = "transactionNumber";
  private static final String ASSOC_FLAG = "associateFlag";
  private static final String KIDVANTAGE_FLAG = "kidvantageFlag";
  private static final String CRAFTSMAN_FLAG = "craftsmanClubFlag";
  private static final String PULSE_FLAG = "pulseFlag";
  private static final String SEARS_CARD = "searsCard";
  private static final String COUPON_CODE = "coupon.code";
  private static final String ITEM_ID = "item[].lineItemId";
  private static final String ITEM_ADJUST_FLAG = "item[].adjustFlag";
  private static final String ITEM_STORE_NUM = "item[].storeNumber";
  private static final String ITEM_PART_NUM = "item[].partNumber";
  private static final String ITEM_CATENTRY = "item[].catentryId";
  private static final String ITEM_FULFILL_TYPE = "item[].fulfillmentType";
  private static final String ITEM_FULFILL_COST = "item[].fulfillmentCost";
  private static final String ITEM_PRICE_MATCH = "item[].priceMatch";
  private static final String ITEM_PLU_TYPE = "item[].pluPriceType";
  private static final String ITEM_PLU_PRICE = "item[].pluPrice";
  private static final String ITEM_QUANTITY = "item[].quantity";


  public OrderFactory(HashMap map)
  {
    order = new Order();
    LineItem item = new LineItem();
    
    Set keys = map.keySet();
    Iterator keyIterator = keys.iterator();
    
    while(keyIterator.hasNext())
    {
      String key = (String)keyIterator.next();
      String val = (String)map.get(key);
      
      if(key.equals(REGISTER_NUM))
      {
        order.setRegisterNumber(val);
      }
      else if(key.equals(PHYSICAL_STORE))
      {
        order.setPhysicalStoreNumber(val);
      }
      else if(key.equals(TRANS_NUM))
      {
        order.setTransactionNumber(val);
      }
      else if(key.equals(ASSOC_FLAG))
      {
        order.setAssociateFlag(val);
      }
      else if(key.equals(KIDVANTAGE_FLAG))
      {
        order.setKidvantageFlag(val);
      }
      else if(key.equals(CRAFTSMAN_FLAG))
      {
        order.setCraftsmanClubFlag(val);
      }
      else if(key.equals(PULSE_FLAG))
      {
        order.setPulseFlag(val);
      }
      else if(key.equals(SEARS_CARD))
      {
        order.setSearsCard(val);
      }
      
      /** the item-level parameters */
      else if(key.equals(ITEM_ID))
      {
        item.setLineItemId(val);
      }
      else if(key.equals(ITEM_ADJUST_FLAG))
      {
        item.setLineItemId(val);
      }
      else if(key.equals(ITEM_STORE_NUM))
      {
        item.setStoreNumber(val);
      }
      else if(key.equals(ITEM_PART_NUM))
      {
        item.setPartNumber(val);
      }
      else if(key.equals(ITEM_CATENTRY))
      {
        item.setCatentryId(val);
      }
      else if(key.equals(ITEM_FULFILL_TYPE))
      {
        item.setFulfillmentType(val);
      }
      else if(key.equals(ITEM_FULFILL_COST))
      {
        item.setFulfillmentCost(val);
      }
      else if(key.equals(ITEM_PRICE_MATCH))
      {
       //item.setPriceMatch(val);
      }
      else if(key.equals(ITEM_PLU_TYPE))
      {
        item.setPluPriceType(val);
      }
      else if(key.equals(ITEM_PLU_PRICE))
      {
        item.setPluPrice(val);
      }
      else if(key.equals(ITEM_QUANTITY))
      {
        item.setQuantity(val);
      }
    }
    
  }  

  public Order getOrder()
  {
    return this.order;
  }
}
