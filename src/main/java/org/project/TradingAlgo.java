package org.project;
import java.util.Observer;

public interface TradingAlgo extends Observer {
    void evaluateOpportunity(FeedMessage feedMessage);
}
