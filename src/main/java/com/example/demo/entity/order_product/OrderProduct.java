package com.example.demo.entity.order_product;

import com.example.demo.entity.AbstractEntity;

public class OrderProduct extends AbstractEntity {

    private int orderId;
    private int productId;
    private int quantity;
    private String volume;

    public OrderProduct(int orderId, int productId, int quantity, String volume) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.volume = volume;
    }

    public OrderProduct() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (orderId != that.orderId) return false;
        if (productId != that.productId) return false;
        if (quantity != that.quantity) return false;
        return volume != null ? volume.equals(that.volume) : that.volume == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + productId;
        result = 31 * result + quantity;
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }
}
