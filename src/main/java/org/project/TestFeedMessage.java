package org.project;

import javax.xml.crypto.Data;
import java.io.*;

/**
 * Created by jojo on 6/4/17.
 */
public class TestFeedMessage implements FeedMessage {

    public String MsgType;
    public Integer SequenceNumber;
    public Integer Price;

    TestFeedMessage(byte[] rawBytes) {
        this.MsgType = DataTypes.decodeAscii(rawBytes);
    }

    public TestFeedMessage(String msgType, Integer sequenceNumber, Integer price) {
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
    public Integer getSeqNo() {
        return this.SequenceNumber;
    }
}
