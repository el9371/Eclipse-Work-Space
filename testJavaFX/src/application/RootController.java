package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
 
public class RootController {
	@FXML
	private Label hour;
	
	@FXML
	private Label minute;
	
	@FXML
	private Label second;
	
	@FXML
	private Button startBtn;
	
	private long sec;
	public void start() {
		CountThread t = new CountThread(hour, minute, second, 0);
		t.start();
	}
}