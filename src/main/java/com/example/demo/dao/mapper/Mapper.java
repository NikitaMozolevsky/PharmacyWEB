package com.example.demo.dao.mapper;

import com.example.demo.entity.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.Optional;

@FunctionalInterface
public interface Mapper <T extends AbstractEntity> {
    Logger logger = LogManager.getLogger();

    Optional<T> map(ResultSet resultSet);

}
