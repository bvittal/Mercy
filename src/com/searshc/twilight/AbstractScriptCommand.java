package com.searshc.twilight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;

import com.searshc.twilight.util.PropertyLoader;
import com.upas.sears.service.domain.*;


public abstract class AbstractScriptCommand
{
  /** these are the keys used in the script */
  public static final String REGISTER_NUM = "registerNumber";
  public static final String PHYSICAL_STORE = "physicalStoreNumber";
  public static final String TRANS_NUM = "transactionNumber";
  public static final String ASSOC_FLAG = "associateFlag";
  public static final String KIDVANTAGE_FLAG = "kidvantageFlag";
  public static final String CRAFTSMAN_FLAG = "craftsmanClubFlag";
  public static final String PULSE_FLAG = "pulseFlag";
  public static final String SEARS_CARD = "searsCard";
  
  public static final String ITEM_LIST = "items";
  public static final String ITEM = "item";
  public static final String ITEM_ID = "lineItemId";
  public static final String ITEM_ADJUST_FLAG = "adjustFlag";
  public static final String ITEM_REGULAR_PRICE = "regularPrice";
  public static final String ITEM_MANUAL_PRICE = "manualPrice";
  public static final String ITEM_STORE_NUM = "storeNumber";
  public static final String ITEM_STORE_NAME = "storeName";
  public static final String ITEM_PART_NUM = "partNumber";
  public static final String ITEM_CATENTRY = "catentryId";
  public static final String ITEM_UPC = "upcCode";
  public static final String ITEM_FULFILL_TYPE = "fulfillmentType";
  public static final String ITEM_FULFILL_COST = "fulfillmentCost";
  public static final String ITEM_PRICE_MATCH = "priceMatch";
  public static final String ITEM_PLU_TYPE = "pluPriceType";
  public static final String ITEM_PLU_PRICE = "pluPrice";
  public static final String ITEM_QUANTITY = "quantity";
  public static final String ITEM_ZERO_PERCENT = "qualifyZeroPercentFinancing";
  public static final String ITEM_DELIVERY_CHARGE = "deliveryCharge";
  
  /** Adjustments */
  public static final String ADJUSTMENT_AMOUNT = "amount";
  public static final String ADJUSTMENT_CSO_CODE = "csoCode";
  public static final String ADJUSTMENT_ENDDATE = "endDate";
  public static final String ADJUSTMENT_EXCL_TEXT = "exclusionText";
  public static final String ADJUSTMENT_LONG_DESC = "longDesc";
  public static final String ADJUSTMENT_MARK_DOWN = "markdownCode";
  public static final String ADJUSTMENT_NFX_CODE = "nfxCode";
  public static final String ADJUSTMENT_PROMO_NAME = "promoName";
  public static final String ADJUSTMENT_PROMO_TYPE = "promoType";
  public static final String ADJUSTMENT_SHORT_DESC = "shortDesc";
  public static final String ADJUSTMENT_TYPE = "adjustmentType";
 
  
  /** the default values for each order attribute */
  private static final String DEFAULT_REGISTER_NUM = "00000";
  private static final String DEFAULT_PHYSICAL_STORE = "11111";
  private static final String DEFAULT_TRANS_NUM = "22222";
  private static final String DEFAULT_ASSOC_FLAG = "0";
  private static final String DEFAULT_KIDVANTAGE_FLAG = "0";
  private static final String DEFAULT_CRAFTSMAN_FLAG = "0";
  private static final String DEFAULT_PULSE_FLAG = "0";
  private static final String DEFAULT_SEARS_CARD = "0";
  private static final String [] DEFAULT_COUPON_CODE = { "R575555", "R5711111" };;
  
  private static final String DEFAULT_ITEM_ADJUST_FLAG = "1";
  private static final String DEFAULT_ITEM_ID = "0";
  private static final String DEFAULT_ITEM_STORE_NUM = DEFAULT_PHYSICAL_STORE;
  private static final String DEFAULT_ITEM_PRICE_MATCH = "0";
  private static final String DEFAULT_ITEM_QUANTITY = "1";
  private static final String DEFAULT_ITEM_STORE_NAME = "SHC CORP";
  private static final String DEFAULT_ITEM_PART_NUM = "00000000000";
  private static final String DEFAULT_ITEM_CATENTRY_ID = "00000";
  private static final String DEFAULT_ITEM_UPC_CODE = "00000";
  private static final String DEFAULT_ITEM_FULFILLMENT_TYPE = "0";
  private static final String DEFAULT_ITEM_FULFILLMENT_COST = "0.00";
  private static final String DEFAULT_ITEM_REGULAR_PRICE = "1.00";
  private static final String DEFAULT_ITEM_MANUAL_PRICE = "";
  private static final String DEFAULT_ITEM_PLU_PRICE = "1.00";
  private static final String DEFAULT_ITEM_PLU_TYPE = "0";
  private static final String DEFAULT_ITEM_ZERO_PERCENT = "";
  private static final String DEFAULT_ITEM_MANUAL_REDUCTIONS = null;
  
  /** Defaults Adjustments Values */
  public static final String DEFAULT_ADJUSTMENT_AMOUNT = "";
  public static final String DEFAULT_ADJUSTMENT_CSO_CODE = "";
  public static final String DEFAULT_ADJUSTMENT_ENDDATE = "";
  public static final String DEFAULT_ADJUSTMENT_EXCL_TEXT = "";
  public static final String DEFAULT_ADJUSTMENT_LONG_DESC = "";
  public static final String DEFAULT_ADJUSTMENT_MARK_DOWN = "";
  public static final String DEFAULT_ADJUSTMENT_NFX_CODE = "";
  public static final String DEFAULT_ADJUSTMENT_PROMO_NAME = "";
  public static final String DEFAULT_ADJUSTMENT_PROMO_TYPE = "";
  public static final String DEFAULT_ADJUSTMENT_SHORT_DESC = "";
  public static final String DEFAULT_ADJUSTMENT_TYPE = "";
  
  protected Order order = new Order();
  protected OrderResponse orderResponse = new OrderResponse();
  protected ObjectMapper mapper = new ObjectMapper();
  
  private final Properties prop = PropertyLoader.loadProperties("config", null);
  
  /** connection parameters */
  public final String HOST = prop.getProperty("host");
  public final String PORT = prop.getProperty("port");
  public final String VERSION = prop.getProperty("version");
  public final String CONTENT_TYPE = prop.getProperty("contentType");
  public final String BASE_URL = "http://" + HOST + ":" + PORT + "/upas/" + VERSION + "/";
  
  /** the methods */
  public abstract void execute();
  public abstract boolean isRequest();
  
  protected void toOrderResponse(TwilightJsonObject obj)
  {
    /** need to account for messages also */
    this.toOrder(obj, false);
    this.orderResponse.setData(order);
  }
  
  protected void toOrder(TwilightJsonObject obj, boolean useDefaults)
  {
    HashMap<String,String> map = obj.getParameters();
    
    if(map != null)
    {
      order = new Order();

      if(map.containsKey(REGISTER_NUM))
        order.setRegisterNumber(map.get(REGISTER_NUM));
      else
        if(useDefaults) order.setRegisterNumber(DEFAULT_REGISTER_NUM);

      if(map.containsKey(PHYSICAL_STORE))
        order.setPhysicalStoreNumber(map.get(PHYSICAL_STORE));
      else
        if(useDefaults) order.setPhysicalStoreNumber(DEFAULT_PHYSICAL_STORE);
                
      if(map.containsKey(TRANS_NUM))
        order.setTransactionNumber(map.get(TRANS_NUM));
      else
        if(useDefaults) order.setTransactionNumber(DEFAULT_TRANS_NUM);
        
      if(map.containsKey(ASSOC_FLAG))
        order.setAssociateFlag(map.get(ASSOC_FLAG));
      else
        if(useDefaults) order.setAssociateFlag(DEFAULT_ASSOC_FLAG);
        
      if(map.containsKey(KIDVANTAGE_FLAG))
        order.setKidvantageFlag(map.get(KIDVANTAGE_FLAG));
      else
        if(useDefaults) order.setKidvantageFlag(DEFAULT_KIDVANTAGE_FLAG);
        
      if(map.containsKey(CRAFTSMAN_FLAG))
        order.setCraftsmanClubFlag(map.get(CRAFTSMAN_FLAG));
      else
        if(useDefaults) order.setCraftsmanClubFlag(DEFAULT_CRAFTSMAN_FLAG);
        
      if(map.containsKey(PULSE_FLAG))
        order.setPulseFlag(PULSE_FLAG);
      else
        if(useDefaults) order.setPulseFlag(DEFAULT_PULSE_FLAG);
        
      if(map.containsKey(SEARS_CARD))
        order.setSearsCard(map.get(SEARS_CARD));
      else
        if(useDefaults) order.setSearsCard(DEFAULT_SEARS_CARD);

      /** the parameter lists */
      List<TwilightJsonArray> arrayList = obj.getTwilightJsonArray();
      
      for(int i=0; i<arrayList.size(); i++)
      {
        String name = arrayList.get(i).getName();
        
        if(name.equals(TwilightJsonArray.COUPON_KEY))
        {
          order.setCouponCodes(arrayList.get(i).getStringArray());
        }
        else if(name.equals(TwilightJsonArray.OPTED_PROMO_KEY))
        {
          order.setOptedPromotions(arrayList.get(i).getStringArray());
        }
      }
    }
      
      /** the item-level parameters */
      List<LineItem> allItems = new ArrayList<LineItem>();
      TwilightJsonObject lineItems;
      
      
      if(obj.getTwilightJsonObject().size() > 0)
      {
        List<TwilightJsonObject> itemList = obj.getTwilightJsonObject().get(0).getTwilightJsonObject();
        Iterator<TwilightJsonObject> itemIterator = itemList.iterator();
        while(itemIterator.hasNext())
        {
          LineItem item = new LineItem();
          lineItems = itemIterator.next();
          HashMap<String,String> items = lineItems.getParameters();
          
          if(items.containsKey(ITEM_ID))
            item.setLineItemId(items.get(ITEM_ID));
          else
            if(useDefaults) item.setLineItemId(DEFAULT_ITEM_ID);
          
          if(items.containsKey(ITEM_STORE_NUM))
            item.setStoreNumber(items.get(ITEM_STORE_NUM));
          else
            if(useDefaults) item.setStoreNumber(DEFAULT_ITEM_STORE_NUM);
          
          if(items.containsKey(ITEM_STORE_NAME))
            item.setStoreName(items.get(ITEM_STORE_NAME));
          else
            if(useDefaults) item.setStoreName(DEFAULT_ITEM_STORE_NAME);
 
          if(items.containsKey(ITEM_PART_NUM))
            item.setPartNumber(items.get(ITEM_PART_NUM));
          else
            if(useDefaults) item.setPartNumber(DEFAULT_ITEM_PART_NUM);
        
          if(items.containsKey(ITEM_ADJUST_FLAG))
            item.setAdjustFlag(items.get(ITEM_ADJUST_FLAG));
          else
            if(useDefaults) item.setAdjustFlag(DEFAULT_ITEM_ADJUST_FLAG);
          
          if(items.containsKey(ITEM_CATENTRY))
            item.setCatentryId(items.get(ITEM_CATENTRY));
          else
            if(useDefaults) item.setCatentryId(DEFAULT_ITEM_CATENTRY_ID);
          
          if(items.containsKey(ITEM_UPC))
            item.setUpcCode(items.get(ITEM_UPC));
          else
            if(useDefaults) item.setUpcCode(DEFAULT_ITEM_UPC_CODE);
          
          if(items.containsKey(ITEM_FULFILL_TYPE))
            item.setFulfillmentType(items.get(ITEM_FULFILL_TYPE));
          else
            if(useDefaults) item.setFulfillmentType(DEFAULT_ITEM_FULFILLMENT_TYPE);
          
          if(items.containsKey(ADJUSTMENT_AMOUNT))
            item.setFulfillmentCost(items.get(ADJUSTMENT_AMOUNT));
          else
            if(useDefaults) item.setFulfillmentCost(DEFAULT_ADJUSTMENT_AMOUNT);
      
          if(items.containsKey(ITEM_REGULAR_PRICE))
            item.setRegularPrice(items.get(ITEM_REGULAR_PRICE));
          else
            if(useDefaults) item.setRegularPrice(DEFAULT_ITEM_REGULAR_PRICE);
        
          if(items.containsKey(ITEM_MANUAL_PRICE))
            item.setManualPrice(items.get(ITEM_MANUAL_PRICE));
          else
            if(useDefaults) item.setManualPrice(DEFAULT_ITEM_MANUAL_PRICE);
 /*       
        if(items.containsKey(ITEM_PRICE_MATCH))
          item.setPriceMatch(items.get(ITEM_PRICE_MATCH));
        else
          item.setPriceMatch(DEFAULT_ITEM_PRICE_MATCH);
   */   
          if(items.containsKey(ITEM_PLU_PRICE))
            item.setPluPrice(items.get(ITEM_PLU_PRICE));
          else
            if(useDefaults) item.setPluPrice(DEFAULT_ITEM_PLU_PRICE);
      
          if(items.containsKey(ITEM_PLU_TYPE))
            item.setPluPriceType(items.get(ITEM_PLU_TYPE));
          else
            if(useDefaults) item.setPluPriceType(DEFAULT_ITEM_PLU_TYPE);
      
          if(items.containsKey(ITEM_QUANTITY))
            item.setQuantity(items.get(ITEM_QUANTITY));
          else
            if(useDefaults) item.setQuantity(DEFAULT_ITEM_QUANTITY);
          
          if(items.containsKey(ITEM_ZERO_PERCENT))
            item.setQualifyZeroPercentFinancing(items.get(ITEM_ZERO_PERCENT));
          else
            if(useDefaults) item.setQualifyZeroPercentFinancing(DEFAULT_ITEM_ZERO_PERCENT);
          
          allItems.add(item);          
        }
      }
      /**
      if(obj.getTwilightJsonObject().size() > 0)
      {
        List<TwilightJsonObject> adjustmentList = obj.getTwilightJsonObject().get(1).getTwilightJsonObject();
        Iterator<TwilightJsonObject> adjustmentItr = adjustmentList.iterator();
        while(adjustmentItr.hasNext())
        {
          LineItem adjustment = new LineItem();
          TwilightJsonObject adjustmentItems = adjustmentItr.next();
          HashMap<String,String> adjustments = adjustmentItems.getParameters();
          /**
          if(adjustments.containsKey(ADJUSTMENT_AMOUNT))
            adjustments.setAmount(items.get(ADJUSTMENT_AMOUNT));
          else
            if(useDefaults) item.getAdjustments().iterator().next().setAmount(DEFAULT_ADJUSTMENT_AMOUNT);
          
          if(items.containsKey(ADJUSTMENT_CSO_CODE))
            item.getAdjustments().iterator().next().setCsoCode(items.get(ADJUSTMENT_CSO_CODE));
          else
            if(useDefaults) item.getAdjustments().iterator().next().setCsoCode(DEFAULT_ADJUSTMENT_CSO_CODE);
          
          if(items.containsKey(ADJUSTMENT_ENDDATE))
            item.getAdjustments().iterator().next().setEndDate(items.get(ADJUSTMENT_ENDDATE));
          else
            if(useDefaults) item.getAdjustments().iterator().next().setEndDate(DEFAULT_ADJUSTMENT_ENDDATE);
          
          allItems.add(item);
          }  
        }
      }*/
      
      order.setLineItems(allItems);
    }
  }