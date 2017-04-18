package dao;

import java.time.LocalDate;
import java.util.List;

import exception.DaoException;
import model.ClassOfHotelRoom;
import model.Photo;
import model.Recall;
import model.Room;

public interface IRoomDao extends IDao<Room>{
	
	List<Room> getAllByClass(ClassOfHotelRoom classOfRoom) throws DaoException;
	
	List<Room> getAllByPrice(float fromPrice, float toPrice) throws DaoException;
	
	List<Room> getAllByCapacity(int numberOfGuests) throws DaoException;
	
	List<Photo> getRoomPhotos(int roomId) throws DaoException;
	
	float getRoomAvgRate(int roomId) throws DaoException;

	List<Recall> getRoomRecalls(int roomId) throws DaoException;
	
	void addRoomPhoto(int roomId, String photoUrl) throws DaoException;
	
	void addRoomRecall(int roomId, Recall recall) throws DaoException;

	List<Room> getAllUnAvailableForDates(LocalDate fromDate, LocalDate toDate) throws DaoException;

	Recall getUserRecallForRoom(int userId, int roomId) throws DaoException;

}
