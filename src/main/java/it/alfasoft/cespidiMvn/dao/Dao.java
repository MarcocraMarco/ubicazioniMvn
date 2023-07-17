package it.alfasoft.cespidiMvn.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao <T,I> {
    List<T> list() throws DaoException, SQLException;
    I add(T elemento)throws DaoException;
    I delete(I id) throws DaoException;
    I update(I id, T elemento)  throws DaoException;
    T getById(I indice)throws DaoException;
    List<T> find(String searchText) throws DaoException;
    List<T> find(T searchtext) throws DaoException;
}
