package exception;


/**
 * An exception that provides information on a service error.
 * 
 * @author Sasha
 *
 */
public class ServiceException extends Exception {


	public ServiceException(Throwable e) {
		super(e);
	}
}
