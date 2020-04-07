package application;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CountThread extends Thread{
	private Label h;
	private Label m;
	private Label s;
	private long sec;
	
	public CountThread(Label h, Label m, Label s, long sec) {
		this.h = h;
		this.m = m;
		this.s = s;
		this.sec = sec;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				sec++;				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("쓰레드 실행중 오류 발생");
			}
			
			Long hour = sec / 3600;
			Long min = sec % 3600 / 60;
			Long second = sec % 60; 
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					h.setText(hour.toString());
					m.setText(min.toString());
					s.setText(second.toString());
				}
			});
			
		}
	}
}