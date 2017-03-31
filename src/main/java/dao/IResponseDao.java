package dao;

import exception.DaoException;
import model.ApplicationResponse;

public interface IResponseDao extends IDao<ApplicationResponse> {
	
	ApplicationResponse getResponseByApplication (int applicationId) throws DaoException;
}
