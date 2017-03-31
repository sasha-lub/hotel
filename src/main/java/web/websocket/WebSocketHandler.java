package web.websocket;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import exception.AppException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sasha
 *
 *         Class manages web-socket commands. Handles open, close, error and
 *         message events
 *
 */
@ServerEndpoint(value = "/webSocketHandler")
public class WebSocketHandler {

	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	/**
	 * @param message
	 * @param session
	 * @throws AppException
	 * 
	 *             Method obtains income type of needed action and invokes
	 *             handler for it.
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws AppException {
		System.out.println("new message");
		System.out.println(message);
		JsonElement jsElem = new JsonParser().parse(message);
		JsonObject jsObj = jsElem.getAsJsonObject();
		String type = jsObj.get("type").getAsString();
		System.out.println("type >> "+type);
		switch (type) {
		case "application": {
			new ApplicationEvent().execute(jsObj, peers);
			break;
		}
		case "search": {
			System.out.println("case search");

			new SearchEvent().execute(jsObj, session);
			break;
		}
		case "reservation": {

			new ReservationEvent().execute(jsObj, peers);
			break;
		}
		}
		System.out.println(type);

	}

	/**
	 * @param peer
	 * 
	 *            Method adds client's session to peers list when new connection
	 *            opens
	 */
	@OnOpen
	public void onOpen(Session peer) {
		peers.add(peer);
	}

	/**
	 * @param peer
	 * 
	 *            Method removes client's session from peers list when one of
	 *            connections closes
	 */
	@OnClose
	public void onClose(Session peer) {
		peers.remove(peer);
	}

	@OnError
	public void onError(Session session, Throwable thr) {
	}

}
