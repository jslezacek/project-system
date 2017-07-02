package org.project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static java.lang.Thread.sleep;

/**
 * Created by jojo on 6/5/17.
 */
public class MulticastPublisher {

    public static final int MULTICAST_PORT = 2000;
    public static final String MULTICAST_GROUP = "224.0.0.1";

    public static void main(String[] args) throws IOException {
        String msg = "Test message";
        InetAddress McastAddress = InetAddress.getByName(MULTICAST_GROUP);
        MulticastSocket mcastSocket = new MulticastSocket(MULTICAST_PORT);
        DatagramPacket packet = new DatagramPacket(msg.getBytes(StandardCharsets.US_ASCII), msg.getBytes().length);
        packet.setAddress(McastAddress);
        packet.setPort(MULTICAST_PORT);
        mcastSocket.setNetworkInterface(NetworkInterface.getByName("lo"));
        for (int i = 0; i < 1; i++) {
            mcastSocket.send(packet);
        }
    }
}
