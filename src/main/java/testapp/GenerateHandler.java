package testapp;

import exception.ServiceException;
import model.Application;
import model.ClassOfHotelRoom;
import model.Role;
import model.User;
import org.springframework.stereotype.Component;
import service.IApplicationService;
import service.IReservationService;
import service.IRoomService;
import service.IUserService;
import service.impl.ApplicationService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Sasha on 19.02.2017.
 */
@Component
public class GenerateHandler {

    @Inject
    IUserService userService;

    @Inject
    IRoomService roomService;

    @Inject
    IApplicationService applicationService;

    @Inject
    IReservationService reservationService;

    public void run() throws ServiceException {
        User u = userService.getByEmail("sasha@s.s");
        userService.setRole(u.getId(), Role.ADMIN);
    }
}
