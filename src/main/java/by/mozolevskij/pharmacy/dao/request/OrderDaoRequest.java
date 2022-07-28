package by.mozolevskij.pharmacy.dao.request;

public enum OrderDaoRequest {
    ;
    public static final String ADD_NEW_ORDER = """
            INSERT INTO orders(user_id, status, date_open, full_cost)
            VALUES(?,?,?,?)
            """;
    public static final String ADD_ORDER_PRODUCT = """
            INSERT INTO order_products(order_id, product_id, quantity, volume, order_product_price)
            VALUES(?,?,?,?,?)
            """;
    public static final String GET_ORDER_PRODUCT_INFO_AND_PRODUCT_INFO_BY_ORDER_ID = """
            SELECT order_products.order_product_id, order_products.order_id, order_products.product_id,
            order_products.quantity, order_products.volume, order_products.order_product_price,
            products.product_name, products.photo FROM order_products JOIN products ON
            order_products.product_id = products.product_id WHERE order_id = ?
            """;
    public static final String SET_ORDER_STATUS_IN_PROCESS = """
            UPDATE orders SET status = ? WHERE order_id = ?
            """;
    public static final String GET_ORDER_ID_AND_STATUS_BY_USER_ID = """
           SELECT order_id, status FROM orders WHERE user_id = ?
           """;
    public static final String GET_ORDER_ID_BY_USER_ID = """
           SELECT order_id FROM orders WHERE user_id = ?
           """;

    public static final String GET_ORDER_PRODUCT_QUANTITIES_AND_ORDER_PRODUCT_IDES_BY_ORDER_ID = """
           SELECT product_id, quantity FROM order_products WHERE order_id = ?
           """;
    public static final String GET_PRODUCT_QUANTITY_AND_PRODUCT_NAME_BY_PRODUCT_ID = """
           SELECT goods_quantity, product_name FROM products WHERE product_id = ?
           """;

    public static final String GET_PRODUCT_NAME_BY_PRODUCT_ID = """
           SELECT product_name FROM products WHERE product_id = ?
           """;

    public static final String SET_PRODUCT_QUANTITY_BY_PRODUCT_ID = """
            UPDATE products SET goods_quantity = ? WHERE product_id = ?
            """;

    public static final String GET_ORDER_STATUS_BY_USER_ID = """
            SELECT status FROM orders WHERE user_id = ?
            """;
    public static final String GET_ALL_ORDER_PRODUCTS_BY_ORDER_ID = """
            SELECT order_product_id, order_id, product_id, quantity,
             volume, order_product_price FROM order_products WHERE order_id = ?
            """;
    public static final String GET_MONEY_AMOUNT_BY_USER_ID = """
            SELECT money_amount FROM users WHERE user_id = ?
            """;
    public static final String GET_FULL_COST_BY_ORDER_ID = """
            SELECT order_product_price FROM order_products WHERE order_id = ?
            """;
    public static final String GET_STATUS_BY_ORDER_ID = """
            SELECT status FROM orders WHERE order_id = ?
            """;
    public static final String SET_FULL_COST_BY_ORDER_ID = """
            UPDATE orders SET full_cost = ? WHERE order_id = ?
            """;
    public static final String UPDATE_MONEY_AMOUNT_BY_USER_ID = """
            UPDATE users SET money_amount = ? WHERE user_id = ?
            """;
    public static final String REMOVE_ORDER_PRODUCT = """
            DELETE FROM order_products WHERE order_product_id = ?
            """;
    public static final String CLOSE_ORDER = """
            UPDATE orders SET address = ?, status = ?, date_close = ? WHERE order_id = ?
            """;
}
