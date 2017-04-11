package exception;


/**
 * An exception that provides information on a service error.
 * 
 * @author Sasha
 *
 */
public class ServiceException extends Exception {
	public ServiceException(String message) {
		super(message);
	}
}
