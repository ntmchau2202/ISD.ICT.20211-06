package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.ecobike.EcoBikeException;

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
	
	public static Timestamp stringToTimeStamp(String dateString) throws EcoBikeException {
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(dateString);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		} catch(Exception e) { 
			throw new EcoBikeException(e.getMessage());
		}
	}
}
