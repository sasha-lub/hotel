package utils.searchfilter;

import model.Room;

/**
 * Filters rooms by price.
 * 
 * @author Sasha
 *
 */
public class PriceFilter extends RoomsFilter {

	private float minPrice;
	private float maxPrice;

	public PriceFilter(RoomsFilter nextFilter, float minPrice, float maxPrice) {
		super(nextFilter);
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	@Override
	public boolean currentAccept(Room room) {
		return room.getPrice() <= maxPrice && room.getPrice() >= minPrice;
	}

}
