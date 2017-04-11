package web.websocket;


import com.google.gson.Gson;

import model.Reservation;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

/**
 * @author Sasha
 *
 *         Parent class for all web-socket event handlers
 */
@EnableAsync
@Controller
public abstract class SocketEvent {

	protected String createAnswer(String type, Object data) {
		Gson gson = new Gson();
		return gson.toJson(new ResultObject(type, data));
	}

	protected String createAnswer(String type, int count, Object data) {
		Gson gson = new Gson();
		return gson.toJson(new ResultObject(type, count, data));
	}
	
	protected String createAnswer(String type, Reservation data, int paidCount, int unpaidCount, int confirmedCount) {
		Gson gson = new Gson();
		return gson.toJson(new ResultObject(type, data, paidCount, unpaidCount, confirmedCount));
	}

	/**
	 * @author Sasha
	 *
	 *         Inner class for creating answer-object for converting to JSON
	 *         format
	 */
	public class ResultObject {
		public ResultObject(String type, Object data) {
			this.type = type;
			this.data = data;
		}

		public ResultObject(String type, int count, Object data) {
			this.type = type;
			this.count = count;
			this.data = data;
		}

		public ResultObject(String type, Reservation data, int paidCount, int unpaidCount, int confirmedCount) {
			this.type = type;
			this.data = data;
			this.paidCount = paidCount;
			this.unpaidCount = unpaidCount;
			this.confirmedCount = confirmedCount;
		}

		int count;
		String type;
		Object data;
		int paidCount;
		int unpaidCount;
		int confirmedCount;
	}
}
