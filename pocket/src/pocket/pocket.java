package pocket;

// 타입 			전기 0, 격투 1, 불 2, 비행 3, 고스트 4, 풀 5, 땅 6, 얼음 7, 노멀 8, 독 9, 바위 10, 물 11, 없음 12
// 상태이상 	화상 0, 마비 1, 중독 2, 얼음 3, 잠 4, 반동 5, 없음 6
/* 관련 규칙들
 * 
 * 스텟
 * 버프/디버프 1회당 개체 초기 스텟의 0.5배 증가/감소 최대 4중첩
 * ex)	n회 버프시 스텟 = 초기 스텟 * ( 1 + 0.5 * n), n회 디버프시 스텟 = 초기 스텟 / (1 + n)
 * 총스텟 = 스텟 * 개체값(iv) * 버프 스택  
 * 개체값은 1, 1.02, 1.04, 1.06, 1.08, 1.1 으로 6가지
 * 
 * 명중률과 회피율
 * 선제 정하기 - 스피드 높은 쪽이
 * 스피드별 회피율 = (100 - 기술 명중률 + 스피드/100)% 	※명중률이 100 기술은 피할 수 없음
 * 명중률 디버프/버프 1회당 20% 증가	※명중률은 20% 미만 180% 초과할 수 없다 -> 100% 초과시 초과값 만큼 치명타 확률 상승
 * 
 * 체력
 * maxHP = (종족값 * 2 * 레벨 /100 + 10 + 레벨) * 개체값
 * 
 * 데미지 [2세대 기준]
 * 데미지 = (위력 * 공격 * (레벨 * 2 / 5 + 2) / 방어 / 50 * [치명타 (1.3배)] + 2) * [자속보정 (1.3배)] * [타입 상성 1, 2 (각 1.5배)] * (0.85 ~ 1 랜덤수)
 * 대괄호[] 식은 상황에 따라 적용 
 */

public class pocket {
	
	enum Type{
		ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
	}
	
	enum States{
		BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
	}
	
	/////////////////////////////////////////////constant number/////////////////////////////////////
	protected int number;
	protected int const_speed, const_ad, const_ap, const_adD, const_apD, const_HP;// const_MP;
	protected Type[] type = new Type[2];
	//////////////////////////////////////////////individual value////////////////////////////////
	protected String name;
	protected int level;
	protected double iv;
	private States state;
	////////////////////////////////////////////////////buff stack //////////////////////////////
	private double accuracy;
	private int b_speed, b_ad, b_ap, b_adD, b_apD, b_accu;//d_speed, d_ad, d_ap, d_adD, d_apD, d_accu
	private int HP, maxHP;//MP, maxMP 
	//////////////////////////////////////////////////First Setting//////////////////////
	// never changed excluding special thing
	protected pocket(int _number, int _speed, int _ad, int _ap, int _adD, int _apD, int _HP, Type[] _type) {
		this.number = _number; this.const_speed = _speed; this.const_ad = _ad; this.const_adD = _adD; this.const_ap = _ap; 
		this.const_apD = _apD; this.const_HP = _HP; this.type = _type; this.state = State.NTH;//this.const_MP = _MP;
		this.maxHP = (double)(this.const_HP * 2 * this.level / 100 + 10 + this.level) * this.iv; this.HP = this.maxHP;
	}
	//////////////////////////////////////////Setting for battle/////////////////////////////////
	// reset on spawning
	public void setting() {
		this.accuracy = 1;
		this.b_speed = 0; this.b_ad = 0; this.b_adD = 0; this.b_ap = 0; this.b_apD = 0; this.b_accu = 0;
		//this.d_speed = 0; this.d_ad = 0; this.d_adD = 0; this.d_ap = 0; this.d_apD = 0; this.d_accu;
	}
	
	////////////////////////////////////////////////accessor////////////////////////////////////
	public double getSpeed() {
		double speed = this.isMin(this.level * this.iv * this.const_speed );
		speed = this.isMax(speed);
		return speed * this.buff_stack(this.b_speed);
	}
	
	public double getAd() {
		double ad = this.isMin(this.level * this.iv * this.const_ad );
		ad = this.isMax(ad);
		return ad * this.buff_stack(this.b_ad);
	}
	
	public double getAdD() {
		double adD = this.isMin(this.level * this.iv * this.const_adD );
		adD = this.isMax(adD);
		return adD * this.buff_stack(this.b_adD);
	}
	
	public double getAp() {
		double ap = this.isMin(this.level * this.iv * this.const_ap );
		ap = this.isMax(ap);
		return ap * this.buff_stack(this.b_ap);
	}
	
	public double getApD() {
		double apD = this.isMin(this.level * this.iv * this.const_apD);
		apD = this.isMax(apD);
		return apD * this.buff_stack(this.b_apD);
	}
	
	public double getAccuracy( ) {
		this.accuracy = 1 + this.b_accu * 0.2;
		return this.accuracy;
	}
	/////////////////////////////////////////buff or debuff function/////////////////////////////////////////
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
	///////////////////////////////////////development tools/////////////////////////////////////////
	private boolean isUpper(int n) {
		if (n > 4) return true;
		return false;
	}
	
	private boolean isLower(int n) {
		if (n < -4) return true;
		return false;
	}
	
	private double isMin(double n) {
		if (n < 10) return 10;
		return n;
	}
	
	private double isMax(double n) {
		if (n > 160) return 160;
		return n;
	}
	
	private double buff_stack(int n) {
		if (n < 0) return 1 / (1+n);
		return (1 + 0.5 * n);
	}
}


