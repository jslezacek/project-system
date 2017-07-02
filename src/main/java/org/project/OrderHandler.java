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
            this.inputStr = orderSocket.getInputStream();
            this.outputStr = orderSocket.getOutputStream();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void sendOrder(TradeOrder tradeOrder) throws IOException {
        this.order = "My order".getBytes();
        this.outputStr.write(this.order);
        Long timestampExit = System.nanoTime();
        tradeOrder.setSendTimestamp(timestampExit);
    }

    private void persistOrder(TradeOrder tradeOrder) {
        // JDBC
    }
}
