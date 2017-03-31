package exception;

/**
 * An exception that provides information on a database access error.
 * 
 * @author Sasha
 *
 */
public class DaoException extends Exception {


	public DaoException() {
	}
	
	public DaoException(String message, Exception e){
		super(message);
	}
}
