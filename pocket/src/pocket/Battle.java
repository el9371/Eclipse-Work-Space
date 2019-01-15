package pocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Battle {
	private int resolution;
	private Pocket mine, your;
	private JFrame BMain = new JFrame();
	private Container BcontentPane;
	private JLabel Dialogue0 = new JLabel(Exec.setImageScale(new ImageIcon("images\\dialogue0.png")));
	private JLabel nameYour = new JLabel("¸ð·¡¼ºÀÌ´ç");
	private JLabel nameMine = new JLabel("¸®ÀÚ¸ù");
	private JLabel levelYour = new JLabel("LV : 30");
	private JLabel levelMine = new JLabel("LV : 30");
	private JLabel hpYour = new JLabel("HP : ");
	private JLabel hpMine = new JLabel("HP : ");
	private JProgressBar hpBarYour = new JProgressBar();
	private JProgressBar hpBarMine = new JProgressBar();
	private JLabel pocketYour = new JLabel(Exec.setImageScale(new ImageIcon("images\\Gengar.png")));
	private JLabel pocketMine = new JLabel(Exec.setImageScale(new ImageIcon("images\\Gengar.png")));
	private JLabel maxHpMine = new JLabel("134 / 134 ");
	
	public Battle(int _resolution,Pocket _mine, Pocket _your) {
		this.resolution = _resolution; this.mine = _mine; this.your = _your;
	}
	
	public Battle(int _resolution) {
		this.resolution = _resolution;
		init();
		
		
		
		
		
	}
	
	public void init() {
		BMain.setTitle("Battle");
		BMain.setLayout(new FlowLayout());
		BMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when pushing close button
		BcontentPane = BMain.getContentPane();
		BcontentPane.setLayout(null);
		nameYour.setBounds(resolution/2, resolution/2, 4*resolution, resolution);
		nameYour.setFont(new Font("±¼¸²", Font.BOLD, resolution /2));
		levelYour.setBounds(resolution/2 + 2, (int)(1.1*resolution), 4*resolution, resolution);
		levelYour.setFont(new Font("±¼¸²", Font.BOLD, resolution /2));
		hpYour.setBounds(resolution/2 + 2, (int)(1.7*resolution), 2*resolution, resolution);
		hpYour.setFont(new Font("±¼¸²", Font.BOLD, resolution /2));
		pocketYour.setBounds((int)(6.5*resolution), resolution/2, 4*resolution, 4*resolution);
		hpBarYour.setBounds(2*resolution, (int)(1.95*resolution), 4*resolution, resolution/2);
		hpBarYour.setValue(100);
		
		nameMine.setBounds((int)(resolution*5), (int)(6.2*resolution), 4*resolution, resolution);
		nameMine.setFont(new Font("±¼¸²", Font.BOLD, resolution /2));
		levelMine.setBounds((int)(resolution*5)+2, (int)(6.8*resolution), 4*resolution, resolution);
		levelMine.setFont(new Font("±¼¸²", Font.BOLD, resolution /2));
		hpMine.setBounds((int)(resolution*5) + 2, (int)(7.4*resolution), 2*resolution, resolution);
		hpMine.setFont(new Font("±¼¸²", Font.BOLD, resolution /2));
		pocketMine.setBounds((int)(0.5*resolution), (int)(6.5*resolution), 4*resolution, 4*resolution);
		hpBarMine.setBounds((int)(resolution*6.5) + 2, (int)(7.65*resolution), 4*resolution, resolution/2);
		hpBarMine.setValue(100);
		maxHpMine.setBounds((int)(resolution*7) + 2, (int)(8*resolution), 4*resolution, resolution);
		maxHpMine.setFont(new Font("±¼¸²", Font.BOLD, resolution /2));
		
		BcontentPane.add(nameYour);
		BcontentPane.add(levelYour);
		BcontentPane.add(hpYour);
		BcontentPane.add(pocketYour);
		BcontentPane.add(hpBarYour);
		BcontentPane.add(nameMine);
		BcontentPane.add(levelMine);
		BcontentPane.add(hpMine);
		BcontentPane.add(pocketMine);
		BcontentPane.add(hpBarMine);
		BcontentPane.add(maxHpMine);
		
		
		Dialogue0.setSize(resolution * 11, resolution * 5);
		Dialogue0.setLocation(0, resolution * 11);
		BcontentPane.add(Dialogue0);
		
		
		BMain.setSize(11*resolution+15, 16*resolution+37);
		BMain.setVisible(true);
	}
}
