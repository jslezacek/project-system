//package org.project;
//import org.junit.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.net.*;
//import java.net.Socket;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//public class FeedConnectionTest {
//
//
//    @Test
//    public void subscribeMulticastTest() {
////        fail("sorry failed..");
////        assertTrue(true);
//    }
//
//    @Test
//    public void decodeMulticastMessage() throws IOException {
//        byte[] receiveBuffer = new byte [1024*1024];
//        int port = 5000;
//        InetAddress ipAddress = InetAddress.getByName("224.0.0.1");
//        MulticastSocket mcastSocket = new MulticastSocket(port);
//        mcastSocket.joinGroup(ipAddress);
//
//        FeedMessage expectedFeedMessage = new TestFeedMessage(0, );
//        MessageDecoder testFeedDecoder = new TestMessageDecoder();
//        FeedMessage decodedTestFeedMessage = feedDecoder.decode(byte[]);
//        feedDecoder.notify(decodedTestFeedMessage);
//
//        OrderHandler testOrderHandler = new TestOrderHandler();
//
//        OrderEncoder testOrderEncoder = new TestOrderEncoder();
//        byte [] testOrderEncoder.encode(testOrderMessage);
//
//    }
//
//    public void testSerializationWithDataDictionary() throws Exception {
//        Message message = new Message("1=TestFeed\0012=1\0013=858\0014=GOOGL");
//        Field msgField = new Field(1, "10", field_id, fieldId);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ObjectOutputStream outs = new ObjectOutputStream(out);
//        outs.writeObject(message);
//    }
//
//    public void testSerializationWithDataDictionary() throws Exception {
//        Message message = new Message("1=TestFeed\0012=1\0013=100\001"
//                + "98=0\001384=2\001372=D\001385=R\001372=8\001385=S\00110=96\001",
//
//        Field
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ObjectOutputStream outs = new ObjectOutputStream(out);
//        outs.writeObject(message);
//    }
//
//}
