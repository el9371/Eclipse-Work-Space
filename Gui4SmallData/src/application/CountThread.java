package application;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;

public class CountThread extends Thread {
	int duration, term;
	private Label firstLabel;
	private Pane p1;
	private AnchorPane p2;
	
	public CountThread(int duration, Label first, Pane p1, AnchorPane p2) {
		this.duration = duration;
		this.firstLabel = first;
		this.p1 = p1; this.p2 = p2;
		this.term = 1000;
	}
	
	@Override
	public void run() {
		int time = duration;
		runLater(time/1000);
		while(time > 0) {
			try { Thread.sleep(term); time -= term; }
			catch (Exception e) { System.out.println("CountThread Error"); }
			runLater(time/1000);
			}
		Platform.runLater(()->{
			p1.setVisible(false); p2.setVisible(true);
		});
	}
	
	public void runLater(int time) {
		String textTime = Integer.toString(time);
		Platform.runLater(()->{
			firstLabel.setText(textTime + "초 후에 시작됩니다.");
			});
		}
}
