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
�� [[�ڼ� ����]] �� Ÿ�Ի�1 �� Ÿ�Ի�2 �� ������(217~255) / 255

HP ��� = (������ x 2) + 60
���� = ������ + 5

*/

public class Pokemon {

	private static Pokemon[] pokes = Pokemon.constructingPokemon();

	private int number, maxHp, hp, attack, defense, sattack, sdefense, speed; // ������ȣ, �ִ�ü��, ����ü��, ����, ����, Ư��, Ư��, ���ǵ�
	private int pp[] = new int[4]; // ��� ���� pp
	private String name; // ���ϸ� �̸�
	private Type type[] = new Type[2]; // ���ϸ� Ÿ�� �ִ� 2 �ּ� 1
	private Skill skill[] = new Skill[4]; // ��� �ִ� 4
	private State state; // ���� ����
	private int kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed; // ������
	private int bAttack, bDefense, bSattack, bSdefense, bSpeed, bAccuracy, bEvasion;// ��������
	private boolean isReaction; // ���� �ݵ����� �������̴� �����ΰ�
	private int turnsInCC; // CC�⿡ �ɸ��� ������ �����°�

	/// -------------------------------------���ϸ� ����
	/// ����--------------------------------------------
	public Pokemon(int _number, String _name, int _kHp, int _kAttack, int _kDefense, int _kSattack, int _kSdefense,
			int _kSpeed, Type[] _type) {
		this.number = _number;
		this.name = _name;
		this.kHp = _kHp;
		this.kAttack = _kAttack;
		this.kDefense = _kDefense;
		this.kSattack = _kSattack;
		this.kSdefense = _kSdefense;
		this.kSpeed = _kSpeed;
		this.type = _type;
	}

	/// -------------------------------------Ư�� ���ϸ� �ʱ�
	/// ����--------------------------------------------
	public Pokemon(int number) {
		Pokemon poke = pokes[number];
		this.number = poke.getNumber();
		this.name = poke.getName();
		this.maxHp = poke.getkHp() * 2 + 60;
		this.attack = poke.getkAttack() + 5;
		this.defense = poke.getkDefense() + 5;
		this.sattack = poke.getkSattack() + 5;
		this.sdefense = poke.getkSdefense() + 5;
		this.speed = poke.getkSpeed() + 5;
		this.type = poke.getType();
		this.state = State.NTH;
		this.bAttack = 0;
		this.bDefense = 0;
		this.bSattack = 0;
		this.bSdefense = 0;
		this.bSpeed = 0;
		this.bAccuracy = 0;
		this.hp = this.maxHp;
		this.isReaction = false;
		this.turnsInCC = 0;
	}

	public void setSkill(int i, Skill _skill) {
		skill[i] = _skill;
		pp[i] = _skill.getMaxPP();
	}

	public int useSkill(int _index, Pokemon you) {
		//2�� ��� ������ ����, 1�� ����, 0�� ����
		if (this.isReaction) {this.isReaction = false; return 2;}
		double damage;
		Skill selected = skill[_index];
		Exec.screen.sendMessage(this.getName() + "�� " + you.getName() + "���� " + selected.getName() + "�� �����ߴ�.");
		pp[_index] = pp[_index] - 1;
		
		if (selected.isBuff() && Math.random() * 100 < selected.getAccuracy() * this.getAccuracy() / you.getEvasion()) {
			if (selected.isMe())
				this.useBuff(selected, this);
			else
				this.useBuff(selected, you);
		} 
		
		else if(Math.random() * 100 < selected.getAccuracy() * this.getAccuracy() / you.getEvasion()) 
			useAttack(selected, you);
		else
			Exec.screen.sendMessage(this.getName() + "�� " + selected.getName() + "�� ��������.");
		return 1;
	}

	public int useBuff(Skill selected, Pokemon you) {
		int ability = selected.getAbility();
		int buffStack = selected.getBuffStack();
		String message = null;
		if (buffStack > 1)
			message = "ũ�� ����ߴ�.";
		else if (buffStack == 1)
			message = "����ߴ�.";
		else if (buffStack == -1)
			message = "�϶��ߴ�.";
		else if (buffStack < -1)
			message = "ũ�� �϶��ߴ�.";
		if (ability == 0) {
			int _ability = you.getbAttack() + buffStack;
			if (_ability > 6)
				_ability = 6;
			else if (_ability < -6)
				_ability = -6;
			you.setbAttack(_ability);
			message = "���� ������ " + message;
		} else if (ability == 1) {
			int _ability = you.getbDefense() + buffStack;
			if (_ability > 6)
				_ability = 6;
			else if (_ability < -6)
				_ability = -6;
			you.setbDefense(_ability);
			message = "���� �� " + message;
		}
		if (ability == 2) {
			int _ability = you.getbSattack() + buffStack;
			if (_ability > 6)
				_ability = 6;
			else if (_ability < -6)
				_ability = -6;
			you.setbSattack(_ability);
			message = "Ư�� ������ " + message;
		}
		if (ability == 3) {
			int _ability = you.getbSdefense() + buffStack;
			if (_ability > 6)
				_ability = 6;
			else if (_ability < -6)
				_ability = -6;
			you.setbSdefense(_ability);
			message = "Ư�� �� " + message;
		}
		if (ability == 4) {
			int _ability = you.getbSpeed() + buffStack;
			if (_ability > 6)
				_ability = 6;
			else if (_ability < -6)
				_ability = -6;
			you.setbSpeed(_ability);
			message = "���ǵ尡 " + message;
		}
		Exec.screen.sendMessage(you.getName() + "�� " + message);
		return 1;
	}

	public int useAttack(Skill selected, Pokemon you) {

		double damage, balance = 0;
		if (selected.isSpecial()) {
			if (Math.random() < 0.125) // �޼� Ȯ��
				damage = selected.getPower() * sattack * (50 * 2 / 5 + 2) / you.getSdefense() / 50 * 1.5 + 2;
			else
				damage = selected.getPower() * sattack * (50 * 2 / 5 + 2) / you.getSdefense() / 50 + 2;
		} else {
			if (Math.random() < 0.125) // �޼� Ȯ��
				damage = selected.getPower() * attack * (50 * 2 / 5 + 2) / you.getDefense() / 50 * 1.5 + 2;
			else
				damage = selected.getPower() * attack * (50 * 2 / 5 + 2) / you.getDefense() / 50 + 2;
		}
		if (selected.getType() == type[0] || selected.getType() == type[1])
			damage = damage * 1.5;

		// �� ��� ����
		balance = Type.getBalance(selected.getType().ordinal(), you.getType()[0].ordinal())
				+ Type.getBalance(selected.getType().ordinal(), you.getType()[1].ordinal());

		if (balance > 3) {
			Exec.screen.sendMessage("ȿ���� ����");
			damage = 0;
		} else if (balance > 0) {
			Exec.screen.sendMessage("ȿ���� �����ߴ�.");
			damage = damage * 2 * balance;
		} else if (balance < 0) {
			Exec.screen.sendMessage("ȿ���� �����ε� �ϴ�.");
			damage = damage / (-2 * balance);
		}

		damage = damage * (Math.random() * 38 + 217) / 255; // ������ ����
		you.getDamaged((int) damage);
		System.out.println(damage);
		// ���� cc�� �۵�
		if (Math.random() * 100 < selected.getProbability()) {
			you.setState(selected.getCC());
			Exec.screen.sendMessage(you.getName() + "�� " + selected.getCC().getName() + "�� �ɷȴ�.");
		}
		// ���� �� �߰� �ൿ
		if (selected.isAfter() && Math.random() * 100 < selected.getAfterAccracy()) {
			Skill afterBuff = new Skill(selected.getAfterAbility(), selected.getAfterbuffStack());
			if (selected.isAfterIsMe())
				this.useBuff(afterBuff, this);
			else
				this.useBuff(afterBuff, you);
		}

		return 1;
	}

	public int getDamaged(int _damage) {
		hp = hp - _damage;
		if (hp < 0)
			hp = 0;
		return 1;
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
		if (bAttack > 0)
			return attack * (2 + bAttack) / 2;
		return attack * 2 / (2 - bAttack);
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		if (bDefense > 0)
			return defense * (2 + bDefense) / 2;
		return defense * 2 / (2 - bDefense);
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSattack() {
		if (bSattack > 0)
			return sattack * (2 + bSattack) / 2;
		return sattack * 2 / (2 - bSattack);
	}

	public void setSattack(int sattack) {
		this.sattack = sattack;
	}

	public int getSdefense() {
		if (bSdefense > 0)
			return sdefense * (2 + bSdefense) / 2;
		return sdefense * 2 / (2 - bSdefense);
	}

	public void setSdefense(int sdefense) {
		this.sdefense = sdefense;
	}

	public int getSpeed() {
		if (bSpeed > 0)
			return speed * (2 + bSpeed) / 2;
		return speed * 2 / (2 - bSpeed);
	}

	public int getAccuracy() {
		if (bAccuracy > 0)
			return 100 * (3 + bAccuracy) / 3;
		return 100 * 3 / (2 - bAccuracy);
	}

	public int getEvasion() {
		if (bEvasion > 0)
			return 100 * (3 + bEvasion) / 3;
		return 100 * 3 / (2 - bEvasion);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int[] getPP() {
		return pp;
	}

	public void setPP(int[] pp) {
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
		this.pp[0] = skill[0].getMaxPP();
		this.pp[1] = skill[1].getMaxPP();
		this.pp[2] = skill[2].getMaxPP();
		this.pp[3] = skill[3].getMaxPP();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		this.turnsInCC = 0;
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

	// ----------------------------��ü �ɷ�ġ ����Ʈ--------------------------
	public void printAbility() {
		String word = this.getName() + " / ü�� : " + this.getHp() + " / ���� : " + this.getAttack() + " / ���� : "
				+ this.getDefense() + " / Ư�� : " + this.getSattack() + " / Ư�� : " + this.getSdefense() + " / ���ǵ� : "
				+ this.getSpeed() + " / ���� : " + this.getState().getName();
		System.out.println(word);
	}

	// -------------------��ü ���ϸ� pokemon.data�� ���� ������ �Է¹ޱ�---------------------
	public static Pokemon[] constructingPokemon() {
		Pokemon[] pokemons = null;
		int numberOfPokemon;
		Scanner scan = null;
		try {
			scan = new Scanner(new File("Pokemon.data"));
			numberOfPokemon = scan.nextInt();
			pokemons = new Pokemon[numberOfPokemon + 1];
			for (int i = 1; i < numberOfPokemon + 1; i++) {
				String name;
				int kHp;
				int kAttack;
				int kDefense;
				int kSattack;
				int kSdefense;
				int kSpeed;
				int typeNum;
				Type types[] = new Type[2];
				int _number = scan.nextInt();
				name = scan.next();
				kHp = scan.nextInt();
				kAttack = scan.nextInt();
				kDefense = scan.nextInt();
				kSattack = scan.nextInt();
				kSdefense = scan.nextInt();
				kSpeed = scan.nextInt();
				typeNum = scan.nextInt();
				types[0] = Type.valueOf(scan.next());
				if (typeNum == 2)
					types[1] = Type.valueOf(scan.next());
				else
					types[1] = Type.NTH;
				// System.out.println(kSpeed);
				pokemons[i] = new Pokemon(i, name, kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed, types);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Pokemon Data File not Found");
			System.exit(0);
		}
		return pokemons;
	}
}
