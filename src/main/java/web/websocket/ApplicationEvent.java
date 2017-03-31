package web.websocket;

import com.google.gson.JsonObject;
import exception.AppException;
import exception.ServiceException;
import model.Application;
import model.ClassOfHotelRoom;
import org.springframework.stereotype.Controller;
import service.IApplicationService;
import service.IUserService;
import utils.validation.Validator;

import javax.inject.Inject;
import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Sasha
 *
 * Application-action handler. Sends new application record to manager-control-page without page reloading
 */
@Controller
public class ApplicationEvent extends SocketEvent {

	@Inject
	private IUserService userService;
	@Inject
	private IApplicationService applicationService;

	/**
	 * @param jsObj - obtained data from client
	 * @param peers - client's session
	 * @throws AppException
	 * 
	 * Method invokes adding new application function with all needed params, and sends processed data to client in right format.
	 */
	public void execute(JsonObject jsObj, Set<Session> peers) throws AppException {
		Application app = extractApplication(jsObj);
		if (app != null) {
			Iterator<Session> iterator = peers.iterator();
			while (iterator.hasNext())
				try {
					int numOfNewApps = getNumOfNewApps();
					iterator.next().getBasicRemote().sendText(createAnswer("app", numOfNewApps, app));
				} catch (IOException e) {
					throw new AppException(e.getMessage());
				}
		}
	}

	private int getNumOfNewApps() throws AppException {
		try {
			return applicationService.getCountOfNewApps();
		} catch (ServiceException e) {
			throw new AppException(e.getMessage());
		}
	}

	private Application extractApplication(JsonObject jsObj) throws AppException {

		Application app = null;
		int numOfGuests = jsObj.get("numberOfGuests").isJsonNull() ? 1 : jsObj.get("numberOfGuests").getAsInt();
		ClassOfHotelRoom classOfHotelRoom = jsObj.get("classOfRoom").isJsonNull() ? ClassOfHotelRoom.STANDARD
				: ClassOfHotelRoom.valueOf(jsObj.get("classOfRoom").getAsString());
		if (Validator.Dates(LocalDate.parse(jsObj.get("fromDate").getAsString()),
				LocalDate.parse(jsObj.get("toDate").getAsString()))) {
			try {
				app = applicationService.newApplication(userService.getById(jsObj.get("userId").getAsInt()), numOfGuests,
						LocalDate.parse(jsObj.get("fromDate").getAsString()),
						LocalDate.parse(jsObj.get("toDate").getAsString()), classOfHotelRoom,
						jsObj.get("comment").getAsString());
			} catch (ServiceException e) {
				throw new AppException(e.getMessage());
			}
		}
		return app;
	}
}
