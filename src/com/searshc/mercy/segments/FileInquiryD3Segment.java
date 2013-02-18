package com.searshc.mercy.segments;

import java.util.HashMap;
import java.util.Map;

import com.starmount.ups.sears.ResponseSegment;
import com.starmount.ups.sears.SegmentField;

public class FileInquiryD3Segment extends ResponseSegment {

    public enum INDICATOR {
        HEX_STRING("D3");
        
        String hexString;
        INDICATOR(String s) {
            hexString = s;
        }
        public String getHexString() {
            return hexString;
        }
    }

    public enum SegmentD3Field implements SegmentField {

      // @formatter:off
    INDICATOR(                   FieldDataType.UNSIGNED_BYTE,   0, 1), 
    FIELD_IDENTIFIER(            FieldDataType.ASCII_STRING,    1, 12);
    
    // @formatter:on

        FieldDataType dataType;
        private int fieldLength;
        private int startPosition;

        private SegmentD3Field(FieldDataType type, int start, int length) {
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
    String fieldIdentifier;
    
    public FileInquiryD3Segment(byte[] byteArray) {
        super(byteArray);
        parse();
    }
    
    public void parse() {
      indicator = String.format("%02X", getFieldBufferByte(SegmentD3Field.INDICATOR));
      fieldIdentifier = getFieldBufferString(SegmentD3Field.FIELD_IDENTIFIER);
    }
    
    @Override
    public String getIndicator() {
        return indicator;
    }

    public String getFieldIdentifier() {
        return fieldIdentifier;
    }
    
    @Override
    public Map<String, String> getPropertyMap() {
        Map<String, String> map = new HashMap<String, String>();

        map.put(SegmentD3Field.INDICATOR.toString(), indicator);
        map.put(SegmentD3Field.FIELD_IDENTIFIER.toString(), fieldIdentifier);
        return map;
    }
}
