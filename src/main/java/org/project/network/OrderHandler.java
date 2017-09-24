package org.project.network;

import org.project.TradeOrder;
import java.io.IOException;

public interface OrderHandler {
    void sendOrder(TradeOrder tradeOrder) throws IOException;
}
