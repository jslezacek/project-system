package org.project.traders;

import org.project.messages.FeedMessage;

public interface TradingAlgo extends Observer {
    void evalTrade(FeedMessage feedMessage);
    String getTraderName();
}
