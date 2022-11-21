package by.mozolevskij.pharmacy.dao.request;

public enum UserDaoRequest {
    ;
    public static final String SELECT_USER_INFO_WITH_ACCESS_LEVEL_BY_LOGIN_AND_PASSWORD = """
            SELECT users.user_id, users.user_name, users.login, users.password, users.email,
             users.phone, users.money_amount, access_level.access_level
              FROM users JOIN access_level ON access_level.user_id = users.user_id
               WHERE login = ? AND password = ?
            """;
    public static final String USER_DATA = """
            SELECT user_id, user_name, email, phone, money_amount
             FROM users WHERE login = ? AND password = ?
            """;
    public static final String GET_USER_ID = "SELECT user_id FROM users WHERE login = ?";
    public static final String REGISTER_USER = """
            INSERT INTO users(user_name, login, password, email, phone, money_amount)
            VALUES(?,?,?,?,?,?)""";
    public static final String GET_ALL_USERS = """
            SELECT user_id, user_name, login, password, email, phone, money_amount
            FROM users""";
    public static final String ADD_CLIENT_ACCESS_LEVEL = """
            INSERT INTO access_level(user_id, access_level) VALUES (?, ?)""";
    public static final String GET_ALL_USERS_WITH_ANY_ACCESS_LEVEL = """
            SELECT users.user_id, users.user_name, users.login, users.password,
            users.email, users.phone, users.money_amount, access_level.access_level
            FROM users JOIN access_level ON access_level.user_id = users.user_id
            """;
    public static final String GET_ALL_USERS_WITH_ACCESS_LEVEL = """
            SELECT users.user_id, users.user_name, users.login, users.password,
            users.email, users.phone, users.money_amount, access_level.access_level
            FROM users JOIN access_level ON access_level.user_id = users.user_id
            WHERE access_level.access_level = ?
            """;
    public static final String GET_ALL_USERS_ACCESS_LEVELS = """
            SELECT access_level FROM access_level
            """;
    public static final String GET_USER_BY_ID = """
            SELECT user_id, user_name, login, email FROM users WHERE user_id = ?
            """;
    public static final String GET_USER_BY_NAME = """
            SELECT user_id, user_name, login, email FROM users WHERE user_name = ?
            """;

    public static final String UPDATE_MONEY_AMOUNT = """
            UPDATE users SET money_amount = ? WHERE user_id = ?
            """;
    public static final String USER_ALREADY_EXIST_CHECK = """
            SELECT login, email, phone FROM users WHERE login LIKE ? OR email LIKE ?
            OR phone LIKE ?
            """;
    public static final String LOGIN_ALREADY_EXIST_CHECK = """
            SELECT login FROM users WHERE login = ?
            """;
    public static final String EMAIL_ALREADY_EXIST_CHECK = """
            SELECT email FROM users WHERE email = ?
            """;
    public static final String PHONE_ALREADY_EXIST_CHECK = """
            SELECT phone FROM users WHERE phone = ?
            """;
    /*public static final String DELETE_USER_BY_ID_SQL = """
            DELETE FROM access_level WHERE user_id = ?;
            DELETE FROM orders WHERE user_id = ?;
            DELETE FROM prescription_requests WHERE client_id = ?;
            DELETE FROM prescription_requests WHERE doctor_id = ?;
            DELETE FROM users WHERE user_id = ?;
            """;*/
}
