package dao.impl;

import dao.IRoomDao;
import exception.DaoException;
import model.ClassOfHotelRoom;
import model.Recall;
import model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * Data base operations with Room entity.
 * 
 * @author Sasha
 *
 */

@Repository
public class RoomDao extends DAO<Room> implements IRoomDao {


	@Override
	public List<Room> getAllByClass(ClassOfHotelRoom classOfRoom) throws DaoException {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Room> criteria = cb.createQuery(Room.class);
		Root<Room> root = criteria.from(Room.class);
		criteria.select(root)
				.where(cb
						.equal(root
								.get( "classOfRoom" ), classOfRoom));
		List<Room> result = getEntityManager().createQuery(criteria).getResultList();
		return result;
	}

	@Override
	public List<Room> getAllByPrice(float fromPrice, float toPrice) throws DaoException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Room> criteria = cb.createQuery(Room.class);
		Root<Room> root = criteria.from(Room.class);
		criteria.select(root)
				.where(cb
						.between(root
								.get( "price" ) , fromPrice , toPrice));
		List<Room> result = getEntityManager().createQuery(criteria).getResultList();
		return result;
	}

	@Override
	public List<Room> getAllUnAvailableForDates(LocalDate fromDate, LocalDate toDate) throws DaoException {
		String hql = "SELECT DISTINCT room FROM Room as room LEFT JOIN Reservation as reserv " +
				"on room.id = reserv.room.id " +
				"WHERE :fromdate BETWEEN reserv.checkInDate AND reserv.checkOutDate " +
				"OR :todate BETWEEN reserv.checkInDate AND reserv.checkOutDate " +
				"OR reserv.checkInDate BETWEEN :fromdate AND :todate " +
				"OR reserv.checkOutDate BETWEEN :fromdate AND :todate";

		Query query = getEntityManager().createQuery(hql).setParameter("fromdate", fromDate).setParameter("todate", toDate);
		return query.getResultList();
	}

	@Override
	public List<Room> getAllByCapacity(int numberOfGuests) throws DaoException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Room> criteria = cb.createQuery(Room.class);
		Root<Room> root = criteria.from(Room.class);
		criteria.select(root)
				.where(cb
						.equal(root
								.get( "capacity" ) , numberOfGuests));
		List<Room> result = getEntityManager().createQuery(criteria).getResultList();
		return result;
	}

	@Override
	public List<String> getRoomPhotos(int roomId) throws DaoException {

		return null;
	}

	@Override
	public float getRoomAvgRate(int roomId) throws DaoException {
		String hql = "SELECT AVG (recall.rate) FROM Recall as recall WHERE recall.room.id ="+roomId;

		Query query = getEntityManager().createQuery(hql);
		return query.getFirstResult();
	}

	@Override
	public List<Recall> getRoomRecalls(int roomId) throws DaoException {

		return null;
	}

	@Override
	public void addRoomPhoto(int roomId, String photoUrl) throws DaoException {

	}

	@Override
	public Recall addRoomRecall(int roomId, Recall recall) throws DaoException {
		return null;
	}

	@Override
	public Recall getUserRecallForRoom(int userId, int roomId) throws DaoException {

		return null;
	}

}