package entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class TimeCounter {
	private boolean activate;
	private Date start, end;
	public TimeCounter() {
		this.activate = false;
	}
	
	public Date startCounter() {
		if (!this.activate) {
			this.activate = true;
			start = Calendar.getInstance().getTime();
		}
		return start;
	}
	
	public Date stopCounter() {
		end = Calendar.getInstance().getTime();
		return end;
	}
	
	public void setActive(boolean active) {
		this.activate = active;
	}

}
