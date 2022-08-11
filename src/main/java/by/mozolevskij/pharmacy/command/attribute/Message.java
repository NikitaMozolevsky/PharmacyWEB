package by.mozolevskij.pharmacy.command.attribute;

public enum Message {
    ;
    public static final String CART_IS_EMPTY_MSG = "<script>window.alert" +
            "('Cart is empty')</script>";
    public static final String PURCHASE_COMPLETED_MSG = "<script>window.alert" +
            "('Purchase completed!')</script>";
    public static final String NOT_ENOUGH_MONEY_MSG = "<script>window.alert" +
            "('Not enough money, top your account')</script>";
    public static final String NOT_ENOUGH_GOODS_MSG = "<script>window.alert" +
            "('There is no such quantity of goods in stock')</script>";
    public static final String PRODUCT_WAS_ADDED_MSG = "<script>window.alert" +
            "('Product was added successful')</script>";
    public static final String PRESCRIPTION_REQUEST_LIST_EMPTY_MSG = "<script>window.alert" +
            "('Prescription request list is empty!')</script>";
    public static final String APPROVED_PRESCRIPTION_MSG = "<script>window.alert" +
            "('The recipe is approved!')</script>";
    public static final String REJECTED_PRESCRIPTION_MSG = "<script>window.alert" +
            "('The recipe is rejected!')</script>";
    public static final String PRODUCT_LIST_EMPTY_MSG = "<script>window.alert" +
            "('Goods list empty!')</script>";
}
