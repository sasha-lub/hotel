package utils.searchfilter;

import exception.AppException;
import model.Room;
import org.springframework.stereotype.Component;

/**
 * Filter-chain pattern abstract class for searching rooms.
 *
 * @author Sasha
 */
public abstract class RoomsFilter {
    private RoomsFilter nextFilter;

    protected RoomsFilter(RoomsFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    protected RoomsFilter() {
    }

    public boolean accept(Room room) throws AppException {
        boolean result = currentAccept(room);
        if (nextFilter != null && result) {
            return nextFilter.accept(room);
        }
        return result;
    }

    public abstract boolean currentAccept(Room room) throws AppException;

}
