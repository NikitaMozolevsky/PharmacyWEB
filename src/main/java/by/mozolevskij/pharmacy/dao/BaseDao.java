package by.mozolevskij.pharmacy.dao;

import by.mozolevskij.pharmacy.entity.AbstractEntity;
import by.mozolevskij.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract boolean delete(T t) throws DaoException;
    public abstract List<Optional<T>> findAll() throws DaoException;
    public abstract T update(T t) throws DaoException;

}
