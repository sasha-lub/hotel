package dao;

import exception.DaoException;
import model.Application;

import java.util.List;


public interface IApplicationDao extends IDao<Application>{
	
	List<Application> getAllByUser(int userId) throws DaoException;

	List<Application> getAllNew() throws DaoException;

	int getCountOfNewApps() throws DaoException;

}
