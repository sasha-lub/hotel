package utils.searchfilter;

import model.ClassOfHotelRoom;
import model.Room;

/**
 * Filters rooms by class.
 * 
 * @author Sasha
 *
 */
public class ClassFilter extends RoomsFilter {
	private ClassOfHotelRoom classOfRoom;

	public ClassFilter(RoomsFilter next, ClassOfHotelRoom classOfRoom) {
		super(next);
		this.classOfRoom = classOfRoom;
	}

	@Override
	public boolean currentAccept(Room room) {
		if (room != null) {
			return classOfRoom.equals(room.getClassOfRoom());
		}
		return false;
	}
}
