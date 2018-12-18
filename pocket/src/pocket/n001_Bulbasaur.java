package pocket;

public class n001_Bulbasaur extends pocket {
	int _number = 1;
	double _speed = 0.7, _ad = 0.7, _ap = 0.7, _adD = 0.7, _apD = 0.7, _HP = 0.7, _MP = 0.7;
	Type[] _type = {Type.GRASS, Type.POISON};
	
	public n001_Bulbasaur(String _name, int _level, int _iv) {
		this.name = _name; this.level = _level; this.iv = _iv;
		this.settingStat(_number, _speed, _ad, _ap, _adD, _apD , _HP , _MP, _type );
	}
	
	public n001_Bulbasaur( int _level, int _iv) {
		this.name = "pencil"; this.level = _level; this.iv = _iv;
		this.settingStat(_number, _speed, _ad, _ap, _adD, _apD , _HP , _MP, _type);
	}
	
	public n001_Bulbasaur() {
		this.name = "pencil"; this.level = 1; this.iv = 1;
		this.settingStat(_number, _speed, _ad, _ap, _adD, _apD , _HP , _MP, _type);
	}
	
}
