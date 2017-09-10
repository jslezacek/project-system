package org.project.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.project.TradeOrder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class OrderHandler {

    Socket orderSocket;
    InputStream inputStr;
    OutputStream outputStream;
    byte[] order;
    Integer orderId = 0;
    KafkaPublisher kafkaBus;
    String sourceName = "OrderHandler";

    public OrderHandler(String hostname, int port) {
        try {
            this.orderSocket = new Socket(hostname, port);
            orderSocket.setTcpNoDelay(true);
            this.inputStr = orderSocket.getInputStream();
            this.outputStream = orderSocket.getOutputStream();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        kafkaBus = new KafkaPublisher("framework:9092", "measurements");
    }

    public void sendOrder(TradeOrder tradeOrder) throws IOException {
        String orderId = tradeOrder.getOrderId();
        String feedId = tradeOrder.getFeedTriggerId();
        long timestamp = System.nanoTime();

        String orderMsg = "\u00018=FIX-5.0|35=ZZ|11="+orderId+"|55=APPL|44=100.20\u0001";
        this.order = orderMsg.getBytes();
        this.outputStream.write(this.order);
//        String benchmarkMsg = ("OrderId: "+ orderId + orderSentTs + " FeedId: " + tradeOrder.getFeedTriggerId());

        HashMap benchmarkMsg = new HashMap();

        benchmarkMsg.put("orderId", orderId);
        benchmarkMsg.put("orderTs", timestamp);
        benchmarkMsg.put("feedId", feedId);
        benchmarkMsg.put("sourceId", this.sourceName);

        Gson gson = new GsonBuilder().create();

        System.out.println("Before kafka:" + System.nanoTime());
        this.kafkaBus.send(gson.toJson(benchmarkMsg));
        System.out.println("After kafka:" + System.nanoTime());
//        this.outputStream.flush(); // send imediately instead of buffering multiple msg.
    }

    private void persistOrder(TradeOrder tradeOrder) {
        // JDBC
    }
}

