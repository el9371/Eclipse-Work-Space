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
		int userx = 0, usery = 0;
		//int[][] obstacles;
		
		JFrame Main = new JFrame();
		Main.setTitle("POCKETMON");
		Main.setLayout(new FlowLayout());
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		Container contentPane = Main.getContentPane();
		contentPane.setLayout(null);
		
		//////////////////////////////////map setting/////////////////////////
		JLabel map = new JLabel(new ImageIcon("images\\test_map.png"));
		map.setSize(640, 288);
		map.setLocation(0,0);
		
		//////////////////////////////////character setting////////////////////////
		JLabel user_front = new JLabel(new ImageIcon("images\\user_front.png"));
		user_front.setSize(16, 16);
		user_front.setLocation(0,0);
		JLabel user_back = new JLabel(new ImageIcon("images\\user_back.png"));
		user_back.setSize(16, 16);
		user_back.setLocation(0,0);
		user_back.setVisible(false);
		JLabel user_left = new JLabel(new ImageIcon("images\\user_left.png"));
		user_left.setSize(16, 16);
		user_left.setLocation(0,0);
		user_left.setVisible(false);
		JLabel user_right = new JLabel(new ImageIcon("images\\user_right.png"));
		user_right.setSize(16, 16);
		user_right.setLocation(0,0);
		user_right.setVisible(false);
		
		
		class Key implements KeyListener {
			int _userx = userx, _usery = usery;
			JLabel _puser, _user = user_front;
			@Override
			public void keyPressed(KeyEvent e) {
				/////////////////////////moving character ////////////////////
				if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
					_puser = _user;
					if (e.getKeyCode() == 37) {
						_userx -= 16;
						_user = user_left;
					}
					else if (e.getKeyCode() == 38) {
						_usery -= 16;
						_user = user_back;
					}
					else if (e.getKeyCode() == 39) {
						_userx += 16;
						_user = user_right;
					}
					else if (e.getKeyCode() == 40) {
						_usery += 16;
						_user = user_front;
					}
					_puser.setVisible(false);
					_user.setLocation(_userx, _usery);
					_user.setVisible(true);
				}
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
		
		class Obstacles {
			int x_min, x_max, y_min, y_max;
			public Obstacles(int xmin, int xmax, int ymin, int ymax) {
				x_min = xmin; x_max = xmax; y_min = ymin; y_max = ymax;
			}
			public boolean isRestricted()
		}
		
		Main.addKeyListener(new Key());
		
		
		Main.setSize(655, 325);
		Main.setVisible(true);
		
		contentPane.add(user_front);
		contentPane.add(user_back);
		contentPane.add(user_right);
		contentPane.add(user_left);
		contentPane.add(map);
	}
	
}

