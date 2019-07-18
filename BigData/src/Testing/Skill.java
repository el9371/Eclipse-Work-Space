package Testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * ����ؾ��� ����
 * ����, ���߷�, ���� or ����, Ư�� or ����, Ÿ��, �����ȣ, ����̸�
 * Skill.data ������ ����
 * 
 * ��ȣ �̸� Ÿ�� pp ���߷� �켱�� �����ΰ�
 * - ���� : �ɷ����� ���� �ڽ��ΰ�
 * - ���� : ���� cc���� ccȮ�� ���Ĺ�����
 * -- ���Ĺ����� : ���� ���� Ȯ�� �ڽ��ΰ�
 * (�ɷ����� 0 ����, 1 ����, 2 Ư��, 3 Ư��, 4 ���ǵ�, 5 ���߷�, 6 �ƹ��͵�)
 */

public class Skill {
	
	private static Skill[] skills = Skill.constructingSkill();
	private String name;
	private int number, power,  maxPP, order; //�����ȣ, ����, maxPP, �켱�� (0 1 2)
	private int ability, buffStack; // ������ ����, ����� ��ų���ΰ� (0 ����, 1 ����, 2 Ư��, 3 Ư��, 4 ���ǵ�, 5 ���߷�, 6 �ƹ��͵�), �󸶸�ŭ ����
	private double accuracy; // ���߷�
	private boolean isBuff, isSpecial, isMe;//�������� ��������, Ư������ �ƴ���, ��ǥ�� �ڽ����� �������
	private State crowdControl; //���ݽ� ȭ��, ���� ��� �����̻� CC��
	private int probability; //cc�� ���� Ȯ�� 
	private int afterAbility, afterbuffStack, afterAccracy; //���� �� ����
	private boolean isAfter, afterIsMe; //������ ���� �ִ���, �� ������ ������
	private Type type; //��� Ÿ��
	
	public Skill()
	{}
	
	public Skill(int _number, String _name, Type _type, int _maxPP, int _accuracy, int _order, boolean _isBuff) {
		this.number = _number; this.name = _name;  this.type = _type; this.maxPP = _maxPP; 
		this.accuracy = _accuracy; this.order = _order; this.isBuff = _isBuff;
	}
	
	
	
	public int getProbability() {
		return probability;
	}
	public void setProbability(int probability) {
		this.probability = probability;
	}
	public int getAfterAbility() {
		return afterAbility;
	}
	public void setAfterAbility(int afterAbility) {
		this.afterAbility = afterAbility;
	}
	public int getAfterbuffStack() {
		return afterbuffStack;
	}
	public void setAfterbuffStack(int afterbuffStack) {
		this.afterbuffStack = afterbuffStack;
	}
	public int getAfterAccracy() {
		return afterAccracy;
	}
	public void setAfterAccracy(int afterAccracy) {
		this.afterAccracy = afterAccracy;
	}
	public boolean isAfter() {
		return isAfter;
	}
	public void setAfter(boolean isAfter) {
		this.isAfter = isAfter;
	}
	public boolean isAfterIsMe() {
		return afterIsMe;
	}
	public void setAfterIsMe(boolean afterIsMe) {
		this.afterIsMe = afterIsMe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getMaxPP() {
		return maxPP;
	}
	public void setMaxPP(int maxPP) {
		this.maxPP = maxPP;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getAbility() {
		return ability;
	}
	public void setAbility(int ability) {
		this.ability = ability;
	}
	public int getBuffStack() {
		return buffStack;
	}
	public void setBuffStack(int buffStack) {
		this.buffStack = buffStack;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public boolean isBuff() {
		return isBuff;
	}
	public void setBuff(boolean isBuff) {
		this.isBuff = isBuff;
	}
	public boolean isSpecial() {
		return isSpecial;
	}
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	public boolean isMe() {
		return isMe;
	}
	public void setMe(boolean isMe) {
		this.isMe = isMe;
	}
	public State getCrowdControl() {
		return crowdControl;
	}
	public void setCrowdControl(State crowdControl) {
		this.crowdControl = crowdControl;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
	
	//-------------------��ü ��� Skill.data�� ���� ������ �Է¹ޱ�---------------------
	public static Skill[] constructingSkill() {
		Skill[] skills = null;
		int numberOfSkill;
		Scanner scan = null;
		try {
			scan = new Scanner(new File("Skill.data"));
			numberOfSkill = scan.nextInt();
			skills = new Skill[numberOfSkill+1];
			for(int i = 1; i < numberOfSkill + 1; i++) 
			{
				int _number; 
				String name; Type type; int maxPP; int accuracy; int order; boolean isBuff;
				_number = scan.nextInt(); name = scan.next(); type = Type.valueOf(scan.next()); maxPP = scan.nextInt();
				accuracy = scan.nextInt(); order = scan.nextInt(); isBuff = (scan.nextInt() != 0);
				System.out.println(type);
				skills[i] = new Skill(_number, name, type, maxPP, accuracy, order, isBuff);
				if (isBuff) //����
					{
					skills[i].setAbility(scan.nextInt());
					skills[i].setBuffStack(scan.nextInt());
					skills[i].setMe(scan.nextInt() != 0);
					} else {
					skills[i].setSpecial(scan.nextInt() != 0);
					skills[i].setPower(scan.nextInt());
					skills[i].setCrowdControl(State.valueOf(scan.next()));
					skills[i].setProbability(scan.nextInt());
					if (scan.nextInt() != 0)//���Ĺ��������� 
						{
						skills[i].setAfter(true);
						skills[i].setAfterAbility(scan.nextInt());
						skills[i].setAfterbuffStack(scan.nextInt());
						skills[i].setAfterAccracy(scan.nextInt());
						skills[i].setAfterIsMe(scan.nextInt() != 0);
						}
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Pocketmon Data File not Found");
			System.exit(0);
		}
		return skills;
	}
	
	
}
