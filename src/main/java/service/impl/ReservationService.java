package service.impl;

import dao.IReservationDao;
import exception.DaoException;
import exception.ServiceException;
import model.Reservation;
import model.ReservationStatus;
import model.Room;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IReservationService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Business logic methods for Reservation entity
 * 
 * @author Sasha
 *
 */
@Service
public class ReservationService implements IReservationService {

	@Inject
	private IReservationDao reservationDao;

	public ReservationService() {}

	@Transactional
	@Override
	public Reservation makeReservation(Room room, User user, LocalDate from, LocalDate to, float price,
								LocalDateTime paymentDeadline) throws ServiceException {
		
		Reservation reservation = new Reservation(room, user, from, to, paymentDeadline, price);
		reservation.setStatus(ReservationStatus.UNPAID);
		try {
			return reservationDao.add(reservation);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public Reservation getById(int reservationId) throws ServiceException {
		try {
			return reservationDao.getById(reservationId, Reservation.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Reservation> getAll() throws ServiceException {
		try {
			return reservationDao.getAll(Reservation.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Reservation> getAllByUser(int userId) throws ServiceException {
		try {
			return reservationDao.getAllByUser(userId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Reservation> getAllByStatus(ReservationStatus status) throws ServiceException {
		try {
			return reservationDao.getAllByStatus(status);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void deleteReservation(int reservationId) throws ServiceException {
		try {
			reservationDao.remove(reservationDao.getById(reservationId, Reservation.class));
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public int getCountOfUnpaid() throws ServiceException {
		try {
			return reservationDao.getCountOfUnpaid();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public int getCountOfPaid() throws ServiceException {
		try {
			return reservationDao.getCountOfPaid();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public int getCountOfConfirmed() throws ServiceException {
		try {
			return reservationDao.getCountOfConfirmed();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void update(Reservation entity) throws ServiceException {
		//todo
	}
}
