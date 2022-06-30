package com.example.demo.dao.request;

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
    public static final String SET_ORDER_STATUS_IN_PROCESS = """
            UPDATE orders SET status = ? WHERE order_id = ?
            """;
    public static final String GET_ORDER_ID_BY_USER_ID = """
           SELECT order_id FROM orders WHERE user_id = ?
           """;
    public static final String GET_ORDER_STATUS_BY_USER_ID = """
            SELECT status FROM orders WHERE user_id = ?
            """;
    public static final String GET_ALL_ORDER_PRODUCT_BY_ORDER_ID = """
            SELECT status FROM orders WHERE user_id = ?
            """;
}
