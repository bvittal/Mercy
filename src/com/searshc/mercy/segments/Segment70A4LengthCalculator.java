package com.searshc.mercy.segments;

import java.nio.ByteBuffer;
import com.starmount.ups.sears.AbstractLengthCalculator;

public class Segment70A4LengthCalculator extends AbstractLengthCalculator {

	private final int LENGTH = 27;

	    public Segment70A4LengthCalculator(String indicator, ByteBuffer buffer) {
	        super(indicator, buffer);
	    }

	    @Override
	    public int getLength() {
	        return LENGTH;
	    }
}
