package pocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;


public class InGame {
	//resolution must be multiple of 16
	private final int resolution = 32;
	private int userx = 31, usery = 8, framex = 5, framey = 5, mapLocationx, mapLocationy;
	private boolean canIMove = true;
	private Map nextMap;
	private HashMap<NPC,JLabel[]> npces = new HashMap<NPC,JLabel[]>();
	private ArrayList<NPC> npc = new ArrayList<NPC>();
	private JFrame Main = new JFrame();
	private Container contentPane;
	private JLabel map;
	private JLabel user_front = new JLabel(setImageScale(new ImageIcon("images\\user_front.png")));
	private JLabel user_back = new JLabel(setImageScale(new ImageIcon("images\\user_back.png")));
	private JLabel user_left = new JLabel(setImageScale(new ImageIcon("images\\user_left.png")));
	private JLabel user_right = new JLabel(setImageScale(new ImageIcon("images\\user_right.png")));
	private JLabel Dialogue0 = new JLabel(setImageScale(new ImageIcon("images\\dialogue0.png")));
	private JLabel Dialogue = new JLabel("¿œ¿ÃªÔªÁø¿¿∞ƒ•∆»±∏Ω ¿œ¿ÃªÔªÁø¿¿∞ƒ•∆»±∏Ω ¿œ");
	
	public InGame()
	{
		Main.setTitle("POCKETMON");
		Main.setLayout(new FlowLayout());
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		contentPane = Main.getContentPane();
		contentPane.setLayout(null);
		
		//////////////////////////////test npc construction///////////////////////////////
		ImageIcon[] img = {setImageScale(new ImageIcon("images\\boldchild_left.png")),setImageScale(new ImageIcon("images\\boldchild_back.png")),setImageScale(new ImageIcon("images\\boldchild_right.png")),setImageScale(new ImageIcon("images\\boldchild_front.png"))};
		NPC testn = new NPC(0,"Hanyang",img);
		NPC[] testnpc = {testn};
		HashMap<NPC,int[]> testlocation = new HashMap<NPC,int[]>();
		testlocation.put(testn, new int[]{32,10});
		/////////////////////////////test map construction//////////////////////////
		boolean[][] ob = new boolean[40][18];
		for(int i = 0; i < 40; i++)for(int j =0; j<18;j++)ob[i][j]=false;
		for (int i = 14; i<30;i++) ob[i][8] = true;
		ob[14][9] = true; ob[14][10] = true; ob[14][11] = true; ob[15][11] = true; ob[33][7] = true;
		for (int i = 18; i < 28; i++)ob[i][11] = true;
		for (int i = 30; i < 34; i++)ob[i][11] = true;
		for (int i = 0; i < 4; i ++)ob[i][7] = true;
		for (int i = 0; i < 8;i++ )ob[3][i] = true;
		for(int i = 3; i < 16; i ++)ob[i][0] = true;
		for(int i = 16; i < 40 ; i++)for(int j = 0; j <3; j++)ob[i][j] = true;
		for(int i = 26; i < 40; i++) {ob[i][3] = true; ob[i][4] = true;}
		for (int i = 34; i < 40; i++) ob[i][5] = true;
		for (int i = 36 ; i < 40; i++) for (int j = 6; j < 10; j ++) ob[i][j] = true;
		for(int i = 34 ; i < 40; i++) for (int j = 10; j < 18; j++) ob[i][j] = true;
		for(int i = 30; i < 34; i++) ob[i][12] = true;
		for(int i = 24; i < 34; i++) for(int j = 13; j < 18 ; j++) ob[i][j] = true;
		for(int i = 18; i < 24; i++) for(int j = 15; j < 18; j++) ob[i][j] =true;
		
		nextMap = new Map("testMap",0,40,18,ob,testnpc,testlocation,setImageScale(new ImageIcon("images\\test_map.png")));
		setNPC(nextMap);
		/////////////////////////////test map construction//////////////////////////
		
		//////////////////////////////////map setting/////////////////////////
		map = new JLabel(nextMap.getImageFile());
		map.setSize(nextMap.getWidth() * resolution, nextMap.getHeight() * resolution);
		mapLocationx = (userx - 5)* -1 * resolution; mapLocationy = (usery - 5) * -1 * resolution;
		map.setLocation(mapLocationx, mapLocationy);
		//map.setLocation(0, 0);
		/////////////////////////////////Dialogue Setting///////////////////////
		Dialogue0.setSize(resolution * 11, resolution * 5);
		Dialogue0.setLocation(0, resolution * 11);
		Dialogue.setSize(resolution * 9, resolution * 3);
		Dialogue.setLocation(resolution, resolution * 12);
		Dialogue.setFont(new Font("±º∏≤", Font.PLAIN, 8 + resolution / 8));
		Dialogue.setVisible(false);
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
		
		Main.setSize(11*resolution+15, 16*resolution+37);
		Main.setVisible(true);
		
		contentPane.add(Dialogue);
		contentPane.add(Dialogue0);
		
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
			////////////////////////////////////moving character ////////////////////////////////
			if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40 && canIMove) {
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
				moveNPC(nextMap);
				System.out.println("x="+userx+" y="+usery );
			} else if ( e.getKeyChar() == 'a') {
				if (canIMove) { 
					DialogueText d = new DialogueText(1, " ¥Î»≠√¢ Ω««Ë¡ﬂ "); 
					setDialogue(d);
					canIMove = false;
					}
				else {Dialogue.setVisible(false); canIMove = true;}
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
	
	/////////////////////////////////Set Dialogue /////////////////////////////////
	class DialogueText {
		private int _type;
		private String _text;
		private DialogueText _next;
		public DialogueText(int _type, String _text) {
			this._type = _type;
			this._text = _text;
			this._next = null;
		} 
		public DialogueText(String _text) {
			this._text = _text;
			this._type = 0;
			this._next = null;
		}
		public DialogueText(String _text, DialogueText _next) {
			this._text = _text;
			this._type = 0;
			this._next = _next;
		}
		public void set_next(DialogueText d) {
			this._next = d;
		}
		public int get_type() {
			return _type;
		}
		public String get_text() {
			return _text;
		}
		public DialogueText get_next() {
			return _next;
		}
	}
	private void setDialogue(DialogueText dt) {
		Dialogue.setText(dt.get_text());
		Dialogue.setVisible(true);
	}
	////////////////////////////////////Set NPC ////////////////////////////////////
	private void setNPC(Map m) {
		for(int i = 0; i < m.getNpc().length; i++)
		{
			NPC n = m.getNpc()[i];
			JLabel tmp0 = new JLabel(n.getImg()[0]);
			JLabel tmp1 = new JLabel(n.getImg()[1]);
			JLabel tmp2 = new JLabel(n.getImg()[2]);
			JLabel tmp3 = new JLabel(n.getImg()[3]);
			tmp0.setSize(resolution, resolution);
			tmp1.setSize(resolution, resolution);
			tmp2.setSize(resolution, resolution);
			tmp3.setSize(resolution, resolution);
			tmp3.setLocation((m.getNPCLocation().get(n)[0] - userx + framex)*resolution,(m.getNPCLocation().get(n)[1] - usery + framey)*resolution);
			System.out.println((m.getNPCLocation().get(n)[0] - userx + framex)*resolution + "  " + (m.getNPCLocation().get(n)[1] - usery + framey)*resolution );
			tmp0.setVisible(false);
			tmp1.setVisible(false);
			tmp2.setVisible(false);
			contentPane.add(tmp0);
			contentPane.add(tmp1);
			contentPane.add(tmp2);
			contentPane.add(tmp3);
			JLabel[] tmp = {tmp0, tmp1, tmp2, tmp3};
			npc.add(n);
			npces.put(n, tmp);
		}
		
	}
	
	private void moveNPC(Map m) {
		for (int i = 0; i < npc.size(); i++)
		{
			NPC n = npc.get(i);
			JLabel[] tmp = npces.get(n);
			for(int j = 0; j < 4; j++)
				tmp[j].setLocation((m.getNPCLocation().get(n)[0] - userx + framex)*resolution,(m.getNPCLocation().get(n)[1] - usery + framey)*resolution);
		}
	}
	
	/////////////////////////////////frame setting tools////////////////////////
	
	private ImageIcon setImageScale(ImageIcon i) {
		Image tmp = i.getImage();
		int w = tmp.getWidth(null) / 16, h = tmp.getHeight(null) / 16;
		return new ImageIcon(tmp.getScaledInstance( w * resolution, h * resolution, java.awt.Image.SCALE_SMOOTH));
		//tmp.getWidth(null) * resolution, tmp.getHeight(null) * resolution
	}
	
}
