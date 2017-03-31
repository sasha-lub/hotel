package dao;

import java.util.List;

import exception.DaoException;
import model.Reservation;
import model.ReservationStatus;

public interface IReservationDao extends IDao<Reservation>{
	
	List<Reservation> getAllByStatus (ReservationStatus status) throws DaoException;
	
	List<Reservation> getAllByUser (int userId) throws DaoException;

	public int getCountOfUnpaid() throws DaoException;

	public int getCountOfPaid() throws DaoException;

	public int getCountOfConfirmed() throws DaoException;

}
