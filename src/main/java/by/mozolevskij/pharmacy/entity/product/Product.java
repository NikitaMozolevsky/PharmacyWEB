package by.mozolevskij.pharmacy.entity.product;

import by.mozolevskij.pharmacy.entity.AbstractEntity;

public class Product extends AbstractEntity {

    private int productId;
    private String productName;
    private String details;
    private double price;
    private DrugType type;
    private String volume;
    private String photo;
    private int quantity;
    private boolean needPrescription;

    public Product(int productId, String productName, String details, double price,
                   DrugType type, String volume, String photo, int quantity,
                   boolean needPrescription) {
        this.productId = productId;
        this.productName = productName;
        this.details = details;
        this.price = price;
        this.type = type;
        this.volume = volume;
        this.photo = photo;
        this.quantity = quantity;
        this.needPrescription = needPrescription;
    }

    public Product() {}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DrugType getType() {
        return type;
    }

    public void setType(DrugType type) {
        this.type = type;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isNeedPrescription() {
        return needPrescription;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (quantity != product.quantity) return false;
        if (needPrescription != product.needPrescription) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (details != null ? !details.equals(product.details) : product.details != null) return false;
        if (type != product.type) return false;
        if (volume != null ? !volume.equals(product.volume) : product.volume != null) return false;
        return photo != null ? photo.equals(product.photo) : product.photo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (needPrescription ? 1 : 0);
        return result;
    }
}
