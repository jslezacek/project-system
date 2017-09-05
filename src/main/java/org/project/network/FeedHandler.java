package org.project.network;

import org.project.messages.FeedMessage;
import org.project.messages.TestFeedMessage;
import org.project.traders.ObservedSubject;
import org.project.traders.Observer;
import org.project.traders.TradingAlgo;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FeedHandler implements ObservedSubject {
    //TODO:  sequential notification
    private ArrayList<Observer> feedSuscribers = new ArrayList<Observer>();
    private final MulticastSocket mcastSocket;
    private DatagramPacket networkPacket;
    private final KafkaPublisher kafkaBus;

    //TODO: builder pattern - telescopic constructor optional args (2 optional)
    public FeedHandler(String ipAddress, Integer portNumber, String networkInterface, Integer buffer) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        this.mcastSocket = new MulticastSocket(portNumber);
        this.mcastSocket.setNetworkInterface(NetworkInterface.getByName(networkInterface));
        this.mcastSocket.joinGroup(inetAddress);
        this.networkPacket = new DatagramPacket(new byte[buffer], buffer);
        this.kafkaBus = new KafkaPublisher("framework:9092");
    }

    public void run() {
        while (true) {
            try {
                mcastSocket.receive(networkPacket);
                long feedReceivedTs = System.nanoTime();
                String received = new String(networkPacket.getData(), 0, networkPacket.getLength(), StandardCharsets.US_ASCII);
                System.out.println(received + " " + feedReceivedTs);
                String benchmarkMsg = "FeedId: " + received;
                kafkaBus.send("test", benchmarkMsg);
                FeedMessage testFeedMessage = new TestFeedMessage("T", received, 50);
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
