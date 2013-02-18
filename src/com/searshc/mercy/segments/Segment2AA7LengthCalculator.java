package com.searshc.mercy.segments;

import java.nio.ByteBuffer;
import com.starmount.ups.sears.AbstractLengthCalculator;

public class Segment2AA7LengthCalculator extends AbstractLengthCalculator {

	private final int LENGTH = 19;

	    public Segment2AA7LengthCalculator(String indicator, ByteBuffer buffer) {
	        super(indicator, buffer);
	    }

	    @Override
	    public int getLength() {
	        return LENGTH;
	    }
}
