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
		BasicSet[] abc = {new BasicSet("����ð�!"), new BasicSet("�̰������� �پ��� ����ü���� �����Ѵٳ�"), new BasicSet("������� �׵��� '���ϸ�'�̶�� �θ���"), new BasicSet("�� ������ ���ϸ��� �����ϸ鼭 �������"), new BasicSet("������ �����. �ڳ��� �̸��� ��� �Ǵ°�?",2), new BasicSet("�׷����θ�")};
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

