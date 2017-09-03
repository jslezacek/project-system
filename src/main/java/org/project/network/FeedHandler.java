package org.project.network;

import org.project.messages.FeedMessage;
import org.project.messages.TestFeedMessage;
import org.project.traders.ObservedSubject;
import org.project.traders.Observer;
import org.project.traders.TradingAlgo;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FeedHandler implements ObservedSubject {
    //TODO:  sequential notification
    private ArrayList<Observer> feedSuscribers = new ArrayList<Observer>();
    private final MulticastSocket mcastSocket;
    private DatagramPacket networkPacket;

    //TODO: builder pattern - telescopic constructor optional args (2 optional)
    public FeedHandler(String ipAddress, Integer portNumber, String networkInterface, Integer packetBuffer) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        this.mcastSocket = new MulticastSocket(portNumber);
        this.mcastSocket.setNetworkInterface(NetworkInterface.getByName(networkInterface));
        this.mcastSocket.joinGroup(inetAddress);
        this.networkPacket = new DatagramPacket(new byte[packetBuffer], packetBuffer);
    }

    public void run() {
        while (true) {
            try {
                mcastSocket.receive(networkPacket);
                Long entryNs = System.nanoTime();
                Long entryMs = System.currentTimeMillis();
                String received = new String(networkPacket.getData(), StandardCharsets.US_ASCII);
                System.out.println(received);
                System.out.println("feed nano:" + entryNs);
                System.out.println("feed milli:" + entryMs);
                FeedMessage testFeedMessage = new TestFeedMessage("T", 1, 50);
                // TODO: observer pattern push vs pull notify()
                notifyObservers(testFeedMessage);
            } catch (IOException e) {
                System.out.println("Invalid packet received.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addObserver(Observer o) {
        String traderName;
        if (o instanceof TradingAlgo) {
            traderName = ((TradingAlgo) o).getTraderName();
        } else traderName = "InvalidTrader";
        System.out.println("New feed suscriber: " + traderName +  " Total subscribers: " + this.feedSuscribers.size());
        this.feedSuscribers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.feedSuscribers.remove(o);
    }

    @Override
    public void notifyObservers(FeedMessage feedMessage) {
        for (Observer feedSuscriber : this.feedSuscribers) {
           feedSuscriber.update(feedMessage);
        }
    }
}
