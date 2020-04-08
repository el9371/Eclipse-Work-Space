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
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller implements Initializable {
	@FXML
	private Pane page1;
	@FXML
	private AnchorPane page2;
	@FXML
	private Button btnHp, btnStr, btnDex, btnLuk;
	@FXML
	private TextField remains, hpStat, strStat, dexStat, lukStat,
	enemyHpStat, enemyStrStat, enemyDexStat, enemyLukStat;
	@FXML
	private Label systemLabel, user1Log, user2Log;
	@FXML
	private ProgressBar user1Hp, user2Hp;
	@FXML
	private ImageView user1Image, user2Image;
	
	Character i, u;
	
	
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
		int ourStat[] = new int[4];
		ourStat[0] = Integer.parseInt(hpStat.getText());
		ourStat[1] = Integer.parseInt(strStat.getText());
		ourStat[2] = Integer.parseInt(dexStat.getText());
		ourStat[3] = Integer.parseInt(lukStat.getText());
		i = new Character("USER", ourStat);
		int enemyStat[] = Exec.randomAbility();
		integerFieldText(enemyHpStat,enemyStat[0]);
		integerFieldText(enemyStrStat,enemyStat[1]);
		integerFieldText(enemyDexStat,enemyStat[2]);
		integerFieldText(enemyLukStat,enemyStat[3]);
		u = new Character("COMPUTER", enemyStat);
		CountThread test1 = new CountThread(3000, systemLabel, page1, page2);
		test1.start();
		i.set4Battle(); u.set4Battle();
	}
	public void page2TestBtn() {
		BattlePage player1, player2;
		player1 = new BattlePage(i, u, user2Hp, user2Log);
		player2 = new BattlePage(u, i, user1Hp, user1Log);
		player1.start();
		player2.start();
		
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

