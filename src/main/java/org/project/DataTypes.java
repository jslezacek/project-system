package org.project;
import java.nio.charset.StandardCharsets;

/**
 * Created by jojo on 5/13/17.
 */
public class DataTypes {

    public static final String strSOH = "\u0001";
    public static final byte[] hexSOH = {0x01};

    public static void getOrderMsg() {
        String testMsg = "8=FIX.4.2\u00019=12\u000135=Y\u0001108=30\u000110=037\u0001";
        String testMsg2 = "108=3010=037";
        System.out.println(testMsg);
        System.out.println(testMsg2.length());
    }


    public static Integer decodeUInt16(byte[] rawBytes) {
        return ((rawBytes[0] & 0xFF) << 8) | (rawBytes[1] & 0xFF);
    }

    public static byte[] encodeUInt16(Integer inputInt) {
        byte [] encodedInt = new byte[2];
        // big-endian
        encodedInt[0] = (byte)(inputInt >> 8 & 0xFF);
        encodedInt[1] = (byte)(inputInt & 0xFF);
        return encodedInt;
    }

    public static String decodeAscii(byte[] rawBytes) {
        return new String(rawBytes, StandardCharsets.US_ASCII);
    }

    public static byte[] encodeAscii(String input_string) {
        return input_string.getBytes(StandardCharsets.US_ASCII);
    }
}