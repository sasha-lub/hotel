package web;

import com.google.gson.Gson;
import exception.AppException;
import exception.ServiceException;
import model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IApplicationService;
import service.IReservationService;
import service.IRoomService;
import service.IUserService;
import service.impl.ApplicationService;
import utils.searchfilter.*;
import web.constants.AnswerStatus;
import web.constants.Path;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/room")
public class RoomController {

    @Inject
    private IReservationService reservationService;
    @Inject
    private IRoomService roomService;
    @Inject
    private IApplicationService applicationService;
    @Inject
    private IUserService userService;

    @RequestMapping(value = "changeReservationStatus", method = RequestMethod.GET)
    public ResponseEntity changeReservationStatus(HttpSession session,
                                                  int reserveId,
                                                  String status) throws AppException {
        try {
            Reservation reservation = reservationService.getById(reserveId);
            reservation.setStatus(ReservationStatus.valueOf(status));
            reservationService.update(reservation);

            return new ResponseEntity(HttpStatus.OK);
        } catch (NumberFormatException | ServiceException e) {
            throw new AppException(e.getMessage());
        }
    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(HttpSession session,
                       Map<String, Object> model,
                       int roomId) throws AppException {
        try {
            Room room = roomService.getById(roomId);
            room.setAvgRating(roomService.getRoomAvgRate(room.getId()));
            room.setPhotos(roomService.getAllPhotos(room.getId()));
            List<Recall> recalls = roomService.getAllRecalls(room.getId());
            model.put("room", room);
            model.put("recalls", recalls);

        } catch (ServiceException e) {
            throw new AppException(e.getMessage());
        }
        return Path.PAGE_ROOM;
    }

    @RequestMapping(value = "app", method = RequestMethod.GET)
    public ResponseEntity addApp(HttpSession session,
                                 Map<String, Object> model,
                                 int userId,
                                 int numberOfGuests,
                                 String fromDate,
                                 String toDate,
                                 String classOfRoom,
                                 String comment) throws AppException {
        try {
            applicationService.newApplication(
                    userService.getById(userId),
                    numberOfGuests,
                    LocalDate.parse(fromDate),
                    LocalDate.parse(toDate),
                    ClassOfHotelRoom.valueOf(classOfRoom),
                    comment
            );
        } catch (ServiceException e) {
            throw new AppException(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public ResponseEntity checkIfAvailable(HttpSession session,
                                           Map<String, Object> model,
                                           int roomId,
                                           String from,
                                           String to) throws AppException {
        try {
            if (from == null || to == null) {
                return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));
            } else {
                LocalDate fromDate = LocalDate.parse(from);
                LocalDate toDate = LocalDate.parse(to);
                if (!roomService.isAvailable(roomId, fromDate, toDate)) {
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.NOT_AVAILABLE));
                } else {
                    return new ResponseEntity(HttpStatus.OK);
                }
            }
        } catch (ServiceException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> filterSearch(HttpSession session,
                                                   Map<String, Object> model,
                                                   String classOfRoom,
                                                   int capacity,
                                                   int maxPrice,
                                                   String from,
                                                   String to,
                                                   String sort) throws AppException {
        List<Room> all;
        List<Room> result = new ArrayList<Room>();
        try {
            all = roomService.getAll();
            System.out.println(all);
            RoomsFilter filter = null;
            if (!classOfRoom.isEmpty()) {
                filter = new ClassFilter(filter, ClassOfHotelRoom.valueOf(classOfRoom));
            }
            if (capacity != 0) {
                filter = new CapacityFilter(filter, capacity);
            }
            if (maxPrice != 0) {
                filter = new PriceFilter(filter, 0, maxPrice);
            }
            if (!from.isEmpty() && !to.isEmpty()) {
                filter = new DatesFilter(filter, LocalDate.parse(from),
                        LocalDate.parse(to), roomService);
            }

            for (Room cur : all) {
                if (filter.accept(cur)) {
                    result.add(cur);
                }
            }

            if (!sort.isEmpty()) {
                if (sort.equals("up")) {
                    Comparator<Room> byPriceUpDown = (Room o1, Room o2) -> (int) (o2.getPrice() - o1.getPrice());
                    result.sort(byPriceUpDown);
                } else if (sort.equals("down")) {
                    Comparator<Room> byPriceDownUp = (Room o1, Room o2) -> (int) (o1.getPrice() - o2.getPrice());
                    result.sort(byPriceDownUp);
                } else if (sort.equals("rating")) {
                    Comparator<Room> byRating = (Room o1, Room o2) -> (int) (o2.getAvgRating() - o1.getAvgRating());
                    result.sort(byRating);
                }
            }
        } catch (ServiceException e) {
            throw new AppException();
        }

        return ResponseEntity.ok(result);
    }
}

