package org.project;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class App {
    public static final int MULTICAST_PORT = 2000;
    public static final String MULTICAST_GROUP = "224.0.0.1";
    public static final String NIC_INTERFACE = "enp3s0";

    public static void main(String[] args) throws IOException {

        byte[] receiveBuffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        InetAddress McastAddress = InetAddress.getByName(MULTICAST_GROUP);
        MulticastSocket mcastSocket = new MulticastSocket(MULTICAST_PORT);
        mcastSocket.setNetworkInterface(NetworkInterface.getByName("lo"));
        OrderHandler testOrderHandler = new OrderHandler("127.0.0.1", 10000);
        TradingAlgo testAlgo = new TestTradingAlgo(100, testOrderHandler);
        mcastSocket.joinGroup(McastAddress);

        while (true) {
            mcastSocket.receive(packet);
            Long entryTimestamp = System.nanoTime();
            String received = new String(packet.getData(), StandardCharsets.US_ASCII);
            System.out.println(received);
            FeedMessage testFeedMessage = new TestFeedMessage("T",1, 50);
            testAlgo.evaluateOpportunity(testFeedMessage);
        }
    }
}
