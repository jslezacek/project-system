package org.project;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.crypto.Data;

public class TestDataTypes extends TestCase
    {
        Integer expectedDecodedInt = new Integer(65536);
        byte[] expectedEncodedInt = new byte[2]; // hex value of int 2000

    public TestDataTypes(String testName )
    {
        super( testName );
        expectedEncodedInt[0] = (byte)0x07;
        expectedEncodedInt[1] = (byte)0xD0;
    }

    public static Test suite()
    {
        return new TestSuite( TestDataTypes.class);
    }

    public void testAsciiEncodeDecode()
    {
        String inputIntString = "2000";
        byte[] encodedString = DataTypes.encodeAscii(inputIntString);
        for (byte b: encodedString) System.out.printf("0x%02X ", b);
        String decoded_string = DataTypes.decodeAscii(encodedString);
        assertEquals(decoded_string, inputIntString);
    }


    public void testUint16Encode() {
        System.out.println(Integer.toBinaryString(expectedDecodedInt));
        byte[] encodedInt = DataTypes.encodeUInt16(expectedDecodedInt);
        for (byte b: encodedInt ) System.out.printf("0x%02X ", b);
        Boolean mismatch = false;
        for (int i = 0; i < expectedEncodedInt.length; i++) {
            if (encodedInt[i] != expectedEncodedInt[i]) { mismatch = true; }
        }
        assertFalse(mismatch);
    }

    public void testUint16Decode() {
        Integer decodedInt = DataTypes.decodeUInt16(expectedEncodedInt);
        for (byte b: expectedEncodedInt) System.out.printf("0x%02X ", b);
        assertEquals(decodedInt, expectedDecodedInt);
    }

    public void testOrdMsg() {
        DataTypes.getOrderMsg();
    }
}