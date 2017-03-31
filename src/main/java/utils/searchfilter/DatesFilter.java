package utils.searchfilter;

import java.time.LocalDate;

import dao.IRoomDao;
import dao.impl.RoomDao;
import exception.AppException;
import exception.ServiceException;
import model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import service.IRoomService;
import service.impl.RoomService;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Filters rooms by availability between two dates.
 * 
 * @author Sasha
 *
 */
@Component
public class DatesFilter extends RoomsFilter{
	@Inject
	private IRoomService roomService;

	private LocalDate from;
	private LocalDate to;

	public DatesFilter(RoomsFilter nextFilter, LocalDate from, LocalDate to, IRoomService roomService) {
		super(nextFilter);
		this.from = from;
		this.to = to;
		this.roomService = roomService;
	}

	public DatesFilter(){}

	@Override
	public boolean currentAccept(Room room) throws AppException {
		System.out.println("ROOM: "+room);
		System.out.println(roomService);
		try {
			return roomService.isAvailable(room.getId(), from, to);
		} catch (ServiceException e) {
			throw new AppException();
		}
	}
}
