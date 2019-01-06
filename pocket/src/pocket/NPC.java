package pocket;

import javax.swing.*;

public class NPC {
	private String name;
	private int number;
	private InGame.DialogueText dt;
	private ImageIcon[] img; //in order of ¡ç¡è¡æ¡é 
	public NPC(int _number, String _name, ImageIcon[] _img, InGame.DialogueText _dt) {
		this.number = _number;
		this.name = _name;
		this.img = _img;
		this.dt = _dt;
	}
	public NPC(int _number, String _name, ImageIcon[] _img) {
		this.number = _number;
		this.name = _name;
		this.img = _img;
	}
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	public InGame.DialogueText getDt() {
		return dt;
	}
	public ImageIcon[] getImg() {
		return img;
	}
	
}
