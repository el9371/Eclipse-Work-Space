package Testing;
import java.io.FileReader;
/*
 * 포켓몬스터 hp 물공 물방 특공 특방 스피드 순
 * 
데미지 = (위력 × 공격 × (레벨 × 2 ÷ 5 + 2 ) ÷ 방어 ÷ 50 × [[급소]] + 2 )
× [[자속 보정]] × 타입상성1 × 타입상성2 × 랜덤수/255

HP 계산 = (종족값 x 2 + 20 + 60) / 2 + 10 + 50
스텟 = (종족값 x 2 + 20 + 60) / 2 + 5

*/



public class Pocketmon {
	private int number, maxHp, hp, attack, defense, sattack, sdefense, speed; //도감번호, 최대체력, 현재체력, 물공, 물방, 특공, 특방, 스피드
	private int pp[] = new int[4]; // 기술 현재 pp
	private String name; //포켓몬 이름
	private Type type[] = new Type[2]; //포켓몬 타입 최대 2 최소 1
	private Skill skill[] = new Skill[4]; // 기술 최대 4
	private State state; //현재 상태
	private int kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed; //종족값
	private int bAttack, bDefense, bSattack, bSdefense, bSpeed, bAccuracy;//버프스택
	
	public Pocketmon(String _name, int _kHp, int _kAttack, int _kDefense, int _kSattack, int _kSdefense, int _kSpeed) {
		this.name = _name; this.kHp = _kHp; this.kAttack = _kAttack; this.kDefense = _kDefense; 
		this.kSattack = _kSattack; this.kSdefense = _kSdefense; this.kSpeed = _kSpeed;
	}
	
	public Pocketmon(int number) {
		Pocketmon tmpPock = Exec.pocketmons[number];
		this.number = tmpPock.getNumber(); this.name = tmpPock.getName();
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
	
	
	
	
	
	
	
	
	
	
	
	
}
