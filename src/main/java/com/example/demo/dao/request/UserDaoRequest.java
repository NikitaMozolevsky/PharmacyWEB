package com.example.demo.dao.request;

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
    public static String SELECT_ALL_USERS = """
            SELECT user_id, user_name, login, password, email, phone, money_amount
            FROM cafe.users""";
    public static final String ADD_CLIENT_ACCESS_LEVEL = """
            INSERT INTO access_level(user_id, access_level) VALUES (?, ?)""";
    public static final String GET_ALL_USERS_WITH_ACCESS_LEVEL = """
             SELECT users.user_id, users.user_name, users.login, users.password,
              users.email, users.phone, users.money_amount, access_level.access_level
               FROM users JOIN access_level ON access_level.user_id = users.user_id
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
}
