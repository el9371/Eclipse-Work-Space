package Testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 고려해야할 사항
 * 위력, 명중률, 버프 or 공격, 특수 or 물리, 타입, 기술번호, 기술이름
 * Skill.data 데이터 순서
 * 
 * 번호 이름 타입 pp 명중률 우선도 버프인가
 * - 버프 : 능력종류 스택 자신인가
 * - 공격 : 위력 cc종류 cc확률 공후버프있
 * -- 공후버프있 : 종류 스택 확률 자신인가
 * (능력종류 0 물공, 1 물방, 2 특공, 3 특방, 4 스피드, 5 명중률, 6 아무것도)
 */

public class Skill {
	
	private static Skill[] skills = Skill.constructingSkill();
	private String name;
	private int number, power,  maxPP, order; //기술번호, 위력, maxPP, 우선도 (0 1 2)
	private int ability, buffStack; // 무엇을 버프, 디버프 시킬것인가 (0 물공, 1 물방, 2 특공, 3 특방, 4 스피드, 5 명중률, 6 아무것도), 얼마만큼 버프
	private double accuracy; // 명중률
	private boolean isBuff, isSpecial, isMe;//버프인지 공격인지, 특공인지 아닌지, 목표가 자신인지 상대인지
	private State crowdControl; //공격시 화상, 마비 등등 상태이상 CC기
	private int probability; //cc기 입힐 확률 
	private int afterAbility, afterbuffStack, afterAccracy; //공격 후 버프
	private boolean isAfter, afterIsMe; //공격후 버프 있는지, 그 버프가 나인지
	private Type type; //기술 타입
	
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
	
	
	
	//-------------------전체 기술 Skill.data로 부터 데이터 입력받기---------------------
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
				if (isBuff) //버프
					{
					skills[i].setAbility(scan.nextInt());
					skills[i].setBuffStack(scan.nextInt());
					skills[i].setMe(scan.nextInt() != 0);
					} else {
					skills[i].setSpecial(scan.nextInt() != 0);
					skills[i].setPower(scan.nextInt());
					skills[i].setCrowdControl(State.valueOf(scan.next()));
					skills[i].setProbability(scan.nextInt());
					if (scan.nextInt() != 0)//공후버프있으면 
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
