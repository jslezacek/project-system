package org.project.messages;

/**
 * Created by jojo on 5/10/17.
 */
public interface FeedMessage {
    public String getMsgType();
    public Integer getPrice();
    public String getSeqNo();
    public byte[] toBinary();
}
