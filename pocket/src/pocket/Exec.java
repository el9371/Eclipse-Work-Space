package pocket;
import javax.swing.*;
import java.awt.*;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class Exec{
	
	static Dialogue dia;
	public static void main(String[] args)
	{
		BasicSet[] abc = {new BasicSet("어서오시게!"), new BasicSet("이곳에서는 다양한 생물체들이 존재한다네"), new BasicSet("사람들은 그들을 '포켓몬'이라고 부르지"), new BasicSet("이 세상은 포켓몬들과 공존하면서 살고있지"), new BasicSet("설명이 길었군. 자네의 이름은 어떻게 되는가?",2), new BasicSet("그렇ㄱ두만")};
		JFrame Main = new JFrame();
		Main.setTitle("POCKETMON");
		Main.setLayout(new FlowLayout());
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		Container contentPane = Main.getContentPane();
		contentPane.setLayout(null);
		
		JLabel img_dr5 = new JLabel(new ImageIcon("images\\dr5.png"));
		img_dr5.setSize(120, 255);
		img_dr5.setLocation(240,20);
		
		Main.setSize(600,400);
		Main.setVisible(true);
		
		contentPane.add(img_dr5);
		
		
		Talking(contentPane, abc);
	}
	
	private static void Talking(Container c, BasicSet[] _s) {
		dia = new Dialogue(c, _s);
		c.add(dia);
		while(!(dia.getisEnd()));
		dia.setVisible(false);
		c.remove(dia);
	}
	
}

