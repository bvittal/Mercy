package com.searshc.mercy.validation.command;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.upas.sears.service.domain.*;

public class LineItemValidationCommand extends AbstractListValidationCommand
{
    protected List<LineItem> baseObject;
    private List<Adjustment> adjustments;
    private List<Adjustment> dcAdjustments;
    //private List<ManualReduction> manualReductions;
    
    private List<Adjustment> baseAdjustments;
    private List<Adjustment> baseDcAdjustments;
    //private List<ManualReduction> baseManualReductions;
	
  public LineItemValidationCommand setBaseValue(List<LineItem> obj)
  {
    this.baseObject = obj;
    return this;
  }
  
  public List<LineItem> getBaseValue()
  {
    return this.baseObject;
  }
  
  @SuppressWarnings("unused")
  @Override
  public boolean passes(List<LineItem> list)
  {
    int base_index = 0;
    
	  Multimap<String, Boolean> results = ArrayListMultimap.create();	  
	   
    /** iterate through baseObject looking for non-null parameters to validate */
  if(this.getBaseValue() != null && list != null){
   for(LineItem item : list)
   {
       adjustments = item.getAdjustments();
       dcAdjustments = item.getDcAdjustments();
       //manualReductions = item.getManualReductions();
       
         for (int index = base_index; index <  this.getBaseValue().size(); index++)
         {
           LineItem baseItem = getBaseValue().get(index);
           baseAdjustments = baseItem.getAdjustments();
           baseDcAdjustments = baseItem.getDcAdjustments();
           //baseManualReductions = baseItem.getManualReductions();

		 String lineItemId = item.getLineItemId();
		 String baseLineItemId = baseItem.getLineItemId();
			 if(StringUtils.isNotBlank(lineItemId) && StringUtils.isBlank(baseLineItemId))
				 results.put("lineItemId", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(lineItemId) && StringUtils.isNotBlank(baseLineItemId))
			     results.put("lineItemId", lineItemId.trim().equalsIgnoreCase(baseLineItemId.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(lineItemId) && StringUtils.isBlank(baseLineItemId))
			   results.put("lineItemId", Boolean.FALSE);
			 
		 String regularPrice = item.getRegularPrice();
		 String baseRegularPrice = baseItem.getRegularPrice();
			 if(StringUtils.isNotBlank(regularPrice) && StringUtils.isBlank(baseRegularPrice))
				 results.put("regularPrice", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(regularPrice) && StringUtils.isNotBlank(baseRegularPrice))
			   results.put("regularPrice", regularPrice.trim().equalsIgnoreCase(baseRegularPrice.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(regularPrice) && StringUtils.isNotBlank(baseRegularPrice))
			   results.put("regularPrice", Boolean.FALSE);
			 
		 String storeNumber = item.getStoreNumber();
		 String baseStoreNumber = baseItem.getStoreNumber();
			 if(StringUtils.isNotBlank(storeNumber) && StringUtils.isBlank(baseStoreNumber))
				 results.put("storeNumber", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(storeNumber) && StringUtils.isNotBlank(baseStoreNumber))
			   results.put("storeNumber", storeNumber.trim().equalsIgnoreCase(baseStoreNumber.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(storeNumber) && StringUtils.isNotBlank(baseStoreNumber))
			   results.put("storeNumber", Boolean.FALSE);
			 
		 String storeName = item.getStoreName();
		 String baseStoreName = baseItem.getStoreName();
			 if(StringUtils.isNotBlank(storeName) && StringUtils.isNotBlank(baseStoreName)){
				 results.put("storeName", storeName.trim().equalsIgnoreCase(baseStoreName.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String qualifyZeroPercentFinancing = item.getQualifyZeroPercentFinancing();
		 String baseQualifyZeroPercentFinancing = baseItem.getQualifyZeroPercentFinancing();
			 if(StringUtils.isNotBlank(qualifyZeroPercentFinancing) && StringUtils.isBlank(baseQualifyZeroPercentFinancing))
				 results.put("qualifyZeroPercentFinancing", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(qualifyZeroPercentFinancing) && StringUtils.isNotBlank(baseQualifyZeroPercentFinancing))
			   results.put("qualifyZeroPercentFinancing", qualifyZeroPercentFinancing.trim().equalsIgnoreCase(baseQualifyZeroPercentFinancing.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(qualifyZeroPercentFinancing) && StringUtils.isNotBlank(baseQualifyZeroPercentFinancing))
			   results.put("qualifyZeroPercentFinancing", Boolean.FALSE);
			 
		 String adjustFlag = item.getAdjustFlag();
		 String baseAdjustFlag = baseItem.getAdjustFlag();
			 if(StringUtils.isNotBlank(adjustFlag) && StringUtils.isBlank(baseAdjustFlag))
				 results.put("adjustFlag", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(adjustFlag) && StringUtils.isNotBlank(baseAdjustFlag))
			   results.put("adjustFlag", adjustFlag.trim().equalsIgnoreCase(baseAdjustFlag.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(adjustFlag) && StringUtils.isNotBlank(baseAdjustFlag))
			   results.put("adjustFlag", Boolean.FALSE);
		 
		 String partNumber = item.getPartNumber();
		 String basePartNumber = baseItem.getPartNumber();
			 if(StringUtils.isNotBlank(partNumber) && StringUtils.isBlank(basePartNumber))
				 results.put("partNumber", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(partNumber) && StringUtils.isNotBlank(basePartNumber))
			   results.put("partNumber", partNumber.trim().equalsIgnoreCase(basePartNumber.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(partNumber) && StringUtils.isNotBlank(basePartNumber))
			   results.put("partNumber", Boolean.FALSE);
			 
		 String catentryId = item.getCatentryId();
		 String baseCatentryId = baseItem.getCatentryId();
			 if(StringUtils.isNotBlank(catentryId) && StringUtils.isBlank(baseCatentryId))
				 results.put("catentryId", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(catentryId) && StringUtils.isNotBlank(baseCatentryId))
			   results.put("catentryId", catentryId.trim().equalsIgnoreCase(baseCatentryId.trim()) ? Boolean.TRUE : Boolean.FALSE); 
			 else if(StringUtils.isBlank(catentryId) && StringUtils.isNotBlank(baseCatentryId))
			   results.put("catentryId", Boolean.FALSE);
			 
		 String upcCode = item.getUpcCode();
		 String baseUpcCode = baseItem.getUpcCode();
			 if(StringUtils.isNotBlank(upcCode) && StringUtils.isBlank(baseUpcCode))
				 results.put("upcCode", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(upcCode) && StringUtils.isNotBlank(baseUpcCode))
			   results.put("upcCode", upcCode.trim().equalsIgnoreCase(baseUpcCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(upcCode) && StringUtils.isNotBlank(baseUpcCode))
			   results.put("upcCode", Boolean.FALSE);
			 
		 String fulfillmentType = item.getFulfillmentType();
		 String baseFulfillmentType = baseItem.getFulfillmentType();
			 if(StringUtils.isNotBlank(fulfillmentType) && StringUtils.isBlank(baseFulfillmentType))
				 results.put("fulfillmentType", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(fulfillmentType) && StringUtils.isNotBlank(baseFulfillmentType))
			   results.put("fulfillmentType", fulfillmentType.trim().equalsIgnoreCase(baseFulfillmentType.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(fulfillmentType) && StringUtils.isNotBlank(baseFulfillmentType))
			   results.put("fulfillmentType", Boolean.FALSE);
			 
		 String fulfillmentCost = item.getFulfillmentCost();
		 String baseFulfillmentCost = baseItem.getFulfillmentCost();
			 if(StringUtils.isNotBlank(fulfillmentCost) && StringUtils.isBlank(baseFulfillmentCost))
				 results.put("fulfillmentCost", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(fulfillmentCost) && StringUtils.isNotBlank(baseFulfillmentCost))
			   results.put("fulfillmentCost", fulfillmentCost.trim().equalsIgnoreCase(baseFulfillmentCost.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(fulfillmentCost) && StringUtils.isNotBlank(baseFulfillmentCost))
			   results.put("fulfillmentCost", Boolean.FALSE);
			 
		 String manualPrice = item.getManualPrice();
		 String baseManualPrice = baseItem.getManualPrice();
			 if(StringUtils.isNotBlank(manualPrice) && StringUtils.isBlank(baseManualPrice))
				 results.put("manualPrice", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(manualPrice) && StringUtils.isNotBlank(baseManualPrice))
			   results.put("manualPrice", manualPrice.trim().equalsIgnoreCase(baseManualPrice.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(manualPrice) && StringUtils.isNotBlank(baseManualPrice))
			   results.put("manualPrice", Boolean.FALSE);
		 
		 String pluPrice = item.getPluPrice();
		 String basePluPrice = baseItem.getPluPrice();
			 if(StringUtils.isNotBlank(pluPrice) && StringUtils.isBlank(basePluPrice))
				 results.put("pluPrice", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(pluPrice) && StringUtils.isNotBlank(basePluPrice))
			   results.put("pluPrice", pluPrice.trim().equalsIgnoreCase(basePluPrice.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(pluPrice) && StringUtils.isNotBlank(basePluPrice))
			   results.put("pluPrice", Boolean.FALSE);
		 
		 String pluPriceType = item.getPluPriceType();
		 String basePluPriceType = baseItem.getPluPriceType();
			 if(StringUtils.isNotBlank(pluPriceType) && StringUtils.isBlank(basePluPriceType))
				 results.put("pluPriceType", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(pluPriceType) && StringUtils.isNotBlank(basePluPriceType))
			   results.put("pluPriceType", pluPriceType.trim().equalsIgnoreCase(basePluPriceType.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(pluPriceType) && StringUtils.isNotBlank(basePluPriceType))
			   results.put("pluPriceType", Boolean.FALSE);
		 
		 String quantity = item.getQuantity();
		 String baseQuantity = baseItem.getQuantity();
			 if(StringUtils.isNotBlank(quantity) && StringUtils.isBlank(baseQuantity))
				 results.put("quantity", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(quantity) && StringUtils.isNotBlank(baseQuantity))
			   results.put("quantity", quantity.trim().equalsIgnoreCase(baseQuantity.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(quantity) && StringUtils.isNotBlank(baseQuantity))
			   results.put("quantity", Boolean.FALSE);
		 
		 String deliveryCharge = item.getDeliveryCharge();
		 String baseDeliveryCharge = baseItem.getDeliveryCharge();
			 if(StringUtils.isNotBlank(deliveryCharge) && StringUtils.isBlank(baseDeliveryCharge))
				 results.put("deliveryCharge", Boolean.FALSE);
			 else if(StringUtils.isNotBlank(deliveryCharge) && StringUtils.isNotBlank(baseDeliveryCharge))
			   results.put("deliveryCharge", deliveryCharge.trim().equalsIgnoreCase(baseDeliveryCharge.trim()) ? Boolean.TRUE : Boolean.FALSE);
			 else if(StringUtils.isBlank(deliveryCharge) && StringUtils.isNotBlank(baseDeliveryCharge))
			   results.put("deliveryCharge", Boolean.FALSE);

		//adjustments Iterator start
		if(adjustments != null && baseAdjustments != null)
		{  
				for(Adjustment adjustment : adjustments)
				{
				  for(Adjustment baseAdjustment : baseAdjustments)
				  {
				  /**  
				  String promoName = adjustment.getPromoName();
				  String basePromoName = baseAdjustment.getPromoName();
					  if(StringUtils.isNotBlank(promoName) && StringUtils.isNotBlank(basePromoName))
					  {
						  results.put("promoName", promoName.replace(" ", "").equalsIgnoreCase(basePromoName.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String promoType = adjustment.getPromoType();
				  String basePromoType = baseAdjustment.getPromoType();
					  if(StringUtils.isNotBlank(promoType) && StringUtils.isNotBlank(basePromoType))
					  {
						  results.put("promoType", promoType.trim().equalsIgnoreCase(basePromoType.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  */
				  String promoCode = adjustment.getPromoCode();
				  String basePromoCode = baseAdjustment.getPromoCode();
					  if(StringUtils.isNotBlank(promoCode) && StringUtils.isBlank(basePromoCode))
					    results.put("promoCode",Boolean.FALSE);
					  else if(StringUtils.isNotBlank(promoCode) && StringUtils.isNotBlank(basePromoCode))
					    results.put("promoCode", promoCode.trim().equalsIgnoreCase(basePromoCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  else if(StringUtils.isBlank(promoCode) && StringUtils.isNotBlank(basePromoCode))
					    results.put("promoCode",Boolean.FALSE);
					  
				  String nfxCode = adjustment.getNfxCode();
				  String baseNfxCode = baseAdjustment.getNfxCode();
					  if(StringUtils.isNotBlank(nfxCode) && StringUtils.isBlank(baseNfxCode))
					    results.put("nfxCode", Boolean.FALSE);
					  else if(StringUtils.isNotBlank(nfxCode) && StringUtils.isNotBlank(baseNfxCode))
					    results.put("nfxCode", nfxCode.trim().equalsIgnoreCase(baseNfxCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  else if(StringUtils.isBlank(nfxCode) && StringUtils.isNotBlank(baseNfxCode))
					    results.put("nfxCode", Boolean.FALSE);
				  
				  String csoCode = adjustment.getCsoCode();
				  String baseCsoCode = baseAdjustment.getCsoCode();
					  if(StringUtils.isNotBlank(csoCode) && StringUtils.isBlank(baseCsoCode))
					    results.put("csoCode", Boolean.FALSE);
					  else if(StringUtils.isNotBlank(csoCode) && StringUtils.isNotBlank(baseCsoCode))
						  results.put("csoCode", csoCode.trim().equalsIgnoreCase(baseCsoCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  else if(StringUtils.isBlank(csoCode) && StringUtils.isNotBlank(baseCsoCode))
					    results.put("csoCode", Boolean.FALSE);
				  
				  String couponOfferCode = adjustment.getCouponOfferCode();
				  String baseCouponOfferCode = baseAdjustment.getCouponOfferCode();
					  if(StringUtils.isNotBlank(couponOfferCode) && StringUtils.isNotBlank(baseCouponOfferCode))
					    results.put("couponOfferCode", Boolean.FALSE);
					  else if(StringUtils.isNotBlank(couponOfferCode) && StringUtils.isNotBlank(baseCouponOfferCode))
						  results.put("couponOfferCode", couponOfferCode.trim().equalsIgnoreCase(baseCouponOfferCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  else if(StringUtils.isBlank(couponOfferCode) && StringUtils.isNotBlank(baseCouponOfferCode))
					  results.put("couponOfferCode", Boolean.FALSE);
					  
				  String amount = adjustment.getAmount();
				  String baseAmount = baseAdjustment.getAmount();
					  if(StringUtils.isNotBlank(amount) && StringUtils.isBlank(baseAmount))
						  results.put("amount", Boolean.FALSE);
					  else if(StringUtils.isNotBlank(amount) && StringUtils.isNotBlank(baseAmount))
					    results.put("amount", amount.trim().equalsIgnoreCase(baseAmount.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  else if(StringUtils.isBlank(amount) && StringUtils.isNotBlank(baseAmount))
					    results.put("amount", Boolean.FALSE);
					  
				  String markdownCode = adjustment.getMarkdownCode();
				  String baseMarkdownCode = baseAdjustment.getMarkdownCode();
					  if(StringUtils.isNotBlank(markdownCode) && StringUtils.isBlank(baseMarkdownCode))
						  results.put("markdownCode", Boolean.FALSE);
					  else if(StringUtils.isNotBlank(markdownCode) && StringUtils.isNotBlank(baseMarkdownCode))
					    results.put("markdownCode", markdownCode.equalsIgnoreCase(baseMarkdownCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  else if(StringUtils.isBlank(markdownCode) && StringUtils.isNotBlank(baseMarkdownCode))
					    results.put("markdownCode", Boolean.FALSE);
					/**  
				  String shortDesc = adjustment.getShortDesc();
				  String baseShortDesc = baseAdjustment.getShortDesc();
					  if(StringUtils.isNotBlank(shortDesc) && StringUtils.isNotBlank(baseShortDesc))
					  {
						  results.put("shortDesc", shortDesc.replace(" ", "").equalsIgnoreCase(baseShortDesc.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String longDesc = adjustment.getLongDesc();
				  String baseLongDesc = baseAdjustment.getLongDesc();
					  if(StringUtils.isNotBlank(longDesc) && StringUtils.isNotBlank(baseLongDesc))
					  {
						  results.put("longDesc", longDesc.replace(" ", "").equalsIgnoreCase(baseLongDesc.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String exclusionText = adjustment.getExclusionText();
				  String baseExclusionText = baseAdjustment.getExclusionText();
					  if(StringUtils.isNotBlank(exclusionText) && StringUtils.isNotBlank(baseExclusionText))
					  {
						  results.put("exclusionText", exclusionText.replace(" ", "").equalsIgnoreCase(baseExclusionText.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  */
				  String endDate = adjustment.getEndDate();
				  String baseEndDate = baseAdjustment.getEndDate();
					  if(StringUtils.isNotBlank(endDate) && StringUtils.isBlank(baseEndDate))
						  results.put("endDate", Boolean.FALSE);
					  else if(StringUtils.isNotBlank(endDate) && StringUtils.isNotBlank(baseEndDate))
					    results.put("endDate", endDate.trim().equalsIgnoreCase(baseEndDate.trim()) ? Boolean.TRUE : Boolean.FALSE);
					  else if(StringUtils.isBlank(endDate) && StringUtils.isNotBlank(baseEndDate))
					    results.put("endDate", Boolean.FALSE);
				  
				  String adjustmentSource = adjustment.getAdjustmentSource();
				  String baseAdjustmentSource = baseAdjustment.getAdjustmentSource();
					  if(StringUtils.isNotBlank(adjustmentSource) && StringUtils.isBlank(baseAdjustmentSource))
						  results.put("adjustmentSource", Boolean.FALSE);  
					  else if(StringUtils.isNotBlank(adjustmentSource) && StringUtils.isNotBlank(baseAdjustmentSource))
					    results.put("adjustmentSource", adjustmentSource.trim().equalsIgnoreCase(baseAdjustmentSource.trim()) ? Boolean.TRUE : Boolean.FALSE);  
					  else if(StringUtils.isBlank(adjustmentSource) && StringUtils.isNotBlank(baseAdjustmentSource))
					    results.put("adjustmentSource", Boolean.FALSE);  
					  
					  break;
				   }
				}
			}
			 
		 
			//dcadjustments Iterator start
			  if(dcAdjustments != null && baseDcAdjustments != null)
			  {
			    for(Adjustment dcAdjustment : dcAdjustments)
	        {
	          for(Adjustment basedcAdjustment : baseDcAdjustments)
	          { 
	          /**  
					 String dcPromoName = dcAdjustment.getPromoName();
					 String baseDcPromoName = basedcAdjustment.getPromoName();
						 if(StringUtils.isNotBlank(dcPromoName) && StringUtils.isNotBlank(baseDcPromoName))
						 {
							 results.put("dcPromoName", dcPromoName.replace(" ", "").equalsIgnoreCase(baseDcPromoName.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 }
					 
					 String dcPromoType = dcAdjustment.getPromoType();
					 String baseDcPromoType = basedcAdjustment.getPromoType();
						 if(StringUtils.isNotBlank(dcPromoType) && StringUtils.isNotBlank(baseDcPromoType))
						 {
							 results.put("dcPromoType", dcPromoType.trim().equalsIgnoreCase(baseDcPromoType.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 */
					 String dcPromoCode = dcAdjustment.getPromoCode();
					 String baseDcPromoCode = basedcAdjustment.getPromoCode();
						 if(StringUtils.isNotBlank(dcPromoCode) && StringUtils.isBlank(baseDcPromoCode))
							 results.put("dcPromoCode", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcPromoCode) && StringUtils.isNotBlank(baseDcPromoCode))
						   results.put("dcPromoCode", dcPromoCode.trim().equalsIgnoreCase(baseDcPromoCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcPromoCode) && StringUtils.isNotBlank(baseDcPromoCode))
						   results.put("dcPromoCode", Boolean.FALSE);
						 
					 String dcNfxCode = dcAdjustment.getNfxCode();
					 String baseDcNfxCode = basedcAdjustment.getNfxCode();
						 if(StringUtils.isNotBlank(dcNfxCode) && StringUtils.isBlank(baseDcNfxCode))
							 results.put("dcNfxCode", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcNfxCode) && StringUtils.isNotBlank(baseDcNfxCode))
						   results.put("dcNfxCode", dcNfxCode.trim().equalsIgnoreCase(baseDcNfxCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcNfxCode) && StringUtils.isNotBlank(baseDcNfxCode))
						   results.put("dcNfxCode", Boolean.FALSE);
						 
					 String dcCsoCode = dcAdjustment.getCsoCode();
					 String baseDcCsoCode = basedcAdjustment.getCsoCode();
						 if(StringUtils.isNotBlank(dcCsoCode) && StringUtils.isBlank(baseDcCsoCode))
							 results.put("dcCsoCode", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcCsoCode) && StringUtils.isNotBlank(baseDcCsoCode))
						   results.put("dcCsoCode", dcCsoCode.trim().equalsIgnoreCase(baseDcCsoCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcCsoCode) && StringUtils.isNotBlank(baseDcCsoCode))
						   results.put("dcCsoCode", Boolean.FALSE);
						 
					 String dcCouponOfferCode = dcAdjustment.getCouponOfferCode();
					 String baseDcCouponOfferCode = basedcAdjustment.getCouponOfferCode();
						 if(StringUtils.isNotBlank(dcCouponOfferCode) && StringUtils.isBlank(baseDcCouponOfferCode))
							 results.put("dcCouponOfferCode", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcCouponOfferCode) && StringUtils.isNotBlank(baseDcCouponOfferCode))
						   results.put("dcCouponOfferCode", dcCouponOfferCode.trim().equalsIgnoreCase(baseDcCouponOfferCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcCouponOfferCode) && StringUtils.isNotBlank(baseDcCouponOfferCode))
						   results.put("dcCouponOfferCode", Boolean.FALSE);
						 
					 String dcAmount = dcAdjustment.getAmount().trim();
					 String baseDcAmount = basedcAdjustment.getAmount();
						 if(StringUtils.isNotBlank(dcAmount) && StringUtils.isBlank(baseDcAmount))
							 results.put("dcAmount", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcAmount) && StringUtils.isNotBlank(baseDcAmount))
						   results.put("dcAmount", dcAmount.equalsIgnoreCase(baseDcAmount.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcAmount) && StringUtils.isNotBlank(baseDcAmount))
						   results.put("dcAmount", Boolean.FALSE);
						 
					 String dcMarkdownCode = dcAdjustment.getMarkdownCode();
					 String baseDcMarkdownCode = basedcAdjustment.getMarkdownCode();
						 if(StringUtils.isNotBlank(dcMarkdownCode) && StringUtils.isBlank(baseDcMarkdownCode))
							 results.put("dcMarkdownCode", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcMarkdownCode) && StringUtils.isNotBlank(baseDcMarkdownCode))
						   results.put("dcMarkdownCode", dcMarkdownCode.trim().equalsIgnoreCase(baseDcMarkdownCode.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcMarkdownCode) && StringUtils.isNotBlank(baseDcMarkdownCode))
						   results.put("dcMarkdownCode", Boolean.FALSE);
					
					 /**	 
					 String dcShortDesc = dcAdjustment.getShortDesc();
					 String baseDcShortDesc = basedcAdjustment.getShortDesc();
						 if(StringUtils.isNotBlank(dcShortDesc) && StringUtils.isBlank(baseDcShortDesc))
							 results.put("dcShortDesc", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcShortDesc) && StringUtils.isNotBlank(baseDcShortDesc))
						   results.put("dcShortDesc", dcShortDesc.replace(" ", "").equalsIgnoreCase(baseDcShortDesc.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcShortDesc) && StringUtils.isNotBlank(baseDcShortDesc))
						   results.put("dcShortDesc", Boolean.FALSE);
						 
					 String dcLongDesc = dcAdjustment.getLongDesc();
					 String basedcLongDesc = basedcAdjustment.getLongDesc();
						 if(StringUtils.isNotBlank(dcLongDesc) && StringUtils.isNotBlank(basedcLongDesc))
						 {
							 results.put("dcLongDesc", dcLongDesc.replace(" ", "").equalsIgnoreCase(basedcLongDesc.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcExclusionText = dcAdjustment.getExclusionText();
					 String baseDcExclusionText = basedcAdjustment.getExclusionText();
						 if(StringUtils.isNotBlank(dcExclusionText) && StringUtils.isNotBlank(baseDcExclusionText))
						 {
							 results.put("dcExclusionText", dcExclusionText.replace(" ", "").equalsIgnoreCase(baseDcExclusionText) ? Boolean.TRUE : Boolean.FALSE);
						 }
					 */ 
					 String dcEndDate = dcAdjustment.getEndDate();
					 String baseDcEndDate = basedcAdjustment.getEndDate();
						 if(StringUtils.isNotBlank(dcEndDate) && StringUtils.isBlank(baseDcEndDate))
							 results.put("dcEndDate", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcEndDate) && StringUtils.isNotBlank(baseDcEndDate))
						   results.put("dcEndDate", dcEndDate.trim().equalsIgnoreCase(baseDcEndDate.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcEndDate) && StringUtils.isNotBlank(baseDcEndDate))
						   results.put("dcEndDate", Boolean.FALSE);
						 
					 String dcAdjustmentSource = dcAdjustment.getAdjustmentSource();
					 String baseDcAdjustmentSource = basedcAdjustment.getAdjustmentSource();
						 if(StringUtils.isNotBlank(dcAdjustmentSource) && StringUtils.isBlank(baseDcAdjustmentSource))
							 results.put("dcAdjustmentSource", Boolean.FALSE);
						 else if(StringUtils.isNotBlank(dcAdjustmentSource) && StringUtils.isNotBlank(baseDcAdjustmentSource))
						   results.put("dcAdjustmentSource", dcAdjustmentSource.trim().equalsIgnoreCase(baseDcAdjustmentSource.trim()) ? Boolean.TRUE : Boolean.FALSE);
						 else if(StringUtils.isBlank(dcAdjustmentSource) && StringUtils.isNotBlank(baseDcAdjustmentSource))
						   results.put("dcAdjustmentSource", Boolean.FALSE);
						   
						 break;
	          }	 
	        }
			  }
			  
			  /**
				  //manualReductions Iterator start
					  if(manualReductions != null && baseManualReductions != null){
						 Iterator<ManualReduction> manualReductionsItr = manualReductions.iterator();
						 while (manualReductionsItr.hasNext()) {
							 
							 String reductionMethod = manualReductionsItr.next().getReductionMethod();
							 String baseReductionMethod = baseManualReductions.iterator().next().getReductionMethod();
								 if(StringUtils.isNotBlank(reductionMethod) && StringUtils.isNotBlank(baseReductionMethod)){
									 results.put("reductionMethod", reductionMethod.equalsIgnoreCase(baseReductionMethod) ? Boolean.TRUE : Boolean.FALSE);
								 }
							 
							 String reductionScope = manualReductionsItr.next().getReductionScope();
							 String baseReductionScope = baseManualReductions.iterator().next().getReductionScope();
								 if(StringUtils.isNotBlank(reductionScope) && StringUtils.isNotBlank(baseReductionScope)){
									 results.put("reductionScope", reductionScope.equalsIgnoreCase(baseReductionScope) ? Boolean.TRUE : Boolean.FALSE);
								 }
							 
							 String reductionSource = manualReductionsItr.next().getReductionSource();
							 String baseReductionSource = baseManualReductions.iterator().next().getReductionSource();
								 if(StringUtils.isNotBlank(reductionSource) && StringUtils.isNotBlank(baseReductionSource)){
									 results.put("reductionSource", reductionSource.equalsIgnoreCase(baseReductionSource) ? Boolean.TRUE : Boolean.FALSE);
								 }
							 
							 String reductionamount = manualReductionsItr.next().getAmount();
							 String baseReductionamount = baseManualReductions.iterator().next().getAmount();
								 if(StringUtils.isNotBlank(reductionamount) && StringUtils.isNotBlank(baseReductionamount)){
									 results.put("reductionamount", reductionamount.equalsIgnoreCase(baseReductionamount) ? Boolean.TRUE : Boolean.FALSE);
								 }
						  }
					  }//manualReductions Iterator ends*/
					  base_index++;
					  break;
         }
       }
     }
 
   for (Map.Entry<String, Boolean> entry : results.entries())
   {
		      System.out.println("Validating : " + entry.getKey() + " - Match Found : " + entry.getValue() + "\n");
	 }
   
   if(results.containsValue(Boolean.FALSE))
   {
     return Boolean.FALSE;
   }
   return true;
  }

  @Override
  protected String getParameterName()
  {
    return "Line items";
  }

  @Override
  public boolean passes(Order order)
  {
	 return this.passes(order.getLineItems());
  }
}