package Testing;

/*
 * 고려해야할 사항
 * 위력, 명중률, 버프 or 공격, 특수 or 물리, 타입, 기술번호, 기술이름
 * 
 */

public class Skill {
	private String name;
	private int number, power,  maxPp, order; //기술번호, 위력, maxPP, 우선도 (0 1 2)
	private int ability, buffStack; // 무엇을 버프, 디버프 시킬것인가 (0 물공, 1 물방, 2 특공, 3 특방, 4 스피드, 5 명중률, 6 아무것도), 얼마만큼 버프
	private double accuracy; // 명중률
	private boolean isBuff, isSpecial, isMe;//버프인지 공격인지, 특공인지 아닌지, 목표가 자신인지 상대인지
	private State crowdControl; //공격시 화상, 마비 등등 상태이상 CC기
	private Type type; //기술 타입
	
	
	
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
