package pocket;
import java.util.*;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class exec {
	public static void main(String[] args)
	{
		int alpha = 10;
		double beta = 0;
		beta = (double)(alpha * 5 / 3);
		System.out.println(beta);
	}
}
