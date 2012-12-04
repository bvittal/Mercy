package com.searshc.twilight;

public interface TwilightPojo
{
  public static final String ITEMS_KEY = "items";
  public static final String ITEM_KEY = "item";
  public static final String ADJUSTMENTS_KEY = "adjustments";
  public static final String ADJUSTMENT_KEY = "adjustment";
  public static final String DCADJUSTMENTS_KEY = "dcAdjustments";
  public static final String DCADJUSTMENT_KEY = "dcAdjustment";
  public static final String COUPON_KEY = "couponCodes";
  public static final String OPTED_PROMO_KEY = "optedPromotions";
  
  public void setName(String str);
  public TwilightPojoType getType();
}
