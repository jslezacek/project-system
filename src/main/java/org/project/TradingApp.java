package org.project;
import org.project.network.*;
import org.project.traders.TestTradingAlgo;
import org.project.traders.TradingAlgo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Observer;
import java.util.Properties;

public class TradingApp {
    public static int MULTICAST_PORT;
    public static String MULTICAST_GROUP;
//    public static final String NIC_INTERFACE = "eth1";
    public static String NIC_INTERFACE = "eth1";
    public static final Integer bufferLength = 2048;

    public static void main(String[] args) throws IOException {

        InputStream configFile = TradingApp.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(configFile);

        MULTICAST_PORT = Integer.parseInt(prop.getProperty("mcast-port"));;
        MULTICAST_GROUP = prop.getProperty("feed-mcast-group");

        String kafkaTopic = prop.getProperty("kafka-topic");
        String feedMcastGroup = prop.getProperty("feed-mcast-group");
        String kafkaBootstrap = prop.getProperty("kafka-bootstrap");
        int feedMcastPort = Integer.parseInt(prop.getProperty("mcast-port"));
        String orderGwHost = prop.getProperty("order-gw-ip");
        int orderGwPort = Integer.parseInt(prop.getProperty("order-gw-port"));

        FeedHandler feedHandler = new FeedHandler(MULTICAST_GROUP, MULTICAST_PORT, NIC_INTERFACE, bufferLength);

        Socket orderSocket = new Socket(orderGwHost, orderGwPort);


        KafkaPublisher kafkaBus = new KafkaPublisher(kafkaBootstrap, kafkaTopic);
        OrderHandler coreOrderHandler = new CoreOrderHandler(orderSocket);
        OrderHandler measuredOrderHandler = new MeasuredOrderHandler(coreOrderHandler, kafkaBus);

        TradingAlgo testAlgo1 = new TestTradingAlgo(100, measuredOrderHandler, "trader1");
        TradingAlgo testAlgo2 = new TestTradingAlgo(80, measuredOrderHandler, "trader2");
        feedHandler.addObserver(testAlgo1);
        feedHandler.addObserver(testAlgo2);
        feedHandler.run();
    }
}