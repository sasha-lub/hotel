package exception;


/**
 * An exception that provides information on an application error.
 * 
 * @author Sasha
 *
 */
public class AppException extends Exception {

	public AppException(String message) {
		super(message);
		System.out.println();
	}

	public AppException() {
		super();
		System.out.println(this.getStackTrace());

	}
}
