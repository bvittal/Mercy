package com.searshc.twilight.segments;

import java.nio.ByteBuffer;
import com.starmount.ups.sears.AbstractLengthCalculator;

public class Segment12A1LengthCalculator extends AbstractLengthCalculator {

	private final int LENGTH = 27;

	    public Segment12A1LengthCalculator(String indicator, ByteBuffer buffer) {
	        super(indicator, buffer);
	    }

	    @Override
	    public int getLength() {
	        return LENGTH;
	    }
}
