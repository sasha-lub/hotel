package dao;

import java.util.List;

import exception.DaoException;

public interface IDao<T> {
	
	T add (T entity) throws DaoException;

	T getById(int id, Class<T> clazz) throws DaoException;
	
	List<T> getAll(Class<T> clazz) throws DaoException;
	
	void remove (T entity) throws DaoException;

}
