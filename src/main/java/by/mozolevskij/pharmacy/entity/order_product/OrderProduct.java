package by.mozolevskij.pharmacy.entity.order_product;

import by.mozolevskij.pharmacy.entity.AbstractEntity;

public class OrderProduct extends AbstractEntity {

    private int orderProductId;
    private int orderId;
    private int productId;
    private int quantity;
    private String volume;
    private double orderProductPrice;
    private int userId;
    private String productName;
    private String photo;

    public OrderProduct(int orderProductId, int orderId, int productId, int quantity, String volume,
                        double orderProductPrice, int userId, String productName, String photo) {
        this.orderProductId = orderProductId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.volume = volume;
        this.orderProductPrice = orderProductPrice;
        this.userId = userId;
        this.productName = productName;
        this.photo = photo;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public double getOrderProductPrice() {
        return orderProductPrice;
    }

    public void setOrderProductPrice(double orderProductPrice) {
        this.orderProductPrice = orderProductPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (orderProductId != that.orderProductId) return false;
        if (orderId != that.orderId) return false;
        if (productId != that.productId) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(that.orderProductPrice, orderProductPrice) != 0) return false;
        if (userId != that.userId) return false;
        if (volume != null ? !volume.equals(that.volume) : that.volume != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        return photo != null ? photo.equals(that.photo) : that.photo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderProductId;
        result = 31 * result + orderId;
        result = 31 * result + productId;
        result = 31 * result + quantity;
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        temp = Double.doubleToLongBits(orderProductPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + userId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
