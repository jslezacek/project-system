package org.project.messages;

import java.io.Externalizable;
import java.io.Serializable;

/**
 * Created by jojo on 5/10/17.
 */
public interface FeedMessage {
    public String getMsgType();
    public Integer getPrice();
    public Integer getSeqNo();
    public byte[] toBinary();
}
