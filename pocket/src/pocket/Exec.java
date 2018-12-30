package pocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class Exec{
	public static void main(String[] args)
	{
		final int resolution = 16;
		int userx = 34, usery = 8;
		Map nextMap;
		
		/////////////////////////////test map construction//////////////////////////
		int[][] ob = new int[40][18];
		for(int i = 0; i < 40; i++)
			for(int j =0; j<18;j++)
				ob[i][j]=3;
		for (int i = 14; i<30;i++)
			ob[i][8] = 0;
		ob[14][9] = 0; ob[14][10] = 0; ob[14][11] = 0; ob[15][11] = 0;
		for (int i = 18; i < 28; i++)
			ob[i][11] = 0;
		for (int i = 30; i < 34; i++)
			ob[i][11] = 0;
		nextMap = new Map("testMap",0,640,288,ob,new ImageIcon("images\\test_map.png"));
		/////////////////////////////test map construction//////////////////////////
		
		
		JFrame Main = new JFrame();
		Main.setTitle("POCKETMON");
		Main.setLayout(new FlowLayout());
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		Container contentPane = Main.getContentPane();
		contentPane.setLayout(null);
		
		//////////////////////////////////map setting/////////////////////////
		JLabel map = new JLabel(nextMap.getImageFile());
		map.setSize(nextMap.getWidth() * resolution / 16, nextMap.getHeight() * resolution / 16);
		map.setLocation(0,0);
		
		//////////////////////////////////character setting////////////////////////
		JLabel user_front = new JLabel(new ImageIcon("images\\user_front.png"));
		user_front.setSize(resolution, resolution);
		user_front.setLocation(userx*resolution,usery*resolution);
		JLabel user_back = new JLabel(new ImageIcon("images\\user_back.png"));
		user_back.setSize(resolution, resolution);
		user_back.setLocation(0,0);
		user_back.setVisible(false);
		JLabel user_left = new JLabel(new ImageIcon("images\\user_left.png"));
		user_left.setSize(resolution, resolution);
		user_left.setLocation(0,0);
		user_left.setVisible(false);
		JLabel user_right = new JLabel(new ImageIcon("images\\user_right.png"));
		user_right.setSize(resolution, resolution);
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
						if(_userx > 0 && nextMap.testTile(_userx - 1, _usery)) _userx -= 1;
						_user = user_left;
					}
					else if (e.getKeyCode() == 38) {
						if( _usery > 0 && nextMap.testTile(_userx, _usery - 1)) _usery -= 1;
						_user = user_back;
					}
					else if (e.getKeyCode() == 39) {
						if(_userx < nextMap.getWidth() - 1 && nextMap.testTile(_userx + 1, _usery)) _userx += 1;
						_user = user_right;
					}
					else if (e.getKeyCode() == 40) {
						if(_usery < nextMap.getHeight() - 1 && nextMap.testTile(_userx, _usery + 1)) _usery += 1;
						_user = user_front;
					}
					_puser.setVisible(false);
					_user.setLocation(_userx * resolution, _usery * resolution);
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
		
		Main.addKeyListener(new Key());
		
		Main.setSize(nextMap.getWidth()*resolution/16+15, nextMap.getHeight()*resolution/16+37);
		Main.setVisible(true);
		
		contentPane.add(user_front);
		contentPane.add(user_back);
		contentPane.add(user_right);
		contentPane.add(user_left);
		contentPane.add(map);
	}
	
}

