package web;

import exception.AppException;
import exception.ServiceException;
import exception.WrongEmailException;
import exception.WrongPassException;
import model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.IApplicationService;
import service.IReservationService;
import service.IUserService;
import utils.encoding.Codec;
import utils.validation.Validator;
import web.constants.AnswerStatus;
import web.constants.Path;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/account")
public class AccountController {

    @Inject
    private IApplicationService applicationService;
    @Inject
    private IReservationService reservationService;
    @Inject
    private IUserService userService;

    /**
     * Checks if all data-fields are valid and creates new user
     */
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity registration(
            HttpSession session,
            String phone,
            String password,
            String passwordConfirmation,
            String name,
            String email
    ) throws AppException {
        try {
            if (validateData(phone, password, name, email, passwordConfirmation, userService) == AnswerStatus.OK) {
                userService.addUser(email, name, phone, Codec.md5(password));
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.valueOf(validateData(phone, password, name, email, passwordConfirmation, userService)));
            }
        } catch (ServiceException e) {
            throw new AppException(e.getMessage());
        }
    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return Path.PAGE_INDEX;
    }

    /**
     * Sets user-element in session scope.
     */
    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public ResponseEntity registration(
            HttpSession session,
            String password,
            String email
    ) throws AppException {
        try {
            if (!Validator.Email(email)) {
                throw new WrongEmailException();
            }
            if (!Validator.Password(password)) {
                throw new WrongPassException();
            }

            User user = userService.login(email, Codec.md5(password));
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole());
            return new ResponseEntity(HttpStatus.OK);

        } catch (WrongPassException e) {
            return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.NOT_AVAILABLE));

        } catch (WrongEmailException e) {
            return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.UNKNOWN_EMAIL));

        } catch (ServiceException e) {
            return new ResponseEntity(HttpStatus.valueOf(AnswerStatus.SERVER_ERROR));
        }
    }

    @RequestMapping(value = "redirect", method = RequestMethod.GET)
    public String redirect(HttpSession session,
                           Map<String, Object> model) throws AppException {
        User user = (User) session.getAttribute("user");
        Role role = null;
        if (user != null) {
            role = user.getRole();
            if (role.equals(Role.ADMIN)) {
                initAdminPage(model);
                return Path.PAGE_ADMIN;
            }
        }
        return Path.PAGE_INDEX;
    }

    private int validateData(String phone,
                             String password,
                             String name,
                             String email,
                             String passwordConfirmation,
                             IUserService userService) throws ServiceException {
        if (!Validator.Email(email) || !Validator.Name(name)
                || !Validator.Phone(phone)
                || !Validator.Password(password)
                || !Validator.Password(passwordConfirmation)
                || (!passwordConfirmation.equals(password))) {
            return AnswerStatus.INVALID_INPUT;
        } else if (userService.getByEmail(email) != null) {
            return AnswerStatus.EMAIL_IN_USE;
        }
        return AnswerStatus.OK;
    }

    private void initAdminPage(Map<String, java.lang.Object> model) throws AppException {

        List<Application> apps;
        List<Reservation> unpaidReservations;
        List<Reservation> paidReservations;
        List<Reservation> confirmedReservations;
        List<User> users;
        int appsCounter = 0;

        try {
            appsCounter = applicationService.getCountOfNewApps();
            apps = applicationService.getAllNewApplications();

            unpaidReservations = reservationService.getAllByStatus(ReservationStatus.UNPAID);
            paidReservations = reservationService.getAllByStatus(ReservationStatus.PAID);
            confirmedReservations = reservationService.getAllByStatus(ReservationStatus.CONFIRMED);

            users = userService.getAll();
        } catch (ServiceException e) {
            throw new AppException();
        }

        model.put("apps", apps);
        model.put("unpaidReservations", unpaidReservations);
        model.put("paidReservations", paidReservations);
        model.put("confirmedReservations", confirmedReservations);
        model.put("users", users);
        model.put("appsCounter", appsCounter);
    }
}

