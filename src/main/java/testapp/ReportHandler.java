package testapp;

import exception.ServiceException;
import model.ReservationStatus;
import org.springframework.stereotype.Component;
import service.IApplicationService;
import service.IReservationService;
import service.IRoomService;
import service.IUserService;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * Created by Sasha on 19.02.2017.
 */
@Component
public class ReportHandler {

    @Inject
    IUserService userService;

    @Inject
    IRoomService roomService;

    @Inject
    IApplicationService applicationService;

    @Inject
    IReservationService reservationService;
    public void run() throws ServiceException {
//        System.out.println(roomService.isAvailable(3,
//                LocalDate.of(2017, 3, 3), LocalDate.of(2017, 3, 8)));
//        System.out.println(roomService.isAvailable(3,
//                LocalDate.of(2017, 3, 6), LocalDate.of(2017, 3, 22)));
//        System.out.println(roomService.isAvailable(3,
//                LocalDate.of(2017, 2, 12), LocalDate.of(2017, 3, 2)));
//        System.out.println(roomService.isAvailable(3,
//                LocalDate.of(2017, 2, 12), LocalDate.of(2017, 3, 3)));

        System.out.println(roomService.getAll());
        System.out.println(userService.getAll());

    }
}
