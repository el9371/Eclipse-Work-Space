package Testing;

import java.awt.*;

import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Screen extends JFrame {

	private static final int resolution = 16;
	private JPanel mainPan;

	public Screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 16*resolution + 6*resolution/16 + 15, 24*resolution + 6*resolution/16 + 42);
		setTitle("Æ÷ÄÏ¸ó½ºÅÍ");
		mainPan = new JPanel();
		mainPan.setLayout(null);
		setContentPane(mainPan);
		
		JPanel screen1 = new JPanel();
		screen1.setBackground(Color.WHITE);
		screen1.setBounds(3*resolution/16, 3*resolution/16, 16*resolution, 12*resolution);
		screen1.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		screen1.setLayout(null);
		mainPan.add(screen1);
		
		setMyPoke(screen1, Exec.myPock);
		setYourPoke(screen1, Exec.yourPock);
		
		//screen1.add(setMyPoke(Exec.myPock.getNumber()));
		//screen1.add(setYourPoke(Exec.yourPock.getNumber()));
		
		
		JPanel screen2 = new JPanel();
		screen2.setBackground(Color.WHITE);
		screen2.setBounds(3*resolution/16, 12*resolution + 6*resolution/16, 16*resolution, 12*resolution);
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

	
	
	public static JButton[] constructSkillButton() {
		Skill s[] = Exec.myPock.getSkill();
		JButton b0 = new JButton(s[0].getName() + "   \n   " + s[0].getType().getName() + "      " +Exec.myPock.getPp()[0] + "/" + s[0].getMaxPP());
		JButton b1 = new JButton(s[1].getName() + "   \n   " + s[1].getType().getName() + "      " +Exec.myPock.getPp()[1] + "/" + s[1].getMaxPP());
		JButton b2 = new JButton(s[2].getName() + "   \n   " + s[2].getType().getName() + "      " +Exec.myPock.getPp()[2] + "/" + s[2].getMaxPP());
		JButton b3 = new JButton(s[3].getName() + "   \n   " + s[3].getType().getName() + "      " +Exec.myPock.getPp()[3] + "/" + s[3].getMaxPP());
		b0.setBounds(resolution, resolution*3/4, 6*resolution, 5*resolution);
		b1.setBounds(9*resolution, resolution*3/4, 6*resolution, 5*resolution);
		b2.setBounds(resolution, 7*resolution - resolution*3/4, 6*resolution, 5*resolution);
		b3.setBounds(9*resolution, 7*resolution - resolution*3/4, 6*resolution, 5*resolution);
		b0.addActionListener(new EventHandler());
		b1.addActionListener(new EventHandler());
		b2.addActionListener(new EventHandler());
		b3.addActionListener(new EventHandler());
		JButton b[] = {b0,b1,b2,b3};
		return b;
	}
	
	public static void setMyPoke(JPanel screen, Pocketmon poke) {
		String path = ""; int i = poke.getNumber();
		
		int iLength = (int)(Math.log10(i)+1);
		if (iLength == 1) path = "00" + i;
		else if (iLength == 2) path = "0" + i;
		else path = path + i;
		ImageIcon mypoke = setPokeImageScale(new ImageIcon("img/pokemon/"+ path + ".png"));
		JLabel myIMG = new JLabel(mypoke);
		myIMG.setBounds(resolution, 9*resolution - mypoke.getIconHeight(), mypoke.getIconWidth(), mypoke.getIconHeight());
		screen.add(myIMG);
		
		path = poke.getHp() + "/" + poke.getMaxHp() + "  (" + poke.getType()[0].getName() + ")";
		if (poke.getType()[1].getName() != "") path = path + "(" + poke.getType()[1].getName() + ")";
		JLabel hp = new JLabel(path);
		hp.setFont(new Font("±¼¸²",Font.BOLD, resolution/2+2));
		hp.setBounds(resolution, 7*resolution - mypoke.getIconHeight(), 8*resolution, resolution);
		screen.add(hp);
	}
	
	
	
	public static void setYourPoke(JPanel screen, Pocketmon poke) {
		String path = ""; int i = poke.getNumber();
		int iLength = (int)(Math.log10(i)+1);
		if (iLength == 1) path = "00" + i;
		else if (iLength == 2) path = "0" + i;
		else path = path + i;
		ImageIcon yourpoke = setPokeImageScale(new ImageIcon("img/pokemon/"+ path + ".png"));
		JLabel yourIMG = new JLabel(yourpoke);
		yourIMG.setBounds(15*resolution - yourpoke.getIconWidth(), 3*resolution, yourpoke.getIconWidth(), yourpoke.getIconHeight());
		screen.add(yourIMG);
		
		path = poke.getHp() + "/" + poke.getMaxHp() + "  (" + poke.getType()[0].getName() + ")";
		if (poke.getType()[1].getName() != "") path = path + "(" + poke.getType()[1].getName() + ")";
		JLabel hp = new JLabel(path);
		hp.setFont(new Font("±¼¸²",Font.BOLD, resolution/2+2));
		hp.setBounds(8*resolution, resolution / 2, 8*resolution, resolution);
		screen.add(hp);
	}
	
	public static ImageIcon setPokeImageScale(ImageIcon i) {
		Image tmp = i.getImage();
		int w = tmp.getWidth(null) / 16, h = tmp.getHeight(null) / 16;
		return new ImageIcon(tmp.getScaledInstance( w * resolution * 3, h * resolution * 3, java.awt.Image.SCALE_SMOOTH));
		//tmp.getWidth(null) * resolution, tmp.getHeight(null) * resolution
	}
	
	public static ImageIcon setImageScale(ImageIcon i) {
		Image tmp = i.getImage();
		int w = tmp.getWidth(null) / 16, h = tmp.getHeight(null) / 16;
		return new ImageIcon(tmp.getScaledInstance( w * resolution, h * resolution, java.awt.Image.SCALE_SMOOTH));
		//tmp.getWidth(null) * resolution, tmp.getHeight(null) * resolution
	}
	
	
}
