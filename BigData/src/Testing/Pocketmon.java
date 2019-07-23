package Testing;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
 * 포켓몬스터 hp 물공 물방 특공 특방 스피드 순
 * 
데미지 = (위력 × 공격 × (레벨 × 2 ÷ 5 + 2 ) ÷ 방어 ÷ 50 × [[급소]] + 2 )
× [[자속 보정]] × 타입상성1 × 타입상성2 × 랜덤수(217~255) / 255

HP 계산 = (종족값 x 2) + 60
스텟 = 종족값 + 5

*/

public class Pocketmon {
	
	private static Pocketmon[] pocks = Pocketmon.constructingPocketmon();
	
	private int number, maxHp, hp, attack, defense, sattack, sdefense, speed; //도감번호, 최대체력, 현재체력, 물공, 물방, 특공, 특방, 스피드
	private int pp[] = new int[4]; // 기술 현재 pp
	private String name; //포켓몬 이름
	private Type type[] = new Type[2]; //포켓몬 타입 최대 2 최소 1
	private Skill skill[] = new Skill[4]; // 기술 최대 4
	private State state; //현재 상태
	private int kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed; //종족값
	private int bAttack, bDefense, bSattack, bSdefense, bSpeed, bAccuracy, bEvasion;//버프스택
	private boolean isReaction; //현재 반동으로 못움직이는 상태인가
	private int turnsInCC; //CC기에 걸린지 몇턴이 지났는가
	
	///-------------------------------------포켓몬 종족 생성--------------------------------------------
	public Pocketmon(String _name, int _kHp, int _kAttack, int _kDefense, int _kSattack, int _kSdefense, int _kSpeed, Type[] _type) {
		this.name = _name; this.kHp = _kHp; this.kAttack = _kAttack; this.kDefense = _kDefense; 
		this.kSattack = _kSattack; this.kSdefense = _kSdefense; this.kSpeed = _kSpeed; this.type = _type;
	}
	
	///-------------------------------------특정 포켓몬 초기 생성--------------------------------------------
	public Pocketmon(int number) {
		Pocketmon pock = pocks[number];
		this.number = pock.getNumber(); this.name = pock.getName();
		this.maxHp = pock.getkHp() * 2 + 60;
		this.attack = pock.getkAttack() + 5;
		this.defense = pock.getkDefense()+5;
		this.sattack = pock.getkSattack()+5;
		this.sdefense = pock.getkSdefense()+5;
		this.speed = pock.getkSpeed()+5;
		this.type = pock.getType();
		this.state = State.NTH; this.bAttack = 0; this.bDefense = 0; this.bSattack = 0; this.bSdefense = 0;
		this.bSpeed = 0; this.bAccuracy = 0;	
		this.hp = this.maxHp;
		this.isReaction = false;
		this.turnsInCC = 0;
	}
	
	public void setSkill(int i, Skill _skill) {
		skill[i] = _skill;
		pp[i] = _skill.getMaxPP();
	}
	
	public int useSkill(int _skill, Pocketmon you) {
		//2는 어떠한 이유로 실패, 1은 성공, 0은 오류
		if (this.isReaction) {this.isReaction = false; return 2;}
		double damage;
		Skill selected = skill[_skill];
		System.out.println(this.getName() + "은 " + you.getName() + "에게 " + selected.getName() + "을 시전했다.");
		pp[_skill] = pp[_skill] - 1;
		
		if (selected.isBuff() && Math.random() * 100 < selected.getAccuracy() * this.getAccuracy() / you.getEvasion()) {
			if (selected.isMe())
				this.useBuff(selected, this);
			else
				this.useBuff(selected, you);
		} 
		
		else if(Math.random() * 100 < selected.getAccuracy() * this.getAccuracy() / you.getEvasion()) 
			useAttack(selected, you);
		else
			System.out.println(this.getName() + "의 " + selected.getName() + "는 빗나갔다.");
		return 1;
	}
	
	public int useBuff(Skill selected, Pocketmon you) {
		int ability = selected.getAbility();
		int buffStack = selected.getBuffStack();
		String message = null;
		if (buffStack > 1) message = "크게 상승했다.";
		else if (buffStack == 1) message = "상승했다.";
		else if (buffStack == -1) message = "하락했다.";
		else if (buffStack < -1) message = "크게 하락했다.";
		if (ability == 0) {
			int _ability = you.getbAttack() + buffStack;
			if (_ability > 6) _ability = 6;
			else if (_ability < -6) _ability = -6;
			you.setbAttack(_ability);
			message = "물리 공격이 " + message;
		}
		else if (ability == 1) {
			int _ability = you.getbDefense() + buffStack;
			if (_ability > 6) _ability = 6;
			else if (_ability < -6) _ability = -6;
			you.setbDefense(_ability);
			message = "물리 방어가 " + message;
		}
		if (ability == 2) {
			int _ability = you.getbSattack() + buffStack;
			if (_ability > 6) _ability = 6;
			else if (_ability < -6) _ability = -6;
			you.setbSattack(_ability);
			message = "특수 공격이 " + message;
		}
		if (ability == 3) {
			int _ability = you.getbSdefense() + buffStack;
			if (_ability > 6) _ability = 6;
			else if (_ability < -6) _ability = -6;
			you.setbSdefense(_ability);
			message = "특수 방어가 " + message;
		}
		if (ability == 4) {
			int _ability = you.getbSpeed() + buffStack;
			if (_ability > 6) _ability = 6;
			else if (_ability < -6) _ability = -6;
			you.setbSpeed(_ability);
			message = "스피드가 " + message;
		}
		System.out.println(you.getName() + "의 " + message);
		return 1;
	}
	
	
	public int useAttack(Skill selected, Pocketmon you) {
		
		double damage, balance = 0;
		if (selected.isSpecial()) {
			if (Math.random() < 0.125) // 급소 확률
				damage = selected.getPower() * sattack * (50 * 2 / 5 + 2) / you.getSdefense() / 50 * 1.5 + 2;
			else
				damage = selected.getPower() * sattack * (50 * 2 / 5 + 2) / you.getSdefense() / 50 + 2;
		} else {
			if (Math.random() < 0.125) // 급소 확률
				damage = selected.getPower() * attack * (50 * 2 / 5 + 2) / you.getDefense() / 50 * 1.5 + 2;
			else
				damage = selected.getPower() * attack * (50 * 2 / 5 + 2) / you.getDefense() / 50 + 2;
		}
		if (selected.getType() == type[0] || selected.getType() == type[1])
			damage = damage * 1.5;
		
		//상성 계산 적용
		if (Type.isGreat(selected.getType(), you.getType()[0])) balance = balance + 1;
		if (Type.isGreat(selected.getType(), you.getType()[1])) balance = balance + 1;
		if (Type.isWorse(selected.getType(), you.getType()[0])) balance = balance - 1;
		if (Type.isWorse(selected.getType(), you.getType()[1])) balance = balance - 1;
		if (Type.isEffect(selected.getType(), you.getType()[0])) balance = 5;
		if (Type.isEffect(selected.getType(), you.getType()[1])) balance = 5;
		if (balance > 0 && balance < 3) {
			System.out.println("효과가 굉장했다.");
			damage = damage * 2 * balance ;
		}
		else if (balance < 0) {
			System.out.println("효과가 별로인듯 하다.");
			damage = damage / (-2 * balance);
		}
		else if (balance > 4) {
			System.out.println("효과가 없다");
			damage = 0;
		}
		
		damage = damage * (Math.random() * 38 + 217) / 255; // 데미지 난수 
		you.getDamaged((int) damage);
		System.out.println(damage);
		// 공격 cc기 작동
		if (Math.random() * 100 < selected.getProbability()) {
			you.setState(selected.getCC());
			System.out.println(you.getName() + "은 " + selected.getCC().getName() + "에 걸렸다.");
		}
		// 공격 후 추가 행동
		if (selected.isAfter() && Math.random() * 100 < selected.getAfterAccracy()) {
			Skill afterBuff = new Skill(selected.getAfterAbility(), selected.getAfterbuffStack());
			if(selected.isAfterIsMe())
				this.useBuff(afterBuff, this);
			else
				this.useBuff(afterBuff, you);
		}
		
		return 1;
	}
	
	
	public int getDamaged(int _damage) {
		hp = hp - _damage;
		if (hp < 0) hp = 0;
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
	
	
	
	
	
	
	//----------------------------개체 능력치 프린트--------------------------
	public void printAbility() {
		String word = this.getName() + " / 체력 : " + this.getHp() + " / 물공 : " + this.getAttack() + " / 물방 : " 
	+ this.getDefense() + " / 특공 : " + this.getSattack()  + " / 특방 : " + this.getSdefense() 
	+ " / 스피드 : " + this.getSpeed() + " / 상태 : " + this.getState().getName();
		System.out.println(word);
	}
	
	
	
	//-------------------전체 포켓몬 pocketmon.data로 부터 종족값 입력받기---------------------
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
				//System.out.println(kSpeed);
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
