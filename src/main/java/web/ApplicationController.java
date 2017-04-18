package web;

import exception.AppException;
import exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IApplicationService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(path = "/application")
public class ApplicationController {

    @Inject
    private IApplicationService applicationService;

    @RequestMapping(value = "setAppId", method = RequestMethod.POST)
    public ResponseEntity setAppId(HttpSession session,
                                  int appId) {
        session.setAttribute("appId", appId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "appResponse", method = RequestMethod.POST)
    public ResponseEntity addAppResponse(HttpSession session,
                                         Map<String, Object> model,
                                         int roomId,
                                         int appId,
                                         String comment) throws AppException {

        if (appId != 0 && roomId != 0) {
            try {
                System.out.println("new app response: " + appId + " " + roomId + " " + comment);
                applicationService.newAppResponse(applicationService.getAppById(appId), roomId, comment);
                applicationService.updateApplicationIsNewStatus(appId, false);
                return new ResponseEntity(HttpStatus.OK);
            } catch (ServiceException e) {
                throw new AppException();
            }
        } else {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

