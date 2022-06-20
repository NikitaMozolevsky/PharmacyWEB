package com.example.demo.dao.mapper.impl;

import com.example.demo.dao.mapper.Mapper;
import com.example.demo.entity.user.AccessLevel;
import com.example.demo.entity.user.User;
import org.apache.logging.log4j.Level;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.demo.command.constant.UserAttribute.*;

public class UserMapper implements Mapper<User> {

    private static UserMapper userMapper = new UserMapper();

    private UserMapper() {}

    public static UserMapper getInstance() {
        return userMapper;
    }

    @Override
    public Optional<User> mapEntity(ResultSet resultSet) {
        User user = new User();
        Optional<User> optionalUser;

        try {
            user.setUserId(resultSet.getInt(USER_ID));
            user.setUserName(resultSet.getString(USER_NAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPhone(resultSet.getString(PHONE));
            user.setMoneyAmount(Double.parseDouble(resultSet.getString(MONEY_AMOUNT)));
            /*user.setAccessLevel(AccessLevel.valueOf(resultSet.getString(ACCESS_LEVEL)));*/

            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.DEBUG,"user wasn't mapped in UserMapper. {}", e.getMessage());
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }


}
