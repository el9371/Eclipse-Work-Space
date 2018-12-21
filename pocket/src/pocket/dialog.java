package pocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class dialog extends JButton {
	private int i = 0;
	private String[] text = {"어서오시게!", "이곳에서는 다양한 생물체들이 존재한다네","사람들은 그들을 '포켓몬'이라고 부르지","이 세상은 포켓몬들과 공존하면서 살고있지","설명이 길었군. 자네의 이름은 어떻게 되는가?"};
	
	public dialog(String _text) {
		setText(_text);
		setBackground(Color.WHITE);
		setVerticalAlignment(SwingConstants.TOP);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new Font("한컴돋움", Font.PLAIN, 14));
		setSize(400, 60);
		setLocation(100, 280);
		addActionListener(listener);
	}
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setText(text[++i]);
		}
	} ;
	
	
}
