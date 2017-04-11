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
import service.impl.ReservationService;
import service.impl.RoomService;
import service.impl.UserService;
import utils.encoding.Codec;
import utils.validation.Validator;
import web.constants.AnswerStatus;
import web.constants.Path;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/client")
public class ClientController {

    @Inject
    private IUserService userService;
    @Inject
    private IApplicationService applicationService;
    @Inject
    private IReservationService reservationService;
    @Inject
    private IRoomService roomService;

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String account(HttpSession session,
                          Map< String, Object > model ) throws AppException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.put("errorMessage", "You are not authorized");
            return Path.PAGE_ERROR_PAGE;
        } else {

            try {
//                ApplicationResponse appResponse = null;
//                for (Application application : apps) {
//                    appResponse = applicationService.getResponseForApplication(application.getId());
//                    application.setResponse(appResponse);
//                }
                model.put("reserves", reservationService.getAllByUser(user.getId()));
                model.put("apps", applicationService.getAllApplicationsByUser(user.getId()));
            } catch (ServiceException e) {
                throw new AppException(e.getMessage());
            }
            return Path.PAGE_ACCOUNT;
        }
    }

    @RequestMapping(value = "recall", method = RequestMethod.GET)
    public void addRecall(HttpSession session,
                          Map< String, Object > model,
                            int roomId,
                            int userId,
                            int rate,
                            String comment) throws AppException {

            try {
                Recall r = roomService.getUserRecallForRoom(userId, roomId);

                if (r != null) {
                    r.setRate(rate);
                    r.setComment(comment);
                    roomService.updateRecall(r);
                } else {
                    roomService.addNewRecall(userService.getById(userId), roomService.getById(roomId), rate, comment);
                }
                Room room = roomService.getById(roomId);
                room.setAvgRating(roomService.getRoomAvgRate(roomId));
                roomService.update(room);
            } catch (ServiceException e) {
                throw new AppException();
            }
        }

    @RequestMapping(value = "reserve", method = RequestMethod.POST)
    public ResponseEntity reserve(HttpSession session,
                                   Map< String, Object > model,
                                  Writer writer,
                                  int roomId,
                                   String from,
                                   String to) throws AppException, IOException {

        try {
            User user = (User)session.getAttribute("user");
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            if (user == null) {
                return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.UNAUTHORIZED));
            } else if (fromDate == null || toDate == null) {
                return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));
            } else {
                if (!Validator.Dates(fromDate, toDate)) {
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));
                } else if (!roomService.isAvailable(roomId, fromDate, toDate)) {
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.NOT_AVAILABLE));
                } else {
                    int days = (int) ChronoUnit.DAYS.between(fromDate, toDate);
                    float pricePerDay = roomService.getById(roomId).getPrice();
                    float totalPrice = pricePerDay * days;
                    Reservation reservation = reservationService.makeReservation(roomService.getById(roomId), user,
                             fromDate.plusDays(1), toDate.plusDays(1),
                            totalPrice, LocalDateTime.now().plusDays(2));
                    writer.write(new Gson().toJson(reservation.getId()));
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.OK));
                }
            }
        } catch (ServiceException e) {
            return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.SERVER_ERROR));
        }
    }


    @RequestMapping(value = "deleteReserve", method = RequestMethod.GET)
    public ResponseEntity deleteReserve(HttpSession session,
                          Map< String, Object > model,
                          int reserveId) throws AppException {
        try {
            Reservation reservation = reservationService.getById(reserveId);
            if (reservation.getStatus() == ReservationStatus.CONFIRMED) {
                return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.NOT_AVAILABLE));

            } else {
                reservationService.deleteReservation(reserveId);
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (NumberFormatException | ServiceException e) {
            throw new AppException(e.getMessage());
        }
    }

    @RequestMapping(value = "editInfo", method = RequestMethod.GET)
    public ResponseEntity editInfo(HttpSession session,
                                        Map< String, Object > model,
                                   String field,
                                   String password,
                                   String name,
                                   String email,
                                   String phone,
                                   String newPass1,
                                   String newPass2) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.UNAUTHORIZED));

        }if (!Validator.Password(password)) {
            return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));

        } else if (!user.getPassword().equals(Codec.md5(password))) {
            return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.NOT_AVAILABLE));

        }

        if (field.trim().isEmpty()) {
            return null;
        }
        try {
            switch (field) {
            case "name": {
                if (Validator.Name(name)) {
                    userService.setName(user.getId(), name);
                } else {
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));
                }
                break;
            }
            case "phone": {

                if (Validator.Phone(phone)) {
                    userService.setPhone(user.getId(), phone);
                } else {
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));
                }
                break;
            }
            case "email": {
                if (Validator.Email(email) && userService.getByEmail(email) == null) {
                    userService.setEmail(user.getId(), email);
                } else {
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));
                }
                break;
            }
            case "password": {
                if (Validator.Password(newPass1) && newPass1.equals(newPass2)) {
                    userService.setPassword(user.getId(), Codec.md5(newPass1));
                } else {
                    return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.INVALID_INPUT));
                }
                break;
            }
        }
        } catch (ServiceException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

