package pocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class dialog extends JButton {
	private int i = 0;
	private String[] text = {"����ð�!", "�̰������� �پ��� ����ü���� �����Ѵٳ�","������� �׵��� '���ϸ�'�̶�� �θ���","�� ������ ���ϸ��� �����ϸ鼭 �������","������ �����. �ڳ��� �̸��� ��� �Ǵ°�?"};
	
	public dialog(String _text) {
		setText(_text);
		setBackground(Color.WHITE);
		setVerticalAlignment(SwingConstants.TOP);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new Font("���ĵ���", Font.PLAIN, 14));
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
