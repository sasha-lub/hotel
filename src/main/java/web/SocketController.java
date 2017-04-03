package web;

import exception.AppException;
import exception.ServiceException;
import model.Application;
import model.ClassOfHotelRoom;
import model.Reservation;
import model.Room;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import service.IApplicationService;
import service.IReservationService;
import service.IRoomService;
import service.IUserService;
import utils.searchfilter.*;
import utils.validation.Validator;
import web.jsonentity.ApplicationParams;
import web.jsonentity.ReservationId;
import web.jsonentity.SearchParams;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sasha on 01.03.2017.
 */
@EnableAsync
@Controller
public class SocketController {

    @Inject
    private IRoomService roomService;
    @Inject
    private IApplicationService applicationService;
    @Inject
    private IUserService userService;
    @Inject
    private IReservationService reservationService;

    @MessageMapping("/search")
    @SendTo("/search/result")
    private List<Room> search(SearchParams params) throws AppException {

        List<Room> all;
        List<Room> result = new ArrayList<Room>();
        try {
            all = roomService.getAll();
            RoomsFilter filter = null;
            if (!params.getClassOfRoom().isEmpty()) {
                    filter = new ClassFilter(filter, ClassOfHotelRoom.valueOf(params.getClassOfRoom()));
            }
            if (!params.getCapacity().isEmpty()) {
                filter = new CapacityFilter(filter, Integer.parseInt(params.getCapacity()));
            }
            if (!params.getMaxPrice().isEmpty()) {
                filter = new PriceFilter(filter, 0, Float.parseFloat(params.getMaxPrice()));
            }
            if (!params.getFromDate().isEmpty() && !params.getToDate().isEmpty()) {

                filter = new DatesFilter(filter, LocalDate.parse(params.getFromDate()),
                        LocalDate.parse(params.getToDate()), roomService);
            }

            for (Room cur : all) {
                if (filter.accept(cur)) {
                    result.add(cur);
                }
            }

            if (!params.getSort().isEmpty()) {
                if (params.getSort().equals("up")) {
                    Comparator<Room> byPriceUpDown = (Room o1, Room o2) -> (int) (o2.getPrice() - o1.getPrice());
                    result.sort(byPriceUpDown);
                } else if (params.getSort().equals("down")) {
                    Comparator<Room> byPriceDownUp = (Room o1, Room o2) -> (int) (o1.getPrice() - o2.getPrice());
                    result.sort(byPriceDownUp);
                } else if (params.getSort().equals("rating")) {
                    Comparator<Room> byRating = (Room o1, Room o2) -> (int) (o2.getAvgRating() - o1.getAvgRating());
                    result.sort(byRating);
                }
            }
        } catch (ServiceException e) {
            throw new AppException();
        }
        return result;
    }

    @MessageMapping("/newApp")
    @SendTo("/application/new")
    private Application addApp(ApplicationParams params) throws AppException {

        Application app = null;
        int numOfGuests = params.getNumberOfGuests().isEmpty() ? 1 : Integer.parseInt(params.getNumberOfGuests());
        ClassOfHotelRoom classOfHotelRoom = params.getClassOfRoom().isEmpty() ? ClassOfHotelRoom.STANDARD
                : ClassOfHotelRoom.valueOf(params.getClassOfRoom());
        if (Validator.Dates(LocalDate.parse(params.getFromDate()),
                LocalDate.parse(params.getToDate()))) {
            try {
                app = applicationService.newApplication(userService.getById(Integer.parseInt(params.getUserId())), numOfGuests,
                        LocalDate.parse(params.getFromDate()),
                        LocalDate.parse(params.getToDate()), classOfHotelRoom,
                        params.getComment());
            } catch (ServiceException e) {
                throw new AppException(e.getMessage());
            }
        }
        return app;
    }

    @MessageMapping("/appsCount")
    @SendTo("/application/appsCount")
    private int newAppsCount() throws AppException {
        try {
            System.out.println("apps counter invoked");
            return applicationService.getCountOfNewApps();
        } catch (ServiceException e) {
            throw new AppException(e.getMessage());
        }
    }

    @MessageMapping("/newReservation")
    @SendTo("/reservation/newrow")
    private Reservation newReservationRow(ReservationId id) throws AppException {
        Reservation reserve;
        int reserveId = Integer.parseInt(id.getId());
        try {
            reserve = reservationService.getById(reserveId);
            System.out.println(reserve);
        } catch (ServiceException e) {
            throw new AppException();
        }
        return reserve;
    }

    @MessageMapping("/reservationCounters")
    @SendTo("/reservation/counters")
    private ResultObject reservationsCounters() throws AppException {
            try {
                System.out.println("apps counter invoked");
                int paid = reservationService.getCountOfPaid();
                int unpaid = reservationService.getCountOfUnpaid();
                int confirmed = reservationService.getCountOfConfirmed();
                return new ResultObject(paid, unpaid, confirmed);
            } catch (ServiceException e) {
                throw new AppException(e.getMessage());
            }
    }

}

class ResultObject {
    int paid;
    int unpaid;
    int confirmed;
    public ResultObject(int paid, int unpaid, int confirmed) {
        this.paid = paid;
        this.unpaid = unpaid;
        this.confirmed = confirmed;
    }

    public ResultObject() {
    }

    public int getPaid() {
        return paid;
    }

    public int getUnpaid() {
        return unpaid;
    }

    public int getConfirmed() {
        return confirmed;
    }
}