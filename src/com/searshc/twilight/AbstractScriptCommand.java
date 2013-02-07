package com.searshc.twilight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
  public static final String ASSOCIATE_ID = "associateId";
  
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
  public static final String BASE_ADJUSTMENTS = "adjustments";
  public static final String BASE_DC_ADJUSTMENTS = "dcAdjustments";
  public static final String ADJUSTMENT_AMOUNT = "amount";
  public static final String ADJUSTMENT_CSO_CODE = "csoCode";
  public static final String ADJUSTMENT_ENDDATE = "endDate";
  public static final String ADJUSTMENT_EXCL_TEXT = "exclusionText";
  public static final String ADJUSTMENT_LONG_DESC = "longDesc";
  public static final String ADJUSTMENT_MARK_DOWN = "markdownCode";
  public static final String ADJUSTMENT_NFX_CODE = "nfxCode";
  public static final String ADJUSTMENT_PROMO_NAME = "promoName";
  public static final String ADJUSTMENT_PROMO_TYPE = "promoType";
  public static final String ADJUSTMENT_PROMO_CODE = "promoCode";
  public static final String ADJUSTMENT_COUPON_OFFER_CODE = "couponOfferCode";
  public static final String ADJUSTMENT_SHORT_DESC = "shortDesc";
  public static final String ADJUSTMENT_SOURCE = "adjustmentSource";
  
  /** Incompatibility */
  public static final String BASE_INCOMPATIBILITIES = "incompatibilities";
  public static final String SUB_BASE_INCOMPATIBILITY = "incompatibility";
  public static final String INCOMPATIBILITY_UNAPPLIED_OFFERS = "unappliedOffer";
  public static final String INCOMPATIBILITY_REASON = "reason";
  public static final String INCOMPATIBILITY_FIELDS = "fields";
  public static final String INCOMPATIBILITY_OPTED_PROMO = "incompOptedPromo";
  public static final String INCOMPATIBILITY_OPTED_PROMO_TYPE = "incompOptedPromoType";
  public static final String INCOMPATIBILITY_UNAPPLIED_OFFERS_OFFER_ID = "incompOfferId";
  public static final String INCOMPATIBILITY_UNAPPLIED_OFFERS_REASON_CODE = "incompReasonCode";
  public static final String INCOMPATIBILITY_REASON_FIELD = "incompField";
  public static final String INCOMPATIBILITY_REASON_PROMO_CODES = "incompPromoCodes";
  public static final String INCOMPATIBILITY_REASON_COUPON_CODES = "incompCouponCodes";
  
  /** Unused Coupon Codes*/
  public static final String BASE_UNUSED_COUPON_CODE = "unusedCouponCodes";
  public static final String SUB_BASE_COUPON_CODE = "couponCode";
  public static final String UNUSED_COUPON_CODE = "code";
  public static final String UNUSED_COUPON_CODE_REASON = "couponReason";
  
  /** the default values for each order attribute */
  private static final String DEFAULT_REGISTER_NUM = "00000";
  private static final String DEFAULT_PHYSICAL_STORE = "11111";
  private static final String DEFAULT_TRANS_NUM = "22222";
  private static final String DEFAULT_ASSOC_FLAG = "0";
  private static final String DEFAULT_KIDVANTAGE_FLAG = "0";
  private static final String DEFAULT_CRAFTSMAN_FLAG = "0";
  private static final String DEFAULT_PULSE_FLAG = "0";
  private static final String DEFAULT_SEARS_CARD = "0";
  private static final String DEFAULT_ASSOCIATE_ID = "1234";
  
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
    List<CoupounCode> couponCodesList = new ArrayList<CoupounCode>();
    List<Incompatibility> incompatibilityList = new ArrayList<Incompatibility>();
    HashMap<String,String> map = obj.getParameters();
    Iterator<TwilightJsonObject> jasonObjItr = obj.getTwilightJsonObject().iterator();
    order = new Order();
    
    while(jasonObjItr.hasNext())
    {
      TwilightJsonObject twilightJsonObj = jasonObjItr.next();
     
    if(twilightJsonObj != null && twilightJsonObj.getName().equalsIgnoreCase(BASE_UNUSED_COUPON_CODE))
    {
      List<TwilightJsonObject> subObject = twilightJsonObj.getTwilightJsonObject();
      for(TwilightJsonObject objt : subObject)
      {
          couponCodesList.add(this.getCouponCodes(objt.getParameters()));
          order.setUnusedCouponCodes(couponCodesList);
        }
      }
    else if(twilightJsonObj != null && twilightJsonObj.getName().equalsIgnoreCase(BASE_INCOMPATIBILITIES))
    {
      Iterator<TwilightJsonObject> incompatibilityObjItr = twilightJsonObj.getTwilightJsonObject().iterator();
      
      while(incompatibilityObjItr.hasNext())
      {
        TwilightJsonObject subTwilightJsonObj = incompatibilityObjItr.next();
        incompatibilityList.add(this.getIncompatibilities(subTwilightJsonObj));
      }
    }
  }
    
    if(map != null)
    {
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
      
      if(map.containsKey(ASSOCIATE_ID))
        order.setAssociateId(map.get(ASSOCIATE_ID));
      else
        if(useDefaults) order.setAssociateId(DEFAULT_ASSOCIATE_ID);
      
      /** the parameter lists */
      List<TwilightJsonArray> arrayList = obj.getTwilightJsonArray();
      
      for(int i=0; i<arrayList.size(); i++)
      {
        String name = arrayList.get(i).getName();
        
        if(name.equals(TwilightJsonArray.COUPON_KEY))
        {
          List<Coupon> couponList = new ArrayList<Coupon>();
          Coupon coupon = new Coupon();
          String [] couponSet = arrayList.get(i).getStringArray();
          if(couponSet != null){
            for(int y=0; y<couponSet.length; y++){
              coupon.setCode(couponSet[y]);
              }
          }
          couponList.add(coupon);
          Coupon[] couponArray = new Coupon[couponList.size()];
          couponList.toArray(couponArray);
          order.setCouponCodes(couponArray);
        }
        else if(name.equals(TwilightJsonArray.OPTED_PROMO_KEY))
        {
          order.setOptedPromotions(arrayList.get(i).getStringArray());
          }
        }
      }
      
      /** the item-level parameters */
      List<LineItem> allItems = new ArrayList<LineItem>();
      List<Adjustment> adjustments = new ArrayList<Adjustment>();
      List<Adjustment> dcAdjustments = new ArrayList<Adjustment>();
      Iterator<TwilightJsonObject> itemIterator = null;
      TwilightJsonObject lineItems;
      
    if (obj.getTwilightJsonObject().size() > 0)
    {
        List<TwilightJsonObject> itemList = obj.getTwilightJsonObject();
        
        for(TwilightJsonObject itemObj : itemList){
          List<TwilightJsonObject> subItemList = itemObj.getTwilightJsonObject();
          
          for(TwilightJsonObject subItemObj : subItemList){
            if(!subItemObj.getName().equalsIgnoreCase(SUB_BASE_COUPON_CODE)){
                itemIterator = subItemList.iterator();
             }
          }
        }

      while (itemIterator.hasNext())
      {
        LineItem item = new LineItem();
        lineItems = itemIterator.next();
        HashMap<String, String> items = lineItems.getParameters();
        Iterator<TwilightJsonObject> itr = lineItems.getTwilightJsonObject().iterator();

        while (itr.hasNext())
        {
          TwilightJsonObject adjustmentObj = itr.next();
          if (adjustmentObj != null)
        {
          HashMap<String, String> adjustmentMap = adjustmentObj.getParameters();

          if (adjustmentMap != null)
          {
            if (adjustmentObj.getName().equalsIgnoreCase(BASE_ADJUSTMENTS))
            {
              adjustments.add(this.getAdjustments(adjustmentMap));
              item.setAdjustments(adjustments);
            }
          else if (adjustmentObj.getName().equalsIgnoreCase(BASE_DC_ADJUSTMENTS))
          {
            dcAdjustments.add(this.getAdjustments(adjustmentMap));
            item.setDcAdjustments(dcAdjustments);
              }
            }
          }
        }
          
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
      
          if(items.containsKey(ITEM_REGULAR_PRICE))
            item.setRegularPrice(items.get(ITEM_REGULAR_PRICE));
          else
            if(useDefaults) item.setRegularPrice(DEFAULT_ITEM_REGULAR_PRICE);
        
          if(items.containsKey(ITEM_MANUAL_PRICE))
            item.setManualPrice(items.get(ITEM_MANUAL_PRICE));
          else
            if(useDefaults) item.setManualPrice(DEFAULT_ITEM_MANUAL_PRICE);
          /**
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
    
      if(incompatibilityList.size() > 0){
        Incompatibility[] incompatibilityArray = new Incompatibility[incompatibilityList.size()];
        incompatibilityList.toArray(incompatibilityArray);
        order.setIncompatibilities(incompatibilityArray);
      }
      order.setLineItems(allItems);
    }
  
  private Adjustment getAdjustments(HashMap<String,String> map){
    Adjustment adj = new Adjustment();
    for (Map.Entry<String, String> entry : map.entrySet()){
      if(entry.getKey().equals(ADJUSTMENT_AMOUNT))
        adj.setAmount(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_CSO_CODE))
        adj.setCsoCode(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_ENDDATE))  
        adj.setEndDate(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_EXCL_TEXT))
        adj.setExclusionText(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_LONG_DESC))
        adj.setLongDesc(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_MARK_DOWN))
        adj.setMarkdownCode(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_NFX_CODE))
        adj.setNfxCode(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_PROMO_NAME))
        adj.setPromoName(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_PROMO_TYPE))
        adj.setPromoType(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_PROMO_CODE))
        adj.setPromoCode(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_COUPON_OFFER_CODE))
        adj.setCouponOfferCode(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_SHORT_DESC))
        adj.setShortDesc(entry.getValue());
      else if(entry.getKey().equals(ADJUSTMENT_SOURCE))
        adj.setAdjustmentSource(entry.getValue());
    }
    return adj;
    }
  
  private CoupounCode getCouponCodes(HashMap<String,String> map){
    CoupounCode couponCode = new CoupounCode();
    for (Map.Entry<String, String> entry : map.entrySet())
    {
      if(entry.getKey().equals(UNUSED_COUPON_CODE)){
        String code [] = {entry.getValue()};
        couponCode.setCodes(code);
      }else if(entry.getKey().equals(UNUSED_COUPON_CODE_REASON)){
        couponCode.setReason(entry.getValue());
      }
    }
    return couponCode;
    }
  
  

  private Incompatibility getIncompatibilities(TwilightJsonObject incompatibilityObj){
    Incompatibility incompatibility = new Incompatibility();
    UnappliedOffer unappliedOffer = new UnappliedOffer();
    
    List<AppMessage> appMessageList = new ArrayList<AppMessage>();
    List<UnappliedOffer> unappliedOfferList = new ArrayList<UnappliedOffer>();
    
    if(incompatibilityObj != null && incompatibilityObj.getName().equalsIgnoreCase(SUB_BASE_INCOMPATIBILITY)){
      HashMap<String,String> optedPromoMap = incompatibilityObj.getParameters();
      
      for (Map.Entry<String, String> entry : optedPromoMap.entrySet()){
        if(entry.getKey().equals(INCOMPATIBILITY_OPTED_PROMO))
          incompatibility.setOptedPromo(entry.getValue());
        else if(entry.getKey().equals(INCOMPATIBILITY_OPTED_PROMO_TYPE))
          incompatibility.setOptedPromoType(entry.getValue());
      }
      
      Iterator<TwilightJsonObject> unappliedOffersItr = incompatibilityObj.getTwilightJsonObject().iterator();
      while(unappliedOffersItr.hasNext()){
        TwilightJsonObject unappliedOfferObj = unappliedOffersItr.next();
        Iterator<TwilightJsonObject> unappliedOfferItr = unappliedOfferObj.getTwilightJsonObject().iterator();

        while(unappliedOfferItr.hasNext()){
          TwilightJsonObject unappliedOffersTwilightJsonObj = unappliedOfferItr.next();
          if(unappliedOffersTwilightJsonObj != null && 
              unappliedOffersTwilightJsonObj.getName().equalsIgnoreCase(INCOMPATIBILITY_UNAPPLIED_OFFERS)){
              HashMap<String,String> unappliedOffersMap = unappliedOffersTwilightJsonObj.getParameters();
        
            for (Map.Entry<String, String> entry : unappliedOffersMap.entrySet()){
              if(entry.getKey().equals(INCOMPATIBILITY_UNAPPLIED_OFFERS_OFFER_ID))
                unappliedOffer.setOfferId(entry.getValue());
          }
       }
          
        Iterator<TwilightJsonObject> subUnappliedOfferItr = unappliedOffersTwilightJsonObj.getTwilightJsonObject().iterator();
        while(subUnappliedOfferItr.hasNext()){
          TwilightJsonObject reasonsTwilightJsonObj = subUnappliedOfferItr.next();
          Iterator<TwilightJsonObject> reasonOfferItr = reasonsTwilightJsonObj.getTwilightJsonObject().iterator();
          
          while(reasonOfferItr.hasNext()){
            TwilightJsonObject reasonTwilightJsonObj = reasonOfferItr.next();
            AppMessage appMessage = new AppMessage();
            
            if(reasonTwilightJsonObj != null && reasonTwilightJsonObj.getName().equalsIgnoreCase(INCOMPATIBILITY_REASON)){
              HashMap<String,String> reasonsOffersMap = reasonTwilightJsonObj.getParameters();
                for (Map.Entry<String, String> entry : reasonsOffersMap.entrySet()){
                  if(entry.getKey().equals(INCOMPATIBILITY_UNAPPLIED_OFFERS_REASON_CODE))
                  appMessage.setCode(entry.getValue());
             }
         }
              
            Iterator<TwilightJsonObject> fieldsItr = reasonTwilightJsonObj.getTwilightJsonObject().iterator();
            while(fieldsItr.hasNext()){
                TwilightJsonObject fieldsTwilightJsonObj = fieldsItr.next();
                List<String> fieldsList = new ArrayList<String>();
                
                if(fieldsTwilightJsonObj != null && fieldsTwilightJsonObj.getName().equalsIgnoreCase(INCOMPATIBILITY_FIELDS)){
                  HashMap<String,String> fieldsMap = fieldsTwilightJsonObj.getParameters();
                  
                  for (Map.Entry<String, String> entry : fieldsMap.entrySet()){
                    if(entry.getKey().equals(INCOMPATIBILITY_REASON_FIELD))
                      fieldsList.add(entry.getValue());
                    else if(entry.getKey().equals(INCOMPATIBILITY_REASON_PROMO_CODES))
                      fieldsList.add(entry.getValue());
                    else if(entry.getKey().equals(INCOMPATIBILITY_REASON_COUPON_CODES))
                      fieldsList.add(entry.getValue());
                      }
                    }
                    String[] fieldsArray = new String[fieldsList.size()];
                    fieldsList.toArray(fieldsArray);
                    appMessage.setFields(fieldsArray);
                  }
                  if(appMessage != null)
                    appMessageList.add(appMessage);
                }
              }
            }
          }
              AppMessage[] reasonsArray = new AppMessage[appMessageList.size()];
              appMessageList.toArray(reasonsArray);
              unappliedOffer.setReasons(reasonsArray);
              unappliedOfferList.add(unappliedOffer);
              
              UnappliedOffer[] unappliedOfferArray = new UnappliedOffer[unappliedOfferList.size()];
              unappliedOfferList.toArray(unappliedOfferArray);
              incompatibility.setUnappliedOffers(unappliedOfferArray);
            }
            return incompatibility;
          }
        }