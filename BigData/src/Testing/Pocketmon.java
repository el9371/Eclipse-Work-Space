package Testing;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
 * ���ϸ��� hp ���� ���� Ư�� Ư�� ���ǵ� ��
 * 
������ = (���� �� ���� �� (���� �� 2 �� 5 + 2 ) �� ��� �� 50 �� [[�޼�]] + 2 )
�� [[�ڼ� ����]] �� Ÿ�Ի�1 �� Ÿ�Ի�2 �� ������/255

HP ��� = (������ x 2 + 20 + 60) / 2 + 10 + 50
���� = (������ x 2 + 20 + 60) / 2 + 5

*/

public class Pocketmon {
	
	private static Pocketmon[] pocks = Pocketmon.constructingPocketmon();
	
	private int number, maxHp, hp, attack, defense, sattack, sdefense, speed; //������ȣ, �ִ�ü��, ����ü��, ����, ����, Ư��, Ư��, ���ǵ�
	private int pp[] = new int[4]; // ��� ���� pp
	private String name; //���ϸ� �̸�
	private Type type[] = new Type[2]; //���ϸ� Ÿ�� �ִ� 2 �ּ� 1
	private Skill skill[] = new Skill[4]; // ��� �ִ� 4
	private State state; //���� ����
	private int kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed; //������
	private int bAttack, bDefense, bSattack, bSdefense, bSpeed, bAccuracy;//��������
	
	///-------------------------------------���ϸ� ���� ����--------------------------------------------
	public Pocketmon(String _name, int _kHp, int _kAttack, int _kDefense, int _kSattack, int _kSdefense, int _kSpeed, Type[] _type) {
		this.name = _name; this.kHp = _kHp; this.kAttack = _kAttack; this.kDefense = _kDefense; 
		this.kSattack = _kSattack; this.kSdefense = _kSdefense; this.kSpeed = _kSpeed; this.type = _type;
	}
	
	///-------------------------------------Ư�� ���ϸ� �ʱ� ����--------------------------------------------
	public Pocketmon(int number) {
		Pocketmon pock = pocks[number];
		this.number = pock.getNumber(); this.name = pock.getName();
		this.maxHp = (pock.getkHp() * 2 + 80) / 2 + 60;
		this.attack = (pock.getkAttack() * 2 + 80) / 2 + 5;
		this.defense = (pock.getkDefense() * 2 + 80) / 2 + 5;
		this.sattack = (pock.getkSattack() * 2 + 80) / 2 + 5;
		this.sdefense = (pock.getkSdefense() * 2 + 80) / 2 + 5;
		this.speed = (pock.getSpeed() * 2 + 80) / 2 + 5;
		this.type = pock.getType();
		this.state = State.NTH; this.bAttack = 0; this.bDefense = 0; this.bSattack = 0; this.bSdefense = 0;
		this.bSpeed = 0; this.bAccuracy = 0;	
		this.hp = this.maxHp;
	}
	
	
	public void useSkill(int skill, Pocketmon pocketmon) {
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSattack() {
		return sattack;
	}

	public void setSattack(int sattack) {
		this.sattack = sattack;
	}

	public int getSdefense() {
		return sdefense;
	}

	public void setSdefense(int sdefense) {
		this.sdefense = sdefense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int[] getPp() {
		return pp;
	}

	public void setPp(int[] pp) {
		this.pp = pp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type[] getType() {
		return type;
	}

	public void setType(Type[] type) {
		this.type = type;
	}

	public Skill[] getSkill() {
		return skill;
	}

	public void setSkill(Skill[] skill) {
		this.skill = skill;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getkHp() {
		return kHp;
	}

	public void setkHp(int kHp) {
		this.kHp = kHp;
	}

	public int getkAttack() {
		return kAttack;
	}

	public void setkAttack(int kAttack) {
		this.kAttack = kAttack;
	}

	public int getkDefense() {
		return kDefense;
	}

	public void setkDefense(int kDefense) {
		this.kDefense = kDefense;
	}

	public int getkSattack() {
		return kSattack;
	}

	public void setkSattack(int kSattack) {
		this.kSattack = kSattack;
	}

	public int getkSdefense() {
		return kSdefense;
	}

	public void setkSdefense(int kSdefense) {
		this.kSdefense = kSdefense;
	}

	public int getkSpeed() {
		return kSpeed;
	}

	public void setkSpeed(int kSpeed) {
		this.kSpeed = kSpeed;
	}

	public int getbAttack() {
		return bAttack;
	}

	public void setbAttack(int bAttack) {
		this.bAttack = bAttack;
	}

	public int getbDefense() {
		return bDefense;
	}

	public void setbDefense(int bDefense) {
		this.bDefense = bDefense;
	}

	public int getbSattack() {
		return bSattack;
	}

	public void setbSattack(int bSattack) {
		this.bSattack = bSattack;
	}

	public int getbSdefense() {
		return bSdefense;
	}

	public void setbSdefense(int bSdefense) {
		this.bSdefense = bSdefense;
	}

	public int getbSpeed() {
		return bSpeed;
	}

	public void setbSpeed(int bSpeed) {
		this.bSpeed = bSpeed;
	}

	public int getbAccuracy() {
		return bAccuracy;
	}

	public void setbAccuracy(int bAccuracy) {
		this.bAccuracy = bAccuracy;
	}
	
	
	
	
	
	
	//----------------------------��ü �ɷ�ġ ����Ʈ--------------------------
	public void printAbility() {
		String word = this.getName() + " / ü�� : " + this.getHp() + " / ���� : " + this.getAttack() + " / ���� : " 
	+ this.getDefense() + " / Ư�� : " + this.getSattack()  + " / Ư�� : " + this.getSdefense() 
	+ " / ���ǵ� : " + this.getSpeed();
		System.out.println(word);
	}
	
	
	
	//-------------------��ü ���ϸ� pocketmon.data�� ���� ������ �Է¹ޱ�---------------------
	public static Pocketmon[] constructingPocketmon() {
		Pocketmon[] pocketmons = null;
		int numberOfPocketmon;
		Scanner scan = null;
		try {
			scan = new Scanner(new File("Pocketmon.data"));
			numberOfPocketmon = scan.nextInt();
			pocketmons = new Pocketmon[numberOfPocketmon+1];
			for(int i = 1; i < numberOfPocketmon + 1; i++) {
				String name; int kHp; int kAttack; int kDefense; int kSattack; int kSdefense; int kSpeed;
				int typeNum; Type types[] = new Type[2];
				int _number = scan.nextInt();
				name = scan.next(); kHp = scan.nextInt(); kAttack = scan.nextInt(); kDefense = scan.nextInt();
				kSattack = scan.nextInt(); kSdefense = scan.nextInt(); kSpeed = scan.nextInt();
				typeNum = scan.nextInt(); types[0] = Type.valueOf(scan.next());
				if (typeNum == 2) types[1] = Type.valueOf(scan.next()); else types[1] = Type.NTH;
				System.out.println(types[0].name());
				pocketmons[i] = new Pocketmon(name, kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed, types);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Pocketmon Data File not Found");
			System.exit(0);
		}
		return pocketmons;
	}
}
