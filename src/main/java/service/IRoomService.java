package service;

import java.time.LocalDate;
import java.util.List;

import exception.ServiceException;
import model.ClassOfHotelRoom;
import model.Recall;
import model.Room;
import model.User;

public interface IRoomService {

	Room addRoom(int capacity, ClassOfHotelRoom classOfRoom, float price, String description, String photoUrl)
			throws ServiceException;

	Room getById(int roomId) throws ServiceException;

	void updateAvgRate(int roomId, float rate) throws ServiceException;

	List<Room> getAll() throws ServiceException;

	List<Room> getAllByClass(ClassOfHotelRoom classOfRoom) throws ServiceException;

	List<Room> getAllByCapacity(int numberOfGuests) throws ServiceException;

	List<Room> getAllByPrice(float from, float to) throws ServiceException;

	void delete(int roomId) throws ServiceException;

	List<String> getAllPhotos(int roomId) throws ServiceException;

	void addNewPhoto(int roomId, String photoUrl) throws ServiceException;

	List<Recall> getAllRecalls(int roomId) throws ServiceException;

	void addNewRecall(User user, Room room, int rate, String recall) throws ServiceException;

	float getRoomAvgRate(int roomId) throws ServiceException;

	boolean isAvailable(int roomId, LocalDate from, LocalDate to) throws ServiceException;
	
	Recall getUserRecallForRoom(int userId, int roomId) throws ServiceException;

	void updateRecallRate(Recall recall, int rate) throws ServiceException;

	void updateRecallComment(Recall recall, String comment) throws ServiceException;



}
