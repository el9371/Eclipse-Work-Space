package pocket;

// ���� 0, ���� 1, �� 2, ���� 3, ��Ʈ 4, Ǯ 5, �� 6, ���� 7, ��� 8, �� 9, ���� 10, �� 11

/* ���� ��Ģ��
 * 
 * ����
 * ���� = ���� * ���ݻ��	�ط����� 1~100, ���ݻ���� 0.1~1.6, ������ 10~160	�ؽ����� ��갪�� 10 �̸��ϰ�� 10 ����
 * ���ݿ��� ���ǵ�, ����, Ư��, ����, Ư��, ü��, ������ ���� 
 * ����/����� 1ȸ�� ��ü �ʱ� ������ 0.5�� ����/���� �ִ� 4��ø
 * ex)	nȸ ������ ���� = �ʱ� ���� * ( 1 + 0.5 * n), nȸ ������� ���� = �ʱ� ���� / (1 + n)
 * �ѽ��� = ���� * ��ü��(iv) * ���� ����  
 * ��ü���� 1, 1.02, 1.04, 1.06, 1.08, 1.1 ���� 6����
 * 
 * ���߷��� ȸ����
 * ���� ���ϱ� - ���ǵ� ���� ����
 * ���ǵ庰 ȸ���� = (100 - ��� ���߷� + ���ǵ彺��/10)% 	�ظ��߷��� 100 ����� ���� �� ����
 * ���߷� �����/���� 1ȸ�� 20% ����	�ظ��߷��� 20% �̸� 180% �ʰ��� �� ���� -> 100% �ʰ��� �ʰ��� ��ŭ ġ��Ÿ Ȯ�� ���
 * 
 * ������
 * ���ݷ� = ���� �ѽ��� * ��ų ����
 * ���� = ��� ���ݷ� * ��� �ѽ��� / 180
 * ������ = (���ݷ� - ����) * Ÿ��ȿ�� * �����̻� * ġ��Ÿ	�ص������� 1 �̸��� �� 1�� ����
 * ġ��Ÿ�� ���� �������� 1.3��
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


