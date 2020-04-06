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


public class Controller implements Initializable {
	@FXML
	private Button btnHp, btnStr, btnDex, btnLuk;
	@FXML
	private TextField remains;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void plusHp() {
		remains.setText(Integer.toString(Integer.parseInt(remains.getText()) - 1));
		System.out.println("hp 올리기");
	}
	public void plusStr() {
		System.out.println("데미지 올리기");
	}
	public void plusDex() {
		System.out.println("공속 올리기");
	}
	public void plusLuk() {
		System.out.println("회피율 올리기");
	}
	
}

