import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class EcoBikeMain {

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
		System.out.println(Time.valueOf(LocalDateTime.now().toLocalTime()));
	}

}
