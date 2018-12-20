package pocket;

public class n001_Bulbasaur extends pocket {
	private int _number = 1;
	private int _speed = 45, _ad = 49, _ap = 65, _adD = 49, _apD = 65, _HP = 45;
	//int _number, double _speed, double _ad, double _ap, double _adD, double _apD, double _HP, Type[] _type
	private Type[] _type = {Type.GRASS, Type.POISON};
	
	public n001_Bulbasaur(String _name, int _level, int _iv) {
		this.name = _name; this.level = _level; this.iv = _iv;
		this.pocket(_number, _speed, _ad, _ap, _adD, _apD , _HP, _type );
	}
	
	public n001_Bulbasaur( int _level, int _iv) {
		this.name = "Bulbasaur"; this.level = _level; this.iv = _iv;
		this.pocket(_number, _speed, _ad, _ap, _adD, _apD , _HP, _type);
	}
	
}
