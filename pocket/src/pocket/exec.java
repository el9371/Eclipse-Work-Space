package pocket;
import javax.swing.*;
import java.awt.*;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class exec{
	
	public static void main(String[] args)
	{
		
		JFrame Main = new JFrame();
		Main.setTitle("POCKETMON");
		Main.setLayout(new FlowLayout());//what is 'setting layout'?
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		Container contentPane = Main.getContentPane();
		contentPane.setLayout(null);
		
		JLabel img_dr5 = new JLabel(new ImageIcon("images\\dr5.png"));
		img_dr5.setSize(120, 255);
		img_dr5.setLocation(240,20);
		contentPane.add(img_dr5);
		
		contentPane.add(new dialog("어서오시게!"));
		
		Main.setSize(600,400);
		Main.setVisible(true);
	}
}
