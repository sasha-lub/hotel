package dao.impl;

import dao.IApplicationDao;
import exception.DaoException;
import model.Application;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data base operations with Application entity.
 * 
 * @author Sasha
 *
 */

@Repository
public class ApplicationDao extends DAO<Application> implements IApplicationDao {

	@Override
	public List<Application> getAllByUser(int userId) throws DaoException {
		String hql = "SELECT app FROM Application as app WHERE app.user.id = "+userId;

		Query query = getEntityManager().createQuery(hql);
		return query.getResultList();
	}
	
	@Override
	public List<Application> getAllNew() throws DaoException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Application> criteria = cb.createQuery(Application.class);
		Root<Application> root = criteria.from(Application.class);
		criteria.select(root)
				.where(cb
						.equal(root
								.get("isNew"), true));
		List<Application> result = getEntityManager().createQuery(criteria).getResultList();
		return result;
	}

	@Override
	public int getCountOfNewApps() throws DaoException {
		String hql = "SELECT count(*) FROM Application as application WHERE application.isNew = true";

		Query query = getEntityManager().createQuery(hql);
		return Integer.parseInt(query.getResultList().get(0).toString());
	}
}
