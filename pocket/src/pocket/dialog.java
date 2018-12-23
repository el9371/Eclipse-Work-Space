package pocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class dialog extends JButton {
	private int maxPages, page = 0;
	private String[] text;
	public dialog(String _text) {
		//setBorderPainted(false);
		init();
		setText(_text);
		addActionListener(listener);
	}
	
	public dialog(String[] _text) {
		init();
		maxPages = _text.length;
		text = _text;
		setText(text[0]);
		addActionListener(listener);
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
	
	/////////////////////////////////Button Action///////////////////////////
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (++page < maxPages)
				setText(text[page]);
			else setText("");
		}
	} ;
	
	
}
