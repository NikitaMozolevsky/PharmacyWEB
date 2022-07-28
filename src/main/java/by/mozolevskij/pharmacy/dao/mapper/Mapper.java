package by.mozolevskij.pharmacy.dao.mapper;

import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.entity.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@FunctionalInterface
public interface Mapper <T extends AbstractEntity> {
    Logger logger = LogManager.getLogger();

    Optional<T> mapEntity(ResultSet resultSet) throws SQLException, DaoException;

}
