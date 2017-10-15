package org.project.messages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class TestFeedMessage implements FeedMessage {
    private final int len = 15;
    private final String msgType = "T";
    private final int seqNo;
    private final String product;
    private final int price;

    public TestFeedMessage(int seqNumber, String product, int price) {
        this.seqNo = seqNumber;
        this.product = product;
        this.price = price;
    }

    public String getMsgType() {
        return msgType;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }

    public int getLen() {
        return len;
    }

    public byte[] getBytes() {
        byte[] byteMsg = new byte[this.getLen()];
        ByteBuffer result = ByteBuffer.allocate(this.getLen());
        result.put(Codecs.encodeUInt16(this.getLen()), 0, 2);
        result.put(Codecs.encodeAscii(this.getMsgType()), 2, 1);
        result.put(Codecs.encodeUInt16(this.getSeqNo()), 3, 2);
        result.put(Codecs.encodeAscii(this.getProduct()), 5, 8);
        result.put(Codecs.encodeUInt16(this.getPrice()), 13, 2);
        return result.array();
    }

    public static FeedMessage parse(byte[] bytearray) {
        Integer seqNum = Codecs.decodeUInt16(Arrays.copyOfRange(bytearray, 3, 5 ));
        String product = Codecs.decodeAscii(Arrays.copyOfRange(bytearray, 5, 13 ));
        Integer price = Codecs.decodeUInt16(Arrays.copyOfRange(bytearray, 13, 15 ));
        TestFeedMessage result = new TestFeedMessage(seqNum, product, price);
        return result;
    }
}
