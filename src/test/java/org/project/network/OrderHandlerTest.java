package org.project.network;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.project.TradeOrder;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

public class OrderHandlerTest {
    @Test
    public void sendOrder() throws Exception {
        Socket socketMock = mock(Socket.class);
        ByteArrayOutputStream socketOutStream = new ByteArrayOutputStream();
        when(socketMock.getOutputStream()).thenReturn(socketOutStream);

        KafkaPublisher kafkaMock = mock(KafkaPublisher.class);

        OrderHandler coreOrderHandler = new CoreOrderHandler(socketMock);
        OrderHandler measuredOrderHandler = new MeasuredOrderHandler(coreOrderHandler, kafkaMock);

        ArgumentCaptor<String> kafkaMessage = ArgumentCaptor.forClass(String.class);

        TradeOrder testOrder = new TradeOrder();
        testOrder.setOrderId("mockTest");
        testOrder.setFeedTriggerId(10);

        measuredOrderHandler.sendOrder(testOrder);

        verify(kafkaMock).send(kafkaMessage.capture());
        verify(socketMock, atLeastOnce()).getOutputStream();

        System.out.println(socketOutStream.toString()); // CoreOrderHandler outputStream.write(this.order);
        System.out.println(kafkaMessage.getValue());    // MeasuredOrderHandler kafkaBus.send(measurement.toJson());
    }
}