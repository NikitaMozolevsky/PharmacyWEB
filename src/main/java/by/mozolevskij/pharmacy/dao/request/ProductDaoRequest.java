package by.mozolevskij.pharmacy.dao.request;

public enum ProductDaoRequest {
    ;
    public static final String ADD_PRODUCT_REQUEST = """
            INSERT INTO products(product_name, details, price, type, photo, goods_quantity,
            need_prescription) VALUES(?,?,?,?,?,?,?)""";

    public static final String GET_ALL_PRODUCTS = """
            SELECT product_id, product_name, details, price, type, photo, goods_quantity,
            need_prescription FROM products
            """;

    public static final String GET_CURRENT_PRODUCTS_QUANTITY = """
            SELECT goods_quantity FROM products WHERE product_id = ?
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

    public static final String GET_PRODUCT_NAME_BY_ID = """
            SELECT product_name FROM products WHERE product_id = ?
            """;

    public static final String GET_PRODUCT_QUANTITY = """
            SELECT goods_quantity FROM products WHERE product_id = ?
            """;

    public static final String UPDATE_GOODS_QUANTITY = """
            UPDATE products SET goods_quantity = ? WHERE product_id = ?
            """;

}
