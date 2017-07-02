package org.project;

import java.io.IOException;

/**
 * Created by jojo on 6/5/17.
 */
public class TestTradingAlgo implements TradingAlgo {

//    String Product;
    Integer PriceThreshold;
    Integer Quantity = 10;
    OrderHandler testOrderHandler;

    public TestTradingAlgo(Integer buyPrice, OrderHandler testOrderHandler) {
        this.PriceThreshold = buyPrice;
        this.testOrderHandler = testOrderHandler;
    }

    @Override
    public void evaluateOpportunity(FeedMessage feedMessage) {
        System.out.println(feedMessage.getMsgType());
        if (feedMessage.getMsgType() == "T") {
            if (feedMessage.getPrice() <= this.PriceThreshold) {
                System.out.println("Valid trade opportunity.");
                TradeOrder testTradeOrder = new TradeOrder();
                testTradeOrder.setFeedTriggerId(feedMessage.getSeqNo());
                testTradeOrder.setOrderId(10);
                testTradeOrder.setQuantity(this.Quantity);
                testTradeOrder.setPrice(feedMessage.getPrice());
                testTradeOrder.setProduct("TEST-Stock");
                try {
                    System.out.println("Sending order.");
                    this.testOrderHandler.sendOrder(testTradeOrder);
                } catch (IOException e) {
                    System.out.println(e);
                    System.exit(1);
                }
            }
        }
    }
}
