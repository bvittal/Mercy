package com.searshc.mercy.validation;

import com.starmount.ups.sears.responses.segment2AB7.ResponseSegment2AB7;

public interface SegmentValidator
{
  public String getErrorMessageString();
  public boolean isValid(ResponseSegment2AB7 responseSeg2AB7);
}
