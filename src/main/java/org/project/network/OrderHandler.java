package org.project.network;

import org.project.TradeOrder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class OrderHandler {

    Socket orderSocket;
    InputStream inputStr;
    OutputStream outputStream;
    byte[] order;
    Integer orderId = 0;
    KafkaPublisher kafkaBus;

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
        kafkaBus = new KafkaPublisher("framework:9092");
    }

    public void sendOrder(TradeOrder tradeOrder) throws IOException {
        String orderId = tradeOrder.getOrderId();
        String feedTriggerId = tradeOrder.getFeedTriggerId();
        String orderMsg = "\u00018=FIX-5.0|35=ZZ|11="+orderId+"|55=APPL|44=100.20\u0001";
        this.order = orderMsg.getBytes();
        this.outputStream.write(this.order);
        long orderSentTs = System.nanoTime();
        String benchmarkMsg = ("OrderId: "+ orderId + orderSentTs + " FeedId: " + tradeOrder.getFeedTriggerId());
        this.kafkaBus.send("test", benchmarkMsg);
        this.outputStream.flush(); // send imediately instead of buffering multiple msg.
    }

    private void persistOrder(TradeOrder tradeOrder) {
        // JDBC
    }
}
