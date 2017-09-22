package org.project;
import org.project.network.FeedHandler;
import org.project.network.OrderHandler;
import org.project.traders.TestTradingAlgo;
import org.project.traders.TradingAlgo;

import java.io.IOException;
import java.util.Observer;

public class TradingApp {
    public static final int MULTICAST_PORT = 2000;
    public static final String MULTICAST_GROUP = "224.0.0.1";
    public static final String NIC_INTERFACE = "lo";
    public static final Integer bufferLength = 2048;
    Observer tradingAlgo;

    public static void main(String[] args) throws IOException {

        FeedHandler testFeedHandler = new FeedHandler(MULTICAST_GROUP, MULTICAST_PORT, NIC_INTERFACE, bufferLength);
        OrderHandler testOrderHandler = new OrderHandler("127.0.0.1", 10000);
        TradingAlgo testAlgo1 = new TestTradingAlgo(100, testOrderHandler, "trader1");
        TradingAlgo testAlgo2 = new TestTradingAlgo(80, testOrderHandler, "trader2");
        testFeedHandler.addObserver(testAlgo1);
//        testFeedHandler.addObserver(testAlgo2);
        testFeedHandler.run();
    }
}