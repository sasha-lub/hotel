package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import exception.DaoException;
import exception.ServiceException;
import model.Reservation;
import model.ReservationStatus;
import model.Room;
import model.User;

public interface IReservationService {
	
	Reservation makeReservation(Room room, User user, LocalDate from, LocalDate to, float price,
						 LocalDateTime paymentDeadline) throws ServiceException;
	
	Reservation getById(int reservationId) throws ServiceException;
	
	void update(Reservation entity) throws ServiceException;		
	
	List<Reservation> getAll() throws ServiceException;

	List<Reservation> getAllByUser(int userId) throws ServiceException;
	
	List<Reservation> getAllByStatus(ReservationStatus status) throws ServiceException;
	
	void deleteReservation(int reservationId) throws ServiceException;

	public int getCountOfUnpaid() throws ServiceException;

	public int getCountOfPaid() throws ServiceException;

	public int getCountOfConfirmed() throws ServiceException;

}
