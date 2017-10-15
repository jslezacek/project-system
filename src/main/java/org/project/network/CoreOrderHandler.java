package org.project.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.project.TradeOrder;
import org.project.messages.BenchmarkMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class CoreOrderHandler implements OrderHandler {

    Socket orderSocket;
    InputStream inputStr;
    OutputStream outputStream;
    byte[] order;
    Integer orderId = 0;
    KafkaPublisher kafkaBus;
    String sourceName = "CoreOrderHandler";

    public CoreOrderHandler(Socket orderSocket) {
        try {
            orderSocket.setTcpNoDelay(true);
            this.inputStr = orderSocket.getInputStream();
            this.outputStream = orderSocket.getOutputStream();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    @Override
    public void sendOrder(TradeOrder tradeOrder) throws IOException {
        String orderId = tradeOrder.getOrderId();
        String product = tradeOrder.getProduct();

        String orderMsg = "\u00018=FIX-5.0|35=ZZ|11="+orderId+"|55="+product+"|44=100.20\u0001";
        this.order = orderMsg.getBytes();
        this.outputStream.write(this.order);
    }
}