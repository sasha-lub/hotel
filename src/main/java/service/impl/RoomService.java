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
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public Room getById(int roomId) throws ServiceException {
		try {
			return dao.getById(roomId, Room.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateAvgRate(int roomId, float rate) throws ServiceException {
		try {
			Room room = dao.getById(roomId, Room.class);
			room.setAvgRating(rate);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Room> getAll() throws ServiceException {
		try {
			return dao.getAll(Room.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Room> getAllByClass(ClassOfHotelRoom classOfRoom) throws ServiceException {
		try {
			return dao.getAllByClass(classOfRoom);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Room> getAllByCapacity(int numberOfGuests) throws ServiceException {
		try {
			return dao.getAllByCapacity(numberOfGuests);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Room> getAllByPrice(float from, float to) throws ServiceException {
		try {
			return dao.getAllByPrice(from, to);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
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
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	@Transactional
	@Override
	public void delete(int roomId) throws ServiceException {
		try {
			dao.remove(dao.getById(roomId, Room.class));
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<String> getAllPhotos(int roomId) throws ServiceException {

		try {
			return dao.getRoomPhotos(roomId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void addNewPhoto(int roomId, String photoUrl) throws ServiceException {
		try {
			dao.addRoomPhoto(roomId, photoUrl);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Recall> getAllRecalls(int roomId) throws ServiceException {
		try {
			return dao.getRoomRecalls(roomId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void addNewRecall(User user, Room room, int rate, String recallComment) throws ServiceException {

		Recall recall = new Recall(user, rate, recallComment);
		try {
			dao.addRoomRecall(room.getId(), recall);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public float getRoomAvgRate(int roomId) throws ServiceException {
		try {
			return dao.getRoomAvgRate(roomId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public Recall getUserRecallForRoom(int userId, int roomId) throws ServiceException {
		try {
			return dao.getUserRecallForRoom(userId, roomId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void updateRecallRate(Recall recall, int rate) {
		recall.setRate(rate);
	}


	@Transactional
	@Override
	public void updateRecallComment(Recall recall, String comment){
		recall.setComment(comment);
	}
}
