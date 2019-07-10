package Testing;

/*
 * 고려해야할 사항
 * 위력, 명중률, 버프 or 공격, 특수 or 물리, 타입, 기술번호, 기술이름
 * 
 */

public class Skill {
	private String name;
	private int number, power, pp, maxPp, order;
	private double accuracy;
	private boolean isBuff, isSpecial;
	private Type type;
	//우선도 0인 공격스킬
	public Skill(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isBuff, boolean _isSpecial, Type _type)
	{
		name = _name; number = _number; power = _power; accuracy = _accuracy;
		pp = _pp; maxPp = _maxPp; isSpecial = _isSpecial; type = _type;
		isBuff = _isBuff; order = 0;
	}
	//우선도 0이 아닌 공격스킬
	public Skill(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, int _order, boolean _isBuff, boolean _isSpecial, Type _type)
		{
			name = _name; number = _number; power = _power; accuracy = _accuracy;
			pp = _pp; maxPp = _maxPp; order = _order; isSpecial = _isSpecial; type = _type;
			isBuff = _isBuff;
		}
	//버프스킬
	public Skill(String _name, int _number, boolean _isBuff, int _power, double _accuracy, int _pp, int _maxPp, Type _type)
		{
			name = _name; number = _number; power = _power; accuracy = _accuracy;
			pp = _pp; maxPp = _maxPp; isBuff = _isBuff; type = _type;
			isSpecial = false; order = 0;
		}
	
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	public int getPower() {
		return power;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public boolean isBuff() {
		return isBuff;
	}
	public boolean isSpecial() {
		return isSpecial;
	}
	public Type getType() {
		return type;
	}
	
	
}
