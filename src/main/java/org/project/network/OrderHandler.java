package org.project.network;

import org.project.TradeOrder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by jojo on 6/5/17.
 */
public class OrderHandler {

    Socket orderSocket;
    InputStream inputStr;
    OutputStream outputStr;
    byte[] order;

    public OrderHandler(String hostname, int port) {
        try {
            this.orderSocket = new Socket(hostname, port);
            orderSocket.setTcpNoDelay(true);
            this.inputStr = orderSocket.getInputStream();
            this.outputStr = orderSocket.getOutputStream();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void sendOrder(TradeOrder tradeOrder) throws IOException {
        this.order = "\u00018=FIX-5.0|35=ZZ|11=trader1-001|55=APPL|44=100.20\u0001".getBytes();
        System.out.println(this.order.length);
        this.outputStr.write(this.order);
        this.outputStr.flush(); // send imediately instead of buffering multiple msg.
        Long timestampNs = System.nanoTime();
        Long timestampMs = System.currentTimeMillis();
        tradeOrder.setSendTimestamp(timestampNs);
        tradeOrder.setSendTimestampMs(timestampMs);
        System.out.println("Order ns: " + timestampNs);
        System.out.println("Order ms: " + timestampMs);
    }

    private void persistOrder(TradeOrder tradeOrder) {
        // JDBC
    }
}
