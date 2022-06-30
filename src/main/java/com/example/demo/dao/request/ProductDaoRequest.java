package com.example.demo.dao.request;

public enum ProductDaoRequest {
    ;
    public static final String ADD_PRODUCT_REQUEST = """
            INSERT INTO products(product_name, details, price, type, photo)
            VALUES(?,?,?,?,?)""";

    public static final String GET_ALL_PRODUCTS = """
            SELECT product_id, product_name, details, price, type, photo FROM products
            """;

    public static final String GET_PRODUCT_BY_NAME = """
            SELECT product_id, product_name, details, price, type, 
            volume, photo FROM products WHERE product_name = ?
            """;

    public static final String GET_PRODUCT_BY_ID = """
            SELECT product_id, product_name, details, price, type,
             volume, photo FROM products WHERE product_name = ?
            """;
    public static final String GET_PRODUCT_PRICE_BY_ID = """
            SELECT price FROM products WHERE product_id = ?
            """;
}
