package web.websocket;

import com.google.gson.JsonObject;
import exception.AppException;
import exception.ServiceException;
import model.ClassOfHotelRoom;
import model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import service.IRoomService;
import service.impl.RoomService;
import utils.searchfilter.*;

import javax.inject.Inject;
import javax.websocket.Session;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sasha
 *         <p>
 *         Search-action handler. Works as a filter for interactive room-search
 */
@EnableAsync
@Component
public class SearchEvent extends SocketEvent {

    @Autowired
    private IRoomService roomService;

    /**
     * @param jsObj   - obtained data from client
     * @param session - client's session
     * @throws AppException Method invokes search function with all needed params, and
     *                      sends processed data to client in right format.
     */
    public void execute(JsonObject jsObj, Session session) throws AppException {
        List<Room> rooms = filterSearch(jsObj);
        session.getAsyncRemote().sendText(createAnswer("rooms", rooms));
    }

    private List<Room> filterSearch(JsonObject jsObj) throws AppException {
        List<Room> all;
        List<Room> result = new ArrayList<Room>();
        try {
            all = roomService.getAll();
            System.out.println(all);
            RoomsFilter filter = null;
            if (!jsObj.get("classOfRoom").isJsonNull()) {
                if (!jsObj.get("classOfRoom").getAsString().trim().isEmpty()) {
                    filter = new ClassFilter(filter, ClassOfHotelRoom.valueOf(jsObj.get("classOfRoom").getAsString()));
                }
            }
            if (!jsObj.get("capacity").isJsonNull()) {
                filter = new CapacityFilter(filter, jsObj.get("capacity").getAsInt());
            }
            if (!jsObj.get("maxPrice").isJsonNull()) {
                filter = new PriceFilter(filter, 0, jsObj.get("maxPrice").getAsFloat());
            }
            if (!jsObj.get("fromDate").isJsonNull() && !jsObj.get("toDate").isJsonNull()
                    && !jsObj.get("fromDate").getAsString().trim().isEmpty()
                    && !jsObj.get("toDate").getAsString().trim().isEmpty()) {

                filter = new DatesFilter(filter, LocalDate.parse(jsObj.get("fromDate").getAsString()),
                        LocalDate.parse(jsObj.get("toDate").getAsString()), roomService);
            }

            for (Room cur : all) {
                if (filter.accept(cur)) {
                    result.add(cur);
                }
            }

            if (!jsObj.get("sort").isJsonNull()) {
                if (jsObj.get("sort").getAsString().equals("up")) {
                    Comparator<Room> byPriceUpDown = (Room o1, Room o2) -> (int) (o2.getPrice() - o1.getPrice());
                    result.sort(byPriceUpDown);
                } else if (jsObj.get("sort").getAsString().equals("down")) {
                    Comparator<Room> byPriceDownUp = (Room o1, Room o2) -> (int) (o1.getPrice() - o2.getPrice());
                    result.sort(byPriceDownUp);
                } else if (jsObj.get("sort").getAsString().equals("rating")) {
                    Comparator<Room> byRating = (Room o1, Room o2) -> (int) (o2.getAvgRating() - o1.getAvgRating());
                    result.sort(byRating);
                }
            }
        } catch (ServiceException e) {
            throw new AppException();
        }

        return result;
    }
}
