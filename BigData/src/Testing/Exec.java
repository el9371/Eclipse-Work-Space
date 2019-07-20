package Testing;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isSpecial, Type _type)
enum Type{
	ELECTRICITY("전기"), FAIRY("페어리") ,IRON("강철"), 
	FIGHTHING("격투"), FIRE("불"), FLYING("비행"),
	GHOST("고스트"), GRASS("풀"), GROUND("땅"), ICE("얼음"), 
	NORMAL("노말"), POISON("독"), BUG("벌레"), ROCK("바위"), 
	WATER("물"), NTH("");
	Type(String _name) { this.name = _name;}
	private String name;
	public String getName() { return name;}
	static public boolean isGreat(Type me, Type you) {
		
		return false;
	}
}
enum State{
	BURN("화상"), PARALYSIS("마비"), DADDICTION("중독"),
	ADDICTION("중독"), FROZEN("얼음"), SLEEPING("수면"),
	DISAPPOINT("풀죽기"), NTH("");
	State(String _name) { this.name = _name;}
	private String name;
	public String getName() {return name;}
}

public class Exec {
	
	public static void main(String[] args)
	{
		/*
		Skill skills[] = new Skill[8];
		skills[0] = new Skill("열풍", 1, 95, 0.9, 10, 10, false, true, Type.FIRE);
		skills[1] = new Skill("불대문자", 2, 110, 0.85, 5, 5, false, true, Type.FIRE);
		skills[2] = new Skill("지진", 3, 100, 1, 10, 10, false, true, Type.GROUND);
		skills[3] = new Skill("오버히트", 4, 130, 0.9, 5, 5, true, true, Type.FIRE);
		*/
		new Skill();
		Pocketmon lizamong = new Pocketmon(4);
		Pocketmon ninetales = new Pocketmon(6);
		lizamong.printAbility(); ninetales.printAbility();

	}
	
}
