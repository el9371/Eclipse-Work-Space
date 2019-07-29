package Testing;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.border.*;
import javax.swing.*;

public class Screen extends JFrame {

	private static final int resolution = 32;
	private JPanel mainPan;
	private static JPanel screen1 = null;
	private static JPanel screen2 = null;
	private static JLabel mainText = null;
	
	public Screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 16*resolution + 6*resolution/16 + 15, 24*resolution + 6*resolution/16 + 42);
		setTitle("Æ÷ÄÏ¸ó½ºÅÍ");
		mainPan = new JPanel();
		mainPan.setLayout(null);
		setContentPane(mainPan);
		
		screen1 = new JPanel();
		screen1.setBackground(Color.WHITE);
		screen1.setBounds(3*resolution/16, 3*resolution/16, 16*resolution, 12*resolution);
		screen1.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		screen1.setLayout(null);
		mainPan.add(screen1);
		
		setMyPoke(screen1, Exec.myPoke);
		setYourPoke(screen1, Exec.yourPoke);
		
		mainText = new JLabel("");
		mainText.setBounds(resolution, 10*resolution, 14*resolution, 3*resolution/2);
		mainText.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		mainText.setVisible(false);
		screen1.add(mainText);
		//screen1.add(setMyPoke(Exec.myPoke.getNumber()));
		//screen1.add(setYourPoke(Exec.yourPoke.getNumber()));
		
		
		screen2 = new JPanel();
		screen2.setBackground(Color.WHITE);
		screen2.setBounds(3*resolution/16, 12*resolution + 6*resolution/16, 16*resolution, 12*resolution);
		screen2.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		screen2.setLayout(null);
		mainPan.add(screen2);
		
		if( Exec.myPoke != null) {
			JButton b[] = constructSkillButton();
			for (int i = 0; i < 4 ; i++)
				screen2.add(b[i]);
		}
	
		
		setVisible(true);
	}

	public void sendMessage(String text) {
		class pressA implements KeyListener {
			public boolean wait = true;
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == 'a')
					wait = false;
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		pressA key = new pressA();
		mainText.setText(text);
		mainText.setVisible(true);
		mainText.addKeyListener(key);
		//while(key.wait) {}

	}
	
	public static JButton[] constructSkillButton() {
		Skill s[] = Exec.myPoke.getSkill();
		Button b0 = new Button("<html>" + s[0].getName() + "<br />"+Exec.myPoke.getPP()[0] + "/" + s[0].getMaxPP() + "["+s[0].getType().getName()+"]" + "</html>");
		Button b1 = new Button(s[1].getName() + "   \n   " + s[1].getType().getName() + "      " +Exec.myPoke.getPP()[1] + "/" + s[1].getMaxPP());
		Button b2 = new Button(s[2].getName() + "   \n   " + s[2].getType().getName() + "      " +Exec.myPoke.getPP()[2] + "/" + s[2].getMaxPP());
		Button b3 = new Button(s[3].getName() + "   \n   " + s[3].getType().getName() + "      " +Exec.myPoke.getPP()[3] + "/" + s[3].getMaxPP());
		b0.setBounds(resolution, resolution*3/4, 6*resolution, 5*resolution);
		b1.setBounds(9*resolution, resolution*3/4, 6*resolution, 5*resolution);
		b2.setBounds(resolution, 7*resolution - resolution*3/4, 6*resolution, 5*resolution);
		b3.setBounds(9*resolution, 7*resolution - resolution*3/4, 6*resolution, 5*resolution);
		b0.setMyPoke(Exec.myPoke); b0.setYourPoke(Exec.yourPoke); b0.setIndex(0);
		b1.setMyPoke(Exec.myPoke); b1.setYourPoke(Exec.yourPoke); b1.setIndex(1);
		b2.setMyPoke(Exec.myPoke); b2.setYourPoke(Exec.yourPoke); b2.setIndex(2);
		b3.setMyPoke(Exec.myPoke); b3.setYourPoke(Exec.yourPoke); b3.setIndex(3);
		JButton b[] = {b0,b1,b2,b3};
		return b;
	}
	
	public static void setMyPoke(JPanel screen, Pokemon poke) {
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
	
	
	
	public static void setYourPoke(JPanel screen, Pokemon poke) {
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
