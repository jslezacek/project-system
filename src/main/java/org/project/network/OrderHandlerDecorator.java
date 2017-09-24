package org.project.network;

import org.project.TradeOrder;

import java.io.IOException;

public abstract class OrderHandlerDecorator implements OrderHandler {
    private OrderHandler decoratedOrderHandler;

    public OrderHandlerDecorator(OrderHandler decoratedOrderHandler) {
        this.decoratedOrderHandler = decoratedOrderHandler;
    }

    @Override
    public void sendOrder(TradeOrder tradeOrder) throws IOException {
        this.decoratedOrderHandler.sendOrder(tradeOrder);
    }
}
