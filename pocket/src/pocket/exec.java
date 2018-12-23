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
	
	dialog dia;
	public static void main(String[] args)
	{
		String[] abc = {"어서오시게!", "이곳에서는 다양한 생물체들이 존재한다네","사람들은 그들을 '포켓몬'이라고 부르지","이 세상은 포켓몬들과 공존하면서 살고있지","설명이 길었군. 자네의 이름은 어떻게 되는가?"};
		
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
		
		contentPane.add(new dialog(abc));
		
		Main.setSize(600,400);
		Main.setVisible(true);
	}
	
	private boolean setDialog(String _s) {
		dia = new dialog(_s);
	}
	
}

