package pocket;

//(스킬 이름),(스킬 타입), (위력, 명중률, PP, maxPP), (물리 or 특수,공격형 or 버프형 )
public class Skill {
	protected String name;
	protected Type type;
	protected int power, accuracy, PP,maxPP;
	protected boolean isAD, isAttack;
}
