package web;

import exception.AppException;
import exception.ServiceException;
import model.Recall;
import model.Reservation;
import model.ReservationStatus;
import model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IReservationService;
import service.IRoomService;
import web.constants.AnswerStatus;
import web.constants.Path;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/room")
public class RoomController {

    @Inject
    private IReservationService reservationService;
    @Inject
    private IRoomService roomService;

    @RequestMapping(value = "changeReservationStatus", method = RequestMethod.GET)
    public ResponseEntity changeReservationStatus(HttpSession session,
                                                  int reserveId,
                                                  String status) throws AppException {try {
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
                               Map< String, Object > model,
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

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public ResponseEntity checkIfAvailable(HttpSession session,
                                   Map< String, Object > model,
                                   int roomId,
                                   String from,
                                   String to) throws AppException {
        try {
            if (from == null || to == null){
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
}

