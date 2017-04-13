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

//        userService.addUser("balash@s.s", "Balash", "050 111 22 55", "123321");
        roomService.addRoom(2, ClassOfHotelRoom.HONEYMOON, 460.3f,
                "Pretty room with perfect view.", "/resources/images/rooms/honeymoon/3.jpg");
//        reservationService.makeReservation(roomService.getById(3), userService.getById(2),
//                LocalDate.now().plusDays(3), LocalDate.now().plusDays(6), 4545.7f, LocalDateTime.now().plusDays(2));
//        reservationService.makeReservation(roomService.getById(3), userService.getById(2),
//                LocalDate.now().plusDays(3), LocalDate.now().plusDays(6), 4545.7f, LocalDateTime.now().plusDays(2));
//        applicationService.newApplication(userService.getById(2), 2,
//                LocalDate.now().plusDays(3), LocalDate.now().plusDays(6), ClassOfHotelRoom.BUSINESS, "ololo ty huilo");
//        applicationService.newApplication(userService.getById(2), 2,
//                LocalDate.now().plusDays(3), LocalDate.now().plusDays(6), ClassOfHotelRoom.BUSINESS, "ololo ty huilo");


        User user = userService.getByEmail("sasha@s.s");
        userService.setRole(user.getId(),Role.MANAGER);
        userService.setName(user.getId(),"Aleksandra");
        userService.setPhone(user.getId(), "123-456");
    }
}
