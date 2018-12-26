package pocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dialogue extends JButton {
	private Container c;
	private int maxPages, page = 0;
	private boolean isEnd = false;
	private BasicSet[] text;
	private String userText;
	/////////////////////////////////constructor////////////////////////////////////
	//type 0 (Default) - exit, 1 - List view, 2 - input box
	public Dialogue(Container _c, BasicSet _text) {
		init();
		c = _c;
		setText(_text.s);
		addActionListener(listener);
	}
	
	public Dialogue(Container _c, BasicSet[] _text) {
		init();
		maxPages = _text.length;
		text = _text;
		c = _c;
		setText(text[0].s);
		addActionListener(listener4array);
	}
	
	//
	private void init( ) {
		setFocusPainted(false);
		setBackground(Color.WHITE);
		setVerticalAlignment(SwingConstants.TOP);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new Font("ÇÑÄÄµ¸¿ò", Font.PLAIN, 14));
		setSize(400, 60);
		setLocation(100, 280);
	}
	
	public String getuserText() {
		return this.userText;
	}
	
	public boolean getisEnd() {
		return this.isEnd;
	}
	
	/////////////////////////////////Button Action///////////////////////////
	ActionListener listener4array = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (page + 1 < maxPages)
			{
				if (text[page].i == 1) {
					
				}
				else if (text[page].i == 2) {
					setVisible(false);
					
					JTextField _userText = new JTextField(6);
					_userText.setSize(100, 30);
					_userText.setLocation(220, 300);
					
					JButton _userButton = new JButton();
					_userButton.setFont(new Font("ÇÑÄÄµ¸¿ò", Font.PLAIN, 14));
					_userButton.setSize(80, 30);
					_userButton.setLocation(330, 300);
					_userButton.setText("ÀÔ·Â");
					_userButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							userText = _userText.getText();
							_userText.setVisible(false);
							_userButton.setVisible(false);
							c.remove(_userButton); c.remove(_userText);
							setVisible(true);
						}
					});
					c.add(_userButton);
					c.add(_userText);
				}
				setText(text[++page].s);
			}
			else isEnd = true;
		}
	};
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			isEnd = true;
		}
	};
	
}
