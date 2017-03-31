package service.impl;

import dao.IUserDao;
import exception.DaoException;
import exception.ServiceException;
import exception.WrongEmailException;
import exception.WrongPassException;
import model.Role;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IUserService;

import javax.inject.Inject;
import java.util.List;

/**
 * Business logic methods for User entity
 * 
 * @author Sasha
 *
 */
@Service
public class UserService implements IUserService {

	@Inject
	IUserDao dao;

	public UserService() {
	}

	@Transactional
	@Override
	public User addUser(String email, String name, String phone, String password) throws ServiceException {
		try{
			if(getByEmail(email) != null){
				throw new DaoException();
			}
			User user = new User(email, name, phone, password);
			user.setRole(Role.CLIENT);
			return dao.add(user);}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public User getById(int userId) throws ServiceException {
		try {
			return dao.getById(userId, User.class);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public User getByEmail(String email) throws ServiceException {
		try {
			return dao.getByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public List<User> getAll() throws ServiceException {
		try {
			return dao.getAll(User.class);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(int userId) throws ServiceException {
		try {
			dao.remove(dao.getById(userId, User.class));
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void update(User user) throws ServiceException {

	}

	@Transactional
	@Override
	public boolean login(String email, String password)
			throws ServiceException, WrongEmailException, WrongPassException {
		return false;
	}
}
