package com.searshc.twilight.parsers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.twilight.ScriptCommandParser;
import com.searshc.twilight.TwilightJsonObject;
import com.searshc.twilight.util.PropertyLoader;

public class PluResponseParser
{
  private static Logger logger = Logger.getLogger(PluResponseParser.class);
  private static Properties prop;
  
  private static final String INDICATOR = "indicator";
  private static final String SEGMENT_LEVEL = "segmentLevel";
  private static final String SEGMENT_LENGTH = "segmentLength";
  
  //Plu Inquiry Response - 98 - parameters
  private static final String PLU_RESP_FUTURE_PURCHASE_COUPON_ID = "futurePurchaseCouponID";
  private static final String PLU_RESP_START_DATE = "startDate";
  private static final String PLU_RESP_END_DATE = "endDate";
  private static final String PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT = "minimumQualificationAmount";
  private static final String PLU_RESP_MAXIMUM_QUANTITY = "maximumQuantity";
  private static final String PLU_RESP_SEARS_CHARGE_FLAG = "searsChargeFlag";
  private static final String PLU_RESP_REGULAR_PRICE_FLAG = "regularPriceFlag";
  private static final String PLU_RESP_PROMOTION_PRICE_FLAG = "promotionPriceFlag";
  private static final String PLU_RESP_CLEARANCE_PRICE_FLAG =  "clearancePriceFlag";
  private static final String PLU_RESP_MALL_FLAG = "mallFlag";
  private static final String PLU_RESP_HARDWARE_FLAG = "hardwareFlag";
  private static final String PLU_RESP_THE_GREAT_INDOORS_FLAG = "theGreatIndoorsFlag";
  private static final String PLU_RESP_AUTOMOTIVE_FLAG = "automotiveFlag";
  private static final String PLU_RESP_OUTLET_FLAG = "outletFlag";
  private static final String PLU_RESP_DEALER_FLAG = "dealerFlag";
  private static final String PLU_RESP_WWW_FLAG =  "wwwFlag";
  private static final String PLU_RESP_PRODUCT_SERVICE_FLAG = "productServiceFlag";
  private static final String PLU_RESP_DELIVERY_FLAG = "deliveryFlag";
  private static final String PLU_RESP_ASSOCIATE_DISCOUNT_FLAG = "associateDiscountFlag";
  private static final String PLU_RESP_RESERVED_FOR_FUTURE = "reservedForFutureUse";
  private static final String PLU_RESP_COUPON_NUMBER = "couponNumber";
  private static final String PLU_RESP_COUPON_TYPE_CODE = "couponTypeCode";
  private static final String PLU_RESP_MARKDOWN_TYPE_CODE = "markdownTypeCode";
  private static final String PLU_RESP_MARKDOWN_AMOUNT_PERCENT = "markdownAmountPercent";
  private static final String PLU_RESP_FUTURE_PURCHASE_COUPON_DESC = "futurePurchaseCouponDesc";
  
  //Plu Inquiry Response - 98 - default Values
  private static final String DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_ID = "pluResp_98_futurePurchaseCouponID";
  private static final String DEFAULT_PLU_RESP_START_DATE = "pluResp_98_startDate";
  private static final String DEFAULT_PLU_RESP_END_DATE = "pluResp_98_endDate";
  private static final String DEFAULT_PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT = "pluResp_98_minimumQualificationAmount";
  private static final String DEFAULT_PLU_RESP_MAXIMUM_QUANTITY = "pluResp_98_maximumQuantity";
  private static final String DEFAULT_PLU_RESP_SEARS_CHARGE_FLAG = "pluResp_98_searsChargeFlag";
  private static final String DEFAULT_PLU_RESP_REGULAR_PRICE_FLAG = "pluResp_98_regularPriceFlag";
  private static final String DEFAULT_PLU_RESP_PROMOTION_PRICE_FLAG = "pluResp_98_promotionPriceFlag";
  private static final String DEFAULT_PLU_RESP_CLEARANCE_PRICE_FLAG =  "pluResp_98_clearancePriceFlag";
  private static final String DEFAULT_PLU_RESP_MALL_FLAG = "pluResp_98_mallFlag";
  private static final String DEFAULT_PLU_RESP_HARDWARE_FLAG = "pluResp_98_hardwareFlag";
  private static final String DEFAULT_PLU_RESP_THE_GREAT_INDOORS_FLAG = "pluResp_98_theGreatIndoorsFlag";
  private static final String DEFAULT_PLU_RESP_AUTOMOTIVE_FLAG = "pluResp_98_automotiveFlag";
  private static final String DEFAULT_PLU_RESP_OUTLET_FLAG = "pluResp_98_outletFlag";
  private static final String DEFAULT_PLU_RESP_DEALER_FLAG = "pluResp_98_dealerFlag";
  private static final String DEFAULT_PLU_RESP_WWW_FLAG =  "pluResp_98_wwwFlag";
  private static final String DEFAULT_PLU_RESP_PRODUCT_SERVICE_FLAG = "pluResp_98_productServiceFlag";
  private static final String DEFAULT_PLU_RESP_DELIVERY_FLAG = "pluResp_98_deliveryFlag";
  private static final String DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_FLAG = "pluResp_98_associateDiscountFlag";
  private static final String DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE = "pluResp_98_reservedForFutureUse";
  private static final String DEFAULT_PLU_RESP_COUPON_NUMBER = "pluResp_98_couponNumber";
  private static final String DEFAULT_PLU_RESP_COUPON_TYPE_CODE = "pluResp_98_couponTypeCode";
  private static final String DEFAULT_PLU_RESP_MARKDOWN_TYPE_CODE = "pluResp_98_markdownTypeCode";
  private static final String DEFAULT_PLU_RESP_MARKDOWN_AMOUNT_PERCENT = "pluResp_98_markdownAmountPercent";
  private static final String DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_DESC = "pluResp_98_futurePurchaseCouponDesc";
  
  //Plu Inquiry Response - E8 - parameters
  private static final String PLU_RESP_PLU_DIVISION_NUMBER = "pluDivisionNumber";
  private static final String PLU_RESP_PLU_ITEM_NUMBER = "pluItemNumber";
  private static final String PLU_RESP_PLU_SKU = "pluSku";
  private static final String PLU_RESP_PLU_LINE_NUMBER = "pluLineNumber";
  private static final String PLU_RESP_PLU_SUBLINE_NUMBER = "pluSubLineNumber";
  private static final String PLU_RESP_PLU_SUBLINE_VARIABLE_NUMBER = "pluSubLineVariableNumber";
  private static final String PLU_RESP_PLU_ITEM_DESCRIPTION = "pluItemDescription";
  private static final String PLU_RESP_PLU_REGULAR_PRICE = "pluRegularPrice";
  private static final String PLU_RESP_PLU_PRICE =  "pluPrice";
  private static final String PLU_RESP_PLU_PRICE_TYPE = "pluPriceType";
  private static final String PLU_RESP_PLU_SOURCE_ORDER_PROCESSING_TIME = "sourceOrderProcessingTime";
  private static final String PLU_RESP_PLU_BIAS_COUNT = "biasCount";
  private static final String PLU_RESP_PLU_COMMITED_QUANTITY = "commitedQuantity";
  private static final String PLU_RESP_PLU_PRODUCT_REG_FLAGS = "productRegistrationFlags";
  
  //Plu Inquiry Response - E8 - default Values
  private static final String DEFAULT_PLU_RESP_PLU_DIVISION_NUMBER = "pluResp_E8_pluDivisionNumber";
  private static final String DEFAULT_PLU_RESP_PLU_ITEM_NUMBER = "pluResp_E8_pluItemNumber";
  private static final String DEFAULT_PLU_RESP_PLU_SKU = "pluResp_E8_pluSku";
  private static final String DEFAULT_PLU_RESP_PLU_LINE_NUMBER = "pluResp_E8_pluLineNumber";
  private static final String DEFAULT_PLU_RESP_PLU_SUBLINE_NUMBER = "pluResp_E8_pluSubLineNumber";
  private static final String DEFAULT_PLU_RESP_PLU_SUBLINE_VARIABLE_NUMBER = "pluResp_E8_pluSubLineVariableNumber";
  private static final String DEFAULT_PLU_RESP_PLU_ITEM_DESCRIPTION = "pluResp_E8_pluItemDescription";
  private static final String DEFAULT_PLU_RESP_PLU_REGULAR_PRICE = "pluResp_E8_pluRegularPrice";
  private static final String DEFAULT_PLU_RESP_PLU_PRICE =  "pluResp_E8_pluPrice";
  private static final String DEFAULT_PLU_RESP_PLU_PRICE_TYPE = "pluResp_E8_pluPriceType";
  private static final String DEFAULT_PLU_RESP_PLU_SOURCE_ORDER_PROCESSING_TIME = "pluResp_E8_sourceOrderProcessingTime";
  private static final String DEFAULT_PLU_RESP_PLU_BIAS_COUNT = "pluResp_E8_biasCount";
  private static final String DEFAULT_PLU_RESP_PLU_COMMITED_QUANTITY = "pluResp_E8_commitedQuantity";
  private static final String DEFAULT_PLU_RESP_PLU_PRODUCT_REG_FLAGS = "pluResp_E8_productRegistrationFlags";
  
  //Plu Inquiry Response - E9 - parameters
  private static final String PLU_RESP_PLU_RESPONSE_TYPE_CODE = "pluResponseTypeCode";
  
  //Plu Inquiry Response - E9 - default Values
  private static final String DEFAULT_PLU_RESP_E9_PLU_DIVISION_NUMBER = "pluResp_E9_pluDivisionNumber";
  private static final String DEFAULT_PLU_RESP_E9_PLU_ITEM_NUMBER = "pluResp_E9_pluItemNumber";
  private static final String DEFAULT_PLU_RESP_E9_PLU_SKU = "pluResp_E9_pluSku";
  private static final String DEFAULT_PLU_RESP_E9_PLU_RESPONSE_TYPE_CODE = "pluResp_E9_pluResponseTypeCode";
  
  //Plu Inquiry Response - EC - parameters
  private static final String PLU_RESP_PLU_GROUP_ID = "pluGroupId";
  private static final String PLU_RESP_PLU_ITEM_FLAG = "pluItemFlag";
  private static final String PLU_RESP_PLU_GROUP_TYPE = "pluGroupType";
  private static final String PLU_RESP_PLU_GROUP_QUANTITY = "pluGroupQuantity";
  private static final String PLU_RESP_PLU_GROUP_PRICE = "pluGroupPrice";
  
  //Plu Inquiry Response - EC - default Values
  private static final String DEFAULT_PLU_RESP_PLU_GROUP_ID = "pluResp_EC_pluGroupId";
  private static final String DEFAULT_PLU_RESP_PLU_ITEM_FLAG = "pluResp_EC_pluItemFlag";
  private static final String DEFAULT_PLU_RESP_PLU_GROUP_TYPE = "pluResp_EC_pluGroupType";
  private static final String DEFAULT_PLU_RESP_PLU_GROUP_QUANTITY = "pluResp_EC_pluGroupQuantity";
  private static final String DEFAULT_PLU_RESP_PLU_GROUP_PRICE = "pluResp_EC_pluGroupPrice";
  
  //Plu Inquiry Response - 95 - parameters
  private static final String PLU_RESP_REBATE_ID = "rebateId";
  private static final String PLU_RESP_PRINT_EFFECTIVE_DATE = "printEffectiveDate";
  private static final String PLU_RESP_PRINT_END_DATE = "printEndDate";
  private static final String PLU_RESP_REPRINT_END_DATE = "reprintEndDate";
  private static final String PLU_RESP_MIN_QUALIFICATION_AMT = "minimumQualificationAmount";
  private static final String PLU_RESP_MAX_QUANTITY = "maximumQuantity";
  private static final String PLU_RESP_UPC_REQUIRED_FLAG = "upcRequiredFlag";
  private static final String PLU_RESP_ASSOCIATED_PROGRAM_NUMBER = "associatedProgramNumber";
  private static final String PLU_RESP_VENDOR_NAME = "vendorName";
  private static final String PLU_RESP_WEEKS_BEFORE_RECV_REBATE = "weeksBeforeRecvRebate";
  private static final String PLU_RESP_POSTMARK_DATE_TXT = "postMarkDateText";
  private static final String PLU_RESP_REDEMPTION_CENTER_NAME = "redemptionCenterName";
  private static final String PLU_RESP_REDEMPTION_CENTER_ADDRESS1 = "redemptionCenterAddress1";
  private static final String PLU_RESP_REDEMPTION_CENTER_ADDRESS2 = "redemptionCenterAddress2";
  private static final String PLU_RESP_REDEMPTION_CENTER_POBOX = "redemptionCenterPOBox";
  private static final String PLU_RESP_REDEMPTION_CENTER_CITY = "redemptionCenterCity";
  private static final String PLU_RESP_REDEMPTION_CENTER_STATE = "redemptionCenterState";
  private static final String PLU_RESP_REDEMPTION_CENTER_ZIP = "redemptionCenterZip";
  private static final String PLU_RESP_REDEMPTION_CENTER_PHONE = "redemptionCenterPhone";
  private static final String PLU_RESP_REDEMPTION_CENTER_HOURS = "redemptionCenterHours";
  private static final String PLU_RESP_REDEMPTION_CENTER_DESCRIPTION = "redemptionCenterDescription";
  private static final String PLU_RESP_REBATE_AMOUNT = "rebateAmount";
  private static final String PLU_RESP_INSTALLATION_FLAG = "installationFlag";
  private static final String PLU_RESP_FREE_DELIVERY_ZERO_PERCENT_FLAG = "freeDeliveryZeroPercentFlag";
  private static final String PLU_RESP_REBATE_METHOD_CODE = "rebateMethodCode";
  private static final String PLU_RESP_FILLER = "filler";
  
  //Plu Inquiry Response - 95 - default Values
  private static final String DEFAULT_SEGMENT_LEVEL_95 = "pluResp_95_segmentLevel";
  private static final String DEFAULT_PLU_RESP_REBATE_ID = "pluResp_95_rebateId";
  private static final String DEFAULT_PLU_RESP_PRINT_EFFECTIVE_DATE = "pluResp_95_printEffectiveDate";
  private static final String DEFAULT_PLU_RESP_PRINT_END_DATE = "pluResp_95_printEndDate";
  private static final String DEFAULT_PLU_RESP_REPRINT_END_DATE = "pluResp_95_reprintEndDate";
  private static final String DEFAULT_PLU_RESP_MIN_QUALIFICATION_AMT = "pluResp_95_minimumQualificationAmount";
  private static final String DEFAULT_PLU_RESP_MAX_QUANTITY = "pluResp_95_maximumQuantity";
  private static final String DEFAULT_PLU_RESP_UPC_REQUIRED_FLAG = "pluResp_95_upcRequiredFlag";
  private static final String DEFAULT_PLU_RESP_SEARS_CHARGE_FLAG_95 = "pluResp_95_searsChargeFlag";
  private static final String DEFAULT_PLU_RESP_REGULAR_PRICE_FLAG_95 = "pluResp_95_regularPriceFlag";
  private static final String DEFAULT_PLU_RESP_PROMOTION_PRICE_FLAG_95 = "pluResp_95_promotionPriceFlag";
  private static final String DEFAULT_PLU_RESP_CLEARANCE_PRICE_FLAG_95 =  "pluResp_95_clearancePriceFlag";
  private static final String DEFAULT_PLU_RESP_MALL_FLAG_95 = "pluResp_95_mallFlag";
  private static final String DEFAULT_PLU_RESP_HARDWARE_FLAG_95 = "pluResp_95_hardwareFlag";
  private static final String DEFAULT_PLU_RESP_THE_GREAT_INDOORS_FLAG_95 = "pluResp_95_theGreatIndoorsFlag";
  private static final String DEFAULT_PLU_RESP_AUTOMOTIVE_FLAG_95 = "pluResp_95_automotiveFlag";
  private static final String DEFAULT_PLU_RESP_OUTLET_FLAG_95 = "pluResp_95_outletFlag";
  private static final String DEFAULT_PLU_RESP_DEALER_FLAG_95 = "pluResp_95_dealerFlag";
  private static final String DEFAULT_PLU_RESP_WWW_FLAG_95 =  "pluResp_95_wwwFlag";
  private static final String DEFAULT_PLU_RESP_PRODUCT_SERVICE_FLAG_95 = "pluResp_95_productServiceFlag";
  private static final String DEFAULT_PLU_RESP_DELIVERY_FLAG_95 = "pluResp_95_deliveryFlag";
  private static final String DEFAULT_PLU_RESP_COUPON_NUMBER_95 = "pluResp_95_couponNumber";
  private static final String DEFAULT_PLU_RESP_ASSOCIATED_PROGRAM_NUMBER = "pluResp_95_associatedProgramNumber";
  private static final String DEFAULT_PLU_RESP_VENDOR_NAME = "pluResp_95_vendorName";
  private static final String DEFAULT_PLU_RESP_WEEKS_BEFORE_RECV_REBATE = "pluResp_95_weeksBeforeRecvRebate";
  private static final String DEFAULT_PLU_RESP_POSTMARK_DATE_TXT = "pluResp_95_postMarkDateText";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_NAME = "pluResp_95_redemptionCenterName";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_ADDRESS1 = "pluResp_95_redemptionCenterAddress1";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_ADDRESS2 = "pluResp_95_redemptionCenterAddress2";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_POBOX = "pluResp_95_redemptionCenterPOBox";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_CITY = "pluResp_95_redemptionCenterCity";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_STATE = "pluResp_95_redemptionCenterState";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_ZIP = "pluResp_95_redemptionCenterZip";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_PHONE = "pluResp_95_redemptionCenterPhone";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_HOURS = "pluResp_95_redemptionCenterHours";
  private static final String DEFAULT_PLU_RESP_REDEMPTION_CENTER_DESCRIPTION = "pluResp_95_redemptionCenterDescription";
  private static final String DEFAULT_PLU_RESP_REBATE_AMOUNT = "pluResp_95_rebateAmount";
  private static final String DEFAULT_PLU_RESP_INSTALLATION_FLAG = "pluResp_95_installationFlag";
  private static final String DEFAULT_PLU_RESP_FREE_DELIVERY_ZERO_PERCENT_FLAG = "pluResp_95_freeDeliveryZeroPercentFlag";
  private static final String DEFAULT_PLU_RESP_REBATE_METHOD_CODE = "pluResp_95_rebateMethodCode";
  private static final String DEFAULT_PLU_RESP_FILLER = "pluResp_95_filler";
  
  //Plu Inquiry Response - 9C - parameters
  private static final String PLU_RESP_MESSAGE_KEY = "messageKey";
  private static final String PLU_RESP_MESSAGE_TYPE_CODE = "messageTypeCode";
  private static final String PLU_RESP_MESSAGE_TEXT = "messageText";
  
  //Plu Inquiry Response - 9C - default Values
  private static final String DEFAULT_SEGMENT_LENGTH_9C = "pluResp_9C_segmentLength";
  private static final String DEFAULT_PLU_RESP_MESSAGE_KEY = "pluResp_9C_messageKey";
  private static final String DEFAULT_PLU_RESP_MESSAGE_TYPE_CODE = "pluResp_9C_messageTypeCode";
  private static final String DEFAULT_PLU_RESP_MESSAGE_TEXT = "pluResp_9C_messageText";
  
  //Plu Inquiry Response - 40BA - parameters
  private static final String PLU_RESP_TAX_STATUS_CODE = "taxStatusCode";
  private static final String PLU_RESP_FILL_FLOOR_ELIGIBLE = "fillFloorEligible";
  private static final String PLU_RESP_SKU991_ELIGIBLE = "sku991Eligible";
  private static final String PLU_RESP_UNUSED_ONE = "unusedOne";
  private static final String PLU_RESP_EXCEPTIONAL_VALUE_ITEM = "exceptionalValueItem";
  private static final String PLU_RESP_PA_REPLACEMENT_ELIGIBLE = "paReplacementEligible";
  private static final String PLU_RESP_SIGNAL_ITEM = "signalItem";
  private static final String PLU_RESP_RESTOCKING_FEE_ELIGIBLE = "restockingFeeEligible";
  private static final String PLU_RESP_CANCELLATION_FEE_ELIGIBLE =  "cancellationFeeEligible";
  private static final String PLU_RESP_PREPAID_CARD_ITEM = "prepaidCardItem";
  private static final String PLU_RESP_CONVERTER_BOX_ITEM = "converterBoxItem";
  private static final String PLU_RESP_IIAS_ELIGIBLE_ITEM = "iiasEligibleItem";
  private static final String PLU_RESP_ASSOCIATE_DISCOUNT_INELIGIBLE = "associateDiscountIneligible";
  private static final String PLU_RESP_SUBSCRIPTION_PLAN_ELIGIBLE = "subscriptionPlanEligible";
  private static final String PLU_RESP_THIRD_PARTY_ITEM = "thirdPartyItem";
  private static final String PLU_RESP_ALPHALINE_ENTERTAINMENT_ITEM =  "alphalineEntertainmentItem";
  private static final String PLU_RESP_SYWR_REDEMPTION_EXCLUSION = "sywrRedemptionExclusion";
  private static final String PLU_RESP_SYWR_REDEMPTION_AFTER_TAX_FLAG = "sywrRedemptionAfterTaxFlag";
  private static final String PLU_RESP_UNUSED_TWO = "unusedTwo";
  private static final String PLU_RESP_RESTOCKING_FEE_PERCENT = "restockingFeePercent";
  private static final String PLU_RESP_CANCELLATION_FEE_PERCENT = "cancellationFeePercent";
  private static final String PLU_RESP_UNUSED_THREE = "unusedThree";
  
  //Plu Inquiry Response - 40BA - default Values
  private static final String DEFAULT_PLU_RESP_SEGMENT_LENGTH_40BA = "pluResp_40BA_segmentLength";
  private static final String DEFAULT_PLU_RESP_SEGMENT_LEVEL_40BA = "pluResp_40BA_segnmentLevel";
  private static final String DEFAULT_PLU_RESP_TAX_STATUS_CODE = "pluResp_40BA_taxStatusCode";
  private static final String DEFAULT_PLU_RESP_FILL_FLOOR_ELIGIBLE = "pluResp_40BA_fillFloorEligible";
  private static final String DEFAULT_PLU_RESP_SKU991_ELIGIBLE = "pluResp_40BA_sku991Eligible";
  private static final String DEFAULT_PLU_RESP_UNUSED_ONE = "pluResp_40BA_unusedOne";
  private static final String DEFAULT_PLU_RESP_EXCEPTIONAL_VALUE_ITEM = "pluResp_40BA_exceptionalValueItem";
  private static final String DEFAULT_PLU_RESP_PA_REPLACEMENT_ELIGIBLE = "pluResp_40BA_paReplacementEligible";
  private static final String DEFAULT_PLU_RESP_SIGNAL_ITEM = "pluResp_40BA_signalItem";
  private static final String DEFAULT_PLU_RESP_RESTOCKING_FEE_ELIGIBLE = "pluResp_40BA_restockingFeeEligible";
  private static final String DEFAULT_PLU_RESP_CANCELLATION_FEE_ELIGIBLE =  "pluResp_40BA_cancellationFeeEligible";
  private static final String DEFAULT_PLU_RESP_PREPAID_CARD_ITEM = "pluResp_40BA_prepaidCardItem";
  private static final String DEFAULT_PLU_RESP_CONVERTER_BOX_ITEM = "pluResp_40BA_converterBoxItem";
  private static final String DEFAULT_PLU_RESP_IIAS_ELIGIBLE_ITEM = "pluResp_40BA_iiasEligibleItem";
  private static final String DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_INELIGIBLE = "pluResp_40BA_associateDiscountIneligible";
  private static final String DEFAULT_PLU_RESP_SUBSCRIPTION_PLAN_ELIGIBLE = "pluResp_40BA_subscriptionPlanEligible";
  private static final String DEFAULT_PLU_RESP_THIRD_PARTY_ITEM = "pluResp_40BA_thirdPartyItem";
  private static final String DEFAULT_PLU_RESP_ALPHALINE_ENTERTAINMENT_ITEM =  "pluResp_40BA_alphalineEntertainmentItem";
  private static final String DEFAULT_PLU_RESP_SYWR_REDEMPTION_EXCLUSION = "pluResp_40BA_sywrRedemptionExclusion";
  private static final String DEFAULT_PLU_RESP_SYWR_REDEMPTION_AFTER_TAX_FLAG = "pluResp_40BA_sywrRedemptionAfterTaxFlag";
  private static final String DEFAULT_PLU_RESP_UNUSED_TWO = "pluResp_40BA_unusedTwo";
  private static final String DEFAULT_PLU_RESP_RESTOCKING_FEE_PERCENT = "pluResp_40BA_restockingFeePercent";
  private static final String DEFAULT_PLU_RESP_CANCELLATION_FEE_PERCENT = "pluResp_40BA_cancellationFeePercent";
  private static final String DEFAULT_PLU_RESP_UNUSED_THREE = "pluResp_40BA_unusedThree";
  
  //Plu Inquiry Response - 58B1 - parameters
  private static final String PLU_RESP_INSTALLER_LEAD_TIME_VALUE = "installerLeadTimeValue";
  private static final String PLU_RESP_INSTALLATION_CUTOFF_TIME = "installationCutOffTime";
  private static final String PLU_RESP_LONG_ITEM_DESCRIPTION = "longItemDescription";
  
  //Plu Inquiry Response - 58B1 - default Values
  private static final String DEFAULT_PLU_RESP_INSTALLER_LEAD_TIME_VALUE = "pluResp_58B1_installerLeadTimeValue";
  private static final String DEFAULT_PLU_RESP_INSTALLATION_CUTOFF_TIME = "pluResp_58B1_installationCutOffTime";
  private static final String DEFAULT_PLU_RESP_LONG_ITEM_DESCRIPTION = "pluResp_58B1_longItemDescription";
  
  //Plu Inquiry Response - 60B1 - parameters
  private static final String PLU_RESP_OFFER_TYPE = "offerType";
  private static final String PLU_RESP_OFFER_ID = "offerId";
  private static final String PLU_RESP_FINANCIAL_CODE = "financialCode";
  private static final String PLU_RESP_THRESHOLD_DOLLAR_AMOUNT = "thresholdDollarAmount";
  private static final String PLU_RESP_MISCELLANEOUS_REDUCTION_FLAG = "miscellaneousReductionsFlag";
  private static final String PLU_RESP_INSTANT_REBATE_FLAG = "instantRebateFlag";
  private static final String PLU_RESP_MAILIN_REBATE_FLAG = "mailInRebateFlag";
  private static final String PLU_RESP_DELAYED_BILLING_END_DATE_INTERVAL =  "delayedBillingEndDateInterval";
  private static final String PLU_RESP_DELAYED_BILLING_END_DATE_DATE = "delayedBillingEndDateDate";
  private static final String PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE1 = "registerPromoDescriptionLine1";
  private static final String PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE2 = "registerPromoDescriptionLine2";
  private static final String PLU_RESP_NBR_OF_RECEIPT_PROMO_DESC_LINES = "nbrOfReceiptPromoDescriptionLines";
  private static final String PLU_RESP_RECEIPT_PROMO_DESC_LINE = "receiptPromoDescriptionLine";
  
  //Plu Inquiry Response - 60B1 - default Values
  private static final String DEFAULT_PLU_RESP_SEGMENT_LEVEL_60B1 = "pluResp_60B1_segmentLevel";
  private static final String DEFAULT_PLU_RESP_OFFER_TYPE = "pluResp_60B1_offerType";
  private static final String DEFAULT_PLU_RESP_OFFER_ID = "pluResp_60B1_offerId";
  private static final String DEFAULT_PLU_RESP_FINANCIAL_CODE = "pluResp_60B1_financialCode";
  private static final String DEFAULT_PLU_RESP_THRESHOLD_DOLLAR_AMOUNT = "pluResp_60B1_thresholdDollarAmount";
  private static final String DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_FLAG_60B1 = "pluResp_60B1_associateDiscountFlag";
  private static final String DEFAULT_PLU_RESP_MISCELLANEOUS_REDUCTION_FLAG = "pluResp_60B1_miscellaneousReductionsFlag";
  private static final String DEFAULT_PLU_RESP_INSTANT_REBATE_FLAG = "pluResp_60B1_instantRebateFlag";
  private static final String DEFAULT_PLU_RESP_MAILIN_REBATE_FLAG = "pluResp_60B1_mailInRebateFlag";
  private static final String DEFAULT_PLU_RESP_DELAYED_BILLING_END_DATE_INTERVAL =  "pluResp_60B1_delayedBillingEndDateInterval";
  private static final String DEFAULT_PLU_RESP_DELAYED_BILLING_END_DATE_DATE = "pluResp_60B1_delayedBillingEndDateDate";
  private static final String DEFAULT_PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE1 = "pluResp_60B1_registerPromoDescriptionLine1";
  private static final String DEFAULT_PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE2 = "pluResp_60B1_registerPromoDescriptionLine2";
  private static final String DEFAULT_PLU_RESP_NBR_OF_RECEIPT_PROMO_DESC_LINES = "pluResp_60B1_nbrOfReceiptPromoDescriptionLines";
  private static final String DEFAULT_PLU_RESP_RECEIPT_PROMO_DESC_LINE = "pluResp_60B1_receiptPromoDescriptionLine";
  
  //Plu Inquiry Response - 62B1 - parameters
  private static final String PLU_RESP_RESTRICTION_TYPE = "restrictionType";
  private static final String PLU_RESP_MINIMUM_AGE_VALUE = "minimumAgeValue";
  private static final String PLU_RESP_MAXIMUM_QUANTITY_VALUE = "maximumQuantityValue";
  private static final String PLU_RESP_ITEM_MATCH_INDICATOR = "itemMatchIndicator";
  
  //Plu Inquiry Response - 62B1 - default Values
  private static final String DEFAULT_PLU_RESP_SEGMENT_LEVEL_62B1 = "pluResp_62B1_segmentLevel";
  private static final String DEFAULT_PLU_RESP_RESTRICTION_TYPE = "pluResp_62B1_restrictionType";
  private static final String DEFAULT_PLU_RESP_MINIMUM_AGE_VALUE = "pluResp_62B1_minimumAgeValue";
  private static final String DEFAULT_PLU_RESP_MAXIMUM_QUANTITY_VALUE = "pluResp_62B1_maximumQuantityValue";
  private static final String DEFAULT_PLU_RESP_ITEM_MATCH_INDICATOR = "pluResp_62B1_itemMatchIndicator";
  
  
  public PluResponseParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  
  public StringBuilder getPluResponse(TwilightJsonObject twilightJsonObject){
    StringBuilder builder = new StringBuilder();
    
    if(twilightJsonObject != null)
    {
      Iterator<TwilightJsonObject> itr = twilightJsonObject.getTwilightJsonObject().iterator();
      
      while(itr.hasNext())
      { 
        Iterator<TwilightJsonObject> subItr = itr.next().getTwilightJsonObject().iterator();
        
        while(subItr.hasNext())
        {
          TwilightJsonObject obj = subItr.next();
          
          if(obj != null && obj.getName().equalsIgnoreCase("indicator_98"))
          {
            builder.append(this.processPluResponse98(obj.getParameters()));
          }
        }
      }
    }
    return builder;
  }
  
  private StringBuilder processPluResponseE8(TwilightJsonObject twilightJsonObject)
  {
      String indicator = "E8";
      String pluDivisionNumber = StringUtils.EMPTY;
      String pluItemNumber = StringUtils.EMPTY;
      String pluSku = StringUtils.EMPTY;
      String pluLineNumber = StringUtils.EMPTY;
      String pluSubLineNumber = StringUtils.EMPTY;
      String pluSubLineVariableNumber = StringUtils.EMPTY;
      String pluItemDescription = StringUtils.EMPTY;
      String pluRegularPrice = StringUtils.EMPTY;
      String pluPrice = StringUtils.EMPTY;
      String pluPriceType = StringUtils.EMPTY;
      String sourceOrderProcessingTime = StringUtils.EMPTY;
      String biasCount = StringUtils.EMPTY;
      String commitedQuantity = StringUtils.EMPTY;
      String productRegistrationFlags = StringUtils.EMPTY;
      
      final Map<String, String> pluInquiryE8Map = twilightJsonObject.getParameters();
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(pluInquiryE8Map.size() > 0)
    {
      for (Map.Entry<String, String> entry : pluInquiryE8Map.entrySet())
      { 
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_DIVISION_NUMBER))
        {
            modifiableMap.put(PLU_RESP_PLU_DIVISION_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_PLU_DIVISION_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_ITEM_NUMBER))
        {
            modifiableMap.put(PLU_RESP_PLU_ITEM_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_PLU_ITEM_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_SKU))
        {
            modifiableMap.put(PLU_RESP_PLU_SKU, prop.getProperty(DEFAULT_PLU_RESP_PLU_SKU));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_LINE_NUMBER))
        {
            modifiableMap.put(PLU_RESP_PLU_LINE_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_PLU_LINE_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_SUBLINE_NUMBER))
        {
            modifiableMap.put(PLU_RESP_PLU_SUBLINE_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_PLU_SUBLINE_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_SUBLINE_VARIABLE_NUMBER))
        {
            modifiableMap.put(PLU_RESP_PLU_SUBLINE_VARIABLE_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_PLU_SUBLINE_VARIABLE_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_ITEM_DESCRIPTION))
        {
            modifiableMap.put(PLU_RESP_PLU_ITEM_DESCRIPTION, prop.getProperty(DEFAULT_PLU_RESP_PLU_ITEM_DESCRIPTION));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_REGULAR_PRICE))
        {
            modifiableMap.put(PLU_RESP_PLU_REGULAR_PRICE, prop.getProperty(DEFAULT_PLU_RESP_PLU_REGULAR_PRICE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_PRICE))
        {
            modifiableMap.put(PLU_RESP_PLU_PRICE, prop.getProperty(DEFAULT_PLU_RESP_PLU_PRICE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_PRICE_TYPE))
        {
            modifiableMap.put(PLU_RESP_PLU_PRICE_TYPE, prop.getProperty(DEFAULT_PLU_RESP_PLU_PRICE_TYPE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_SOURCE_ORDER_PROCESSING_TIME))
        {
            modifiableMap.put(PLU_RESP_PLU_SOURCE_ORDER_PROCESSING_TIME, prop.getProperty(DEFAULT_PLU_RESP_PLU_SOURCE_ORDER_PROCESSING_TIME));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_BIAS_COUNT))
        {
            modifiableMap.put(PLU_RESP_PLU_BIAS_COUNT, prop.getProperty(DEFAULT_PLU_RESP_PLU_BIAS_COUNT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_COMMITED_QUANTITY))
        {
            modifiableMap.put(PLU_RESP_PLU_COMMITED_QUANTITY, prop.getProperty(DEFAULT_PLU_RESP_PLU_COMMITED_QUANTITY));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_PRODUCT_REG_FLAGS))
        {
            modifiableMap.put(PLU_RESP_PLU_PRODUCT_REG_FLAGS, prop.getProperty(DEFAULT_PLU_RESP_PLU_PRODUCT_REG_FLAGS));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_DIVISION_NUMBER))
           pluDivisionNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_ITEM_NUMBER))
           pluItemNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_SKU))
           pluSku = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_LINE_NUMBER))
           pluLineNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_SUBLINE_NUMBER))
           pluSubLineNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_SUBLINE_VARIABLE_NUMBER))
           pluSubLineVariableNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_ITEM_DESCRIPTION))
           pluItemDescription = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_REGULAR_PRICE))
           pluRegularPrice = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_PRICE))
           pluPrice = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_PRICE_TYPE))
           pluPriceType = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_SOURCE_ORDER_PROCESSING_TIME))
           sourceOrderProcessingTime = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_BIAS_COUNT))
           biasCount = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_COMMITED_QUANTITY))
           commitedQuantity = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_PRODUCT_REG_FLAGS))
           productRegistrationFlags = entry.getValue();
        }
      }
      
      sb.append(indicator)
      .append(" ")
      .append(this.byteResponse(pluDivisionNumber.getBytes()))
      .append(this.byteResponse(pluItemNumber.getBytes()))
      .append(this.byteResponse(pluSku.getBytes()))
      .append(this.byteResponse(pluLineNumber.getBytes()))
      .append(this.byteResponse(pluSubLineNumber.getBytes()))
      .append(this.byteResponse(pluSubLineVariableNumber.getBytes()))
      .append(this.byteResponse(pluItemDescription.getBytes()))
      .append(this.byteResponse(pluRegularPrice.getBytes()))
      .append(this.byteResponse(pluPrice.getBytes()))
      .append(this.byteResponse(pluPriceType.getBytes()))
      .append(this.byteResponse(sourceOrderProcessingTime.getBytes()))
      .append(this.byteResponse(biasCount.getBytes()))
      .append(this.byteResponse(commitedQuantity.getBytes()))
      .append(this.byteResponse(productRegistrationFlags.getBytes()));
      
      return sb;
   }
  
  private StringBuilder processPluResponseE9(TwilightJsonObject twilightJsonObject)
  {
      String indicator = "E9";
      String pluDivisionNumber = StringUtils.EMPTY;
      String pluItemNumber = StringUtils.EMPTY;
      String pluSku = StringUtils.EMPTY;
      String pluResponseTypeCode = StringUtils.EMPTY;
      
      
      final Map<String, String> pluInquiryE8Map = twilightJsonObject.getParameters();
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(pluInquiryE8Map.size() > 0)
    {
      for (Map.Entry<String, String> entry : pluInquiryE8Map.entrySet())
      { 
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_DIVISION_NUMBER))
        {
            modifiableMap.put(PLU_RESP_PLU_DIVISION_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_E9_PLU_DIVISION_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_ITEM_NUMBER))
        {
            modifiableMap.put(PLU_RESP_PLU_ITEM_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_E9_PLU_ITEM_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_SKU))
        {
            modifiableMap.put(PLU_RESP_PLU_SKU, prop.getProperty(DEFAULT_PLU_RESP_E9_PLU_SKU));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiryE8Map.containsKey(PLU_RESP_PLU_RESPONSE_TYPE_CODE))
        {
            modifiableMap.put(PLU_RESP_PLU_RESPONSE_TYPE_CODE, prop.getProperty(DEFAULT_PLU_RESP_E9_PLU_RESPONSE_TYPE_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_DIVISION_NUMBER))
           pluDivisionNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_ITEM_NUMBER))
           pluItemNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_SKU))
           pluSku = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_RESPONSE_TYPE_CODE))
           pluResponseTypeCode = entry.getValue();
        }
      }
      
      sb.append(indicator)
      .append(" ")
      .append(this.byteResponse(pluDivisionNumber.getBytes()))
      .append(this.byteResponse(pluItemNumber.getBytes()))
      .append(this.byteResponse(pluSku.getBytes()))
      .append(this.byteResponse(pluResponseTypeCode.getBytes()));
      
      return sb;
   }
  
  
  private StringBuilder processPluResponse98(Map<String,String> pluResponse98Map)
  {
      String indicator = "98";
      String unknown = "02";
      String futurePurchaseCouponID = StringUtils.EMPTY;
      String startDate = StringUtils.EMPTY;
      String endDate = StringUtils.EMPTY;
      String minimumQualificationAmount = StringUtils.EMPTY;
      String maximumQuantity = StringUtils.EMPTY;
      String searsChargeFlag = StringUtils.EMPTY;
      String regularPriceFlag = StringUtils.EMPTY;
      String promotionPriceFlag = StringUtils.EMPTY;
      String clearancePriceFlag =  StringUtils.EMPTY;
      String mallFlag = StringUtils.EMPTY;
      String hardwareFlag = StringUtils.EMPTY;
      String theGreatIndoorsFlag = StringUtils.EMPTY;
      String automotiveFlag = StringUtils.EMPTY;
      String outletFlag = StringUtils.EMPTY;
      String dealerFlag = StringUtils.EMPTY;
      String wwwFlag =  StringUtils.EMPTY;
      String productServiceFlag = StringUtils.EMPTY;
      String deliveryFlag = StringUtils.EMPTY;
      String associateDiscountFlag = StringUtils.EMPTY;
      String reservedForFutureUse = StringUtils.EMPTY;
      String couponNumber = StringUtils.EMPTY;
      String couponTypeCode = StringUtils.EMPTY;
      String markdownTypeCode = StringUtils.EMPTY;
      String markdownAmountPercent = StringUtils.EMPTY;
      String futurePurchaseCouponDesc = StringUtils.EMPTY;
      
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(pluResponse98Map.size() > 0)
     {
      for (Map.Entry<String, String> entry : pluResponse98Map.entrySet())
      { 
        if(!pluResponse98Map.containsKey(PLU_RESP_FUTURE_PURCHASE_COUPON_ID))
        {
            modifiableMap.put(PLU_RESP_FUTURE_PURCHASE_COUPON_ID, prop.getProperty(DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_ID));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_START_DATE))
        {
            modifiableMap.put(PLU_RESP_START_DATE, prop.getProperty(DEFAULT_PLU_RESP_START_DATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_END_DATE))
        {
            modifiableMap.put(PLU_RESP_END_DATE, prop.getProperty(DEFAULT_PLU_RESP_END_DATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT))
        {
            modifiableMap.put(PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT, prop.getProperty(DEFAULT_PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        if(!pluResponse98Map.containsKey(PLU_RESP_MAXIMUM_QUANTITY))
        {
            modifiableMap.put(PLU_RESP_MAXIMUM_QUANTITY, prop.getProperty(DEFAULT_PLU_RESP_MAXIMUM_QUANTITY));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_SEARS_CHARGE_FLAG))
        {
            modifiableMap.put(PLU_RESP_SEARS_CHARGE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_SEARS_CHARGE_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_REGULAR_PRICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_REGULAR_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_REGULAR_PRICE_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_PROMOTION_PRICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_PROMOTION_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_PROMOTION_PRICE_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        if(!pluResponse98Map.containsKey(PLU_RESP_CLEARANCE_PRICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_CLEARANCE_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_CLEARANCE_PRICE_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_MALL_FLAG))
        {
            modifiableMap.put(PLU_RESP_MALL_FLAG, prop.getProperty(DEFAULT_PLU_RESP_MALL_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_HARDWARE_FLAG))
        {
            modifiableMap.put(PLU_RESP_HARDWARE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_HARDWARE_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_THE_GREAT_INDOORS_FLAG))
        {
            modifiableMap.put(PLU_RESP_THE_GREAT_INDOORS_FLAG, prop.getProperty(DEFAULT_PLU_RESP_THE_GREAT_INDOORS_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_AUTOMOTIVE_FLAG))
        {
            modifiableMap.put(PLU_RESP_AUTOMOTIVE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_AUTOMOTIVE_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_OUTLET_FLAG))
        {
            modifiableMap.put(PLU_RESP_OUTLET_FLAG, prop.getProperty(DEFAULT_PLU_RESP_OUTLET_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_DEALER_FLAG))
        {
            modifiableMap.put(PLU_RESP_DEALER_FLAG, prop.getProperty(DEFAULT_PLU_RESP_DEALER_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_WWW_FLAG))
        {
            modifiableMap.put(PLU_RESP_WWW_FLAG, prop.getProperty(DEFAULT_PLU_RESP_WWW_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_PRODUCT_SERVICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_PRODUCT_SERVICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_PRODUCT_SERVICE_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_DELIVERY_FLAG))
        {
            modifiableMap.put(PLU_RESP_DELIVERY_FLAG, prop.getProperty(DEFAULT_PLU_RESP_DELIVERY_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG))
        {
            modifiableMap.put(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG, prop.getProperty(DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_RESERVED_FOR_FUTURE))
        {
            modifiableMap.put(PLU_RESP_RESERVED_FOR_FUTURE, prop.getProperty(DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_COUPON_NUMBER))
        {
            modifiableMap.put(PLU_RESP_COUPON_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_COUPON_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_COUPON_TYPE_CODE))
        {
            modifiableMap.put(PLU_RESP_COUPON_TYPE_CODE, prop.getProperty(DEFAULT_PLU_RESP_COUPON_TYPE_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_MARKDOWN_TYPE_CODE))
        {
            modifiableMap.put(PLU_RESP_MARKDOWN_TYPE_CODE, prop.getProperty(DEFAULT_PLU_RESP_MARKDOWN_TYPE_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_MARKDOWN_AMOUNT_PERCENT))
        {
            modifiableMap.put(PLU_RESP_MARKDOWN_AMOUNT_PERCENT, prop.getProperty(DEFAULT_PLU_RESP_MARKDOWN_AMOUNT_PERCENT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse98Map.containsKey(PLU_RESP_FUTURE_PURCHASE_COUPON_DESC))
        {
            modifiableMap.put(PLU_RESP_FUTURE_PURCHASE_COUPON_DESC, prop.getProperty(DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_DESC));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(PLU_RESP_FUTURE_PURCHASE_COUPON_ID)){
           futurePurchaseCouponID = entry.getValue();
           if(StringUtils.isBlank(futurePurchaseCouponID) || futurePurchaseCouponID.equalsIgnoreCase("null")){
             futurePurchaseCouponID = StringUtils.rightPad(StringUtils.EMPTY, 10);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_START_DATE)){
           startDate = entry.getValue();
           if(StringUtils.isBlank(startDate) || startDate.equalsIgnoreCase("null")){
             startDate = StringUtils.rightPad(StringUtils.EMPTY, 10);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_END_DATE)){
           endDate = entry.getValue();
           if(StringUtils.isBlank(endDate) || endDate.equalsIgnoreCase("null")){
             endDate = StringUtils.rightPad(StringUtils.EMPTY, 10);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT)){
           minimumQualificationAmount = entry.getValue();
           if(StringUtils.isBlank(minimumQualificationAmount) || minimumQualificationAmount.equalsIgnoreCase("null")){
             minimumQualificationAmount = StringUtils.rightPad(StringUtils.EMPTY, 7);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MAXIMUM_QUANTITY)){
           maximumQuantity = entry.getValue();
           if(StringUtils.isBlank(maximumQuantity) || maximumQuantity.equalsIgnoreCase("null")){
             maximumQuantity = StringUtils.rightPad(StringUtils.EMPTY, 3);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SEARS_CHARGE_FLAG)){
           searsChargeFlag = entry.getValue();
           if(StringUtils.isBlank(searsChargeFlag) || searsChargeFlag.equalsIgnoreCase("null")){
             searsChargeFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REGULAR_PRICE_FLAG)){
           regularPriceFlag = entry.getValue();
           if(StringUtils.isBlank(regularPriceFlag) || regularPriceFlag.equalsIgnoreCase("null")){
             regularPriceFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PROMOTION_PRICE_FLAG)){
           promotionPriceFlag = entry.getValue();
           if(StringUtils.isBlank(promotionPriceFlag) || promotionPriceFlag.equalsIgnoreCase("null")){
             promotionPriceFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_CLEARANCE_PRICE_FLAG)){
           clearancePriceFlag = entry.getValue();
           if(StringUtils.isBlank(clearancePriceFlag) || clearancePriceFlag.equalsIgnoreCase("null")){
             clearancePriceFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MALL_FLAG)){
           mallFlag = entry.getValue();
           if(StringUtils.isBlank(mallFlag) || mallFlag.equalsIgnoreCase("null")){
             mallFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_HARDWARE_FLAG)){
           hardwareFlag = entry.getValue();
           if(StringUtils.isBlank(hardwareFlag) || hardwareFlag.equalsIgnoreCase("null")){
             hardwareFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_THE_GREAT_INDOORS_FLAG)){
           theGreatIndoorsFlag = entry.getValue();
           if(StringUtils.isBlank(theGreatIndoorsFlag) || theGreatIndoorsFlag.equalsIgnoreCase("null")){
             theGreatIndoorsFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_AUTOMOTIVE_FLAG)){
           automotiveFlag = entry.getValue();
           if(StringUtils.isBlank(automotiveFlag) || automotiveFlag.equalsIgnoreCase("null")){
             automotiveFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_OUTLET_FLAG)){
           outletFlag = entry.getValue();
           if(StringUtils.isBlank(outletFlag) || outletFlag.equalsIgnoreCase("null")){
             outletFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DEALER_FLAG)){
           dealerFlag = entry.getValue();
           if(StringUtils.isBlank(dealerFlag) || dealerFlag.equalsIgnoreCase("null")){
             dealerFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_WWW_FLAG)){
           wwwFlag = entry.getValue();
           if(StringUtils.isBlank(wwwFlag) || wwwFlag.equalsIgnoreCase("null")){
             wwwFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PRODUCT_SERVICE_FLAG)){
           productServiceFlag = entry.getValue();
           if(StringUtils.isBlank(productServiceFlag) || productServiceFlag.equalsIgnoreCase("null")){
             productServiceFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DELIVERY_FLAG)){
           deliveryFlag = entry.getValue();
           if(StringUtils.isBlank(deliveryFlag) || deliveryFlag.equalsIgnoreCase("null")){
             deliveryFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG)){
           associateDiscountFlag = entry.getValue();
           if(StringUtils.isBlank(associateDiscountFlag) || associateDiscountFlag.equalsIgnoreCase("null")){
             associateDiscountFlag = StringUtils.rightPad(StringUtils.EMPTY, 1);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESERVED_FOR_FUTURE)){
           reservedForFutureUse = entry.getValue();
           if(StringUtils.isBlank(reservedForFutureUse) || reservedForFutureUse.equalsIgnoreCase("null")){
             reservedForFutureUse = StringUtils.rightPad(StringUtils.EMPTY, 4);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_COUPON_NUMBER)){
           couponNumber = entry.getValue();
           if(StringUtils.isBlank(couponNumber) || couponNumber.equalsIgnoreCase("null")){
             couponNumber = StringUtils.rightPad(StringUtils.EMPTY, 8);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_COUPON_TYPE_CODE)){
           couponTypeCode = entry.getValue();
           if(StringUtils.isBlank(couponTypeCode) || couponTypeCode.equalsIgnoreCase("null")){
             couponTypeCode = StringUtils.rightPad(StringUtils.EMPTY, 10);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MARKDOWN_TYPE_CODE)){
           markdownTypeCode = entry.getValue();
           if(StringUtils.isBlank(markdownTypeCode) || markdownTypeCode.equalsIgnoreCase("null")){
             markdownTypeCode = StringUtils.rightPad(StringUtils.EMPTY, 10);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MARKDOWN_AMOUNT_PERCENT)){
           markdownAmountPercent = entry.getValue();
           if(StringUtils.isBlank(markdownAmountPercent) || markdownAmountPercent.equalsIgnoreCase("null")){
             markdownAmountPercent = StringUtils.rightPad(StringUtils.EMPTY, 5);
           }
         }
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_FUTURE_PURCHASE_COUPON_DESC)){
           futurePurchaseCouponDesc = entry.getValue();
           if(StringUtils.isBlank(futurePurchaseCouponDesc) || futurePurchaseCouponDesc.equalsIgnoreCase("null")){
             futurePurchaseCouponDesc = StringUtils.rightPad(StringUtils.EMPTY, 480);
           }
         }
        }
      }

     sb.append(indicator)
     .append(" ")
      .append(this.byteResponse(unknown.getBytes()))
      .append(this.byteResponse(futurePurchaseCouponID.getBytes()))
      .append(this.byteResponse(startDate.getBytes()))
      .append(this.byteResponse(endDate.getBytes()))
      .append(this.byteResponse(minimumQualificationAmount.getBytes()))
      .append(this.byteResponse(maximumQuantity.getBytes()))
      .append(this.byteResponse(searsChargeFlag.getBytes()))
      .append(this.byteResponse(regularPriceFlag.getBytes()))
      .append(this.byteResponse(promotionPriceFlag.getBytes()))
      .append(this.byteResponse(clearancePriceFlag.getBytes()))
      .append(this.byteResponse(mallFlag.getBytes()))
      .append(this.byteResponse(hardwareFlag.getBytes()))
      .append(this.byteResponse(theGreatIndoorsFlag.getBytes()))
      .append(this.byteResponse(automotiveFlag.getBytes()))
      .append(this.byteResponse(outletFlag.getBytes()))
      .append(this.byteResponse(dealerFlag.getBytes()))
      .append(this.byteResponse(wwwFlag.getBytes()))
      .append(this.byteResponse(productServiceFlag.getBytes()))
      .append(this.byteResponse(deliveryFlag.getBytes()))
      .append(this.byteResponse(associateDiscountFlag.getBytes()))
      .append(this.byteResponse(reservedForFutureUse.getBytes()))
      .append(this.byteResponse(couponNumber.getBytes()))
      .append(this.byteResponse(couponTypeCode.getBytes()))
      .append(this.byteResponse(markdownTypeCode.getBytes()))
      .append(this.byteResponse(markdownAmountPercent.getBytes()))
      .append(this.byteResponse(futurePurchaseCouponDesc.getBytes()));
      
      return sb;
     }
  
  private StringBuilder processPluResponseEC(TwilightJsonObject twilightJsonObject)
  {
    String indicator = "EC";
    String pluGroupId = StringUtils.EMPTY;
    String pluItemFlag = StringUtils.EMPTY;
    String pluGroupType = StringUtils.EMPTY;
    String pluGroupQuantity = StringUtils.EMPTY;
    String pluGroupPrice = StringUtils.EMPTY;
    
    final Map<String, String> pluResponseECMap = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
    if(pluResponseECMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : pluResponseECMap.entrySet())
      { 
        if(!pluResponseECMap.containsKey(PLU_RESP_PLU_GROUP_ID))
        {
            modifiableMap.put(PLU_RESP_PLU_GROUP_ID, prop.getProperty(DEFAULT_PLU_RESP_PLU_GROUP_ID));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponseECMap.containsKey(PLU_RESP_PLU_ITEM_FLAG))
        {
            modifiableMap.put(PLU_RESP_PLU_ITEM_FLAG, prop.getProperty(DEFAULT_PLU_RESP_PLU_ITEM_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponseECMap.containsKey(PLU_RESP_PLU_GROUP_TYPE))
        {
            modifiableMap.put(PLU_RESP_PLU_GROUP_TYPE, prop.getProperty(DEFAULT_PLU_RESP_PLU_GROUP_TYPE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponseECMap.containsKey(PLU_RESP_PLU_GROUP_QUANTITY))
        {
            modifiableMap.put(PLU_RESP_PLU_GROUP_QUANTITY, prop.getProperty(DEFAULT_PLU_RESP_PLU_GROUP_QUANTITY));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponseECMap.containsKey(PLU_RESP_PLU_GROUP_PRICE))
        {
            modifiableMap.put(PLU_RESP_PLU_GROUP_PRICE, prop.getProperty(DEFAULT_PLU_RESP_PLU_GROUP_PRICE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_GROUP_ID))
           pluGroupId = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_ITEM_FLAG))
           pluItemFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_GROUP_TYPE))
           pluGroupType = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_GROUP_QUANTITY))
           pluGroupQuantity = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PLU_GROUP_PRICE))
           pluGroupPrice = entry.getValue();
        }
      }
      
      sb.append(indicator)
      .append(" ")
      .append(this.byteResponse(pluGroupId.getBytes()))
      .append(this.byteResponse(pluItemFlag.getBytes()))
      .append(this.byteResponse(pluGroupType.getBytes()))
      .append(this.byteResponse(pluGroupQuantity.getBytes()))
      .append(this.byteResponse(pluGroupPrice.getBytes()));
      return sb;
  }
  
  private StringBuilder processPluResponse95(TwilightJsonObject twilightJsonObject)
  {
    String indicator = "95";
    String segmentLevel = StringUtils.EMPTY;
    String rebateId = StringUtils.EMPTY;
    String printEffectiveDate = StringUtils.EMPTY;
    String printEndDate = StringUtils.EMPTY;
    String reprintEndDate = StringUtils.EMPTY;
    String minimumQualificationAmount = StringUtils.EMPTY;
    String maximumQuantity = StringUtils.EMPTY;
    String upcRequiredFlag = StringUtils.EMPTY;
    String searsChargeFlag = StringUtils.EMPTY;
    String regularPriceFlag = StringUtils.EMPTY;
    String promotionPriceFlag = StringUtils.EMPTY;
    String clearancePriceFlag =  StringUtils.EMPTY;
    String mallFlag = StringUtils.EMPTY;
    String hardwareFlag = StringUtils.EMPTY;
    String theGreatIndoorsFlag = StringUtils.EMPTY;
    String automotiveFlag = StringUtils.EMPTY;
    String outletFlag = StringUtils.EMPTY;
    String dealerFlag = StringUtils.EMPTY;
    String wwwFlag =  StringUtils.EMPTY;
    String productServiceFlag = StringUtils.EMPTY;
    String deliveryFlag = StringUtils.EMPTY;
    String couponNumber = StringUtils.EMPTY;
    String associatedProgramNumber = StringUtils.EMPTY;
    String vendorName = StringUtils.EMPTY;
    String weeksBeforeRecvRebate = StringUtils.EMPTY;
    String postMarkDateText = StringUtils.EMPTY;
    String redemptionCenterName = StringUtils.EMPTY;
    String redemptionCenterAddress1 = StringUtils.EMPTY;
    String redemptionCenterAddress2 = StringUtils.EMPTY;
    String redemptionCenterPOBox = StringUtils.EMPTY;
    String redemptionCenterCity = StringUtils.EMPTY;
    String redemptionCenterState = StringUtils.EMPTY;
    String redemptionCenterZip = StringUtils.EMPTY;
    String redemptionCenterPhone = StringUtils.EMPTY;
    String redemptionCenterHours = StringUtils.EMPTY;
    String redemptionCenterDescription = StringUtils.EMPTY;
    String rebateAmount = StringUtils.EMPTY;
    String installationFlag = StringUtils.EMPTY;
    String freeDeliveryZeroPercentFlag = StringUtils.EMPTY;
    String rebateMethodCode = StringUtils.EMPTY;
    String filler = StringUtils.EMPTY;
    
    final Map<String, String> pluResponse95Map = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
    if(pluResponse95Map.size() > 0)
    {
      for (Map.Entry<String, String> entry : pluResponse95Map.entrySet())
      { 
        if(!pluResponse95Map.containsKey(SEGMENT_LEVEL))
        {
            modifiableMap.put(SEGMENT_LEVEL, prop.getProperty(DEFAULT_SEGMENT_LEVEL_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REBATE_ID))
        {
            modifiableMap.put(PLU_RESP_REBATE_ID, prop.getProperty(DEFAULT_PLU_RESP_REBATE_ID));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_PRINT_EFFECTIVE_DATE))
        {
            modifiableMap.put(PLU_RESP_PRINT_EFFECTIVE_DATE, prop.getProperty(DEFAULT_PLU_RESP_PRINT_EFFECTIVE_DATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_PRINT_END_DATE))
        {
            modifiableMap.put(PLU_RESP_PRINT_END_DATE, prop.getProperty(DEFAULT_PLU_RESP_PRINT_END_DATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REPRINT_END_DATE))
        {
            modifiableMap.put(PLU_RESP_REPRINT_END_DATE, prop.getProperty(DEFAULT_PLU_RESP_REPRINT_END_DATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_MIN_QUALIFICATION_AMT))
        {
            modifiableMap.put(PLU_RESP_MIN_QUALIFICATION_AMT, prop.getProperty(DEFAULT_PLU_RESP_MIN_QUALIFICATION_AMT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_MAX_QUANTITY))
        {
            modifiableMap.put(PLU_RESP_MAX_QUANTITY, prop.getProperty(DEFAULT_PLU_RESP_MAX_QUANTITY));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_UPC_REQUIRED_FLAG))
        {
            modifiableMap.put(PLU_RESP_UPC_REQUIRED_FLAG, prop.getProperty(DEFAULT_PLU_RESP_UPC_REQUIRED_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_SEARS_CHARGE_FLAG))
        {
            modifiableMap.put(PLU_RESP_SEARS_CHARGE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_SEARS_CHARGE_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REGULAR_PRICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_REGULAR_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_REGULAR_PRICE_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_PROMOTION_PRICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_PROMOTION_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_PROMOTION_PRICE_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_CLEARANCE_PRICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_CLEARANCE_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_CLEARANCE_PRICE_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_MALL_FLAG))
        {
            modifiableMap.put(PLU_RESP_MALL_FLAG, prop.getProperty(DEFAULT_PLU_RESP_MALL_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_HARDWARE_FLAG))
        {
            modifiableMap.put(PLU_RESP_HARDWARE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_HARDWARE_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_THE_GREAT_INDOORS_FLAG))
        {
            modifiableMap.put(PLU_RESP_THE_GREAT_INDOORS_FLAG, prop.getProperty(DEFAULT_PLU_RESP_THE_GREAT_INDOORS_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_AUTOMOTIVE_FLAG))
        {
            modifiableMap.put(PLU_RESP_AUTOMOTIVE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_AUTOMOTIVE_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_OUTLET_FLAG))
        {
            modifiableMap.put(PLU_RESP_OUTLET_FLAG, prop.getProperty(DEFAULT_PLU_RESP_OUTLET_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_DEALER_FLAG))
        {
            modifiableMap.put(PLU_RESP_DEALER_FLAG, prop.getProperty(DEFAULT_PLU_RESP_DEALER_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_WWW_FLAG))
        {
            modifiableMap.put(PLU_RESP_WWW_FLAG, prop.getProperty(DEFAULT_PLU_RESP_WWW_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_PRODUCT_SERVICE_FLAG))
        {
            modifiableMap.put(PLU_RESP_PRODUCT_SERVICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_PRODUCT_SERVICE_FLAG_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_DELIVERY_FLAG))
        {
            modifiableMap.put(PLU_RESP_DELIVERY_FLAG, prop.getProperty(DEFAULT_PLU_RESP_DELIVERY_FLAG_95));
        }
        
        if(!pluResponse95Map.containsKey(DEFAULT_PLU_RESP_COUPON_NUMBER))
        {
            modifiableMap.put(DEFAULT_PLU_RESP_COUPON_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_COUPON_NUMBER_95));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_ASSOCIATED_PROGRAM_NUMBER))
        {
            modifiableMap.put(PLU_RESP_ASSOCIATED_PROGRAM_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_ASSOCIATED_PROGRAM_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_VENDOR_NAME))
        {
            modifiableMap.put(PLU_RESP_VENDOR_NAME, prop.getProperty(DEFAULT_PLU_RESP_VENDOR_NAME));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_WEEKS_BEFORE_RECV_REBATE))
        {
            modifiableMap.put(PLU_RESP_WEEKS_BEFORE_RECV_REBATE, prop.getProperty(DEFAULT_PLU_RESP_WEEKS_BEFORE_RECV_REBATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_POSTMARK_DATE_TXT))
        {
            modifiableMap.put(PLU_RESP_POSTMARK_DATE_TXT, prop.getProperty(DEFAULT_PLU_RESP_POSTMARK_DATE_TXT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_NAME))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_NAME, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_NAME));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }

        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_ADDRESS1))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_ADDRESS1, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_ADDRESS1));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_ADDRESS2))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_ADDRESS2, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_ADDRESS2));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_POBOX))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_POBOX, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_POBOX));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_CITY))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_CITY, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_CITY));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_STATE))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_STATE, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_STATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_ZIP))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_ZIP, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_ZIP));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_PHONE))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_PHONE, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_PHONE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_HOURS))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_HOURS, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_HOURS));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REDEMPTION_CENTER_DESCRIPTION))
        {
            modifiableMap.put(PLU_RESP_REDEMPTION_CENTER_DESCRIPTION, prop.getProperty(DEFAULT_PLU_RESP_REDEMPTION_CENTER_DESCRIPTION));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REBATE_AMOUNT))
        {
            modifiableMap.put(PLU_RESP_REBATE_AMOUNT, prop.getProperty(DEFAULT_PLU_RESP_REBATE_AMOUNT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_INSTALLATION_FLAG))
        {
            modifiableMap.put(PLU_RESP_INSTALLATION_FLAG, prop.getProperty(DEFAULT_PLU_RESP_INSTALLATION_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_FREE_DELIVERY_ZERO_PERCENT_FLAG))
        {
            modifiableMap.put(PLU_RESP_FREE_DELIVERY_ZERO_PERCENT_FLAG, prop.getProperty(DEFAULT_PLU_RESP_FREE_DELIVERY_ZERO_PERCENT_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_REBATE_METHOD_CODE))
        {
            modifiableMap.put(PLU_RESP_REBATE_METHOD_CODE, prop.getProperty(DEFAULT_PLU_RESP_REBATE_METHOD_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluResponse95Map.containsKey(PLU_RESP_FILLER))
        {
            modifiableMap.put(PLU_RESP_FILLER, prop.getProperty(DEFAULT_PLU_RESP_FILLER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
    
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(SEGMENT_LEVEL))
           segmentLevel = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REBATE_ID))
           rebateId = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PRINT_EFFECTIVE_DATE))
           printEffectiveDate = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PRINT_END_DATE))
           printEndDate = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REPRINT_END_DATE))
           reprintEndDate = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MIN_QUALIFICATION_AMT))
           minimumQualificationAmount = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MAX_QUANTITY))
           maximumQuantity = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_UPC_REQUIRED_FLAG))
           upcRequiredFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SEARS_CHARGE_FLAG))
           searsChargeFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REGULAR_PRICE_FLAG))
           regularPriceFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PROMOTION_PRICE_FLAG))
           promotionPriceFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_CLEARANCE_PRICE_FLAG))
           clearancePriceFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MALL_FLAG))
           mallFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_HARDWARE_FLAG))
           hardwareFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_THE_GREAT_INDOORS_FLAG))
           theGreatIndoorsFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_AUTOMOTIVE_FLAG))
           automotiveFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_OUTLET_FLAG))
           outletFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DEALER_FLAG))
           dealerFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_WWW_FLAG))
           wwwFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PRODUCT_SERVICE_FLAG))
           productServiceFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DELIVERY_FLAG))
           deliveryFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_COUPON_NUMBER))
           couponNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_ASSOCIATED_PROGRAM_NUMBER))
           associatedProgramNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_VENDOR_NAME))
           vendorName = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_WEEKS_BEFORE_RECV_REBATE))
           weeksBeforeRecvRebate = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_POSTMARK_DATE_TXT))
           postMarkDateText = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_NAME))
           redemptionCenterName = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_ADDRESS1))
           redemptionCenterAddress1 = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_ADDRESS2))
           redemptionCenterAddress2 = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_POBOX))
           redemptionCenterPOBox = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_CITY))
           redemptionCenterCity = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_STATE))
           redemptionCenterState = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_ZIP))
           redemptionCenterZip = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_PHONE))
           redemptionCenterPhone = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_HOURS))
           redemptionCenterHours = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REDEMPTION_CENTER_DESCRIPTION))
           redemptionCenterDescription = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REBATE_AMOUNT))
           rebateAmount = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_INSTALLATION_FLAG))
           installationFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_FREE_DELIVERY_ZERO_PERCENT_FLAG))
           freeDeliveryZeroPercentFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REBATE_METHOD_CODE))
           rebateMethodCode = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_FILLER))
           filler = entry.getValue();
        }
      }
      
      
      sb.append(indicator)
      .append(" ")
      .append(this.byteResponse(segmentLevel.getBytes()))
      .append(this.byteResponse(rebateId.getBytes()))
      .append(this.byteResponse(printEffectiveDate.getBytes()))
      .append(this.byteResponse(printEndDate.getBytes()))
      .append(this.byteResponse(reprintEndDate.getBytes()))
      .append(this.byteResponse(minimumQualificationAmount.getBytes()))
      .append(this.byteResponse(maximumQuantity.getBytes()))
      .append(this.byteResponse(upcRequiredFlag.getBytes()))
      .append(this.byteResponse(searsChargeFlag.getBytes()))
      .append(this.byteResponse(regularPriceFlag.getBytes()))
      .append(this.byteResponse(promotionPriceFlag.getBytes()))
      .append(this.byteResponse(clearancePriceFlag.getBytes()))
      .append(this.byteResponse(mallFlag.getBytes()))
      .append(this.byteResponse(hardwareFlag.getBytes()))
      .append(this.byteResponse(theGreatIndoorsFlag.getBytes()))
      .append(this.byteResponse(automotiveFlag.getBytes()))
      .append(this.byteResponse(outletFlag.getBytes()))
      .append(this.byteResponse(dealerFlag.getBytes()))
      .append(this.byteResponse(wwwFlag.getBytes()))
      .append(this.byteResponse(productServiceFlag.getBytes()))
      .append(this.byteResponse(deliveryFlag.getBytes()))
      .append(this.byteResponse(couponNumber.getBytes()))
      .append(this.byteResponse(associatedProgramNumber.getBytes()))
      .append(this.byteResponse(vendorName.getBytes()))
      .append(this.byteResponse(weeksBeforeRecvRebate.getBytes()))
      .append(this.byteResponse(postMarkDateText.getBytes()))
      .append(this.byteResponse(redemptionCenterName.getBytes()))
      .append(this.byteResponse(redemptionCenterAddress1.getBytes()))
      .append(this.byteResponse(redemptionCenterAddress2.getBytes()))
      .append(this.byteResponse(redemptionCenterPOBox.getBytes()))
      .append(this.byteResponse(redemptionCenterCity.getBytes()))
      .append(this.byteResponse(redemptionCenterState.getBytes()))
      .append(this.byteResponse(redemptionCenterZip.getBytes()))
      .append(this.byteResponse(redemptionCenterPhone.getBytes()))
      .append(this.byteResponse(redemptionCenterHours.getBytes()))
      .append(this.byteResponse(redemptionCenterDescription.getBytes()))
      .append(this.byteResponse(rebateAmount.getBytes()))
      .append(this.byteResponse(installationFlag.getBytes()))
      .append(this.byteResponse(freeDeliveryZeroPercentFlag.getBytes()))
      .append(this.byteResponse(rebateMethodCode.getBytes()))
      .append(this.byteResponse(filler.getBytes()));
      
      return sb;
  }

  private StringBuilder processPluResponse9C(TwilightJsonObject twilightJsonObject)
  {
      String indicator = "9C";
      String segmentLength = StringUtils.EMPTY;
      String messageKey = StringUtils.EMPTY;
      String messageTypeCode = StringUtils.EMPTY;
      String messageText = StringUtils.EMPTY;
      
      
      final Map<String, String> pluInquiry9CMap = twilightJsonObject.getParameters();
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(pluInquiry9CMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : pluInquiry9CMap.entrySet())
      { 
        if(!pluInquiry9CMap.containsKey(SEGMENT_LENGTH))
        {
            modifiableMap.put(SEGMENT_LENGTH, prop.getProperty(DEFAULT_SEGMENT_LENGTH_9C));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry9CMap.containsKey(PLU_RESP_MESSAGE_KEY))
        {
            modifiableMap.put(PLU_RESP_MESSAGE_KEY, prop.getProperty(DEFAULT_PLU_RESP_MESSAGE_KEY));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry9CMap.containsKey(PLU_RESP_MESSAGE_TYPE_CODE))
        {
            modifiableMap.put(PLU_RESP_MESSAGE_TYPE_CODE, prop.getProperty(DEFAULT_PLU_RESP_MESSAGE_TYPE_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry9CMap.containsKey(PLU_RESP_MESSAGE_TEXT))
        {
            modifiableMap.put(PLU_RESP_MESSAGE_TEXT, prop.getProperty(DEFAULT_PLU_RESP_MESSAGE_TEXT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(SEGMENT_LENGTH))
           segmentLength = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MESSAGE_KEY))
           messageKey = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MESSAGE_TYPE_CODE))
           messageTypeCode = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MESSAGE_TEXT))
           messageText = entry.getValue();
        }
      }
      
      sb.append(indicator)
      .append(" ")
      .append(this.byteResponse(segmentLength.getBytes()))
      .append(this.byteResponse(messageKey.getBytes()))
      .append(this.byteResponse(messageTypeCode.getBytes()))
      .append(this.byteResponse(messageText.getBytes()));
      
      return sb;
   }

  private StringBuilder processPluResponse40BA(TwilightJsonObject twilightJsonObject)
  {
      String indicator = "40 BA";
      String segmentLength = StringUtils.EMPTY;
      String segmentLevel = StringUtils.EMPTY;
      String taxStatusCode = StringUtils.EMPTY;
      String fillFloorEligible = StringUtils.EMPTY;
      String sku991Eligible = StringUtils.EMPTY;
      String unusedOne = StringUtils.EMPTY;
      String exceptionalValueItem = StringUtils.EMPTY;
      String paReplacementEligible = StringUtils.EMPTY;
      String signalItem = StringUtils.EMPTY;
      String restockingFeeEligible = StringUtils.EMPTY;
      String cancellationFeeEligible = StringUtils.EMPTY;
      String prepaidCardItem = StringUtils.EMPTY;
      String converterBoxItem = StringUtils.EMPTY;
      String iiasEligibleItem = StringUtils.EMPTY;
      String associateDiscountIneligible = StringUtils.EMPTY;
      String subscriptionPlanEligible = StringUtils.EMPTY;
      String thirdPartyItem = StringUtils.EMPTY;
      String alphalineEntertainmentItem = StringUtils.EMPTY;
      String sywrRedemptionExclusion = StringUtils.EMPTY;
      String sywrRedemptionAfterTaxFlag = StringUtils.EMPTY;
      String unusedTwo = StringUtils.EMPTY;
      String restockingFeePercent = StringUtils.EMPTY;
      String cancellationFeePercent = StringUtils.EMPTY;
      String unusedThree = StringUtils.EMPTY;
      
      final Map<String, String> pluInquiry40BAMap = twilightJsonObject.getParameters();
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(pluInquiry40BAMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : pluInquiry40BAMap.entrySet())
      { 
        if(!pluInquiry40BAMap.containsKey(SEGMENT_LENGTH))
        {
            modifiableMap.put(SEGMENT_LENGTH, prop.getProperty(DEFAULT_PLU_RESP_SEGMENT_LENGTH_40BA));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(SEGMENT_LEVEL))
        {
            modifiableMap.put(SEGMENT_LEVEL, prop.getProperty(DEFAULT_PLU_RESP_SEGMENT_LEVEL_40BA));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_TAX_STATUS_CODE))
        {
            modifiableMap.put(PLU_RESP_TAX_STATUS_CODE, prop.getProperty(DEFAULT_PLU_RESP_TAX_STATUS_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_FILL_FLOOR_ELIGIBLE))
        {
            modifiableMap.put(PLU_RESP_FILL_FLOOR_ELIGIBLE, prop.getProperty(DEFAULT_PLU_RESP_FILL_FLOOR_ELIGIBLE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_SKU991_ELIGIBLE))
        {
            modifiableMap.put(PLU_RESP_SKU991_ELIGIBLE, prop.getProperty(DEFAULT_PLU_RESP_SKU991_ELIGIBLE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_UNUSED_ONE))
        {
            modifiableMap.put(PLU_RESP_UNUSED_ONE, prop.getProperty(DEFAULT_PLU_RESP_UNUSED_ONE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_EXCEPTIONAL_VALUE_ITEM))
        {
            modifiableMap.put(PLU_RESP_EXCEPTIONAL_VALUE_ITEM, prop.getProperty(DEFAULT_PLU_RESP_EXCEPTIONAL_VALUE_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_PA_REPLACEMENT_ELIGIBLE))
        {
            modifiableMap.put(PLU_RESP_PA_REPLACEMENT_ELIGIBLE, prop.getProperty(DEFAULT_PLU_RESP_PA_REPLACEMENT_ELIGIBLE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_SIGNAL_ITEM))
        {
            modifiableMap.put(PLU_RESP_SIGNAL_ITEM, prop.getProperty(DEFAULT_PLU_RESP_SIGNAL_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_RESTOCKING_FEE_ELIGIBLE))
        {
            modifiableMap.put(PLU_RESP_RESTOCKING_FEE_ELIGIBLE, prop.getProperty(DEFAULT_PLU_RESP_RESTOCKING_FEE_ELIGIBLE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_CANCELLATION_FEE_ELIGIBLE))
        {
            modifiableMap.put(PLU_RESP_CANCELLATION_FEE_ELIGIBLE, prop.getProperty(DEFAULT_PLU_RESP_CANCELLATION_FEE_ELIGIBLE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_PREPAID_CARD_ITEM))
        {
            modifiableMap.put(PLU_RESP_PREPAID_CARD_ITEM, prop.getProperty(DEFAULT_PLU_RESP_PREPAID_CARD_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_CONVERTER_BOX_ITEM))
        {
            modifiableMap.put(PLU_RESP_CONVERTER_BOX_ITEM, prop.getProperty(DEFAULT_PLU_RESP_CONVERTER_BOX_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_IIAS_ELIGIBLE_ITEM))
        {
            modifiableMap.put(PLU_RESP_IIAS_ELIGIBLE_ITEM, prop.getProperty(DEFAULT_PLU_RESP_IIAS_ELIGIBLE_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_ASSOCIATE_DISCOUNT_INELIGIBLE))
        {
            modifiableMap.put(PLU_RESP_ASSOCIATE_DISCOUNT_INELIGIBLE, prop.getProperty(DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_INELIGIBLE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_SUBSCRIPTION_PLAN_ELIGIBLE))
        {
            modifiableMap.put(PLU_RESP_SUBSCRIPTION_PLAN_ELIGIBLE, prop.getProperty(DEFAULT_PLU_RESP_SUBSCRIPTION_PLAN_ELIGIBLE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_THIRD_PARTY_ITEM))
        {
            modifiableMap.put(PLU_RESP_THIRD_PARTY_ITEM, prop.getProperty(DEFAULT_PLU_RESP_THIRD_PARTY_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_ALPHALINE_ENTERTAINMENT_ITEM))
        {
            modifiableMap.put(PLU_RESP_ALPHALINE_ENTERTAINMENT_ITEM, prop.getProperty(DEFAULT_PLU_RESP_ALPHALINE_ENTERTAINMENT_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_SYWR_REDEMPTION_EXCLUSION))
        {
            modifiableMap.put(PLU_RESP_SYWR_REDEMPTION_EXCLUSION, prop.getProperty(DEFAULT_PLU_RESP_SYWR_REDEMPTION_EXCLUSION));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_SYWR_REDEMPTION_AFTER_TAX_FLAG))
        {
            modifiableMap.put(PLU_RESP_SYWR_REDEMPTION_AFTER_TAX_FLAG, prop.getProperty(DEFAULT_PLU_RESP_SYWR_REDEMPTION_AFTER_TAX_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_UNUSED_TWO))
        {
            modifiableMap.put(PLU_RESP_UNUSED_TWO, prop.getProperty(DEFAULT_PLU_RESP_UNUSED_TWO));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_RESTOCKING_FEE_PERCENT))
        {
            modifiableMap.put(PLU_RESP_RESTOCKING_FEE_PERCENT, prop.getProperty(DEFAULT_PLU_RESP_RESTOCKING_FEE_PERCENT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_CANCELLATION_FEE_PERCENT))
        {
            modifiableMap.put(PLU_RESP_CANCELLATION_FEE_PERCENT, prop.getProperty(DEFAULT_PLU_RESP_CANCELLATION_FEE_PERCENT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!pluInquiry40BAMap.containsKey(PLU_RESP_UNUSED_THREE))
        {
            modifiableMap.put(PLU_RESP_UNUSED_THREE, prop.getProperty(DEFAULT_PLU_RESP_UNUSED_THREE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
    }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(SEGMENT_LENGTH))
           segmentLength = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(SEGMENT_LEVEL))
           segmentLevel = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_TAX_STATUS_CODE))
           taxStatusCode = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_FILL_FLOOR_ELIGIBLE))
           fillFloorEligible = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SKU991_ELIGIBLE))
           sku991Eligible = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_UNUSED_ONE))
           unusedOne = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_EXCEPTIONAL_VALUE_ITEM))
           exceptionalValueItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PA_REPLACEMENT_ELIGIBLE))
           paReplacementEligible = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SIGNAL_ITEM))
           signalItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESTOCKING_FEE_ELIGIBLE))
           restockingFeeEligible = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_CANCELLATION_FEE_ELIGIBLE))
           cancellationFeeEligible = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PREPAID_CARD_ITEM))
           prepaidCardItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_CONVERTER_BOX_ITEM))
           converterBoxItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_IIAS_ELIGIBLE_ITEM))
           iiasEligibleItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_ASSOCIATE_DISCOUNT_INELIGIBLE))
           associateDiscountIneligible = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SUBSCRIPTION_PLAN_ELIGIBLE))
           subscriptionPlanEligible = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_THIRD_PARTY_ITEM))
           thirdPartyItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_ALPHALINE_ENTERTAINMENT_ITEM))
           alphalineEntertainmentItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SYWR_REDEMPTION_EXCLUSION))
           sywrRedemptionExclusion = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SYWR_REDEMPTION_AFTER_TAX_FLAG))
           sywrRedemptionAfterTaxFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_UNUSED_TWO))
           unusedTwo = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESTOCKING_FEE_PERCENT))
           restockingFeePercent = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_CANCELLATION_FEE_PERCENT))
           cancellationFeePercent = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(PLU_RESP_UNUSED_THREE))
           unusedThree = entry.getValue();
        }
      }
      
      sb.append(indicator)
      .append(" ")
      .append(this.byteResponse(segmentLength.getBytes()))
      .append(this.byteResponse(segmentLevel.getBytes()))
      .append(this.byteResponse(taxStatusCode.getBytes()))
      .append(this.byteResponse(fillFloorEligible.getBytes()))
      .append(this.byteResponse(sku991Eligible.getBytes()))
      .append(this.byteResponse(unusedOne.getBytes()))
      .append(this.byteResponse(exceptionalValueItem.getBytes()))
      .append(this.byteResponse(paReplacementEligible.getBytes()))
      .append(this.byteResponse(signalItem.getBytes()))
      .append(this.byteResponse(restockingFeeEligible.getBytes()))
      .append(this.byteResponse(cancellationFeeEligible.getBytes()))
      .append(this.byteResponse(prepaidCardItem.getBytes()))
      .append(this.byteResponse(converterBoxItem.getBytes()))
      .append(this.byteResponse(iiasEligibleItem.getBytes()))
      .append(this.byteResponse(associateDiscountIneligible.getBytes()))
      .append(this.byteResponse(subscriptionPlanEligible.getBytes()))
      .append(this.byteResponse(thirdPartyItem.getBytes()))
      .append(this.byteResponse(alphalineEntertainmentItem.getBytes()))
      .append(this.byteResponse(sywrRedemptionExclusion.getBytes()))
      .append(this.byteResponse(sywrRedemptionAfterTaxFlag.getBytes()))
      .append(this.byteResponse(unusedTwo.getBytes()))
      .append(this.byteResponse(restockingFeePercent.getBytes()))
      .append(this.byteResponse(cancellationFeePercent.getBytes()))
      .append(this.byteResponse(unusedThree.getBytes()));
      
      return sb;
   }
  
  
  private StringBuilder processPluResponse58B1(TwilightJsonObject twilightJsonObject)
  {
    
    String indicator = "58 B1";
    String installerLeadTimeValue = StringUtils.EMPTY;
    String installationCutOffTime = StringUtils.EMPTY;
    String longItemDescription = StringUtils.EMPTY;
    
    final Map<String, String> pluResponse58B1Map = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(pluResponse58B1Map.size() > 0)
  {
    for (Map.Entry<String, String> entry : pluResponse58B1Map.entrySet())
    { 
      if(!pluResponse58B1Map.containsKey(PLU_RESP_INSTALLER_LEAD_TIME_VALUE))
      {
          modifiableMap.put(PLU_RESP_INSTALLER_LEAD_TIME_VALUE, prop.getProperty(DEFAULT_PLU_RESP_INSTALLER_LEAD_TIME_VALUE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse58B1Map.containsKey(PLU_RESP_INSTALLATION_CUTOFF_TIME))
      {
          modifiableMap.put(PLU_RESP_INSTALLATION_CUTOFF_TIME, prop.getProperty(DEFAULT_PLU_RESP_INSTALLATION_CUTOFF_TIME));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse58B1Map.containsKey(PLU_RESP_LONG_ITEM_DESCRIPTION))
      {
          modifiableMap.put(PLU_RESP_LONG_ITEM_DESCRIPTION, prop.getProperty(DEFAULT_PLU_RESP_LONG_ITEM_DESCRIPTION));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
    }
    
    if(modifiableMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : modifiableMap.entrySet())
      { 
       if(entry.getKey().equalsIgnoreCase(PLU_RESP_INSTALLER_LEAD_TIME_VALUE))
         installerLeadTimeValue = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_INSTALLATION_CUTOFF_TIME))
         installationCutOffTime = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_LONG_ITEM_DESCRIPTION))
         longItemDescription = entry.getValue();
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(installerLeadTimeValue.getBytes()))
    .append(this.byteResponse(installationCutOffTime.getBytes()))
    .append(this.byteResponse(longItemDescription.getBytes()));
    }
  
    return sb;
  }
  
  
  private StringBuilder processPluResponse60B1(TwilightJsonObject twilightJsonObject)
  {
    String indicator = "60 B1";
    String segmentLevel = StringUtils.EMPTY;
    String offerType = StringUtils.EMPTY;
    String offerId = StringUtils.EMPTY;
    String financialCode = StringUtils.EMPTY;
    String thresholdDollarAmount = StringUtils.EMPTY;
    String associateDiscountFlag = StringUtils.EMPTY;
    String miscellaneousReductionsFlag = StringUtils.EMPTY;
    String instantRebateFlag = StringUtils.EMPTY;
    String mailInRebateFlag = StringUtils.EMPTY;
    String delayedBillingEndDateInterval = StringUtils.EMPTY;
    String delayedBillingEndDateDate = StringUtils.EMPTY;
    String registerPromoDescriptionLine1 = StringUtils.EMPTY;
    String registerPromoDescriptionLine2 = StringUtils.EMPTY;
    String nbrOfReceiptPromoDescriptionLines = StringUtils.EMPTY;
    
    String receiptPromoDescriptionLine = StringUtils.EMPTY;
    
    
    final Map<String, String> pluResponse60B1Map = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(pluResponse60B1Map.size() > 0)
  {
    for (Map.Entry<String, String> entry : pluResponse60B1Map.entrySet())
    { 
      if(!pluResponse60B1Map.containsKey(SEGMENT_LEVEL))
      {
          modifiableMap.put(SEGMENT_LEVEL, prop.getProperty(DEFAULT_PLU_RESP_SEGMENT_LEVEL_60B1));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_OFFER_TYPE))
      {
          modifiableMap.put(PLU_RESP_OFFER_TYPE, prop.getProperty(DEFAULT_PLU_RESP_OFFER_TYPE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_OFFER_ID))
      {
          modifiableMap.put(PLU_RESP_OFFER_ID, prop.getProperty(DEFAULT_PLU_RESP_OFFER_ID));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_FINANCIAL_CODE))
      {
          modifiableMap.put(PLU_RESP_FINANCIAL_CODE, prop.getProperty(DEFAULT_PLU_RESP_FINANCIAL_CODE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      if(!pluResponse60B1Map.containsKey(PLU_RESP_THRESHOLD_DOLLAR_AMOUNT))
      {
          modifiableMap.put(PLU_RESP_THRESHOLD_DOLLAR_AMOUNT, prop.getProperty(DEFAULT_PLU_RESP_THRESHOLD_DOLLAR_AMOUNT));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG))
      {
          modifiableMap.put(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG, prop.getProperty(DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_FLAG_60B1));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_MISCELLANEOUS_REDUCTION_FLAG))
      {
          modifiableMap.put(PLU_RESP_MISCELLANEOUS_REDUCTION_FLAG, prop.getProperty(DEFAULT_PLU_RESP_MISCELLANEOUS_REDUCTION_FLAG));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_INSTANT_REBATE_FLAG))
      {
          modifiableMap.put(PLU_RESP_INSTANT_REBATE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_INSTANT_REBATE_FLAG));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      if(!pluResponse60B1Map.containsKey(PLU_RESP_MAILIN_REBATE_FLAG))
      {
          modifiableMap.put(PLU_RESP_MAILIN_REBATE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_MAILIN_REBATE_FLAG));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_DELAYED_BILLING_END_DATE_INTERVAL))
      {
          modifiableMap.put(PLU_RESP_DELAYED_BILLING_END_DATE_INTERVAL, prop.getProperty(DEFAULT_PLU_RESP_DELAYED_BILLING_END_DATE_INTERVAL));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_DELAYED_BILLING_END_DATE_DATE))
      {
          modifiableMap.put(PLU_RESP_DELAYED_BILLING_END_DATE_DATE, prop.getProperty(DEFAULT_PLU_RESP_DELAYED_BILLING_END_DATE_DATE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE1))
      {
          modifiableMap.put(PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE1, prop.getProperty(DEFAULT_PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE1));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE2))
      {
          modifiableMap.put(PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE2, prop.getProperty(DEFAULT_PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE2));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse60B1Map.containsKey(PLU_RESP_NBR_OF_RECEIPT_PROMO_DESC_LINES))
      {
          modifiableMap.put(PLU_RESP_NBR_OF_RECEIPT_PROMO_DESC_LINES, prop.getProperty(DEFAULT_PLU_RESP_NBR_OF_RECEIPT_PROMO_DESC_LINES));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
    }
    
    if(modifiableMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : modifiableMap.entrySet())
      { 
       if(entry.getKey().equalsIgnoreCase(SEGMENT_LEVEL))
         segmentLevel = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_OFFER_TYPE))
         offerType = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_OFFER_ID))
         offerId = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_FINANCIAL_CODE))
         financialCode = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_THRESHOLD_DOLLAR_AMOUNT))
         thresholdDollarAmount = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG))
         associateDiscountFlag = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MISCELLANEOUS_REDUCTION_FLAG))
         miscellaneousReductionsFlag = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_INSTANT_REBATE_FLAG))
         instantRebateFlag = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MAILIN_REBATE_FLAG))
         mailInRebateFlag = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DELAYED_BILLING_END_DATE_INTERVAL))
         delayedBillingEndDateInterval = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DELAYED_BILLING_END_DATE_DATE))
         delayedBillingEndDateDate = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE1))
         registerPromoDescriptionLine1 = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REGISTER_PROMO_DESCRIPTION_LINE2))
         registerPromoDescriptionLine2 = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_NBR_OF_RECEIPT_PROMO_DESC_LINES))
         nbrOfReceiptPromoDescriptionLines = entry.getValue();
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(segmentLevel.getBytes()))
    .append(this.byteResponse(offerType.getBytes()))
    .append(this.byteResponse(offerId.getBytes()))
    .append(this.byteResponse(financialCode.getBytes()))
    .append(this.byteResponse(thresholdDollarAmount.getBytes()))
    .append(this.byteResponse(associateDiscountFlag.getBytes()))
    .append(this.byteResponse(miscellaneousReductionsFlag.getBytes()))
    .append(this.byteResponse(instantRebateFlag.getBytes()))
    .append(this.byteResponse(mailInRebateFlag.getBytes()))
    .append(this.byteResponse(delayedBillingEndDateInterval.getBytes()))
    .append(this.byteResponse(delayedBillingEndDateDate.getBytes()))
    .append(this.byteResponse(registerPromoDescriptionLine1.getBytes()))
    .append(this.byteResponse(registerPromoDescriptionLine2.getBytes()))
    .append(this.byteResponse(nbrOfReceiptPromoDescriptionLines.getBytes()));
  }
      
    
      for(TwilightJsonObject promoDescObject : twilightJsonObject.getTwilightJsonObject())
      {
        final Iterator<TwilightJsonObject> promoDescLinesItr = promoDescObject.getTwilightJsonObject().iterator();
        
        while(promoDescLinesItr.hasNext())
        {
          final Map<String, String> promoDescLinesMap = promoDescLinesItr.next().getParameters();
          final Map<String, String> promoDescLinesModifiableMap = new HashMap<String,String>();
          
          if(promoDescLinesMap.size() > 0)
          {
            for (Map.Entry<String, String> entry : promoDescLinesMap.entrySet())
            {
                if(!promoDescLinesMap.containsKey(PLU_RESP_RECEIPT_PROMO_DESC_LINE))
                {
                  promoDescLinesModifiableMap.put(PLU_RESP_RECEIPT_PROMO_DESC_LINE, prop.getProperty(DEFAULT_PLU_RESP_RECEIPT_PROMO_DESC_LINE));
                }
                else
                {
                  promoDescLinesModifiableMap.put(entry.getKey(), entry.getValue());
                }
              }
            
              if(promoDescLinesModifiableMap.size() > 0)
              {
                for (Map.Entry<String, String> entry : promoDescLinesModifiableMap.entrySet())
                { 
                  if(entry.getKey().equalsIgnoreCase(PLU_RESP_RECEIPT_PROMO_DESC_LINE))
                    receiptPromoDescriptionLine = entry.getValue();
                }
              }
            sb.append(this.byteResponse(receiptPromoDescriptionLine.getBytes()));
            }
          }
        }
      
      return sb;
      }
  
  private StringBuilder processPluResponse62B1(TwilightJsonObject twilightJsonObject)
  {
    
    String indicator = "62 B1";
    String segmentLevel = StringUtils.EMPTY;
    String restrictionType = StringUtils.EMPTY;
    String minimumAgeValue = StringUtils.EMPTY;
    String maximumQuantityValue = StringUtils.EMPTY;
    String itemMatchIndicator = StringUtils.EMPTY;
    
    final Map<String, String> pluResponse62B1Map = twilightJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(pluResponse62B1Map.size() > 0)
  {
    for (Map.Entry<String, String> entry : pluResponse62B1Map.entrySet())
    { 
      if(!pluResponse62B1Map.containsKey(SEGMENT_LEVEL))
      {
          modifiableMap.put(SEGMENT_LEVEL, prop.getProperty(DEFAULT_PLU_RESP_SEGMENT_LEVEL_62B1));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse62B1Map.containsKey(PLU_RESP_RESTRICTION_TYPE))
      {
          modifiableMap.put(PLU_RESP_RESTRICTION_TYPE, prop.getProperty(DEFAULT_PLU_RESP_RESTRICTION_TYPE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse62B1Map.containsKey(PLU_RESP_MINIMUM_AGE_VALUE))
      {
          modifiableMap.put(PLU_RESP_MINIMUM_AGE_VALUE, prop.getProperty(DEFAULT_PLU_RESP_MINIMUM_AGE_VALUE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse62B1Map.containsKey(PLU_RESP_MAXIMUM_QUANTITY_VALUE))
      {
          modifiableMap.put(PLU_RESP_MAXIMUM_QUANTITY_VALUE, prop.getProperty(DEFAULT_PLU_RESP_MAXIMUM_QUANTITY_VALUE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!pluResponse62B1Map.containsKey(PLU_RESP_ITEM_MATCH_INDICATOR))
      {
          modifiableMap.put(PLU_RESP_ITEM_MATCH_INDICATOR, prop.getProperty(DEFAULT_PLU_RESP_ITEM_MATCH_INDICATOR));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
    }
    
    if(modifiableMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : modifiableMap.entrySet())
      { 
       if(entry.getKey().equalsIgnoreCase(SEGMENT_LEVEL))
         segmentLevel = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESTRICTION_TYPE))
         restrictionType = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MINIMUM_AGE_VALUE))
         minimumAgeValue = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MAXIMUM_QUANTITY_VALUE))
         maximumQuantityValue = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(PLU_RESP_ITEM_MATCH_INDICATOR))
         itemMatchIndicator = entry.getValue();
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(segmentLevel.getBytes()))
    .append(this.byteResponse(restrictionType.getBytes()))
    .append(this.byteResponse(minimumAgeValue.getBytes()))
    .append(this.byteResponse(maximumQuantityValue.getBytes()))
    .append(this.byteResponse(itemMatchIndicator.getBytes()));
    }
  
    return sb;
  }
  
  private String byteResponse(byte[] buffer)
  {
    StringBuilder sb = new StringBuilder();

    for (byte b : buffer)
    {
      sb.append(String.format("%02x", b).toUpperCase());
      sb.append(" ");
    }
    return sb.toString();
  }
}
