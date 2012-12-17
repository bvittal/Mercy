package com.searshc.twilight;

public interface TwilightPojo
{
  public static final String ITEMS_KEY = "items";
  public static final String ITEM_KEY = "item";
  public static final String ADJUSTMENTS_KEY = "adjustments";
  public static final String DCADJUSTMENTS_KEY = "dcAdjustments";
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
  
  
  public void setName(String str);
  public TwilightPojoType getType();
}
