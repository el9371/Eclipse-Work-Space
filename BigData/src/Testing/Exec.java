package Testing;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isSpecial, Type _type)
enum Type{
	ELECTRICITY("����"), FAIRY("��") ,IRON("��ö"), 
	FIGHTHING("����"), FIRE("��"), FLYING("����"),
	GHOST("��Ʈ"), GRASS("Ǯ"), GROUND("��"), ICE("����"), 
	NORMAL("�븻"), POISON("��"), BUG("����"), ROCK("����"), 
	WATER("��"), NTH("");
	Type(String _name) { this.name = _name;}
	private String name;
	public String getName() { return name;}
	static public boolean isGreat(Type me, Type you) {
		
		return false;
	}
}
enum State{
	BURN("ȭ��"), PARALYSIS("����"), DADDICTION("�ߵ�"),
	ADDICTION("�ߵ�"), FROZEN("����"), SLEEPING("����"),
	DISAPPOINT("Ǯ�ױ�"), NTH("");
	State(String _name) { this.name = _name;}
	private String name;
	public String getName() {return name;}
}

public class Exec {
	
	public static void main(String[] args)
	{
		/*
		Skill skills[] = new Skill[8];
		skills[0] = new Skill("��ǳ", 1, 95, 0.9, 10, 10, false, true, Type.FIRE);
		skills[1] = new Skill("�Ҵ빮��", 2, 110, 0.85, 5, 5, false, true, Type.FIRE);
		skills[2] = new Skill("����", 3, 100, 1, 10, 10, false, true, Type.GROUND);
		skills[3] = new Skill("������Ʈ", 4, 130, 0.9, 5, 5, true, true, Type.FIRE);
		*/
		new Skill();
		Pocketmon lizamong = new Pocketmon(4);
		Pocketmon ninetales = new Pocketmon(6);
		lizamong.printAbility(); ninetales.printAbility();

	}
	
}
