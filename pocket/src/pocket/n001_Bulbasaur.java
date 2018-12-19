package pocket;

public class n001_Bulbasaur extends pocket {
	int _number = 1;
	double _speed = 0.7, _ad = 0.7, _ap = 0.7, _adD = 0.7, _apD = 0.7, _HP = 0.7;
	//int _number, double _speed, double _ad, double _ap, double _adD, double _apD, double _HP, Type[] _type
	Type[] _type = {Type.GRASS, Type.POISON};
	
	public n001_Bulbasaur(String _name, int _level, int _iv) {
		this.name = _name; this.level = _level; this.iv = _iv;
		this.pocket(_number, _speed, _ad, _ap, _adD, _apD , _HP, _type );
	}
	
	public n001_Bulbasaur( int _level, int _iv) {
		this.name = "pencil"; this.level = _level; this.iv = _iv;
		this.pocket(_number, _speed, _ad, _ap, _adD, _apD , _HP, _type);
	}
	
}
