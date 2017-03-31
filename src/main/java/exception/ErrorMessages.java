package exception;

/**
 * List of error messages
 * 
 * @author Sasha
 *
 */
public class ErrorMessages {

	//user dao
	public static final String CANT_CREATE_USER = "Error ocured during creating user";
	public static final String CANT_UPDATE_USER = "Error ocured during updating user";
	public static final String CANT_DELETE_USER = "Error ocured during deleting user";
	public static final String CANT_GET_ALL_USERS = "Error ocured during getting users list";
	public static final String CANT_GET_USER_BY_ID = "Error ocured during getting user by id : ";
	public static final String CANT_GET_USER_BY_EMAIL = "Error ocured during getting user by e-mail : ";
	
	//room dao
	public static final String CANT_CREATE_ROOM = "Error ocured during creating room";
	public static final String CANT_DELETE_ROOM = "Error ocured during deleting room";
	public static final String CANT_GET_ROOM_BY_ID = "Error ocured during getting room by id : ";
	public static final String CANT_UPDATE_ROOM = "Error ocured during updating room";
	public static final String CANT_GET_ROOMS_BY_PRICE = "Error ocured during getting rooms for price";
	public static final String CANT_GET_ROOMS_BY_DATES = "Error ocured during getting rooms for dates";
	public static final String CANT_GET_ROOMS_BY_CAPACITY = "Error ocured during getting rooms by capacity";
	public static final String CANT_GET_ROOM_PHOTOS = "Error ocured during getting room's photos";
	public static final String CANT_GET_AVARAGE_ROOM_RATE = "Error ocured during getting room's avarige rate";
	public static final String CANT_ADD_PHOTO = "Error ocured during adding room photo";
	public static final String CANT_GET_ALL_ROOMS = "Error ocured during getting all rooms";
	public static final String CANT_GET_ALL_ROOMS_BY_CLASS = "Error ocured during getting all rooms by class ";
	
	//room recalls
	public static final String CANT_ADD_RECALL = "Error ocured during adding room recall";
	public static final String CANT_UPDATE_RECALL = "Error ocured during updating recall";
	public static final String CANT_GET_ROOM_RECALLS = "Error ocured during getting all room's recalls";
	public static final String CANT_GET_USER_RECALL_FOR_ROOM = "Error ocured during getting user's recall for room";
	
	//application
	public static final String CANT_CREATE_APP = "Error ocured during creating application";
	public static final String CANT_UPDATE_APP = "Error ocured during updating application";
	public static final String CANT_GET_APP_BY_ID = "Error ocured during getting application by id : ";
	public static final String CANT_DELETE_APP = "Error ocured during deleting application ";
	public static final String CANT_GET_ALL_APPS = "Error ocured during getting all applications";
	public static final String CANT_GET_ALL_APPS_BY_USER = "Error ocured during getting all applications by user ";
	public static final String CANT_COUNT_NEW_APPS = "Error ocured during counting new applications";
	public static final String CANT_GET_ALL_NEW_APPS = "Error ocured during getting new applications";
	
	//application response
	public static final String CANT_CREATE_RESPONSE = "Error ocured during creating response";
	public static final String CANT_UPDATE_RESPONSE = "Error ocured during updating response";
	public static final String CANT_GET_RESPONSE_BY_ID = "Error ocured during getting response by id :";
	public static final String CANT_GET_ALL_RESPONSES = "Error ocured during getting all responses";
	public static final String CANT_DELETE_RESPONSE = "Error ocured during deleting response";
	public static final String CANT_GET_RESPONSE_BY_APP = "Error ocured during getting response by application";

	//reservation
	public static final String CANT_CREATE_RESERVATION = "Error ocured during creating reservation";
	public static final String CANT_UPDATE_RESERVATION = "Error ocured during updating reservation";
	public static final String CANT_GET_RESERV_BY_ID = "Error ocured during getting reservation by id : ";
	public static final String CANT_GET_ALL_RESERVS = "Error ocured during getting all reservations";
	public static final String CANT_DELETE_RESERV = "Error ocured during deleting reservation";
	public static final String CANT_GET_ALL_RESERVS_BY_USER = "Error ocured during getting all reservations by user";
	public static final String CANT_GET_ALL_RESERVS_BY_STATUS = "Error ocured during getting reservations by status ";
}
