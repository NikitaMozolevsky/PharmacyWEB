package by.mozolevskij.pharmacy.command.attribute;

public enum OrderAttribute {
    ;
    public static final String ORDER_PRODUCT = "order_product";
    public static final String ORDER_PRODUCTS = "order_products";
    public static final String ORDER_PRODUCT_ID = "order_product_id";
    public static final String ORDER_PRODUCT_PRICE = "order_product_price";
    public static final String ORDER_ID = "order_id";
    public static final String STATUS = "status";
    public static final String CREATING = "CREATING";
    public static final String IN_PROCESS = "IN_PROCESS";
    public static final String CLOSED = "CLOSED";
    public static final String DATE_OPEN = "date_open";
    public static final String DATE_CLOSE = "date_close";
    public static final String FULL_PRICE = "full_price";
    public static final String FULL_COST = "full_cost";
    public static final Double EMPTY_CART_COST = 0.00;
    public static final String ADDRESS = "address";
    public static final String ORDER_EXIST = "order_exist";
    public static final String SET_MONEY_AMOUNT = "set_money_amount";
    public static final String NOT_ENOUGH_MONEY = "not_enough_money";
    public static final String NOT_ENOUGH_GOODS = "not_enough_goods";
    public static final String NOT_ENOUGH_GOODS_NAMES = "not_enough_goods_names";
    public static final String QUANTITY = "quantity";
    public static final String PURCHASE_COMPLETED = "purchase_completed";
    public static final String PRODUCT_WAS_ADDED = "product_was_added";
    public static final String CART_IS_EMPTY = "cart_is_empty";
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

}
