package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides functions for helping processes in classes
 * @author chauntm
 */

public class FunctionalUtils {
	public static boolean contains(String str, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return true;
		}
		return false;
	}
}
