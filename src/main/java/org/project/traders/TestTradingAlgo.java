package org.project.traders;

import org.project.network.OrderHandler;
import org.project.TradeOrder;
import org.project.messages.FeedMessage;

import java.io.IOException;

public class TestTradingAlgo implements TradingAlgo {

    Integer PriceThreshold;
    Integer Quantity = 10;
    OrderHandler testCoreOrderHandler;
    String traderName;
    Integer counter = 200;
    //TODO: might pass subject as well

    public TestTradingAlgo(Integer buyPrice, OrderHandler testCoreOrderHandler, String traderName) {
        this.PriceThreshold = buyPrice;
        this.testCoreOrderHandler = testCoreOrderHandler;
        this.traderName = traderName;
    }

    @Override
    public void evalTrade(FeedMessage feedMessage) {
        System.out.println(feedMessage.getMsgType());
        if (feedMessage.getMsgType() == "T") {
            if (feedMessage.getPrice() <= this.PriceThreshold) {
                String orderId = this.traderName + " " + counter;
                TradeOrder testTradeOrder = new TradeOrder();
                testTradeOrder.setFeedTriggerId(feedMessage.getSeqNo());
                testTradeOrder.setOrderId(orderId);
                testTradeOrder.setQuantity(this.Quantity);
                testTradeOrder.setPrice(feedMessage.getPrice());
                testTradeOrder.setTraderId(this.traderName + "one");
                try {
                    System.out.println("Sending order " + this.traderName);
                    this.testCoreOrderHandler.sendOrder(testTradeOrder);
                    counter ++;
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
        this.evalTrade(feedMessage);
    }
}
