package service.impl;

import dao.IRoomDao;
import exception.DaoException;
import exception.ServiceException;
import model.ClassOfHotelRoom;
import model.Recall;
import model.Room;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IRoomService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * Business logic methods for Room entity
 * 
 * @author Sasha
 *
 */
@Service
public class RoomService implements IRoomService {

	@Inject
	private IRoomDao dao;

	public RoomService() {
	}

	@Transactional
	@Override
	public Room addRoom(int capacity, ClassOfHotelRoom classOfRoom, float price, String description, String photoUrl)
			throws ServiceException {
		try {
			return dao.add(new Room(capacity, classOfRoom, price, description, photoUrl));
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public Room getById(int roomId) throws ServiceException {
		try {
			return dao.getById(roomId, Room.class);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public List<Room> getAll() throws ServiceException {
		try {
			return dao.getAll(Room.class);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public List<Room> getAllByClass(ClassOfHotelRoom classOfRoom) throws ServiceException {
		try {
			return dao.getAllByClass(classOfRoom);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public List<Room> getAllByCapacity(int numberOfGuests) throws ServiceException {
		try {
			return dao.getAllByCapacity(numberOfGuests);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public List<Room> getAllByPrice(float from, float to) throws ServiceException {
		try {
			return dao.getAllByPrice(from, to);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean isAvailable(int roomId, LocalDate from, LocalDate to) throws ServiceException {

		try {
			List<Room> unAvailableRooms = dao.getAllUnAvailableForDates(from, to);
			if (unAvailableRooms != null) {
				Room room = dao.getById(roomId, Room.class);
				return !unAvailableRooms.contains(room);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return false;
	}

	@Transactional
	@Override
	public void delete(int roomId) throws ServiceException {
		try {
			dao.remove(dao.getById(roomId, Room.class));
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void update(Room room) throws ServiceException {

	}

	@Transactional
	@Override
	public List<String> getAllPhotos(int roomId) throws ServiceException {

		try {
			return dao.getRoomPhotos(roomId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void addNewPhoto(int roomId, String photoUrl) throws ServiceException {
		try {
			dao.addRoomPhoto(roomId, photoUrl);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public List<Recall> getAllRecalls(int roomId) throws ServiceException {
		try {
			return dao.getRoomRecalls(roomId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public Recall addNewRecall(User user, Room room, int rate, String recallComent) throws ServiceException {

		Recall recall = new Recall(user, rate, recallComent);
		try {
			return dao.addRoomRecall(room.getId(), recall);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public float getRoomAvgRate(int roomId) throws ServiceException {
		try {
			return dao.getRoomAvgRate(roomId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public Recall getUserRecallForRoom(int userId, int roomId) throws ServiceException {
		try {
			return dao.getUserRecallForRoom(userId, roomId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void updateRecall(Recall recall) throws ServiceException {

	}
}
