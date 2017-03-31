package dao.impl;

import dao.IReservationDao;
import exception.DaoException;
import model.Application;
import model.Reservation;
import model.ReservationStatus;
import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Data base operations with Reservation entity.
 * 
 * @author Sasha
 *
 */
@Repository
public class ReservationDao extends DAO<Reservation> implements IReservationDao{

	@Override
	public List<Reservation> getAllByStatus(ReservationStatus status) throws DaoException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Reservation> criteria = cb.createQuery(Reservation.class);
		Root<Reservation> root = criteria.from(Reservation.class);
		criteria.select(root)
				.where(cb
						.equal(root
								.get( "status" ) , status));
		List<Reservation> result = getEntityManager().createQuery(criteria).getResultList();
		return result;
	}

	@Override
	public List<Reservation> getAllByUser(int userId) throws DaoException {
		String hql = "SELECT reservation FROM Reservation as reservation WHERE reservation.user.id = "+userId;

		Query query = getEntityManager().createQuery(hql);
		return query.getResultList();
	}

	@Override
	public int getCountOfUnpaid() throws DaoException {
		String hql = "SELECT count(*) FROM Reservation as reservation WHERE reservation.status = 'UNPAID'";

		Query query = getEntityManager().createQuery(hql);
		return Integer.parseInt(query.getResultList().get(0).toString());
	}

	@Override
	public int getCountOfConfirmed() throws DaoException {
		String hql = "SELECT count(*) FROM Reservation as reservation WHERE reservation.status = 'PAID'";

		Query query = getEntityManager().createQuery(hql);
		return Integer.parseInt(query.getResultList().get(0).toString());
	}


	@Override
	public int getCountOfPaid() throws DaoException {
		String hql = "SELECT count(*) FROM Reservation as reservation WHERE reservation.status = 'CONFIRMED'";

		Query query = getEntityManager().createQuery(hql);
		return Integer.parseInt(query.getResultList().get(0).toString());
	}
}
