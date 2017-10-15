package org.project.network;

import org.project.TradeOrder;
import org.project.messages.BenchmarkMessage;
import java.io.IOException;

public class MeasuredOrderHandler extends OrderHandlerDecorator {
    private final String sourceId = "OrderHandler";
    KafkaPublisher kafkaBus;

    public MeasuredOrderHandler(OrderHandler decoratedOrderHandler, KafkaPublisher kafkaBus) {
        super(decoratedOrderHandler);
        this.kafkaBus = kafkaBus;
    }

    @Override
    public void sendOrder(TradeOrder tradeOrder) throws IOException {
        super.sendOrder(tradeOrder); // delegate
        this.sendMeasurement(tradeOrder); // decorate with measurement
    }

    private void sendMeasurement(TradeOrder tradeOrder) {
        String orderId = tradeOrder.getOrderId();
        int feedId = tradeOrder.getFeedTriggerId();
        long timestamp = System.nanoTime();

        BenchmarkMessage measurement = new BenchmarkMessage.Builder(this.sourceId).
                orderId(orderId).
                feedId(feedId).
                orderTs(timestamp).
                build();
        this.kafkaBus.send(measurement.toJson());
    }
}
