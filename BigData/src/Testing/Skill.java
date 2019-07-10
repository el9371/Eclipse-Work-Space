package Testing;

/*
 * ����ؾ��� ����
 * ����, ���߷�, ���� or ����, Ư�� or ����, Ÿ��, �����ȣ, ����̸�
 * 
 */

public class Skill {
	private String name;
	private int number, power, pp, maxPp, order;
	private double accuracy;
	private boolean isBuff, isSpecial;
	private Type type;
	//�켱�� 0�� ���ݽ�ų
	public Skill(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isBuff, boolean _isSpecial, Type _type)
	{
		name = _name; number = _number; power = _power; accuracy = _accuracy;
		pp = _pp; maxPp = _maxPp; isSpecial = _isSpecial; type = _type;
		isBuff = _isBuff; order = 0;
	}
	//�켱�� 0�� �ƴ� ���ݽ�ų
	public Skill(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, int _order, boolean _isBuff, boolean _isSpecial, Type _type)
		{
			name = _name; number = _number; power = _power; accuracy = _accuracy;
			pp = _pp; maxPp = _maxPp; order = _order; isSpecial = _isSpecial; type = _type;
			isBuff = _isBuff;
		}
	//������ų
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
