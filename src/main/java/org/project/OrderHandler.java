package org.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

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
        this.order = "My order |".getBytes();
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
