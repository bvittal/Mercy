package com.searshc.twilight;

public interface TwilightPojo
{
  public static final String ITEMS_KEY = "items";
  public static final String ITEM_KEY = "item";
  public static final String ADJUSTMENTS_KEY = "adjustments";
  public static final String ADJUSTMENT_KEY = "adjustment";
  public static final String DCADJUSTMENTS_KEY = "dcAdjustments";
  public static final String DCADJUSTMENT_KEY = "dcAdjustment";
  public static final String UNUSED_COUPON_CODES = "unusedCouponCodes";
  public static final String UNUSED_COUPON_KEY = "couponCode";
  public static final String COUPON_KEY = "couponCodes";
  public static final String OPTED_PROMO_KEY = "optedPromotions";
  public static final String INCOMPATIBILITIES_KEY = "incompatibilities";
  public static final String INCOMPATIBILITY_KEY = "incompatibility";
  public static final String INCOMPATIBILITIES_UNAPPLIED_OFFERS_KEY = "unappliedOffers";
  public static final String INCOMPATIBILITIES_UNAPPLIED_OFFER_KEY = "unappliedOffer";
  public static final String INCOMPATIBILITIES_REASONS_KEY = "reasons";
  public static final String INCOMPATIBILITIES_REASON_KEY = "reason";
  public static final String INCOMPATIBILITIES_FIELDS_KEY = "fields";
  public static final String PLU_RESPONSES_KEY = "resp";
  public static final String PROMO_DESC_KEY = "promoDesc";
  public static final String DESC_KEY = "desc";
  
  //Plu Response object
  public static final String PLU_RESP_EA_KEY = "indicator_EA";
  public static final String PLU_RESP_98_KEY = "indicator_98";
  public static final String PLU_RESP_E8_KEY = "indicator_E8";
  public static final String PLU_RESP_E9_KEY = "indicator_E9";
  public static final String PLU_RESP_EC_KEY = "indicator_EC";
  public static final String PLU_RESP_95_KEY = "indicator_95";
  public static final String PLU_RESP_9C_KEY = "indicator_9C";
  public static final String PLU_RESP_40BA_KEY = "indicator_40BA";
  public static final String PLU_RESP_60B1_KEY = "indicator_60B1";
  public static final String PLU_RESP_62B1_KEY = "indicator_62B1";
  public static final String PLU_RESP_58B1_KEY = "indicator_58B1";
  
  //Coupon Response object
  public static final String COUPON_RESP_2AB7_KEY = "indicator_2AB7";
  
  public static final String COUPON_RESP_INCL_EXCLS_KEY = "includeExclude";
  public static final String COUPON_RESP_INCL_EXCL_KEY = "inclExcl";
  
  public void setName(String str);
  public TwilightPojoType getType();
}
