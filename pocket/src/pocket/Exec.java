package pocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class Exec{
	public static void main(String[] args)
	{
		JFrame Main = new JFrame();
		Main.setTitle("POCKETMON");
		Main.setLayout(new FlowLayout());
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		Container contentPane = Main.getContentPane();
		contentPane.setLayout(null);
		
		JLabel map = new JLabel(new ImageIcon("images\\map.png"));
		map.setSize(640, 576);
		map.setLocation(0,0);
		
		JLabel user = new JLabel(new ImageIcon("images\\user.png"));
		user.setSize(32, 32);
		user.setLocation(320,320);
		
		
		
		class Key implements KeyListener {
			int userx = 320, usery = 320;
			JLabel _user = user;
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 37) userx -= 20;
				else if (e.getKeyCode() == 38) usery -= 20;
				else if (e.getKeyCode() == 39) userx += 20;
				else if (e.getKeyCode() == 40) usery += 20;
				_user.setLocation(userx, usery);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		Main.addKeyListener(new Key());
		
		
		Main.setSize(640, 576);
		Main.setVisible(true);
		
		contentPane.add(user);
		contentPane.add(map);
		
	}
	
}

