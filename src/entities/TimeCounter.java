package entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

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
	
	public LocalTime startCounter() {
		this.activate = true;
		return LocalTime.now();
	}
	
	public LocalTime stopCounter() {
		this.activate = false;
		return LocalTime.now();
	}
	
	public int getCountedTime() {
		return this.time;
	}

}
