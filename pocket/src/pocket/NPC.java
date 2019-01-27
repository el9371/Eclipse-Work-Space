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
	
	public class DialogueText {
		private int _type;
		private String _text;
		private DialogueText _next;
		public DialogueText(int _type, String _text) {
			this._type = _type;
			this._text = _text;
			this._next = null;
		} 
		public DialogueText(String _text) {
			this._text = _text;
			this._type = 0;
			this._next = null;
		}
		public DialogueText(String _text, DialogueText _next) {
			this._text = _text;
			this._type = 0;
			this._next = _next;
		}
		public void set_next(DialogueText d) {
			this._next = d;
		}
		public void setNextDialogue(DialogueText d) {
			DialogueText tmp = this;
			while (tmp.get_next() != null) tmp = tmp.get_next();
			tmp.set_next(d);
		}
		public int get_type() {
			return _type;
		}
		public String get_text() {
			return _text;
		}
		public DialogueText get_next() {
			return _next;
		}
		public void printDialogue() {
			DialogueText tmp = this.get_next();
			while (tmp != null) {
				System.out.println(tmp.get_text());
				tmp = tmp.get_next();
			}
		}
	}
	
}
