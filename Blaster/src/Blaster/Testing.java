package Blaster;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.net.URL;

public class Testing extends JFrame {

	private int width = 1360, height = 765;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testing frame = new Testing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Testing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 20, width+15, height+37);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new ImagePanel("ilium");
		panel.setBounds(0, 0, 800, 600);
		contentPane.add(panel);
		
		JLabel user = new JLabel(new ImageIcon("Images\\User.png"));
		user.setBounds(width / 2 - 60, height - 250, 120, 160);
		contentPane.add(user);
	}
	
	class ImagePanel extends JPanel {

		  Image image;
		  public int width, heigt;

		  public ImagePanel(String str) {
		    image = Toolkit.getDefaultToolkit().createImage("Images\\" + str + ".gif");
		    this.width = image.getWidth(null);
		    this.heigt = image.getHeight(null);
		  }
		  
		 // public get
			
		  @Override
		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    if (image != null) {
		      g.drawImage(image, 0, 0, this);
		    }
		  }
		  
	}
	
}
