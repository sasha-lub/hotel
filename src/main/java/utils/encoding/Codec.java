package utils.encoding;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Wrapper for coding password by md5 algorithm.
 * 
 * @author Sasha
 *
 */
public class Codec {
	public static String md5(String password) {
	    String md5Hex = DigestUtils.md5Hex(password);
	    return md5Hex;
	}
}
