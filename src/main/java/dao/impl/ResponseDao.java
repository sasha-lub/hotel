package dao.impl;

import dao.IResponseDao;
import exception.DaoException;
import model.Application;
import model.ApplicationResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Data base operations with ApplicationResponse entity.
 *
 * @author Sasha
 */

@Repository
public class ResponseDao extends DAO<ApplicationResponse> implements IResponseDao {

    @Override
    public ApplicationResponse getResponseByApplication(int applicationId) throws DaoException {
        String hql = "SELECT response FROM ApplicationResponse as response WHERE response.application.id =" + applicationId;
        Query query = getEntityManager().createQuery(hql);
        List<ApplicationResponse> result = query.getResultList();
        return result.size() == 1 ? result.get(0) : null;
    }
}