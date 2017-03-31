package dao;

import exception.DaoException;
import model.User;

public interface IUserDao extends IDao<User>{

	User getByEmail(String email) throws DaoException;
	
}
