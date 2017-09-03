package org.project.messages;

public class TestOrderMessage implements OrderMessage {

    private static final String FIELD_SEPARATOR = "|";
    public static final String strSOH = "\u0001";
    private static final String VALUE_SEPARATOR = "=";
    String messageBody;

    public TestOrderMessage(String msg) {
        String msgBody = "8=FIX-5.0\u000135=ZZ\u000111=trader1-001\u000155=APPL\u000144=100.20\u000110=999";
    }

}
