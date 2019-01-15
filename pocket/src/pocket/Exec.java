package pocket;

import java.awt.Image;

import javax.swing.ImageIcon;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class Exec{
	static int resolution = 32;
	
	public static void main(String[] args)
	{
		
		//new InGame(resolution);
		new Battle(resolution);
	}
	
	public static ImageIcon setImageScale(ImageIcon i) {
		Image tmp = i.getImage();
		int w = tmp.getWidth(null) / 16, h = tmp.getHeight(null) / 16;
		return new ImageIcon(tmp.getScaledInstance( w * resolution, h * resolution, java.awt.Image.SCALE_SMOOTH));
		//tmp.getWidth(null) * resolution, tmp.getHeight(null) * resolution
	}
}

