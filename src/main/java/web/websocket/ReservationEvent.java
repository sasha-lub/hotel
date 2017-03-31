package web.websocket;

import com.google.gson.JsonObject;
import exception.AppException;
import exception.ServiceException;
import model.Reservation;
import model.ReservationStatus;
import org.springframework.stereotype.Controller;
import service.IReservationService;

import javax.inject.Inject;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Sasha
 *
 *         Reservation-action handler. Sends new reservation record to
 *         manager-control-page without page reloading
 */
@Controller
public class ReservationEvent extends SocketEvent {

	@Inject
	private IReservationService reservationService;

	/**
	 * @param jsObj
	 *            - obtained data from client
	 * @param peers
	 *            - client's session
	 * @throws AppException
	 * 
	 *             Method invokes reservation function with all needed params,
	 *             and sends processed data to manager in right format.
	 */
	public void execute(JsonObject jsObj, Set<Session> peers) throws AppException {

		Reservation reservation = extractReservation(jsObj);
		if (reservation != null) {
			Iterator<Session> iterator = peers.iterator();
			while (iterator.hasNext())
				try {
					iterator.next().getBasicRemote().sendText(createAnswer("reservation", reservation,
							reservationService.getAllByStatus(ReservationStatus.PAID).size(),
							reservationService.getAllByStatus(ReservationStatus.UNPAID).size(),
							reservationService.getAllByStatus(ReservationStatus.CONFIRMED).size()));
				} catch (IOException e) {
					throw new AppException(e.getMessage());
				} catch (ServiceException e) {
					throw new AppException(e.getMessage());
				}
		}
	}

	private Reservation extractReservation(JsonObject jsObj) throws AppException {
		Reservation reserve;
		int reserveId = jsObj.get("reserveId").getAsInt();
		try {
			reserve = reservationService.getById(reserveId);
		} catch (ServiceException e) {
			throw new AppException();
		}
		return reserve;
	}
}
