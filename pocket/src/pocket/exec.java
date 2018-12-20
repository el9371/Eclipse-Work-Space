package pocket;
import javax.swing.*;
import java.util.*;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

class MyFrame extends JFrame {
	public MyFrame() {
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("POCKETMON");
		setVisible(true);
	}
}

public class exec {
	public static void main(String[] args)
	{
		MyFrame frame = new MyFrame();
	}
}
