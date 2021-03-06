package com.searshc.mercy.segments;

import java.nio.ByteBuffer;
import com.starmount.ups.sears.AbstractLengthCalculator;

public class Segment72A2LengthCalculator extends AbstractLengthCalculator {

	private final int LENGTH = 27;

	    public Segment72A2LengthCalculator(String indicator, ByteBuffer buffer) {
	        super(indicator, buffer);
	    }

	    @Override
	    public int getLength() {
	        return LENGTH;
	    }
}
