package com.searshc.twilight.segments;

import java.util.HashMap;
import java.util.Map;

import com.starmount.ups.sears.ResponseSegment;
import com.starmount.ups.sears.SegmentField;

public class PluInquiryD8Segment extends ResponseSegment {

    public enum INDICATOR {
        HEX_STRING("D8");
        
        String hexString;
        INDICATOR(String s) {
            hexString = s;
        }
        public String getHexString() {
            return hexString;
        }
    }

    public enum SegmentD8Field implements SegmentField {

	    // @formatter:off
	  INDICATOR(                   FieldDataType.UNSIGNED_BYTE,   0, 1), 
		DIVISION_NUMBER(             FieldDataType.ASCII_STRING,    1, 3), 
		ITEM_NUMBER(                 FieldDataType.ASCII_STRING,    4, 5), 
		SKU(                         FieldDataType.ASCII_STRING,    9, 3), 
		INQUIRY_TYPE(  				       FieldDataType.ASCII_STRING,    9, 1),
		SEGMENT_LEVEL(               FieldDataType.ASCII_STRING,   4,  2); 
		// @formatter:on

        FieldDataType dataType;
        private int fieldLength;
        private int startPosition;

        private SegmentD8Field(FieldDataType type, int start, int length) {
            dataType = type;
            startPosition = start;
            fieldLength = length;
        }

        public int getStartPosition() {
            return startPosition;
        }

        public int getLength() {
            return fieldLength;
        }

        public FieldDataType getFieldDataType() {
            return dataType;
        }
    }
    
    String indicator;
    String pluDivisionNumber;
    String pluItemNumber;
    String pluSKU;
    String inquiryType;
    String segmentLevel;
    
    public PluInquiryD8Segment(byte[] byteArray) {
        super(byteArray);
        parse();
    }
    
    public void parse() {
    	indicator = String.format("%02X", getFieldBufferByte(SegmentD8Field.INDICATOR));
    	pluDivisionNumber = getFieldBufferString(SegmentD8Field.DIVISION_NUMBER);
    	pluItemNumber = getFieldBufferString(SegmentD8Field.ITEM_NUMBER);
    	pluSKU = getFieldBufferString(SegmentD8Field.SKU);
    	inquiryType = getFieldBufferString(SegmentD8Field.INQUIRY_TYPE);
    	segmentLevel = getFieldBufferString(SegmentD8Field.SEGMENT_LEVEL);
    }
    
    @Override
    public String getIndicator() {
        return indicator;
    }

    public String getPLUDivisionNumber() {
        return pluDivisionNumber;
    }

    public String getPLUItemNumber() {
        return pluItemNumber;
    }

    public String getPLUSKU() {
        return pluSKU;
    }

    public String getPLUInquiryTrpe() {
        return inquiryType;
    }

    public String getPLUSegmentLevel() {
        return segmentLevel;
    }
    
    @Override
    public Map<String, String> getPropertyMap() {
        Map<String, String> map = new HashMap<String, String>();

        map.put(SegmentD8Field.INDICATOR.toString(), indicator);
        map.put(SegmentD8Field.DIVISION_NUMBER.toString(), pluDivisionNumber);
        map.put(SegmentD8Field.ITEM_NUMBER.toString(), pluItemNumber);
        map.put(SegmentD8Field.SKU.toString(), pluSKU);
        map.put(SegmentD8Field.INQUIRY_TYPE.toString(), inquiryType);
        map.put(SegmentD8Field.SEGMENT_LEVEL.toString(), segmentLevel);
        return map;
    }
}
