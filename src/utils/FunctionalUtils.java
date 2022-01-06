package utils;

import java.sql.Date; 
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.ecobike.EcoBikeException;

/**
 * This class provides functions for helping processes in classes
 * @author chauntm
 */

public class FunctionalUtils {
	
	  @SuppressWarnings("unused")
	  private static Logger LOGGER = getLogger(FunctionalUtils.class.getName());

	  static {
	    System.setProperty("java.util.logging.SimpleFormatter.format", 
	        "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	  }

	  public static Logger getLogger(String className) {
	    return Logger.getLogger(className);
	  }
	
	public static boolean contains(String str, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return true;
		}
		return false;
	}
	
	public static Date stringToDate(String dateString) throws EcoBikeException {
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(dateString);
		    return parsedDate;
		} catch(Exception e) { 
			throw new EcoBikeException(e.getMessage());
		}
	}
}
