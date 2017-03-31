package exception;

/**
 * An exception that provides information on a transaction error.
 * 
 * @author Sasha
 *
 */
public class TransactionException extends Exception {

	private static final long serialVersionUID = -7460745767060059597L;

	public TransactionException(Exception cause) {
		super(cause);
	}

	public TransactionException(Exception cause, String message) {
		super(message, cause);
	}

}
