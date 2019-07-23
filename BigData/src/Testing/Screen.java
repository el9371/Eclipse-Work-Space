package Testing;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;

public class Screen extends JFrame {

	private JPanel mainPan;

	public Screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540+20, 758+20);
		setTitle("포켓몬스터");
		mainPan = new JPanel();
		mainPan.setLayout(null);
		setContentPane(mainPan);
		
		JPanel screen1 = new JPanel();
		screen1.setBackground(Color.WHITE);
		screen1.setBounds(12, 10, 520, 360);
		screen1.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		screen1.setLayout(null);
		mainPan.add(screen1);
		
		
		ImageIcon image1 = new ImageIcon("img/pokemon/001.png");

		JLabel testIMG = new JLabel(image1);
		testIMG.setBounds(40, 226, 103, 50);
		screen1.add(testIMG);
		
		JPanel screen2 = new JPanel();
		screen2.setBackground(Color.WHITE);
		screen2.setBounds(12, 370, 520, 360);
		screen2.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		screen2.setLayout(null);
		mainPan.add(screen2);
		
		if( Exec.myPock != null) {
			JButton b[] = constructSkillButton();
			for (int i = 0; i < 4 ; i++)
				screen2.add(b[i]);
		}
	
		
		setVisible(true);
	}

	
	
	public JButton[] constructSkillButton() {
		Skill s[] = Exec.myPock.getSkill();
		JButton b0 = new JButton(s[0].getName() + "   \n   " + s[0].getType().getName() + "      " +Exec.myPock.getPp()[0] + "/" + s[0].getMaxPP());
		JButton b1 = new JButton(s[1].getName() + "   \n   " + s[1].getType().getName() + "      " +Exec.myPock.getPp()[1] + "/" + s[1].getMaxPP());
		JButton b2 = new JButton(s[2].getName() + "   \n   " + s[2].getType().getName() + "      " +Exec.myPock.getPp()[2] + "/" + s[2].getMaxPP());
		JButton b3 = new JButton(s[3].getName() + "   \n   " + s[3].getType().getName() + "      " +Exec.myPock.getPp()[3] + "/" + s[3].getMaxPP());
		b0.setBounds(12, 10, 236, 160);
		b1.setBounds(272, 10, 236, 160);
		b2.setBounds(12, 190, 236, 160);
		b3.setBounds(272, 190, 236, 160);
		JButton b[] = {b0,b1,b2,b3};
		return b;
	}
}
