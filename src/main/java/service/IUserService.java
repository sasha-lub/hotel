package service;

import java.util.List;

import exception.ServiceException;
import exception.WrongEmailException;
import exception.WrongPassException;
import model.Role;
import model.User;

public interface IUserService {

    User addUser(String email, String name, String phone, String password) throws ServiceException;

    User getById(int userId) throws ServiceException;

    User getByEmail(String email) throws ServiceException;

    List<User> getAll() throws ServiceException;

    void delete(int userId) throws ServiceException;

    void setRole(int userId, Role role) throws ServiceException;

    void setName(int userId, String name) throws ServiceException;

    void setPhone(int userId, String phone) throws ServiceException;

    void setEmail(int userId, String email) throws ServiceException;

    void setPassword(int userId, String newPassword) throws ServiceException;

    boolean login(String email, String password) throws ServiceException, WrongPassException, WrongEmailException;

}
