package com.example.demo.entity.order;

import com.example.demo.entity.AbstractEntity;

import java.time.LocalDateTime;

public class Order extends AbstractEntity {

    private int orderId;
    private int userId;
    private String address;
    private OrderStatus orderStatus;
    private LocalDateTime dateOpen;
    private LocalDateTime dateClose;
    private Double fullCost;
    private boolean isOrderExist;

    public Order(int orderId, int userId, String address, OrderStatus orderStatus,
                 LocalDateTime dateOpen, LocalDateTime dateClose, Double fullCost,
                 boolean isOrderExist) {
        this.orderId = orderId;
        this.userId = userId;
        this.address = address;
        this.orderStatus = orderStatus;
        this.dateOpen = dateOpen;
        this.dateClose = dateClose;
        this.fullCost = fullCost;
        this.isOrderExist = isOrderExist;
    }

    public Order(int orderId, boolean isOrderExist) {
        this.orderId = orderId;
        this.isOrderExist = isOrderExist;
    }

    public Order() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(LocalDateTime dateOpen) {
        this.dateOpen = dateOpen;
    }

    public LocalDateTime getDateClose() {
        return dateClose;
    }

    public void setDateClose(LocalDateTime dateClose) {
        this.dateClose = dateClose;
    }

    public Double getFullCost() {
        return fullCost;
    }

    public void setFullCost(Double fullCost) {
        this.fullCost = fullCost;
    }

    public boolean isOrderExist() {
        return isOrderExist;
    }

    public void setOrderExist(boolean orderExist) {
        isOrderExist = orderExist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (isOrderExist != order.isOrderExist) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (orderStatus != order.orderStatus) return false;
        if (dateOpen != null ? !dateOpen.equals(order.dateOpen) : order.dateOpen != null) return false;
        if (dateClose != null ? !dateClose.equals(order.dateClose) : order.dateClose != null) return false;
        return fullCost != null ? fullCost.equals(order.fullCost) : order.fullCost == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (dateOpen != null ? dateOpen.hashCode() : 0);
        result = 31 * result + (dateClose != null ? dateClose.hashCode() : 0);
        result = 31 * result + (fullCost != null ? fullCost.hashCode() : 0);
        result = 31 * result + (isOrderExist ? 1 : 0);
        return result;
    }
}
