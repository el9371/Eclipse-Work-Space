package Testing;

/*
 * ����ؾ��� ����
 * ����, ���߷�, ���� or ����, Ư�� or ����, Ÿ��, �����ȣ, ����̸�
 * 
 */

public class Skill {
	private String name;
	private int number, power,  maxPp, order; //�����ȣ, ����, maxPP, �켱�� (0 1 2)
	private int ability, buffStack; // ������ ����, ����� ��ų���ΰ� (0 ����, 1 ����, 2 Ư��, 3 Ư��, 4 ���ǵ�, 5 ���߷�, 6 �ƹ��͵�), �󸶸�ŭ ����
	private double accuracy; // ���߷�
	private boolean isBuff, isSpecial, isMe;//�������� ��������, Ư������ �ƴ���, ��ǥ�� �ڽ����� �������
	private State crowdControl; //���ݽ� ȭ��, ���� ��� �����̻� CC��
	private Type type; //��� Ÿ��
	
	
	
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
	public int getMaxPp() {
		return maxPp;
	}
	public void setMaxPp(int maxPp) {
		this.maxPp = maxPp;
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
	
	
	
}
