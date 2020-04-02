package smallD;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Exec {
	
	public static void main(String args[]) {
		Character i = new Character("Alpha"), u = new Character("Beta");
		Thread t1 = new BattlePase(i, u), t2 = new BattlePase(u, i);
		i.set4Battle(); u.set4Battle();
		t1.start();
		t2.start();
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
