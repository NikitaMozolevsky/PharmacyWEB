package com.example.demo.dao.request;

public enum OrderDaoRequest {
    ;
    public static final String ADD_NEW_ORDER = """
            INSERT INTO orders(user_id, status, date_open, full_cost)
            VALUES(?,?,?,?)""";
    public static final String ADD_ORDER_PRODUCT = """
            INSERT INTO order_products(order_id, product_id, quantity, volume)
            VALUES(?,?,?,?)""";
    public static final String GET_ORDER_ID_BY_USER_ID = """
           SELECT order_id FROM orders WHERE user_id = ?""";
}
