package smallD;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Exec {
	
	public static void main(String args[]) {
		Character i = new Character("Alpha"), u = new Character("Beta");
		Thread t1 = new BattlePase(i, u), t2 = new BattlePase(u, i);
		randomAbility(i); randomAbility(u);
		i.set4Battle(); u.set4Battle();
		i.printAbility(); u.printAbility();
		t1.start();
		t2.start();
	}
	
	public static void randomAbility(Character c) {
		for (int i = 0 ; i < 5 ; i++) {
			int rnd = (int)(Math.random() * 3) + 1;
			switch(rnd) {
			case 1:
				c.plusMaxHp();
				break;
			case 2:
				c.plusStr();
				break;
			case 3:
				c.plusDex();
				break;
			case 4:
				c.plusLuk();
			}
		}
	}
/*
	private JFrame frame;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exec window = new Exec();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Exec() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
*/

}
