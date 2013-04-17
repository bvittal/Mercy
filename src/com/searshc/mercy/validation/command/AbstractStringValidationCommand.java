package com.searshc.mercy.validation.command;

import java.util.List;

import com.upas.sears.service.domain.Allocation;
import com.upas.sears.service.domain.CoupounCode;
import com.upas.sears.service.domain.Incompatibility;
import com.upas.sears.service.domain.Rebate;

public abstract class AbstractStringValidationCommand extends AbstractValidationCommand
{
  protected String baseValue;
  protected List<CoupounCode> baseValueforUnusedCoupons;
  protected Incompatibility[] baseValueForIncompatibilities;
  protected List<Rebate> baseValueForRebates;
  protected List<Allocation> baseValueForAllocations;
  
  
  public AbstractStringValidationCommand setBaseValue(String str)
  {
    this.baseValue = str;
    return this;
  }
  
  public String getBaseValue()
  {
    return this.baseValue;
  }
  
  public List<CoupounCode> getBaseValueforUnusedCoupons()
  {
    return baseValueforUnusedCoupons;
  }

  public AbstractStringValidationCommand setBaseValueforUnusedCoupons(List<CoupounCode> baseValueforUnusedCoupons)
  {
    this.baseValueforUnusedCoupons = baseValueforUnusedCoupons;
    return this;
  }

  public AbstractStringValidationCommand setBaseValueForIncompatibilities(Incompatibility[] incompatibilities)
  {
    this.baseValueForIncompatibilities = incompatibilities;
    return this;
  }
}