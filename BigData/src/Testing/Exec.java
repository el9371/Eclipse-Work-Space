package Testing;
import java.util.*;

//(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isSpecial, Type _type)


enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum State{
	BURN, PARALYSIS, DADDICTION, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class Exec {
	public static void main(String[] args)
	{
		Skill skills[] = new Skill[8];
		skills[0] = new Skill("열풍", 1, 95, 0.9, 10, 10, false, true, Type.FIRE);
		skills[1] = new Skill("불대문자", 2, 110, 0.85, 5, 5, false, true, Type.FIRE);
		skills[2] = new Skill("지진", 3, 100, 1, 10, 10, false, true, Type.GROUND);
		skills[3] = new Skill("오버히트", 4, 130, 0.9, 5, 5, true, true, Type.FIRE);
		
	}
}
