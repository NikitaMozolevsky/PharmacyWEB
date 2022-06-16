package com.example.demo.dao.mapper.impl;

import com.example.demo.dao.mapper.Mapper;
import com.example.demo.entity.AccessLevel;
import com.example.demo.entity.User;
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
    public Optional<User> map(ResultSet resultSet) {
        return Optional.empty();
    }


}
