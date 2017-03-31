package service;

import java.util.List;

import exception.ServiceException;
import exception.WrongEmailException;
import exception.WrongPassException;
import model.User;

public interface IUserService {
	
	User addUser(String email, String name, String phone, String password) throws ServiceException;
	
	User getById(int userId) throws ServiceException;
	
	User getByEmail(String email) throws ServiceException;
	
	List<User> getAll() throws ServiceException;
	
	void delete(int userId) throws ServiceException;
	
	void update(User user) throws ServiceException;
	
	boolean login(String email, String password) throws ServiceException, WrongPassException, WrongEmailException;
	
}
