package org.project.messages;

public class TestFeedMessage implements FeedMessage {

    public String MsgType;
    public String SequenceNumber;
    public Integer Price;

    TestFeedMessage(byte[] rawBytes) {
        this.MsgType = DataTypes.decodeAscii(rawBytes);
    }

    // Setters are not the same thing, because they would allow you to change object properties after its creation.
    public TestFeedMessage(String msgType, String sequenceNumber, Integer price) {
        MsgType = msgType;
        SequenceNumber = sequenceNumber;
        Price = price;
    }

    @Override
    public String getMsgType() {
        return this.MsgType;
    }

    @Override
    public Integer getPrice() {
        return this.Price;
    }

    @Override
    public String getSeqNo() {
        return this.SequenceNumber;
    }

    @Override
    public byte[] toBinary() {
        return new byte[0];
    }
}
