package by.mozolevskij.pharmacy.dao.mapper.impl;

import by.mozolevskij.pharmacy.dao.mapper.Mapper;
import by.mozolevskij.pharmacy.entity.user.AccessLevel;
import by.mozolevskij.pharmacy.entity.user.User;
import org.apache.logging.log4j.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;

public class UserMapper implements Mapper<User> {

    private static UserMapper userMapper = new UserMapper();

    private UserMapper() {}

    public static UserMapper getInstance() {
        return userMapper;
    }

    @Override
    public Optional<User> mapEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        Optional<User> optionalUser;
        /*if (resultSet.getString(USER_NAME)!=null) {

        }*/
        try {
            user.setUserId(resultSet.getInt(USER_ID));
            user.setUserName(resultSet.getString(USER_NAME));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPhone(resultSet.getString(PHONE));
            user.setMoneyAmount(Double.parseDouble(resultSet.getString(MONEY_AMOUNT)));
            user.setAccessLevel(AccessLevel.valueOf(resultSet.getString(ACCESS_LEVEL)));

            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.DEBUG,"user wasn't mapped in UserMapper. {}", e.getMessage());
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }


}
