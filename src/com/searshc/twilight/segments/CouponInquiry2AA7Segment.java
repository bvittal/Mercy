package com.searshc.twilight.segments;

import java.util.HashMap;
import java.util.Map;

import com.starmount.ups.sears.ResponseSegment;
import com.starmount.ups.sears.SegmentField;

public class CouponInquiry2AA7Segment extends ResponseSegment {

    public enum INDICATOR {
        HEX_STRING("2AA7");

        String hexString;

        INDICATOR(String s) {
            hexString = s;
        }

        public String getHexString() {
            return hexString;
        }
    }
       
    public enum Segment2AA7Field implements SegmentField {

        // @formatter:off
        INDICATOR(                  FieldDataType.UNSIGNED_BYTE,  0,  2),
        SEGMENT_LEVEL(              FieldDataType.ASCII_STRING,   4,  2),
        SEGMENT_LENGTH(             FieldDataType.UNSIGNED_SHORT, 2,  2),
        COUPON_NUMBER(              FieldDataType.UNSIGNED_BYTE,   6,  8),
        STORE_NUMBER(               FieldDataType.UNSIGNED_BYTE,   14,  5);    
        // @formatter:on

        FieldDataType dataType;
        private int fieldLength;
        private int startPosition;

        private Segment2AA7Field(FieldDataType type, int start, int length) {
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

    public static final int LENGTH = 0;

    String indicator;
    String segmentLength;
    String segmentLevel;
    String couponNumber;
    String storeNumber;

    public CouponInquiry2AA7Segment(byte[] byteArray) {
        super(byteArray);
        parse();
    }

    public void parse() {
        byte[] bb = getFieldBufferBytes(Segment2AA7Field.INDICATOR);
        indicator = String.format("%02X%02X", bb[0], bb[1]);
        segmentLength = getFieldBufferString(Segment2AA7Field.SEGMENT_LENGTH);
        segmentLevel = getFieldBufferString(Segment2AA7Field.SEGMENT_LEVEL);
        couponNumber = getFieldBufferString(Segment2AA7Field.COUPON_NUMBER);
        storeNumber = getFieldBufferString(Segment2AA7Field.STORE_NUMBER);
    }

    @Override
    public String getIndicator() {
        return indicator;
    }

    public String getSegmentLevel() {

        return segmentLevel;
    }

    public String getSegmentLength() {

        return segmentLength;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    @Override
    public Map<String, String> getPropertyMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(Segment2AA7Field.INDICATOR.toString(), getIndicator());
        map.put(Segment2AA7Field.SEGMENT_LEVEL.toString(), getSegmentLevel());
        map.put(Segment2AA7Field.SEGMENT_LENGTH.toString(), getSegmentLength());
        map.put(Segment2AA7Field.COUPON_NUMBER.toString(), getCouponNumber());
        map.put(Segment2AA7Field.STORE_NUMBER.toString(), getStoreNumber());
        return map;
    }
}
