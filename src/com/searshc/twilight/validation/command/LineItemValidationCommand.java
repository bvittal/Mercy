package com.searshc.twilight.validation.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.upas.sears.service.domain.*;

public class LineItemValidationCommand extends AbstractListValidationCommand
{
    protected List<LineItem> baseObject;
    private List<Adjustment> adjustments;
    private List<Adjustment> dcAdjustments;
    private List<ManualReduction> manualReductions;
    
    private List<Adjustment> baseAdjustments;
    private List<Adjustment> baseDcAdjustments;
    private List<ManualReduction> baseManualReductions;
	
  public LineItemValidationCommand setBaseValue(List<LineItem> obj)
  {
    this.baseObject = obj;
    return this;
  }
  
  public List<LineItem> getBaseValue()
  {
    return this.baseObject;
  }
  
  @Override
  public boolean passes(List<LineItem> list)
  {
	  Multimap<String, Boolean> results = ArrayListMultimap.create();
	  System.out.println("LINE ITEM LIST SIZE " + list.size());
	  System.out.println("LINE ITEM BASE LIST SIZE " + getBaseValue().size());
	  int base_index = 0;
    /** iterate through baseObject looking for non-null parameters to validate */
 if(this.getBaseValue() != null && list != null){

   for(LineItem item : list)
   {
       adjustments = item.getAdjustments();
       dcAdjustments = item.getDcAdjustments();
       manualReductions = item.getManualReductions();
       
         //for(LineItem baseItem : this.getBaseValue())
         //{
         for (int index = base_index; index <  this.getBaseValue().size(); index++) {
           LineItem baseItem = getBaseValue().get(index);
           baseAdjustments = baseItem.getAdjustments();
           baseDcAdjustments = baseItem.getDcAdjustments();
           baseManualReductions = baseItem.getManualReductions();

		 String lineItemId = item.getLineItemId();
		 String baseLineItemId = baseItem.getLineItemId();
			 if(StringUtils.isNotBlank(lineItemId) && StringUtils.isNotBlank(baseLineItemId)){
				 results.put("lineItemId", lineItemId.equals(baseLineItemId) ? Boolean.TRUE : Boolean.FALSE);
			 }

		 String regularPrice = item.getRegularPrice();
		 String baseRegularPrice = baseItem.getRegularPrice();
			 if(StringUtils.isNotBlank(regularPrice) && StringUtils.isNotBlank(baseRegularPrice)){
				 results.put("regularPrice", regularPrice.equals(baseRegularPrice) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String storeNumber = item.getStoreNumber();
		 String baseStoreNumber = baseItem.getStoreNumber();
			 if(StringUtils.isNotBlank(storeNumber) && StringUtils.isNotBlank(baseStoreNumber)){
				 results.put("storeNumber", storeNumber.equals(baseStoreNumber) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String storeName = item.getStoreName();
		 String baseStoreName = baseItem.getStoreName();
			 if(StringUtils.isNotBlank(storeName) && StringUtils.isNotBlank(baseStoreName)){
				 results.put("storeName", storeName.equals(baseStoreName) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String qualifyZeroPercentFinancing = item.getQualifyZeroPercentFinancing();
		 String baseQualifyZeroPercentFinancing = baseItem.getQualifyZeroPercentFinancing();
			 if(StringUtils.isNotBlank(qualifyZeroPercentFinancing) && StringUtils.isNotBlank(baseQualifyZeroPercentFinancing)){
				 results.put("qualifyZeroPercentFinancing", qualifyZeroPercentFinancing.equals(baseQualifyZeroPercentFinancing) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String adjustFlag = item.getAdjustFlag();
		 String baseAdjustFlag = baseItem.getAdjustFlag();
			 if(StringUtils.isNotBlank(adjustFlag) && StringUtils.isNotBlank(baseAdjustFlag)){
				 results.put("adjustFlag", adjustFlag.equals(baseAdjustFlag) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String partNumber = item.getPartNumber();
		 String basePartNumber = baseItem.getPartNumber();
			 if(StringUtils.isNotBlank(partNumber) && StringUtils.isNotBlank(basePartNumber)){
				 results.put("partNumber", partNumber.equals(basePartNumber) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String catentryId = item.getCatentryId();
		 String baseCatentryId = baseItem.getCatentryId();
			 if(StringUtils.isNotBlank(catentryId) && StringUtils.isNotBlank(baseCatentryId)){
				 results.put("catentryId", catentryId.equals(baseCatentryId) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String upcCode = item.getUpcCode();
		 String baseUpcCode = baseItem.getUpcCode();
			 if(StringUtils.isNotBlank(upcCode) && StringUtils.isNotBlank(baseUpcCode)){
				 results.put("upcCode", upcCode.equals(baseUpcCode) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String fulfillmentType = item.getFulfillmentType();
		 String baseFulfillmentType = baseItem.getFulfillmentType();
			 if(StringUtils.isNotBlank(fulfillmentType) && StringUtils.isNotBlank(baseFulfillmentType)){
				 results.put("fulfillmentType", fulfillmentType.equals(baseFulfillmentType) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String fulfillmentCost = item.getFulfillmentCost();
		 String baseFulfillmentCost = baseItem.getFulfillmentCost();
			 if(StringUtils.isNotBlank(fulfillmentCost) && StringUtils.isNotBlank(baseFulfillmentCost)){
				 results.put("fulfillmentCost", fulfillmentCost.equals(baseFulfillmentCost) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String manualPrice = item.getManualPrice();
		 String baseManualPrice = baseItem.getManualPrice();
			 if(StringUtils.isNotBlank(manualPrice) && StringUtils.isNotBlank(baseManualPrice)){
				 results.put("manualPrice", manualPrice.equals(baseManualPrice) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String pluPrice = item.getPluPrice();
		 String basePluPrice = baseItem.getPluPrice();
			 if(StringUtils.isNotBlank(pluPrice) && StringUtils.isNotBlank(basePluPrice)){
				 results.put("pluPrice", pluPrice.equals(basePluPrice) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String pluPriceType = item.getPluPriceType();
		 String basePluPriceType = baseItem.getPluPriceType();
			 if(StringUtils.isNotBlank(pluPriceType) && StringUtils.isNotBlank(basePluPriceType)){
				 results.put("pluPriceType", pluPriceType.equals(basePluPriceType) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String quantity = item.getQuantity();
		 String baseQuantity = baseItem.getQuantity();
			 if(StringUtils.isNotBlank(quantity) && StringUtils.isNotBlank(baseQuantity)){
				 results.put("quantity", quantity.equals(baseQuantity) ? Boolean.TRUE : Boolean.FALSE);
			 }
		 
		 String deliveryCharge = item.getDeliveryCharge();
		 String baseDeliveryCharge = baseItem.getDeliveryCharge();
			 if(StringUtils.isNotBlank(deliveryCharge) && StringUtils.isNotBlank(baseDeliveryCharge)){
				 results.put("deliveryCharge", deliveryCharge.equals(baseDeliveryCharge) ? Boolean.TRUE : Boolean.FALSE);
			 }

		//adjustments Iterator start
		if(adjustments != null && baseAdjustments != null){
			 Iterator<Adjustment> adjustmentsItr = adjustments.iterator();
			  while (adjustmentsItr.hasNext()) {
				  
				  String promoName = adjustmentsItr.next().getPromoName();
				  String basePromoName = baseAdjustments.iterator().next().getPromoName();
					  if(StringUtils.isNotBlank(promoName) && StringUtils.isNotBlank(basePromoName)){
						  results.put("promoName", promoName.equals(basePromoName) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String promoType = adjustmentsItr.next().getPromoType();
				  String basePromoType = baseAdjustments.iterator().next().getPromoType();
					  if(StringUtils.isNotBlank(promoType) && StringUtils.isNotBlank(basePromoType)){
						  results.put("promoType", promoType.equals(basePromoType) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String promoCode = adjustmentsItr.next().getPromoCode();
				  String basePromoCode = baseAdjustments.iterator().next().getPromoCode();
					  if(StringUtils.isNotBlank(promoCode) && StringUtils.isNotBlank(basePromoCode)){
						  results.put("promoCode", promoCode.equals(basePromoCode) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String nfxCode = adjustmentsItr.next().getNfxCode();
				  String baseNfxCode = baseAdjustments.iterator().next().getNfxCode();
					  if(StringUtils.isNotBlank(nfxCode) && StringUtils.isNotBlank(baseNfxCode)){
						  results.put("nfxCode", nfxCode.equals(baseNfxCode) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String csoCode = adjustmentsItr.next().getCsoCode();
				  String baseCsoCode = baseAdjustments.iterator().next().getCsoCode();
					  if(StringUtils.isNotBlank(csoCode) && StringUtils.isNotBlank(baseCsoCode)){
						  results.put("csoCode", csoCode.equals(baseCsoCode) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String couponOfferCode = adjustmentsItr.next().getCouponOfferCode();
				  String baseCouponOfferCode = baseAdjustments.iterator().next().getCouponOfferCode();
					  if(StringUtils.isNotBlank(couponOfferCode) && StringUtils.isNotBlank(baseCouponOfferCode)){
						  results.put("couponOfferCode", couponOfferCode.equals(baseCouponOfferCode) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String amount = adjustmentsItr.next().getAmount();
				  String baseAmount = baseAdjustments.iterator().next().getAmount();
					  if(StringUtils.isNotBlank(amount) && StringUtils.isNotBlank(baseAmount)){
						  results.put("amount", amount.equals(baseAmount) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String markdownCode = adjustmentsItr.next().getMarkdownCode();
				  String baseMarkdownCode = baseAdjustments.iterator().next().getMarkdownCode();
					  if(StringUtils.isNotBlank(markdownCode) && StringUtils.isNotBlank(baseMarkdownCode)){
						  results.put("markdownCode", markdownCode.equals(baseMarkdownCode) ? Boolean.TRUE : Boolean.FALSE);
					  }
					  
				  String shortDesc = adjustmentsItr.next().getShortDesc();
				  String baseShortDesc = baseAdjustments.iterator().next().getShortDesc();
					  if(StringUtils.isNotBlank(shortDesc) && StringUtils.isNotBlank(baseShortDesc)){
						  results.put("shortDesc", shortDesc.equals(baseShortDesc) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String longDesc = adjustmentsItr.next().getLongDesc();
				  String baseLongDesc = baseAdjustments.iterator().next().getLongDesc();
					  if(StringUtils.isNotBlank(longDesc) && StringUtils.isNotBlank(baseLongDesc)){
						  results.put("longDesc", longDesc.equals(baseLongDesc) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String exclusionText = adjustmentsItr.next().getExclusionText();
				  String baseExclusionText = baseAdjustments.iterator().next().getExclusionText();
					  if(StringUtils.isNotBlank(exclusionText) && StringUtils.isNotBlank(baseExclusionText)){
						  results.put("exclusionText", exclusionText.equals(baseExclusionText) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String endDate = adjustmentsItr.next().getEndDate();
				  String baseEndDate = baseAdjustments.iterator().next().getEndDate();
					  if(StringUtils.isNotBlank(endDate) && StringUtils.isNotBlank(baseEndDate)){
						  results.put("endDate", endDate.equals(baseEndDate) ? Boolean.TRUE : Boolean.FALSE);
					  }
				  
				  String adjustmentSource = adjustmentsItr.next().getAdjustmentSource();
				  String baseAdjustmentSource = baseAdjustments.iterator().next().getAdjustmentSource();
					  if(StringUtils.isNotBlank(adjustmentSource) && StringUtils.isNotBlank(baseAdjustmentSource)){
						  results.put("adjustmentSource", adjustmentSource.equals(baseAdjustmentSource) ? Boolean.TRUE : Boolean.FALSE);  
			      }
				}
			}//adjustments Itrator ends
			 
		 
			//dcadjustments Iterator start
			  if(dcAdjustments != null && baseDcAdjustments != null){
				 Iterator<Adjustment> dcAdjustmentsItr = dcAdjustments.iterator();
				 while (dcAdjustmentsItr.hasNext()) {
					 
					 String dcPromoName = dcAdjustmentsItr.next().getPromoName();
					 String baseDcPromoName = baseDcAdjustments.iterator().next().getPromoName();
						 if(StringUtils.isNotBlank(dcPromoName) && StringUtils.isNotBlank(baseDcPromoName)){
							 results.put("dcPromoName", dcPromoName.equals(baseDcPromoName) ? Boolean.TRUE : Boolean.FALSE);
						 }
					 
					 String dcPromoType = dcAdjustmentsItr.next().getPromoType();
					 String baseDcPromoType = baseDcAdjustments.iterator().next().getPromoType();
						 if(StringUtils.isNotBlank(dcPromoType) && StringUtils.isNotBlank(baseDcPromoType)){
							 results.put("dcPromoType", dcPromoType.equals(baseDcPromoType) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcPromoCode = dcAdjustmentsItr.next().getPromoCode();
					 String baseDcPromoCode = baseDcAdjustments.iterator().next().getPromoCode();
						 if(StringUtils.isNotBlank(dcPromoCode) && StringUtils.isNotBlank(baseDcPromoCode)){
							 results.put("dcPromoCode", dcPromoCode.equals(baseDcPromoCode) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcNfxCode = dcAdjustmentsItr.next().getNfxCode();
					 String baseDcNfxCode = baseDcAdjustments.iterator().next().getNfxCode();
						 if(StringUtils.isNotBlank(dcNfxCode) && StringUtils.isNotBlank(baseDcNfxCode)){
							 results.put("dcNfxCode", dcNfxCode.equals(baseDcNfxCode) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcCsoCode = dcAdjustmentsItr.next().getCsoCode();
					 String baseDcCsoCode = baseDcAdjustments.iterator().next().getCsoCode();
						 if(StringUtils.isNotBlank(dcCsoCode) && StringUtils.isNotBlank(baseDcCsoCode)){
							 results.put("dcCsoCode", dcCsoCode.equals(baseDcCsoCode) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcCouponOfferCode = dcAdjustmentsItr.next().getCouponOfferCode();
					 String baseDcCouponOfferCode = baseDcAdjustments.iterator().next().getCouponOfferCode();
						 if(StringUtils.isNotBlank(dcCouponOfferCode) && StringUtils.isNotBlank(baseDcCouponOfferCode)){
							 results.put("dcCouponOfferCode", dcCouponOfferCode.equals(baseDcCouponOfferCode) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcAmount = dcAdjustmentsItr.next().getAmount();
					 String baseDcAmount = baseDcAdjustments.iterator().next().getAmount();
						 if(StringUtils.isNotBlank(dcAmount) && StringUtils.isNotBlank(baseDcAmount)){
							 results.put("dcAmount", dcAmount.equals(baseDcAmount) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcMarkdownCode = dcAdjustmentsItr.next().getMarkdownCode();
					 String baseDcMarkdownCode = baseDcAdjustments.iterator().next().getMarkdownCode();
						 if(StringUtils.isNotBlank(dcMarkdownCode) && StringUtils.isNotBlank(baseDcMarkdownCode)){
							 results.put("dcMarkdownCode", dcMarkdownCode.equals(baseDcMarkdownCode) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcShortDesc = dcAdjustmentsItr.next().getShortDesc();
					 String baseDcShortDesc = baseDcAdjustments.iterator().next().getShortDesc();
						 if(StringUtils.isNotBlank(dcShortDesc) && StringUtils.isNotBlank(baseDcShortDesc)){
							 results.put("dcShortDesc", dcShortDesc.equals(baseDcShortDesc) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcLongDesc = dcAdjustmentsItr.next().getLongDesc();
					 String basedcLongDesc = baseDcAdjustments.iterator().next().getLongDesc();
						 if(StringUtils.isNotBlank(dcLongDesc) && StringUtils.isNotBlank(basedcLongDesc)){
							 results.put("dcLongDesc", dcLongDesc.equals(basedcLongDesc) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcExclusionText = dcAdjustmentsItr.next().getExclusionText();
					 String baseDcExclusionText = baseDcAdjustments.iterator().next().getExclusionText();
						 if(StringUtils.isNotBlank(dcExclusionText) && StringUtils.isNotBlank(baseDcExclusionText)){
							 results.put("dcExclusionText", dcExclusionText.equals(baseDcExclusionText) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcEndDate = dcAdjustmentsItr.next().getEndDate();
					 String baseDcEndDate = baseDcAdjustments.iterator().next().getEndDate();
						 if(StringUtils.isNotBlank(dcEndDate) && StringUtils.isNotBlank(baseDcEndDate)){
							 results.put("dcEndDate", dcEndDate.equals(baseDcEndDate) ? Boolean.TRUE : Boolean.FALSE);
						 }
						 
					 String dcAdjustmentSource = dcAdjustmentsItr.next().getAdjustmentSource();
					 String baseDcAdjustmentSource = baseDcAdjustments.iterator().next().getAdjustmentSource();
						 if(StringUtils.isNotBlank(dcAdjustmentSource) && StringUtils.isNotBlank(baseDcAdjustmentSource)){
							 results.put("dcAdjustmentSource", dcAdjustmentSource.equals(baseDcAdjustmentSource) ? Boolean.TRUE : Boolean.FALSE);
						 }
				 }	 
			 }//dcadjustments Iterator ends

				  //manualReductions Iterator start
					  if(manualReductions != null && baseManualReductions != null){
						 Iterator<ManualReduction> manualReductionsItr = manualReductions.iterator();
						 while (manualReductionsItr.hasNext()) {
							 
							 String reductionMethod = manualReductionsItr.next().getReductionMethod();
							 String baseReductionMethod = baseManualReductions.iterator().next().getReductionMethod();
								 if(StringUtils.isNotBlank(reductionMethod) && StringUtils.isNotBlank(baseReductionMethod)){
									 results.put("reductionMethod", reductionMethod.equals(baseReductionMethod) ? Boolean.TRUE : Boolean.FALSE);
								 }
							 
							 String reductionScope = manualReductionsItr.next().getReductionScope();
							 String baseReductionScope = baseManualReductions.iterator().next().getReductionScope();
								 if(StringUtils.isNotBlank(reductionScope) && StringUtils.isNotBlank(baseReductionScope)){
									 results.put("reductionScope", reductionScope.equals(baseReductionScope) ? Boolean.TRUE : Boolean.FALSE);
								 }
							 
							 String reductionSource = manualReductionsItr.next().getReductionSource();
							 String baseReductionSource = baseManualReductions.iterator().next().getReductionSource();
								 if(StringUtils.isNotBlank(reductionSource) && StringUtils.isNotBlank(baseReductionSource)){
									 results.put("reductionSource", reductionSource.equals(baseReductionSource) ? Boolean.TRUE : Boolean.FALSE);
								 }
							 
							 String reductionamount = manualReductionsItr.next().getAmount();
							 String baseReductionamount = baseManualReductions.iterator().next().getAmount();
								 if(StringUtils.isNotBlank(reductionamount) && StringUtils.isNotBlank(baseReductionamount)){
									 results.put("reductionamount", reductionamount.equals(baseReductionamount) ? Boolean.TRUE : Boolean.FALSE);
								 }
						  }
					  }//manualReductions Iterator ends
					  base_index++;
					  break;
         }
       }
     }
 
   for (Map.Entry<String, Boolean> entry : results.entries()){
		      System.out.println("Validating Line Item : " + entry.getKey() + " - Match Found : " + entry.getValue());
		      	if(entry.getValue() == Boolean.FALSE){
		      		return false;
		      	  }
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