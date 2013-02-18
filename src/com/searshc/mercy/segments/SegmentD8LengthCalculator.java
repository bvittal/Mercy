package com.searshc.mercy.segments;

import java.nio.ByteBuffer;
import com.starmount.ups.sears.AbstractLengthCalculator;

public class SegmentD8LengthCalculator extends AbstractLengthCalculator {

  private final int LENGTH = 15;

      public SegmentD8LengthCalculator(String indicator, ByteBuffer buffer) {
          super(indicator, buffer);
      }

      @Override
      public int getLength() {
          return LENGTH;
      }
}
