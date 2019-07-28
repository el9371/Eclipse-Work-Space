package Testing;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



//(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isSpecial, Type _type)
enum Type{
	
	NORMAL("�븻"), FIGHTING("����"), POISON("��"), GROUND("��"),
	FLYING("����"), BUG("����"), ROCK("����"), GHOST("��Ʈ"),IRON("��ö"),
	FIRE("�Ҳ�"), WATER("��"), ELECTRIC("����"), GRASS("Ǯ"), ICE("����"),
	PHYSIC("������"), DRAGON("�巡��"), DARK("��"), FAIRY("��"), NTH("");
	Type(String _name) { this.name = _name; }
	private String name; 
	private static final int balance[][] = {
			{0,0,0,0,0,0,-1,5,-1,0,0,0,0,0,0,0,0,0,0},			//�븻
			{1,0,-1,0,-1,-1,1,5,1,0,0,0,0,1,-1,0,1,0,0},		//����
			{0,0,-1,-1,0,0,-1,-1,5,0,0,0,1,0,0,0,0,1,0},		//��
			{0,0,1,0,5,-1,1,0,1,1,0,1,-1,0,0,0,0,0,0},			//��
			{0,1,0,0,0,1,-1,0,-1,0,0,-1,1,0,0,0,0,0,0},		//����
			{0,-1,-1,0,-1,0,0,-1,-1,-1,0,0,1,0,1,0,1,-1,0},	//����
			{0,-1,0,-1,1,1,0,0,-1,1,0,0,0,1,0,0,0,0,0},		//����
			{5,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,-1,0,0},			//��Ʈ
			{0,0,0,0,0,0,1,0,-1,-1,-1,-1,0,1,0,0,0,1,0,},		//��ö
			{0,0,0,0,0,1,-1,0,1,-1,-1,0,1,1,0,-1,0,0,0},		//�Ҳ�
			{0,0,0,1,0,0,1,0,0,1,-1,0,-1,0,0,-1,0,0,0},		//��
			{0,0,0,5,1,0,0,0,0,0,1,-1,-1,0,0,-1,0,0,0},		//����
			{0,0,-1,1,-1,-1,1,0,-1,-1,1,0,-1,0,0,-1,0,0,0},	//Ǯ
			{0,0,0,1,1,0,0,0,-1,-1,-1,0,1,-1,0,1,0,0,0},		//����
			{0,1,1,0,0,0,0,0,-1,0,0,0,0,0,-1,0,5,0,0},			//������
			{0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0,1,0,5,0},			//�巡��
			{0,-1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,-1,-1,0},		//��
			{0,1,-1,0,0,0,0,0,-1,-1,0,0,0,0,0,1,1,0,0}	,		//��
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}				//NTH
		};		//-1 ȿ�� ����, 0 �⺻, 1ȿ�� ����, 5 ȿ�� ����
	public String getName() { return name;}
	public static int getBalance(int attack, int defense) { return balance[attack][defense];}
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
		Pocketmon lizamong = new Pocketmon(3);
		Pocketmon ninetales = new Pocketmon(29);
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
