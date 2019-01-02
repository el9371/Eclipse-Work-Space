package pocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;


public class InGame {
	//resolution must be multiple of 16
	private final int resolution = 32;
	private int userx = 31, usery = 8, framex = 5, framey = 5, mapLocationx, mapLocationy;
	private Map nextMap;
	
	private JFrame Main = new JFrame();
	private Container contentPane;
	private JLabel map;
	private JLabel user_front = new JLabel(setImageScale(new ImageIcon("images\\user_front.png")));
	private JLabel user_back = new JLabel(setImageScale(new ImageIcon("images\\user_back.png")));
	private JLabel user_left = new JLabel(setImageScale(new ImageIcon("images\\user_left.png")));
	private JLabel user_right = new JLabel(setImageScale(new ImageIcon("images\\user_right.png")));
	
	public InGame()
	{
		/////////////////////////////test map construction//////////////////////////
		boolean[][] ob = new boolean[40][18];
		for(int i = 0; i < 40; i++)
			for(int j =0; j<18;j++)
				ob[i][j]=false;
		for (int i = 14; i<30;i++)
			ob[i][8] = true;
		ob[14][9] = true; ob[14][10] = true; ob[14][11] = true; ob[15][11] = true;
		for (int i = 18; i < 28; i++)
			ob[i][11] = true;
		for (int i = 30; i < 34; i++)
			ob[i][11] = true;
		nextMap = new Map("testMap",0,40,18,ob,setImageScale(new ImageIcon("images\\test_map.png")));
		/////////////////////////////test map construction//////////////////////////
		
		Main.setTitle("POCKETMON");
		Main.setLayout(new FlowLayout());
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		contentPane = Main.getContentPane();
		contentPane.setLayout(null);
		//////////////////////////////////map setting/////////////////////////
		map = new JLabel(nextMap.getImageFile());
		map.setSize(nextMap.getWidth() * resolution, nextMap.getHeight() * resolution);
		mapLocationx = (userx - 5)* -1 * resolution; mapLocationy = (usery - 5) * -1 * resolution;
		map.setLocation(mapLocationx, mapLocationy);
		//map.setLocation(0, 0);
		//////////////////////////////////character setting////////////////////////
		
		user_front.setSize(resolution, resolution);
		user_front.setLocation(framex*resolution,framey*resolution);
		user_back.setSize(resolution, resolution);
		user_back.setLocation(0,0);
		user_back.setVisible(false);
		user_left.setSize(resolution, resolution);
		user_left.setLocation(0,0);
		user_left.setVisible(false);
		user_right.setSize(resolution, resolution);
		user_right.setLocation(0,0);
		user_right.setVisible(false);
		
		Main.addKeyListener(new Key());
		
		Main.setSize(11*resolution+15, 11*resolution+37);
		Main.setVisible(true);
		
		contentPane.add(user_front);
		contentPane.add(user_back);
		contentPane.add(user_right);
		contentPane.add(user_left);
		contentPane.add(map);
	}
	
	class Key implements KeyListener {
		JLabel _puser, _user = user_front;
		@Override
		public void keyPressed(KeyEvent e) {
			/////////////////////////moving character ////////////////////
			if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
				_puser = _user;
				if (e.getKeyCode() == 37) {
					//is it possible to move ?
					if (!nextMap.isObstacles(userx - 1, usery)) moveLeft();
					_user = user_left;
				}
				else if (e.getKeyCode() == 38) {
					//is it possible to move ?
					if (!nextMap.isObstacles(userx, usery - 1)) moveUp();
					_user = user_back;
				}
				else if (e.getKeyCode() == 39) {
					//is it possible to move ?
					if (!nextMap.isObstacles(userx + 1, usery)) moveRight();
					_user = user_right;
				}
				else if (e.getKeyCode() == 40) {
					//is it possible to move ?
					if (!nextMap.isObstacles(userx, usery + 1)) moveDown();
					_user = user_front;
				}
				_puser.setVisible(false);
				_user.setLocation(framex * resolution, framey * resolution);
				_user.setVisible(true);
				System.out.println("x="+userx+" y="+usery +" fx="+framex+" fy="+framey+" px="+(userx - 5)* -1 * resolution+" py="+(usery - 5) * -1 * resolution);
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
	/////////////////////////////////frame setting tools////////////////////////
	private void moveLeft() {
		userx -= 1;
		//ordinary case 
		if (userx >= 5  && framex == 5) {
			mapLocationx += resolution;
			map.setLocation(mapLocationx, mapLocationy);
		}
		// special case 
		else framex -= 1;
	}
	private void moveRight() {
		userx += 1;
		//ordinary case 
		if (userx < nextMap.getWidth() - 5  && framex == 5) {
			mapLocationx -= resolution;
			map.setLocation(mapLocationx, mapLocationy);
		}
		// special case 
		else framex += 1;
	}
	private void moveDown() {
		usery += 1;
		//ordinary case 
		if (usery < nextMap.getHeight() - 5  && framey == 5) {
			mapLocationy -= resolution;
			map.setLocation(mapLocationx, mapLocationy);
		}
		// special case 
		else framey += 1;
	}
	private void moveUp() {
		usery -= 1;
		//ordinary case 
		if (usery >= 5  && framey == 5) {
			mapLocationy += resolution;
			map.setLocation(mapLocationx, mapLocationy);
			//System.out.println((userx - 5)* -1 * resolution);
			}
		// special case 
		else framey -= 1;
	}
	
	
	private ImageIcon setImageScale(ImageIcon i) {
		Image tmp = i.getImage();
		int w = tmp.getWidth(null) / 16, h = tmp.getHeight(null) / 16;
		return new ImageIcon(tmp.getScaledInstance( w * resolution, h * resolution, java.awt.Image.SCALE_SMOOTH));
		//tmp.getWidth(null) * resolution, tmp.getHeight(null) * resolution
	}
	
}