package org.project;

public interface TradingAlgo extends Observer {
    void evaluateTradeOpportunity(FeedMessage feedMessage);
}
