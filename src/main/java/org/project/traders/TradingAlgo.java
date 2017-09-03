package org.project.traders;

import org.project.messages.FeedMessage;

public interface TradingAlgo extends Observer {
    void evaluateTradeOpportunity(FeedMessage feedMessage);
    String getTraderName();
}
