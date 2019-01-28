package pocket;

import javax.swing.*;
import java.awt.*;

//import pocket.InGame.DialogueText;

public class NPC {
	private String name;
	private int number;
	private DialogueText dt;
	private JLabel[] img; //in order of ¡ç¡è¡æ¡é 
	
	public NPC(int _number, String _name, JLabel[] _img, DialogueText _dt) {
		this.number = _number;
		this.name = _name;
		this.img = _img;
		this.dt = _dt;
	}
	
	public NPC(int _number, String _name, JLabel[] _img) {
		this.number = _number;
		this.name = _name;
		this.img = _img;
	}
	public NPC() {
	}

	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	public DialogueText getDt() {
		return dt;
	}
	public JLabel[] getImg() {
		return img;
	}
	public void setDt(DialogueText _dt) {
		dt = _dt;
	}
	
	
}
