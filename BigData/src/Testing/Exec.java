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
	WATER("��"), DRAGON("�巡��"),NTH("");
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
		
		1 ��ö���� IRON 25 90 0 0 0 70 NTH 0 1 1 1 100 1
		2 �Ҵ빮�� FIRE 5 85 0 0 0 110 BURN 10 0
		3 �𷡻Ѹ��� GROUND 15 100 0 1 5 1 0
		4 ���� WATER 15 100 0 0 1 80 BURN 30 0
		5 ���̴� NTH 10 100 3 0 0 40 DISAPPOINT 100 0
		*/
		new Skill();
		Skill skills[] = {Skill.skills[1], Skill.skills[2], Skill.skills[3], Skill.skills[4]};
		Pocketmon lizamong = new Pocketmon(6);
		Pocketmon ninetales = new Pocketmon(38);
		lizamong.setSkill(skills); ninetales.setSkill(skills);
		lizamong.printAbility(); ninetales.printAbility();
		lizamong.useSkill(0, ninetales);
		ninetales.printAbility();

	}
	
}
