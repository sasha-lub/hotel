package web;

import exception.AppException;
import exception.ServiceException;
import model.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IApplicationService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping(path = "/manager")
public class ManagerController {

    @Inject
    private IApplicationService applicationService;

    @RequestMapping(value = "setAppId", method = RequestMethod.GET)
    public void account(HttpSession session,
                          Map< String, Object > model,
                          int appId) throws AppException {
        session.setAttribute("appId", appId);
    }

    @RequestMapping(value = "appResponse", method = RequestMethod.GET)
    public void addAppResponse(HttpSession session,
                          Map< String, Object > model,
                          int roomId,
                          int appId,
                          String comment) throws AppException {

        if (appId != 0 && roomId != 0) {
            try {
                applicationService.newAppResponse(applicationService.getAppById(appId), roomId, comment);
                Application application = applicationService.getAppById(appId);
                application.setNew(false);
                applicationService.updateApplication(application);
            } catch (ServiceException e) {
                throw new AppException();
            }
        }
    }
}

