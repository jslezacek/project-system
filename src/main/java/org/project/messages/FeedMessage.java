package org.project.messages;

/**
 * Created by jojo on 5/10/17.
 */
public interface FeedMessage {
    public String getMsgType();
    public int getPrice();
    public int getSeqNo();
    public byte[] getBytes();
    public String getProduct();
}
