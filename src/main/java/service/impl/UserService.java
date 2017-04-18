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
        try {
            if (getByEmail(email) != null) {
                throw new ServiceException("email is already in use");
            }
            User user = new User(email, name, phone, password);
            user.setRole(Role.CLIENT);
            return dao.add(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public User getById(int userId) throws ServiceException {
        try {
            return dao.getById(userId, User.class);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public User getByEmail(String email) throws ServiceException {
        try {
            return dao.getByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return dao.getAll(User.class);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(int userId) throws ServiceException {
        try {
            dao.remove(dao.getById(userId, User.class));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void setRole(int userId, Role role) throws ServiceException {
        try {
            User user = dao.getById(userId, User.class);
            if (user != null) {
                user.setRole(role);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void setName(int userId, String name) throws ServiceException {
        try {
            User user = dao.getById(userId, User.class);
            if (user != null) {
                user.setName(name);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void setPhone(int userId, String phone) throws ServiceException {
        try {
            User user = dao.getById(userId, User.class);
            if (user != null) {
                user.setPhone(phone);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void setEmail(int userId, String email) throws ServiceException {
        try {
            User user = dao.getById(userId, User.class);
            if (user != null) {
                user.setEmail(email);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void setPassword(int userId, String newPassword) throws ServiceException {
        try {
            User user = dao.getById(userId, User.class);
            if (user != null) {
                user.setPassword(newPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public User login(String email, String password)
            throws WrongEmailException, WrongPassException {
        User user = null;
        try {
            user = getByEmail(email);
        } catch (ServiceException e) {
            throw new WrongEmailException();
        }
        if (user == null) {
            throw new WrongEmailException();
        } else if (!user.getPassword().equals(password)) {
            throw new WrongPassException();
        } else {
            return user;
        }
    }
}
