package pocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dialogue extends JButton {
	private Container c;
	private int maxPages, page = 0;
	private boolean isEnd = false;
	private BasicSet[] text;
	/////////////////////////////////constructor////////////////////////////////////
	//type 0 (Default) - exit, 1 - List view, 2 - input box
	public Dialogue(Container _c, BasicSet _text) {
		init();
		setText(_text.s);
		addActionListener(listener);
	}
	
	public Dialogue(Container _c, BasicSet[] _text) {
		init();
		maxPages = _text.length;
		text = _text;
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
	
	public boolean getisEnd() {
		return this.isEnd;
	}
	
	/////////////////////////////////Button Action///////////////////////////
	ActionListener listener4array = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (page + 1 < maxPages)
			{
				if (text[page].i == 1)
				{
					
				}
				else if (text[page].i == 2)
				{
					JTextField t1 = new JTextField(6);
					t1.setSize(200, 20);
					t1.setLocation(200, 280);
					_c.add(t1);
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
