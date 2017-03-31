package dao.impl;

import dao.IResponseDao;
import exception.DaoException;
import model.Application;
import model.ApplicationResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Data base operations with ApplicationResponse entity.
 * 
 * @author Sasha
 *
 */

@Repository
public class ResponseDao extends DAO<ApplicationResponse> implements IResponseDao{

	@Override
	public ApplicationResponse getResponseByApplication(int applicationId) throws DaoException {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ApplicationResponse> criteria = cb.createQuery(ApplicationResponse.class);
		Root<ApplicationResponse> root = criteria.from(ApplicationResponse.class);
		criteria.select(root)
				.where(cb
						.equal(root
								.get( "app_id" ) , applicationId));
		List<ApplicationResponse> result = getEntityManager().createQuery(criteria).getResultList();
		return result.size()==1?result.get(0):null;
	}
}