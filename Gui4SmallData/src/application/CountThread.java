package application;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CountThread extends Thread {
	int duration, term;
	private Label firstLabel, secondLabel, thirdLabel;
	
	public CountThread(int duration, Label first) {
		this.duration = duration;
		this.firstLabel = first;
	}
	
	@Override
	public void run() {
		int time = duration;
		firstLabel.setText(time / 1000 + "초 후에 시작됩니다.");
		while(time >= 0) {
			try { Thread.sleep(term); time += term; }
			catch (Exception e) { System.out.println("CountThread Error"); }
			firstLabel.setText(time / 1000 + "초 후에 시작됩니다.");
			}
	}
}
