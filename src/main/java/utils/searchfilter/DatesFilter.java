package utils.searchfilter;

import exception.AppException;
import exception.ServiceException;
import model.Room;
import service.IRoomService;

import java.time.LocalDate;

/**
 * Filters rooms by availability between two dates.
 * 
 * @author Sasha
 *
 */
public class DatesFilter extends RoomsFilter{
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
		try {
			System.out.println(roomService.isAvailable(room.getId(), from, to));
			return roomService.isAvailable(room.getId(), from, to);
		} catch (ServiceException e) {
			throw new AppException();
		}
	}
}
