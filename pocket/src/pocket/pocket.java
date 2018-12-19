package pocket;

// 전기 0, 격투 1, 불 2, 비행 3, 고스트 4, 풀 5, 땅 6, 얼음 7, 노멀 8, 독 9, 바위 10, 물 11

/* 관련 규칙들
 * 
 * 스텟
 * 스텟 = 레벨 * 스텟상수	※레벨은 1~100, 스텟상수는 0.1~1.6, 스텟은 10~160	※스텟의 계산값이 10 미만일경우 10 고정
 * 스텟에는 스피드, 물공, 특공, 물방, 특방, 체력, 마나가 존재 
 * 버프/디버프 1회당 개체 초기 스텟의 0.5배 증가/감소 최대 4중첩
 * ex)	n회 버프시 스텟 = 초기 스텟 * ( 1 + 0.5 * n), n회 디버프시 스텟 = 초기 스텟 / (1 + n)
 * 총스텟 = 스텟 * 개체값(iv) * 버프 스택  
 * 개체값은 1, 1.02, 1.04, 1.06, 1.08, 1.1 으로 6가지
 * 
 * 명중률과 회피율
 * 선제 정하기 - 스피드 높은 쪽이
 * 스피드별 회피율 = (100 - 기술 명중률 + 스피드스텟/10)% 	※명중률이 100 기술은 피할 수 없음
 * 명중률 디버프/버프 1회당 20% 증가	※명중률은 20% 미만 180% 초과할 수 없다 -> 100% 초과시 초과값 만큼 치명타 확률 상승
 * 
 * 데미지
 * 공격력 = 공격 총스텟 * 스킬 위력
 * 방어력 = 상대 공격력 * 방어 총스텟 / 180
 * 데미지 = (공격력 - 방어력) * 타입효과 * 상태이상 * 치명타	※데미지가 1 미만일 때 1로 고정
 * 치명타는 원래 데미지의 1.3배
 * 
 */

public class pocket {
	
	enum Type{
		ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
	}
	
	enum States{
		BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
	}
	
	//constant number
	protected int number;
	protected double const_speed, const_ad, const_ap, const_adD, const_apD, const_HP;// const_MP;
	protected Type[] type = new Type[2];
	//individual value
	protected String name;
	protected int level;
	protected double iv;
	private States state;
	//buff stack 
	private double accuracy;
	private int b_speed, b_ad, b_ap, b_adD, b_apD, b_accu;//d_speed, d_ad, d_ap, d_adD, d_apD, d_accu
	private int HP, maxHP;//MP, maxMP 
	
	protected pocket(int _number, double _speed, double _ad, double _ap, double _adD, double _apD, double _HP, Type[] _type) {
		this.number = _number; this.const_speed = _speed; this.const_ad = _ad; this.const_adD = _adD; this.const_ap = _ap; 
		this.const_apD = _apD; this.const_HP = _HP; this.type = _type; this.state = State.NTH;//this.const_MP = _MP;
	}
	
	public void setting() {
		this.accuracy = 1;
		this.b_speed = 0; this.b_ad = 0; this.b_adD = 0; this.b_ap = 0; this.b_apD = 0; this.b_accu;
		//this.d_speed = 0; this.d_ad = 0; this.d_adD = 0; this.d_ap = 0; this.d_apD = 0; this.d_accu;
	}
	
	//buff or debuff function
	public boolean buff_speed(int n) {
		if (this.b_speed < -4 || this.b_speed > 4) return false; //do not exist increasing or decreasing
		this.b_speed += n;
		if (this.isUpper(this.b_speed)) this.b_speed = 4;
		else if (this.isLower(this.b_speed)) this.b_speed = -4;
		return true;		//complete buffing or debuffing
	}
	
	public boolean buff_ad(int n) {
		if (this.b_ad < -4 || this.b_ad > 4) return false; //do not exist increasing or decreasing
		this.b_ad += n;
		if (this.isUpper(this.b_ad)) this.b_ad = 4;
		else if (this.isLower(this.b_ad)) this.b_ad = -4;
		return true;		//complete buffing or debuffing
	}
	
	public boolean buff_adD(int n) {
		if (this.b_adD < -4 || this.b_adD > 4) return false; //do not exist increasing or decreasing
		this.b_adD += n;
		if (this.isUpper(this.b_adD)) this.b_adD = 4;
		else if (this.isLower(this.b_adD)) this.b_adD = -4;
		return true;		//complete buffing or debuffing
	}

	public boolean buff_ap(int n) {
		if (this.b_ap < -4 || this.b_ap > 4) return false; //do not exist increasing or decreasing
		this.b_ap += n;
		if (this.isUpper(this.b_ap)) this.b_ap = 4;
		else if (this.isLower(this.b_ap)) this.b_ap = -4;
		return true;		//complete buffing or debuffing
	}

	public boolean buff_apD(int n) {
		if (this.b_apD < -4 || this.b_apD > 4) return false; //do not exist increasing or decreasing
		this.b_apD += n;
		if (this.isUpper(this.b_apD)) this.b_apD = 4;
		else if (this.isLower(this.b_apD)) this.b_apD = -4;
		return true;		//complete buffing or debuffing
	}
	
	public boolean buff_accu(int n) {
		if (this.b_accu < -4 || this.b_accu > 4) return false; //do not exist increasing or decreasing
		this.b_accu += n;
		if (this.isUpper(this.b_accu)) this.b_accu = 4;
		else if (this.isLower(this.b_accu)) this.b_accu = -4;
		return true;		//complete buffing or debuffing
	}
	//development tools
	protected boolean isUpper(int n) {
		if (n > 4) return true;
		return false;
	}
	
	protected boolean isLower(int n) {
		if (n < -4) return true;
		return false;
	}
}


