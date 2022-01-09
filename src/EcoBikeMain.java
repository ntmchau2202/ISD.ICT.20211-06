import java.sql.Connection; 
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import entities.Bike;
import exceptions.ecobike.EcoBikeException;
import utils.Configs;
import utils.DBUtils;

public class EcoBikeMain {

	public static void main(String[] args) throws EcoBikeException, SQLException, ParseException {
		DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");	
		Date date = (Date) dateFormat.parse("09/2023");
	}

}
