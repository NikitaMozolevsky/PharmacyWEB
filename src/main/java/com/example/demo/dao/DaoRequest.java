package com.example.demo.dao;

public enum DaoRequest {
    ;
    public static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE login = ?";
    public static final String REGISTER_USER = """
            INSERT INTO users(user_name, login, password, email, phone, access_level, money_amount)
            VALUES(?,?,?,?,?,?,?)""";
    public static final String ADD_PRODUCT_REQUEST = """
            INSERT INTO products(product_name, details, price, type, volume, photo)
            VALUES(?,?,?,?,?,?)""";
    public static final String GET_ALL_USERS = """
            SELECT user_id, user_name FROM users
            """;
    public static final String GET_ALL_PRODUCTS = """
            SELECT product_id, product_name FROM products
            """;
    public static final String GET_USER_BY_NAME = """
            SELECT user_id, user_name, login, email FROM users WHERE user_name = ?
            """;
    public static final String GET_PRODUCT_BY_NAME = """
            SELECT product_id, product_name, details, price, type, 
            volume, photo FROM products WHERE product_name = ?
            """;
    public static final String GET_USER_BY_ID = """
            SELECT user_id, user_name, login, email FROM users WHERE user_id = ?
            """;
    public static final String GET_PRODUCT_BY_ID = """
            SELECT product_id, product_name, details, price, type,
             volume, photo FROM products WHERE product_name = ?
            """;

}
