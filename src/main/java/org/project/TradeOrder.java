package org.project;

/**
 * Created by jojo on 6/5/17.
 */
public class TradeOrder {
    Integer OrderId;
    Integer FeedTriggerId;
    String Product;
    Integer Price;
    Integer Quantity;
    Long SendTimestamp;

    public void setSendTimestampMs(Long sendTimestampMs) {
        SendTimestampMs = sendTimestampMs;
    }

    Long SendTimestampMs;


    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Integer getFeedTriggerId() {
        return FeedTriggerId;
    }

    public void setFeedTriggerId(Integer feedTriggerId) {
        FeedTriggerId = feedTriggerId;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Long getSendTimestamp() {
        return SendTimestamp;
    }

    public void setSendTimestamp(Long sendTimestamp) {
        SendTimestamp = sendTimestamp;
    }
}