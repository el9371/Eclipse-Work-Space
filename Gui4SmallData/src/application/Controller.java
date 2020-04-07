package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class Controller implements Initializable {
	@FXML
	private Pane page1;
	@FXML
	private Button btnHp, btnStr, btnDex, btnLuk;
	@FXML
	private TextField remains, hpStat, strStat, dexStat, lukStat,
	enemyHpStat, enemyStrStat, enemyDexStat, enemyLukStat;
	@FXML
	private Label systemLabel;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		page1.setVisible(true);
	}
	
	public void plusHp() {
		if (minusOneTextField(remains))
			plusOneTextField(hpStat);
	}
	public void plusStr() {
		if (minusOneTextField(remains))
			plusOneTextField(strStat);
	}
	public void plusDex() {
		if (minusOneTextField(remains))
			plusOneTextField(dexStat);
	}
	public void plusLuk() {
		if (minusOneTextField(remains))
			plusOneTextField(lukStat);
	}
	
	
	public void gameStart() throws Exception {
		if (Integer.parseInt(remains.getText()) != 0) {
			systemLabel.setText("¸ðµç ½ºÅÝÀ» Âï¾îÁÖ¼¼¿ä!");
			return;
		}
		int enemyStat[] = Exec.randomAbility();
		integerFieldText(enemyHpStat,enemyStat[0]);
		integerFieldText(enemyStrStat,enemyStat[1]);
		integerFieldText(enemyDexStat,enemyStat[2]);
		integerFieldText(enemyLukStat,enemyStat[3]);
		
		page1.setVisible(false);
	}
	
	
	public void plusOneTextField(TextField t) {
		t.setText(Integer.toString(Integer.parseInt(t.getText()) + 1));
	}
	public boolean minusOneTextField(TextField t) {
		int number = Integer.parseInt(t.getText());
		if (number <= 0) return false;
		t.setText(Integer.toString(number - 1));
		return true;
	}
	public void integerFieldText(TextField t, int number) {
		t.setText(Integer.toString(number));
	}
}

