package dao.impl;

import dao.IUserDao;
import exception.DaoException;
import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Data base operations with User entity.
 * 
 * @author Sasha
 *
 */
@Repository
public class UserDao extends DAO<User> implements IUserDao {

	@Override
	public User getByEmail(String email) throws DaoException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root)
				.where(cb
						.equal(root
								.get( "email" ),email));

		List<User> result = getEntityManager().createQuery(criteria).getResultList();
		return result.size()==1?result.get(0):null;
	}
}
