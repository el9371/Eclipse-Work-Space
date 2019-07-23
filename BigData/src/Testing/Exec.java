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
	WATER("��"), DRAGON("�巡��"), EVIL("��"), PSYCHIC("������"),NTH("");
	Type(String _name) { this.name = _name;}
	private String name;
	public String getName() { return name;}
	
	static public boolean isEffect(Type me, Type you) {
		if (me.equals(Type.NORMAL)) {
			if (you.equals(Type.GHOST)) return true; }
		else if (me.equals(Type.FIGHTHING)) {
			if (you.equals(Type.GHOST)) return true;}
		else if (me.equals(Type.POISON)) {
			if (you.equals(Type.IRON)) return true;}
		else if (me.equals(Type.GROUND)) {
			if (you.equals(Type.FLYING)) return true;}
		else if (me.equals(Type.GHOST)) {
			if (you.equals(Type.NORMAL)) return true;}
		else if (me.equals(Type.ELECTRICITY)) {
			if (you.equals(Type.GROUND)) return true;}
		else if (me.equals(Type.PSYCHIC)) {
			if (you.equals(Type.EVIL)) return true;}
		else if (me.equals(Type.DRAGON)) {
			if(you.equals(Type.FAIRY)) return true;}
		return false;
	}
	static public boolean isWorse(Type me, Type you) {
		if (me.equals(Type.ELECTRICITY)) {
			if (you.equals(Type.ELECTRICITY)||you.equals(Type.GRASS)||you.equals(Type.DRAGON))return true; }
		else if (me.equals(Type.FAIRY)) {
			if (you.equals(Type.POISON)||you.equals(Type.IRON)||you.equals(Type.FIRE))return true; }
		else if (me.equals(Type.IRON)) {
			if (you.equals(Type.ROCK)||you.equals(Type.ICE)||you.equals(Type.FAIRY))return true; }
		else if (me.equals(Type.FIGHTHING)) {
			if (you.equals(Type.IRON)||you.equals(Type.FIRE)||you.equals(Type.WATER)||you.equals(Type.ELECTRICITY))return true; }
		else if (me.equals(Type.FIRE)) {
			if (you.equals(Type.ROCK)||you.equals(Type.FIRE)||you.equals(Type.WATER)||you.equals(Type.DRAGON))return true; }
		else if (me.equals(Type.FLYING)) {
			if (you.equals(Type.ROCK)||you.equals(Type.IRON)||you.equals(Type.ELECTRICITY))return true; }
		else if (me.equals(Type.GHOST)) {
			if (you.equals(Type.FIGHTHING)||you.equals(Type.GROUND)||you.equals(Type.IRON))return true; }
		else if (me.equals(Type.GRASS)) {
			if (you.equals(Type.POISON)||you.equals(Type.FLYING)||you.equals(Type.BUG)||you.equals(Type.IRON)||you.equals(Type.FIRE)||you.equals(Type.GRASS)||you.equals(Type.DRAGON))return true; }
		else if (me.equals(Type.GROUND)) {
			if (you.equals(Type.BUG)||you.equals(Type.GRASS))return true;}
		else if (me.equals(Type.ICE)) {
			if (you.equals(Type.IRON)||you.equals(Type.FIRE)||you.equals(Type.WATER)||you.equals(Type.ICE))return true; }
		else if (me.equals(Type.POISON)) {
			if (you.equals(Type.POISON)||you.equals(Type.GROUND)||you.equals(Type.ROCK)||you.equals(Type.GHOST)) return true; }
		else if (me.equals(Type.BUG)) {
			if (you.equals(Type.FIGHTHING)||you.equals(Type.POISON)||you.equals(Type.FLYING)||you.equals(Type.GHOST)||you.equals(Type.IRON)||you.equals(Type.FIRE)||you.equals(Type.FAIRY)) return true;}
		else if (me.equals(Type.ROCK)) {
			if (you.equals(Type.FIGHTHING)||you.equals(Type.GROUND)||you.equals(Type.IRON)) return true;}
		else if (me.equals(Type.WATER)) {
			if (you.equals(Type.WATER)||you.equals(Type.GRASS)||you.equals(Type.DRAGON)) return true; }
		else if (me.equals(Type.DRAGON)) {
			if (you.equals(Type.IRON)) return true; }
		else if (me.equals(Type.EVIL)) {
			if (you.equals(Type.FIGHTHING)||you.equals(Type.EVIL)||you.equals(Type.FAIRY)) return true;}
		else if (me.equals(Type.PSYCHIC)) {
			if (you.equals(Type.IRON)||you.equals(Type.PSYCHIC)) return true;}
		return false;
	}
	static public boolean isGreat(Type me, Type you) {
		if (me.equals(Type.ELECTRICITY)) {
			if (you.equals(Type.FLYING) || you.equals(Type.WATER))return true; }
		else if (me.equals(Type.FAIRY)) {
			if (you.equals(Type.FIGHTHING)||you.equals(Type.DRAGON)||you.equals(Type.EVIL))return true; }
		else if (me.equals(Type.IRON)) {
			if (you.equals(Type.ROCK)||you.equals(Type.ICE)||you.equals(Type.FAIRY))return true; }
		else if (me.equals(Type.FIGHTHING)) {
			if (you.equals(Type.NORMAL)||you.equals(Type.ROCK)||you.equals(Type.IRON)||you.equals(Type.ICE)||you.equals(Type.EVIL))return true; }
		else if (me.equals(Type.FIRE)) {
			if (you.equals(Type.BUG)||you.equals(Type.IRON)||you.equals(Type.GRASS)||you.equals(Type.ICE))return true; }
		else if (me.equals(Type.FLYING)) {
			if (you.equals(Type.FIGHTHING)||you.equals(Type.BUG)||you.equals(Type.GRASS))return true; }
		else if (me.equals(Type.GHOST)) {
			if (you.equals(you.equals(Type.GHOST)||you.equals(Type.PSYCHIC)))return true; }
		else if (me.equals(Type.GRASS)) {
			if (you.equals(Type.GROUND)||you.equals(Type.ROCK)||you.equals(Type.WATER))return true; }
		else if (me.equals(Type.GROUND)) {
			if (you.equals(Type.POISON)||you.equals(Type.ROCK)||you.equals(Type.IRON)||you.equals(Type.FIRE)||you.equals(Type.ELECTRICITY))return true;}
		else if (me.equals(Type.ICE)) {
			if (you.equals(Type.GROUND)||you.equals(Type.FLYING)||you.equals(Type.GRASS)||you.equals(Type.DRAGON))return true; }
		else if (me.equals(Type.POISON)) {
			if (you.equals(Type.GRASS)||you.equals(Type.FAIRY)) return true; }
		else if (me.equals(Type.BUG)) {
			if (you.equals(Type.GRASS)||you.equals(Type.PSYCHIC)||you.equals(Type.EVIL)) return true;}
		else if (me.equals(Type.ROCK)) {
			if (you.equals(Type.FLYING)||you.equals(Type.BUG)||you.equals(Type.FIRE)||you.equals(Type.ICE)) return true;}
		else if (me.equals(Type.WATER)) {
			if (you.equals(Type.GROUND)||you.equals(Type.ROCK)||you.equals(Type.FIRE)) return true; }
		else if (me.equals(Type.DRAGON)) {
			if (you.equals(Type.DRAGON)) return true; }
		else if (me.equals(Type.EVIL)) {
			if (you.equals(Type.GHOST)||you.equals(Type.PSYCHIC)) return true;}
		else if (me.equals(Type.PSYCHIC)) {
			if (you.equals(Type.FIGHTHING)||you.equals(Type.POISON)) return true;}
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
	
	public static Pocketmon myPock = null;
	public static Pocketmon yourPock = null;
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
		myPock = lizamong; yourPock = ninetales;
		System.out.println(myPock.getName());
		new Screen();
		/*
		lizamong.printAbility(); ninetales.printAbility();
		lizamong.useSkill(3, ninetales);
		lizamong.printAbility();ninetales.printAbility();
		*/

	}
	
}
