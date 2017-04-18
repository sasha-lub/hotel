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


    }
}
