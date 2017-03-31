package utils.validation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Input fields server validator.
 * 
 * @author Sasha
 *
 */
public class Validator {
	private static Pattern pattern;
	private static Matcher matcher;
	
	/**
	 * Checks if email is correct
	 * 
	 * @param email
	 * @return
	 */
	public static boolean Email(String email) {
		if(email == null){return false;}
		pattern = Pattern.compile("^[a-z0-9._-]*@[a-z0-9]+\\.[a-z0-9]+(.[a-z0-9]+)?$");
		matcher = pattern.matcher(email.trim().toLowerCase());
		return matcher.matches();
	}
	
	/**
	 * Checks if name is correct
	 * 
	 * @param name
	 * @return
	 */
	public static boolean Name(String name) {
		if(name == null){return false;}

		pattern = Pattern.compile("[a-zA-Z ]{1,20}");
		matcher = pattern.matcher(name.trim());

		return matcher.matches();
	}
	
	/**
	 * Checks if phone is correct
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean Phone(String phone) {
		if(phone == null){return false;}

		pattern = Pattern.compile("^[+]?[0-9 ]{6,17}$");
		matcher = pattern.matcher(phone.trim());

		return matcher.matches();
	}
	
	/**
	 * Checks if password is correct
	 * 
	 * @param password
	 * @return
	 */
	public static boolean Password(String password) {
		if(password == null){return false;}

		pattern = Pattern.compile("^.{6,25}$");
		matcher = pattern.matcher(password.trim());

		return matcher.matches();
	}
	
	/**
	 * Checks if dates are correct
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean Dates(LocalDate from, LocalDate to) {
		if(from == null || to == null){return false;}

		LocalDate now = LocalDate.now();
		if(from.isBefore(now) || to.isBefore(now)){
			return false;
		}
		if(to.isBefore(from) || to.isEqual(from)){
			return false;
		}
		return true;
	}
}
