package entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class TimeCounter implements Runnable {
	private boolean activate;
	private int time;
	public TimeCounter() {
		this.activate = true;
		this.time = 0;
	}
	
	@Override
	public void run() {
		System.out.println("Counter started");
		while(activate) {
			try {
				Thread.sleep(1000*60);
				time +=1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Date startCounter() {
		this.activate = true;
		return Calendar.getInstance().getTime();
	}
	
	public Date stopCounter() {
		this.activate = false;
		return Calendar.getInstance().getTime();
	}
	
	public int getCountedTime() {
		return this.time;
	}
	
	public void resetCounter() {
		this.time = 0;
	}

}
