package org.project.traders;

import org.project.network.OrderHandler;
import org.project.TradeOrder;
import org.project.messages.FeedMessage;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by jojo on 6/5/17.
 */
public class TestTradingAlgo implements TradingAlgo {

    Integer PriceThreshold;
    Integer Quantity = 10;
    OrderHandler testOrderHandler;
    String traderName;
    //TODO: might pass subject as well
    public TestTradingAlgo(Integer buyPrice, OrderHandler testOrderHandler, String traderName) {
        this.PriceThreshold = buyPrice;
        this.testOrderHandler = testOrderHandler;
        this.traderName = traderName;
    }

    @Override
    public void evaluateTradeOpportunity(FeedMessage feedMessage) {
        System.out.println(feedMessage.getMsgType());
        if (feedMessage.getMsgType() == "T") {
            if (feedMessage.getPrice() <= this.PriceThreshold) {
//                System.out.println("Valid trade opportunity.");
                TradeOrder testTradeOrder = new TradeOrder();
                testTradeOrder.setFeedTriggerId(feedMessage.getSeqNo());
                testTradeOrder.setOrderId(10);
                testTradeOrder.setQuantity(this.Quantity);
                testTradeOrder.setPrice(feedMessage.getPrice());
                testTradeOrder.setTraderId(this.traderName + "one");
                try {
                    System.out.println("Sending order " + this.traderName);
                    this.testOrderHandler.sendOrder(testTradeOrder);
                } catch (IOException e) {
                    System.out.println(e);
                    System.exit(1);
                }
            }
        }
    }

    @Override
    public String getTraderName() {
        return this.traderName;
    }

    @Override
    public void update(FeedMessage feedMessage) {
        this.evaluateTradeOpportunity(feedMessage);
    }
}
