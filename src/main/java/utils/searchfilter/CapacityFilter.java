package utils.searchfilter;

import model.Room;

/**
 * Filters rooms by number of beds.
 * 
 * @author Sasha
 *
 */

public class CapacityFilter extends RoomsFilter{

	private int capacity; 

	public CapacityFilter(RoomsFilter next, int capacity) {
		super(next);
		this.capacity = capacity;
	}

	@Override
	public boolean currentAccept(Room room) {
		if (room != null) {
			return capacity == room.getCapacity();
		}
		return false;
	}
}
