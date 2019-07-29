package Testing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
	private Pokemon myPoke, yourPoke;
	private int index;
	
	public Button(String _text) {
		super(_text);
		SkillEventHandler handler = new SkillEventHandler(); handler.setB(this);
		this.addActionListener(handler);
	}

	public Pokemon getMyPoke() {
		return myPoke;
	}

	public void setMyPoke(Pokemon myPoke) {
		this.myPoke = myPoke;
	}

	public Pokemon getYourPoke() {
		return yourPoke;
	}

	public void setYourPoke(Pokemon yourPoke) {
		this.yourPoke = yourPoke;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	// ------------------------Button Event Handler-----------------------
	public class SkillEventHandler implements ActionListener {
		private Button b;
		public void setB(Button _b) { b= _b;}
		
		public void actionPerformed(ActionEvent arg0) {// 액션이벤트가 발생됬을떄 수행하는 동작
			//System.out.println(index + "    " + yourPoke.getName());
			if (myPoke.getPP()[index] > 0 && myPoke.useSkill(index, yourPoke) == 1) {
				b.setText(editText());
			}
		}
		
		public String editText() {
			Skill s = myPoke.getSkill()[index];
			return "<html>" + s.getName() + "<br />"+myPoke.getPP()[index] + "/" + s.getMaxPP() + "["+s.getType().getName()+"]" + "</html>";
		}
		
	}
}
